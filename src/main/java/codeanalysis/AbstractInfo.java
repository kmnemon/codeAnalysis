package codeanalysis;

import java.util.List;

import static codeanalysis.JavaType.ABSTRACT_TYPE;

class AbstractInfo extends TypeInfo {
    AbstractInfo(String typeName, String packageName, List<String> typeContent){
        super(typeName, packageName, typeContent);
    }

    @Override
    JavaType getType(){
        return ABSTRACT_TYPE;
    }
}
