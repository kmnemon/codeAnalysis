package codeanalysis;

import java.util.List;
import java.util.Objects;

public class ModuleDependencyInfo {
    private List<String> fanIn;
    private List<String> fanOut;
    private Integer instability;
    private String moduleName;


    public Integer getInstability(){
        if(instability == null){
            instability = Objects.requireNonNull(fanOut).size() / (Objects.requireNonNull(fanIn).size() + fanOut.size());
        }

        return instability;
    }



}
