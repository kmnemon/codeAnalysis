package codeanalysis;

import java.util.List;

import static codeanalysis.JavaType.ENUM_TYPE;


public class EnumInfo extends TypeInfo {
    EnumInfo(String typeName, String packageName, List<String> typeContent){
        super(typeName, packageName, typeContent);
    }

    @Override
    JavaType getType(){
        return ENUM_TYPE;
    }
}
