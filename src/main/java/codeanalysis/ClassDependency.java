package codeanalysis;

import static codeanalysis.TypeDependency.ClassType.CLASS;

class ClassDependency extends TypeDependency {
    ClassDependency(String typeNameWithFullPath, String packageNameWithFullPath){
        super(typeNameWithFullPath, packageNameWithFullPath);
    }


    @Override
    ClassType getType(){
        return CLASS;
    }

}
