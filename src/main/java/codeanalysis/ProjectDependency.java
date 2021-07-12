package codeanalysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TextService;

import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static util.FilePathService.typesPathToFullTypesName;

public class ProjectDependency extends ProjectInfo{
    private static final Logger LOG = LoggerFactory.getLogger(ProjectDependency.class);

    private final List<String> publicAndPaicTypesFull;
    final List<String> publicTypesFull;
    final List<String> importPaicTypes;
    private Map<String, AtomicInteger> fanIn;

    ProjectDependency(List<Path> filesPathInProject){
        super(filesPathInProject);
        publicTypesFull = typesPathToFullTypesName(filesPathInProject);
        importPaicTypes = findAllimportPaicTypes(modulesInProject);
        publicAndPaicTypesFull = Stream.concat(publicTypesFull.stream(), importPaicTypes.stream())
                .collect(Collectors.toUnmodifiableList());
        calcProjectFanIn();
    }


   static List<String> findAllimportPaicTypes(Map<ModuleInfo, List<TypeInfo>> modulesInProject){
        List<String> importTypes = new ArrayList<>();
        modulesInProject.values()
                .forEach(item-> item.stream()
                        .map(TypeInfo::getTypeContent)
                        .forEach(type->type.stream()
                                .filter(str -> str.contains("import"))
                                .filter(str -> str.contains("paic"))
                                .map(String::trim)
                                .map(str-> str.substring(7))
                                .map(String::trim)
                                .forEach(importTypes::add)
                        )
                );

        return importTypes;
    }

    private void calcProjectFanIn(){
        fanIn = new HashMap<>();
        publicTypesFull.forEach(str-> fanIn.putIfAbsent(str, new AtomicInteger()));

        modulesInProject.forEach(this::calcModuleFanInOtherModule);
    }

    private void calcModuleFanInOtherModule(ModuleInfo moduleInfo, List<TypeInfo> typeInModule){
        List<String> typesFullInOtherModule = moduleInfo.getTypesFullInOtherModule(typeInModule, publicTypesFull);
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
