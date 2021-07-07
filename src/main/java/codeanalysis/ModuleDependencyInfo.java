package codeanalysis;

import java.util.List;
import java.util.Objects;

import static codeanalysis.TypeDependency.ClassType.CLASS;
import static codeanalysis.TypeDependency.ClassType.ENUM;

class ModuleDependencyInfo {
    private String moduleNameWithFullPath;
    private List<TypeDependency> typesInModule;

    private Integer instability;
    private Integer abstractness;

    private ModuleDependencyInfo(String moduleNameWithFullPath, List<TypeDependency> typesInModule){
        this.moduleNameWithFullPath = moduleNameWithFullPath;
        this.typesInModule = List.copyOf(typesInModule);
    }

    public static ModuleDependencyInfo create(String moduleName, List<TypeDependency> typesInModule){
        return new ModuleDependencyInfo(moduleName, typesInModule);
    }

    private Integer countFanOut(){
        return typesInModule.stream()
                .mapToInt(type -> type.getDependencyTypes().size())
                .sum();
    }

    private Integer countFanIn(){
        return null;//need update
    }

    private Integer countClassEnumNum(){
        return (int)typesInModule.stream().filter(type-> type.getType() == CLASS || type.getType() == ENUM ).count();
    }


    public Integer getInstability(){
        if(instability == null){
            instability = countFanOut() /
                    (countFanIn() + countFanOut());
        }

        return instability;
    }

    public Integer getAbstractness(){
        if(abstractness == null){
            abstractness = (typesInModule.size() - countClassEnumNum()) / countClassEnumNum();
        }

        return abstractness;
    }



}
