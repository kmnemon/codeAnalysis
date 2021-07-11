package codeanalysis;

import java.util.ArrayList;
import java.util.List;

abstract class TypeInfo {
    private String typeName;
    private String packageName;
    private List<String> typeContent;


    TypeInfo(String typeName, String packageName, List<String> typeContent){
        this.typeName = typeName;
        this.packageName = packageName;
        this.typeContent = typeContent;
    }

    public String getTypeName() {
        return typeName;
    }

    public String getPackageName() {
        return packageName;
    }

    public List<String> getTypeContent() {
        return typeContent;
    }

    abstract JavaType getType();

}
