package codeanalysis;

import java.util.List;

abstract class TypeDependency {
    enum ClassType{CLASS, INTERFACE, ABSTRACT, ENUM};
    private String typeNameWithFullPath;
    private String packageNameWithFullPath;

    private List<String> dependencyTypes;

    TypeDependency(String typeNameWithFullPath, String packageNameWithFullPath){
        this.typeNameWithFullPath = typeNameWithFullPath;
        this.packageNameWithFullPath = packageNameWithFullPath;
    }

    public List<String> getDependencyTypes() {
        return dependencyTypes;
    }

    abstract ClassType getType();

}
