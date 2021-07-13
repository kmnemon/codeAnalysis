package codeanalysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import util.TextUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static codeanalysis.ModuleDependency.calcModuleAbstractness;
import static codeanalysis.ModuleDependency.calcModuleInstability;

public class ProjectDependency{
    private static final Logger LOG = LoggerFactory.getLogger(ProjectDependency.class);

    private ProjectDependency(){}

    static Map<ModuleInfo, Double> calcModulesAbstractness(ProjectInfo p){
        Map<ModuleInfo, Double> modulesAbs = new HashMap<>();
        p.getModulesInProject().forEach((k, v)-> modulesAbs.put(k, calcModuleAbstractness(v)));
        return modulesAbs;
    }

    static Map<ModuleInfo, Double> calcModulesInstability(ProjectInfo p){
        Map<ModuleInfo, Double> modulesInstability = new HashMap<>();
        Map<TypeInfo, AtomicInteger> typesFanIn = calcTypesFanInInProject(p);
        p.getModulesInProject().forEach((k, v)-> modulesInstability.put(k, calcModuleInstability(v, typesFanIn, p.getBasicInfo())));
        return modulesInstability;
    }

    static Map<TypeInfo, AtomicInteger> calcTypesFanInInProject(ProjectInfo p){
        Map<TypeInfo, AtomicInteger> typesFanIn = new HashMap<>();
        p.getBasicInfo().getPublicTypesInfo().forEach(type-> typesFanIn.putIfAbsent(type, new AtomicInteger()));

        p.getModulesInProject().values().forEach(v->calcTypesFanInInOtherModule(v, p, typesFanIn));
        return typesFanIn;
    }

    private static void calcTypesFanInInOtherModule(List<TypeInfo> typesInModule, ProjectInfo p, Map<TypeInfo, AtomicInteger> typesFanIn){
        List<TypeInfo> typesInOtherModule = p.basicInfo.getOtherTypesInfoExclueSelf(p.basicInfo.getPublicTypesInfo(), typesInModule);
        typesInModule.stream()
                .map(TypeAnalysis::subTypeContentSplit)  //Map<String, List<String>>
                .forEach(subTypesContent-> calcSubTypeFanInInOtherModule(subTypesContent, typesInOtherModule, typesFanIn));
    }

    private static void calcSubTypeFanInInOtherModule(Map<String, List<String>> subTypesContent, List<TypeInfo> typesInOtherModule, Map<TypeInfo, AtomicInteger> typesFanIn){
        subTypesContent.values().stream()
                .map(TextUtil::linesToWords)
                .forEach(subTypeWords-> calcSubTypeFanInOtherModule(subTypeWords, typesInOtherModule, typesFanIn));
    }

    private static void calcSubTypeFanInOtherModule(List<String> subTypeWords, List<TypeInfo> typesInOtherModule, Map<TypeInfo, AtomicInteger> typesFanIn){
        typesInOtherModule.stream()
                .filter(type-> TextUtil.containWordsLastToFront(type.getFullTypeName(), subTypeWords))
                .forEach(type-> typesFanIn.get(type).incrementAndGet());
    }
}
