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

public class ProjectDependency extends ProjectInfo{
    private static final Logger LOG = LoggerFactory.getLogger(ProjectDependency.class);

    private Map<String, AtomicInteger> fanIn;


    ProjectDependency(BasicInfo basicInfo){
        super(basicInfo);
        calcProjectFanIn();
    }


    private void calcProjectFanIn(){
        fanIn = new HashMap<>();
        basicInfo.getPublicTypesFull().forEach(str-> fanIn.putIfAbsent(str, new AtomicInteger()));

        modulesInProject.forEach(this::calcModuleFanInOtherModule);
    }

    private void calcModuleFanInOtherModule(ModuleInfo moduleInfo, List<TypeInfo> typeInModule){
        List<String> typesFullInOtherModule = moduleInfo.getTypesFullInOtherModule(typeInModule);
        typeInModule.stream()
                .map(TypeAnalysis::subTypeSplit)  //Map<String, List<String>>
                .forEach(subTypes-> calcTypeFanInOtherModule(subTypes, typesFullInOtherModule));
    }

    private void calcTypeFanInOtherModule(Map<String, List<String>> subTypes, List<String> typesFullInOtherModule){
        subTypes.values().stream()
                .map(TextService::linesToWords)
                .forEach(subTypeWords-> calcSubTypeFanInOtherModule(subTypeWords, typesFullInOtherModule));
    }

    private void calcSubTypeFanInOtherModule(List<String> subTypeWords, List<String> typesFullInOtherModule){
        typesFullInOtherModule.stream()
                .filter(typeStr-> TextService.containWords(typeStr, subTypeWords))
                .forEach(typeStr-> fanIn.get(typeStr).incrementAndGet());
    }
}
