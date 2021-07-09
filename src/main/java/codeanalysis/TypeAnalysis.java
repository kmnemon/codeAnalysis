package codeanalysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static util.FilePathService.typePathToTypeName;

public class TypeAnalysis {
    private static final Logger LOG = LoggerFactory.getLogger(TypeAnalysis.class);

    public static List<String> findImportPaicTypesPerType(List<String> typeContent){
        return typeContent.stream()
                .filter(str -> str.contains("import"))
                .filter(str -> str.contains("paic"))
                .map(String::trim)
                .map(str-> str.substring(7))
                .map(String::trim)
                .collect(toList());
    }

    public static List<String> findAllimportPaicTypes(){

        return null;
    }

    public static String getPackageName(List<String> typeContent){
        return typeContent.stream()
                .filter(line->line.contains("package"))
                .findFirst()
                .orElse("");
    }

    public static TypeInfo initType(Path path) {
        try {
            List<String> typeContent = Files.readAllLines(path);
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
}
