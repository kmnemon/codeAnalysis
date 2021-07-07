package codeanalysis;

import static codeanalysis.TypeDependency.ClassType.ABSTRACT;

public class AbstractDependency extends TypeDependency {
    AbstractDependency(String typeNameWithFullPath, String packageNameWithFullPath){
        super(typeNameWithFullPath, packageNameWithFullPath);
    }

    @Override
    ClassType getType(){
        return ABSTRACT;
    }
}
