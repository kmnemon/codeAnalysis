package codeanalysis;

import java.util.Map;

public interface CodeDisplay {
    void display(Map<ModuleInfo,Double> distance, Map<ModuleInfo, AbsAndInsPair> pair);


}
