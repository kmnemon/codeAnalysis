package codeanalysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static util.FilePathService.typePathToTypeName;

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
                    .collect(Collectors.toList());

            String packageName = getPackageName(typeContent);
            String typeName = typePathToTypeName(path);

            for (String line : typeContent){
                if (line.contains("interface " + typeName)){
                    return new InterfaceInfo(typeName, packageName, typeContent);
                }else if(line.contains("enum " + typeName)){
                    return new EnumInfo(typeName, packageName, typeContent);
                }else if(line.contains("abstract class " + typeName)){
                    return new AbstractInfo(typeName, packageName, typeContent);
                }else if(line.contains("class " + typeName)){
                    return new ClassInfo(typeName, packageName, typeContent);
                }
            }

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
    public static Set<String> calcAndGetDepTypesToOtherModule(TypeInfo typeInfo, List<String> publicAndPaicTypesOtherFull){
        Set<String> words = typeInfo.getTypeContent().stream()
                .flatMap(line-> Arrays.stream(line.split(" ")))
                .collect(Collectors.toSet());

        return publicAndPaicTypesOtherFull.stream()
                .filter(typeStr->containWords(typeStr, words))
                .collect(Collectors.toSet());

    }

    public static boolean containWords(String typeStr, Set<String> words){
        for(String word: words){
            if(typeStr.contains(word))
                return true;
        }
        return false;
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
        List<Integer> indexes = new ArrayList<>();
        Integer lineNum = 0;
        for( String line : typeInfo.getTypeContent()){
            if(isType(line))
                indexes.add(lineNum);
            lineNum++;
        }
        indexes.add(typeInfo.getTypeContent().size()-1);

        return null;



    }

}
