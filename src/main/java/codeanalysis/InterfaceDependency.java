package codeanalysis;

import java.util.List;

import static codeanalysis.ClassType.INTERFACE_TYPE;


public class InterfaceDependency extends TypeDependency {
    InterfaceDependency(String typeNameWithFullPath, String packageNameWithFullPath, List<String> typeContent){
        super(typeNameWithFullPath, packageNameWithFullPath, typeContent);
    }

    @Override
    ClassType getType(){
        return INTERFACE_TYPE;
    }
}
