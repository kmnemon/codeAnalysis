package codeanalysis;

import java.util.List;
import java.util.Objects;

public class ModuleDependencyInfo {
    private String moduleName;
    private List<ClassDependencyInfo> moduleClasses;

    private List<ClassDependencyInfo> fanInClasses;
    private List<ClassDependencyInfo> fanOutClasses;
    private Integer instability;

    private Integer numOfClasses;
    private Integer numOfAbsAndInterfaceClasses;
    private Integer abstractness;

    private ModuleDependencyInfo(String moduleName, List<ClassDependencyInfo> moduleClasses){
        this.moduleName = moduleName;
        this.moduleClasses = List.copyOf(moduleClasses);
    }

    public static ModuleDependencyInfo create(String moduleName, List<ClassDependencyInfo> moduleClasses){
        return new ModuleDependencyInfo(moduleName, moduleClasses);
    }

    public Integer getInstability(){
        if(instability == null){
            instability = Objects.requireNonNull(fanOutClasses).size() /
                    (Objects.requireNonNull(fanInClasses).size() + fanOutClasses.size());
        }

        return instability;
    }

    public Integer getAbstractness(){
        if(abstractness == null){
            abstractness = Objects.requireNonNull(numOfAbsAndInterfaceClasses) / Objects.requireNonNull(numOfClasses);
        }

        return abstractness;
    }



}
