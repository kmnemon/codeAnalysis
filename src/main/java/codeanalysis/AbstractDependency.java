package codeanalysis;

import java.util.List;

import static codeanalysis.ClassType.ABSTRACT_TYPE;

public class AbstractDependency extends TypeDependency {
    AbstractDependency(String typeNameWithFullPath, String packageNameWithFullPath, List<String> typeContent){
        super(typeNameWithFullPath, packageNameWithFullPath, typeContent);
    }

    @Override
    ClassType getType(){
        return ABSTRACT_TYPE;
    }
}
