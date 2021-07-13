package codeanalysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TextService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static util.FilePathService.typePathToTypeName;
import static util.TextService.linesToWords;

;

public class TypeAnalysis {
    private static final Logger LOG = LoggerFactory.getLogger(TypeAnalysis.class);

    public static String getPackageName(List<String> typeContent){
        return typeContent.stream()
                .filter(line->line.contains("package"))
                .map(str->str.replace("package ", ""))
                .map(str->str.replace(";", ""))
                .findFirst()
                .orElse("");
    }

    public static TypeInfo initType(Path path) {
        try {
            List<String> typeContent = Files.readAllLines(path).stream()
                    .filter( line-> !(line.contains("//") || line.contains("/*") || line.contains("*/")) )
                    .filter(line->!line.isEmpty())
                    .collect(Collectors.toUnmodifiableList());
            String packageName = getPackageName(typeContent);
            String typeName = typePathToTypeName(path);

            return new TypeInfo(typeName, packageName, typeContent);

        } catch (IOException e) {
            LOG.info("read java file content failed" + path.toString() + ":" + e.getMessage());
        }

        return null;
    }

    public static TypeInfo initTypeWithStr(String str){
        int index = str.lastIndexOf(".");
        if( index == -1)
            return null;

        String packageName = str.substring(0, index);
        String typeName = str.substring(index+1);

        return new TypeInfo(typeName, packageName, null);
    }


    public static boolean isDepOtherTypes(List<String> subType, List<TypeInfo> publicAndPaicTypesInfoOtherFull){
        List<String> words = linesToWords(subType);

        return publicAndPaicTypesInfoOtherFull.stream()
                .anyMatch(type-> TextService.containWordsLastToFront(type.getFullTypeName(), words));
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

    public static Map<String, List<String>> subTypeContentSplit(TypeInfo typeInfo){
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
        indexes.add(typeContent.size());

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
