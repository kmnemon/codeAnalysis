package codeanalysis;

import java.util.List;

import static codeanalysis.ClassType.CLASS_TYPE;


class ClassDependency extends TypeDependency {
    ClassDependency(String typeNameWithFullPath, String packageNameWithFullPath, List<String> typeContent){
        super(typeNameWithFullPath, packageNameWithFullPath, typeContent);
    }


    @Override
    ClassType getType(){
        return CLASS_TYPE;
    }

}
