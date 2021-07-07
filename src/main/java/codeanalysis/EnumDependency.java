package codeanalysis;

import static codeanalysis.TypeDependency.ClassType.ENUM;

public class EnumDependency extends TypeDependency{
    EnumDependency(String typeNameWithFullPath, String packageNameWithFullPath){
        super(typeNameWithFullPath, packageNameWithFullPath);
    }

    @Override
    ClassType getType(){
        return ENUM;
    }
}
