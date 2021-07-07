package codeanalysis;

import static codeanalysis.TypeDependency.ClassType.INTERFACE;

public class InterfaceDependency extends TypeDependency {
    InterfaceDependency(String typeNameWithFullPath, String packageNameWithFullPath){
        super(typeNameWithFullPath, packageNameWithFullPath);
    }

    @Override
    ClassType getType(){
        return INTERFACE;
    }
}
