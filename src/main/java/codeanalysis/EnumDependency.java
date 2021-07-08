package codeanalysis;

import java.util.List;

import static codeanalysis.ClassType.ENUM_TYPE;


public class EnumDependency extends TypeDependency{
    EnumDependency(String typeNameWithFullPath, String packageNameWithFullPath, List<String> typeContent){
        super(typeNameWithFullPath, packageNameWithFullPath, typeContent);
    }

    @Override
    ClassType getType(){
        return ENUM_TYPE;
    }
}
