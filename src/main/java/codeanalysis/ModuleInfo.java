package codeanalysis;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ModuleInfo {
    private final String moduleName;
    private final List<String> publicAndPaicTypesFull;

    ModuleInfo(String moduleName, List<String> publicAndPaicTypesFull){
        this.moduleName = moduleName;
        this.publicAndPaicTypesFull = publicAndPaicTypesFull;
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

    List<String> getTypesFullInOtherModule(List<TypeInfo> publicTypesInModuleExclude){
        return publicAndPaicTypesFull.stream()
                .filter(typeStr-> !ModuleInfo.containTypesInModule(typeStr, publicTypesInModuleExclude))
                .collect(Collectors.toUnmodifiableList());
    }

    private static boolean containTypesInModule(String typeStr, List<TypeInfo> publicTypesInModule){
        return publicTypesInModule.stream()
                .map(TypeInfo::getTypeName)
                .anyMatch(typeStr::contains);
    }
}
