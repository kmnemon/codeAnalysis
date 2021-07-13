package util;

import codeanalysis.TypeInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BasicTypeUtil {
    //Map<ModuleInfo, List<TypeInfo>> modulesInProject
    public static List<String> findAllimportPaicTypes(List<TypeInfo> allTypesInfo) {
        List<String> importTypes = new ArrayList<>();
        allTypesInfo.stream()
                .map(TypeInfo::getTypeContent)
                .forEach(type -> type.stream()
                        .filter(str -> str.contains("import"))
                        .filter(str -> str.contains("paic"))
                        .filter(str-> !str.contains("*"))
                        .map(String::trim)
                        .map(str -> str.substring(7))
                        .map(String::trim)
                        .map(str-> str.replace(";", ""))
                        .forEach(importTypes::add)
                );

         return importTypes;
     }

     public static <T> List<T> combineList(List<T> str1, List<T> str2){
        return Stream.concat(str1.stream(), str2.stream())
                 .collect(Collectors.toUnmodifiableList());
     }

}
