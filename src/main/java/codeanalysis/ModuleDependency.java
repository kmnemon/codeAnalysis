package codeanalysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ModuleDependency{
    private static final Logger LOG = LoggerFactory.getLogger(ModuleDependency.class);

    ModuleDependency(){ }

    static Double calcModuleInstability(List<TypeInfo> publicTypesInModule, Map<TypeInfo, AtomicInteger> typesFanIn, BasicInfo basicInfo) {
        double modFanIn = calcModuleFanIn(publicTypesInModule, typesFanIn);
        double modFanOut = calcModuleFanOut(publicTypesInModule, basicInfo);
        try {
            return (modFanOut / (modFanIn + modFanOut));
        } catch (ArithmeticException e) {
            LOG.error("fanIn + Out is zero: " + e.getMessage());
        }

        return null;
    }

    static int calcModuleFanIn(List<TypeInfo> publicTypesInModule, Map<TypeInfo, AtomicInteger> typesFanIn){
        return publicTypesInModule.stream()
                .mapToInt(type-> typesFanIn.get(type).get())
                .sum();
    }

    static int calcModuleFanOut(List<TypeInfo> publicTypesInModule, BasicInfo basicInfo){
        List<TypeInfo> publicAndPaicTypesInfoOtherFull = basicInfo.getOtherTypesInfoExclueSelf(basicInfo.getPublicAndPaicTypesInfo(), publicTypesInModule);
        return publicTypesInModule.stream()
                .mapToInt(typeInfo -> subTypesDepOtherModuleCount(typeInfo, publicAndPaicTypesInfoOtherFull))
                .sum();
    }

    /**
     * Dependency define:
     * 1.Type implement
     * 2.Type extend
     * 3.used instance
     * above dependency not include standard library,because standard libraries are stable
     *
     */
    private static int subTypesDepOtherModuleCount(TypeInfo typeInfo, List<TypeInfo> publicAndPaicTypesInfoOtherFull){
        if( typeInfo.getTypeContent().isEmpty()) {
            LOG.info(typeInfo.getTypeName() + " is empty");
            return 0;
        }

        Map<String, List<String>> subTypes = TypeAnalysis.subTypeContentSplit(typeInfo);
        if( subTypes.isEmpty()){
            LOG.info("java file don't have types");
            return 0;
        }

        return (int)subTypes.values().stream()
                .map(subType-> TypeAnalysis.isDepOtherTypes(subType, publicAndPaicTypesInfoOtherFull))
                .filter(item->item.equals(Boolean.TRUE))
                .count();
    }


    static Double calcModuleAbstractness(List<TypeInfo> publicTypesInModule){
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
            double nc = absClassCount + interfaceCount + classAndEnumCount;
            return (nc - classAndEnumCount) / nc;
        } catch (ArithmeticException e){
            LOG.error("nc is zero: " + e.getMessage());
        }

        return null;
    }



}
