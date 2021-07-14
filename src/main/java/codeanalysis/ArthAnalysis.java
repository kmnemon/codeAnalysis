package codeanalysis;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static util.MathUtil.calcPointDistance;
import static util.MathUtil.calcTriangleCHeight;

public class ArthAnalysis {
    /**
     * D =|A+I-1|
     * @param
     * @return
     */
    public static Map<ModuleInfo, Double> calcModuleDistance(Map<ModuleInfo, AbsAndInsPair> aiPairs){
        return aiPairs.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e->cDistance(e.getValue())));
    }

    /**
     *      (0,1)     (1,1)
     *      |
     *     A|
     * (0,0)|____I___(1,0)
     *
     *   *
     *   *  *
     *    *     *
     *  b  *        * c
     *      *           *
     *       *              *
     *          *
     *       p1          *         *
     *                      a          *
     */
    private static Double cDistance(AbsAndInsPair aiPair){
        double a = calcPointDistance(aiPair.getInstability(), aiPair.getAbstractness(), 1.0, 0.0);
        double b = calcPointDistance(aiPair.getInstability(), aiPair.getAbstractness(), 0.0,1.0);
        double c = calcPointDistance(0.0, 1.0, 1.0,0.0);

        return calcTriangleCHeight(a,b,c);
    }


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
