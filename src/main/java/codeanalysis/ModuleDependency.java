package codeanalysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static codeanalysis.JavaType.CLASS_TYPE;
import static codeanalysis.JavaType.ENUM_TYPE;

public class ModuleDependency{
    private static final Logger LOG = LoggerFactory.getLogger(ModuleDependency.class);

    public static Integer calcInstability(List<TypeInfo> typesInModule) {
        try {
            int fanOut = countFanOut(typesInModule);
            return fanOut / (countFanIn(typesInModule) + fanOut);
        } catch (ArithmeticException e) {
            LOG.error("fanIn + Out is zero: " + e.getMessage());
        }
        return null;
    }

    private static int countFanOut(List<TypeInfo> typesInModule){
        return typesInModule.stream()
                .mapToInt(type -> type.calcAndGetDepTypes().size())
                .sum();
    }

    private static int countFanIn(List<TypeInfo> typesInModule){
        return 1;//need update
    }


    public static Integer calcAbstractness(List<TypeInfo> typesInModule){
        try {
            int nc = typesInModule.size();
            return (nc - countClassEnumNum(typesInModule)) / nc;
        } catch (ArithmeticException e){
            LOG.error("nc is zero: " + e.getMessage());
        }
        return null;
    }

    private static int countClassEnumNum(List<TypeInfo> typesInModule){
        return (int)typesInModule.stream()
                .filter(type-> type.getType() == CLASS_TYPE || type.getType() == ENUM_TYPE)
                .count();
    }
}
