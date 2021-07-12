package util;

import codeanalysis.ModuleInfo;
import codeanalysis.TypeInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicTypeService {
    //Map<ModuleInfo, List<TypeInfo>> modulesInProject
    public static List<String> findAllimportPaicTypes(List<TypeInfo> allTypesInfo) {
        List<String> importTypes = new ArrayList<>();
        allTypesInfo.stream()
                .map(TypeInfo::getTypeContent)
                .forEach(type -> type.stream()
                        .filter(str -> str.contains("import"))
                        .filter(str -> str.contains("paic"))
                        .map(String::trim)
                        .map(str -> str.substring(7))
                        .map(String::trim)
                        .forEach(importTypes::add)
                );

         return importTypes;
     }

     public static List<String> combineList(List<String> str1, List<String> str2){
        return Stream.concat(str1.stream(), str2.stream())
                 .collect(Collectors.toUnmodifiableList());
     }
}
