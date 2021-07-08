package codeanalysis;

import java.util.List;

abstract class TypeDependency {
    private String typeNameWithFullPath;
    private String packageNameWithFullPath;

    private List<String> dependencyTypes;
    private List<String> typeContent;

    TypeDependency(String typeNameWithFullPath, String packageNameWithFullPath, List<String> typeContent){
        this.typeNameWithFullPath = typeNameWithFullPath;
        this.packageNameWithFullPath = packageNameWithFullPath;
        this.typeContent = typeContent;
    }

    public List<String> getDependencyTypes() {
        return dependencyTypes;
    }

    abstract ClassType getType();

}
