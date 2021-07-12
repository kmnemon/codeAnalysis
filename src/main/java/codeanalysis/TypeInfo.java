package codeanalysis;

import java.util.ArrayList;
import java.util.List;

public class TypeInfo {
    private final String typeName;
    private final String packageName;
    private final List<String> typeContent;


    public TypeInfo(String typeName, String packageName, List<String> typeContent){
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

}
