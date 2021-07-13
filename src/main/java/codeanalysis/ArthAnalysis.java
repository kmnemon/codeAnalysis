package codeanalysis;

import java.util.HashMap;
import java.util.Map;

public class ArthAnalysis {
    public static Map<ModuleInfo, AbsAndInsPair> combineAbsAndInsToPairMap(Map<ModuleInfo, Double> abs, Map<ModuleInfo,Double> ins){
        Map<ModuleInfo, AbsAndInsPair> pairMap = new HashMap<>();
        abs.entrySet().stream()
                .filter(e-> !e.getValue().isNaN())
                .forEach(e-> {
                    AbsAndInsPair absAndInsPair = findAndCreatePair(e, ins);
                    if( absAndInsPair != null) {
                        pairMap.put(e.getKey(), absAndInsPair);
                    }
                });

        return pairMap;
    }

    private static AbsAndInsPair findAndCreatePair(Map.Entry<ModuleInfo, Double> e, Map<ModuleInfo, Double> ins){
        if(ins.get(e.getKey()) == null || ins.get(e.getKey()).isNaN())
            return null;
        return new AbsAndInsPair(e.getValue(), ins.get(e.getKey()));
    }
}
