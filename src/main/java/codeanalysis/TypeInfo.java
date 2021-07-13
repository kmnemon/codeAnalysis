package codeanalysis;

import java.util.List;
import java.util.Objects;

public class TypeInfo {
    private final String typeName;
    private final String fullTypeName;
    private final String packageName;
    private final List<String> typeContent;

    public TypeInfo(String typeName, String packageName, List<String> typeContent){
        this.typeName = typeName;
        this.packageName = packageName;
        this.fullTypeName = packageName + "." + typeName;
        this.typeContent = typeContent;
    }

    @Override
    public boolean equals(Object o){
        if(o == this)
            return true;
        if(!(o instanceof TypeInfo))
            return false;
        TypeInfo type = (TypeInfo) o;
        return type.typeName.equals(typeName) && type.packageName.equals(packageName);

    }

    @Override
    public int hashCode(){
        return Objects.hash(typeName, packageName);
    }

    @Override
    public String toString(){
        return packageName + ":" + typeName;
    }


    public String getTypeName() {
        return typeName;
    }

    public String getFullTypeName() {
        return fullTypeName;
    }

    public String getPackageName() {
        return packageName;
    }

    public List<String> getTypeContent() {
        return typeContent;
    }

}
