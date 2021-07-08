package util;

import codeanalysis.ClassType;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static codeanalysis.ClassType.*;

public class ClassAnalysis {
    public static List<String> getImportPaicCLass(List<String> clsContent){
        return clsContent.stream()
                .filter(line-> line.contains("import"))
                .filter(line-> line.contains("paic"))
                .collect(Collectors.toList());
    }

    public static String getPackageName(List<String> clsContent){
        return clsContent.stream()
                .filter(line->line.contains("package"))
                .findFirst()
                .orElse("");
    }

    public static Optional<ClassType> getCLassType(List<String> clsContent, String className){
        for (String line : clsContent){
            if (line.contains("interface " + className)){
                return Optional.of(INTERFACE_TYPE);
            }else if(line.contains("enum " + className)){
                return Optional.of(ENUM_TYPE);
            }else if(line.contains("abstract class " + className)){
                return Optional.of(ABSTRACT_TYPE);
            }else if(line.contains("class " + className)){
                return Optional.of(CLASS_TYPE);
            }
        }
        return Optional.empty();
    }
}
