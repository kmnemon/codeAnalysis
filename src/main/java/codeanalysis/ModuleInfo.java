package codeanalysis;

import java.util.Objects;

public class ModuleInfo {
    private final String moduleName;
    protected BasicInfo basicInfo;

    ModuleInfo(String moduleName){
        this.moduleName = moduleName;
    }

    ModuleInfo(String moduleName, BasicInfo basicInfo){
        this.moduleName = moduleName;
        this.basicInfo = basicInfo;
    }

    @Override
    public boolean equals(Object o){
        if(o == this)
            return true;
        if(!(o instanceof ModuleInfo))
            return false;
        ModuleInfo mod = (ModuleInfo) o;
        return mod.moduleName.equals(moduleName);

    }

    @Override
    public int hashCode(){
        return Objects.hashCode(moduleName);
    }

    @Override
    public String toString(){
        return moduleName;
    }

    public String getModuleName() {
        return moduleName;
    }
}
