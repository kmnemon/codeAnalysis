package codeanalysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TextService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static util.FilePathService.typePathToTypeName;
import static util.TextService.linesToWords;

public class TypeAnalysis {
    private static final Logger LOG = LoggerFactory.getLogger(TypeAnalysis.class);

    public static String getPackageName(List<String> typeContent){
        return typeContent.stream()
                .filter(line->line.contains("package"))
                .findFirst()
                .orElse("");
    }

    public static TypeInfo initType(Path path) {
        try {
            List<String> typeContent = Files.readAllLines(path).stream()
                    .filter( line-> !(line.contains("//") || line.contains("/*") || line.contains("*/")) )
                    .collect(Collectors.toUnmodifiableList());
            String packageName = getPackageName(typeContent);
            String typeName = typePathToTypeName(path);

            return new TypeInfo(typeName, packageName, typeContent);

        } catch (IOException e) {
            LOG.info("read java file content failed" + path.toString() + ":" + e.getMessage());
        }

        return null;
    }

    /**
     * Dependency define:
     * 1.Type implement
     * 2.Type extend
     * 3.used instance
     * above dependency not include standard library,because standard libraries are stable
     *
     */
    public static int subTypesDepOtherModuleCount(TypeInfo typeInfo, List<String> publicAndPaicTypesOtherFull){
        if( typeInfo.getTypeContent().isEmpty()) {
            LOG.info(typeInfo.getTypeName() + " is empty");
            return 0;
        }

        Map<String, List<String>> subTypes = subTypeSplit(typeInfo);
        if( subTypes.isEmpty()){
            LOG.info("java file don't have types");
            return 0;
        }

        return (int)subTypes.values().stream()
                .map(subType-> isDepOtherTypes(subType, publicAndPaicTypesOtherFull))
                .filter(item->item.equals(Boolean.TRUE))
                .count();
    }



    public static boolean isDepOtherTypes(List<String> subType, List<String> publicAndPaicTypesOtherFull){
        List<String> words = linesToWords(subType);

        return publicAndPaicTypesOtherFull.stream()
                .anyMatch(typeStr-> TextService.containWords(typeStr, words));
    }


    public static int interfaceCount(TypeInfo typeInfo){
        return (int)typeInfo.getTypeContent().stream()
                .filter(line->line.contains("interface "))
                .count();
    }

    public static int abstractCount(TypeInfo typeInfo){
        return (int)typeInfo.getTypeContent().stream()
                .filter(line->line.contains("class "))
                .filter(line->line.contains("abstract "))
                .count();
    }


    public static int classAndEnumCount(TypeInfo typeInfo){
        return (int)typeInfo.getTypeContent().stream()
                .filter(line->line.contains("class ") || line.contains("enum "))
                .filter(line->!line.contains("abstract "))
                .count();
    }

    public static boolean isType(String line){
        return line.contains("interface ") || line.contains("enum ") || line.contains("class ");
    }

    public static Map<String, List<String>> subTypeSplit(TypeInfo typeInfo){
        if( typeInfo.getTypeContent().isEmpty()) {
            LOG.info(typeInfo.getTypeName() + " is empty");
            return new HashMap<>();
        }

        final List<String> typeContent = typeInfo.getTypeContent();

        List<Integer> indexes = new ArrayList<>();
        Integer lineNum = 0;
        for( String line : typeContent){
            if(isType(line))
                indexes.add(lineNum);
            lineNum++;
        }
        indexes.add(typeContent.size()-1);

        Map<String, List<String>> subTypes = new HashMap<>();
        Integer fromIndex = indexes.get(0);
        for(int i=1; i<indexes.size(); i++){
            Integer toIndex = indexes.get(i);
            subTypes.put(typeContent.get(fromIndex), typeContent.subList(fromIndex, toIndex));
            fromIndex = toIndex;
        }

        return subTypes;
    }

}
