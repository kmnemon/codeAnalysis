package codeanalysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.BasicTypeService;
import util.TextService;

import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static util.BasicTypeService.combineList;
import static util.FilePathService.typesPathToFullTypesName;

public class ProjectDependency{
    private static final Logger LOG = LoggerFactory.getLogger(ProjectDependency.class);

    private static Map<String, AtomicInteger> fanIn = new HashMap<>();

    private ProjectDependency(){}

    private static void calcModulesAbstractness(List<String> publicTypesFull, Map<ModuleInfo, List<TypeInfo>> modulesInProject){
        calcProjectFanIn(publicTypesFull, modulesInProject);
//        modulesInProject.values()
    }

    private static void calcProjectFanIn(List<String> publicTypesFull, Map<ModuleInfo, List<TypeInfo>> modulesInProject){
        publicTypesFull.forEach(str-> fanIn.putIfAbsent(str, new AtomicInteger()));

        modulesInProject.forEach(ProjectDependency::calcModuleFanInOtherModule);
    }

    private static void calcModuleFanInOtherModule(ModuleInfo moduleInfo, List<TypeInfo> typeInModule){
        List<String> typesFullInOtherModule = moduleInfo.getTypesFullInOtherModule(typeInModule);
        typeInModule.stream()
                .map(TypeAnalysis::subTypeSplit)  //Map<String, List<String>>
                .forEach(subTypes-> calcTypeFanInOtherModule(subTypes, typesFullInOtherModule));
    }

    private static void calcTypeFanInOtherModule(Map<String, List<String>> subTypes, List<String> typesFullInOtherModule){
        subTypes.values().stream()
                .map(TextService::linesToWords)
                .forEach(subTypeWords-> calcSubTypeFanInOtherModule(subTypeWords, typesFullInOtherModule));
    }

    private static void calcSubTypeFanInOtherModule(List<String> subTypeWords, List<String> typesFullInOtherModule){
        typesFullInOtherModule.stream()
                .filter(typeStr-> TextService.containWords(typeStr, subTypeWords))
                .forEach(typeStr-> fanIn.get(typeStr).incrementAndGet());
    }
}
