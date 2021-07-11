package codeanalysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static util.FilePathService.typesPathToFullTypesName;

public class ProjectDependency extends ProjectInfo{
    private static final Logger LOG = LoggerFactory.getLogger(ProjectDependency.class);

    private final List<String> publicAndPaicTypesFull;
//    private final List<String> publicTypes;

    ProjectDependency(List<Path> filesPathInProject){
        super(filesPathInProject);
        List<String> publicTypes = typesPathToFullTypesName(filesPathInProject);
        List<String> importPaicTypes = findAllimportPaicTypes(modulesInProject);
        publicAndPaicTypesFull = Stream.concat(publicTypes.stream(), importPaicTypes.stream())
                .collect(Collectors.toUnmodifiableList());
    }


    public static List<String> findAllimportPaicTypes(Map<String, List<TypeInfo>> modulesInProject){
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

//    private Map<String, AtomicInteger> countProjectFanIn(){
//        Map<String, AtomicInteger> fanIn = new HashMap<>();
//        modulesInProject.values().stream()
//                .flatMap(Collection::stream)
//                .map(TypeInfo::getTypeContent)
////                .map(lines-> )
//        return null;
//    }

    private void aaa(List<String> typeContent){
//        for(String line: typeContent){
//            if()



//        }

    }

}
