package codeanalysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static codeanalysis.TypeAnalysis.subTypesDepOtherModuleCount;

public class ModuleDependency extends ModuleInfo{
    private static final Logger LOG = LoggerFactory.getLogger(ModuleDependency.class);

    private final Integer moduleAbstract;
//    private final Integer moduleInstability;

//    private final List<String> publicAndPaicTypesFull;

    ModuleDependency(String moduleName, List<TypeInfo> publicTypesInModule, List<String> publicAndPaicTypesFull){
        super(moduleName, publicAndPaicTypesFull);
        this.moduleAbstract = calcModuleAbstractness(publicTypesInModule);
//        this.moduleInstability = calcModuleInstability(publicTypesInModule);
        this.publicAndPaicTypesFull = publicAndPaicTypesFull;
    }


//    private Integer calcModuleInstability(List<TypeInfo> publicTypesInModule) {
//        try {
//            int moduleFanOut = calcModuleFanOut(publicTypesInModule);
//            return moduleFanOut / (calc..(publicTypesInModule) + moduleFanOut);
//        } catch (ArithmeticException e) {
//            LOG.error("fanIn + Out is zero: " + e.getMessage());
//        }
//        return null;
//    }


//    private int calcModuleFanOut(List<TypeInfo> publicTypesInModule){
//        List<String> publicAndPaicTypesOtherFull = getTypesFullInOtherModule(publicTypesInModule);
//        return publicTypesInModule.stream()
//                .mapToInt(typeInfo -> subTypesDepOtherModuleCount(typeInfo, publicAndPaicTypesOtherFull))
//                .sum();
//    }

    //    private int countFanIn(List<TypeInfo> publicTypesInModule, List<String> publicAndPaicTypesFull){
//        Map<String, AtomicInteger> fanIn = new HashMap<>();
//
//
//
//
//    }


    private Integer calcModuleAbstractness(List<TypeInfo> publicTypesInModule){
        int absClassCount = publicTypesInModule.stream()
                .mapToInt(TypeAnalysis::abstractCount)
                .sum();

        int interfaceCount = publicTypesInModule.stream()
                .mapToInt(TypeAnalysis::interfaceCount)
                .sum();

        int classAndEnumCount = publicTypesInModule.stream()
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


    public Integer getModuleAbstract() {
        return moduleAbstract;
    }

//    public Integer getModuleInstability() {
//        return moduleInstability;
//    }

}
