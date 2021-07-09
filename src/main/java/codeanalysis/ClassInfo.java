package codeanalysis;

import java.util.List;

import static codeanalysis.JavaType.CLASS_TYPE;


class ClassInfo extends TypeInfo {
    ClassInfo(String typeName, String packageName, List<String> typeContent){
        super(typeName, packageName, typeContent);
    }


    @Override
    JavaType getType(){
        return CLASS_TYPE;
    }

}
