package codeanalysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static codeanalysis.TypeAnalysis.calcAndGetDepTypesToOtherModule;

public class ModuleDependency{
    private static final Logger LOG = LoggerFactory.getLogger(ModuleDependency.class);

//    public static Integer calcInstability(List<TypeInfo> typesInModule) {
//        try {
//            int fanOut = countFanOut(typesInModule);
//            return fanOut / (countFanIn(typesInModule) + fanOut);
//        } catch (ArithmeticException e) {
//            LOG.error("fanIn + Out is zero: " + e.getMessage());
//        }
//        return null;
//    }


    private static int countFanOut(List<TypeInfo> publicTypesInModule, List<String> publicAndPaicTypesFull){
        List<String> publicAndPaicTypesOtherFull = getTypesInOtherModule(publicTypesInModule, publicAndPaicTypesFull);
        return publicTypesInModule.stream()
                .flatMap(type -> calcAndGetDepTypesToOtherModule(type, publicAndPaicTypesOtherFull).stream())
                .collect(Collectors.toSet())
                .size();
    }

    private static List<String> getTypesInOtherModule(List<TypeInfo> publicTypesInModule, List<String> publicAndPaicTypesFull){
        return publicAndPaicTypesFull.stream()
                .filter(typeStr-> !containTypesInModule(typeStr, publicTypesInModule))
                .collect(Collectors.toList());
    }

    private static boolean containTypesInModule(String typeStr, List<TypeInfo> publicTypesInModule){
        return publicTypesInModule.stream()
                .flatMap(typeInfo->typeInfo.getTypeContent().stream())
                .anyMatch(typeStr::contains);
    }

//    private static int countFanIn(List<TypeInfo> publicTypesInModule, List<String> publicAndPaicTypesFull){
//        Map<String, AtomicInteger> fanIn = new HashMap<>();
//
//
//
//
//    }


    public static Integer calcAbstractness(List<TypeInfo> publicTypesInModule){
        int absClassCount = (int)publicTypesInModule.stream()
                .mapToInt(TypeAnalysis::abstractCount)
                .sum();

        int interfaceCount = (int)publicTypesInModule.stream()
                .mapToInt(TypeAnalysis::interfaceCount)
                .sum();

        int classAndEnumCount = (int)publicTypesInModule.stream()
                .mapToInt(TypeAnalysis::classAndEnumCount)
                .sum();

        try {
            int nc = absClassCount + interfaceCount + classAndEnumCount;
            return (nc - classAndEnumCount) / nc;
        } catch (ArithmeticException e){
            LOG.error("nc is zero: " + e.getMessage());
        }

        return null;
    }


}
