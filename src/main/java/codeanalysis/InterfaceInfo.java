package codeanalysis;

import java.util.List;

import static codeanalysis.JavaType.INTERFACE_TYPE;


public class InterfaceInfo extends TypeInfo {
    InterfaceInfo(String typeName, String packageName, List<String> typeContent){
        super(typeName, packageName, typeContent);
    }

    @Override
    JavaType getType(){
        return INTERFACE_TYPE;
    }
}
