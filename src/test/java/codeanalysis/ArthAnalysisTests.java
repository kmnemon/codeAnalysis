package codeanalysis;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static codeanalysis.ArthAnalysis.combineAbsAndInsToPairMap;
import static java.lang.Double.NaN;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArthAnalysisTests {
    @Test
    public void combineAbsAndInsToPairMapTests(){
        Map<ModuleInfo, Double> abs = new HashMap<>();
        ModuleInfo m1 = new ModuleInfo("com.aa");
        ModuleInfo m2 = new ModuleInfo("com.bb");
        ModuleInfo m3 = new ModuleInfo("com.cc");
        abs.put(m1, 0.5);
        abs.put(m2, 1.0);
        abs.put(m3, NaN);

        Map<ModuleInfo, Double> ins = new HashMap<>();
        ModuleInfo m4 = new ModuleInfo("com.aa");
        ModuleInfo m5 = new ModuleInfo("com.bb");
        ModuleInfo m6 = new ModuleInfo("com.cc");

        ins.put(m4, 0.7);
        ins.put(m5, NaN);
        ins.put(m6, 0.9);

        Map<ModuleInfo, AbsAndInsPair> testMap = combineAbsAndInsToPairMap(abs, ins);
        assertEquals(1, testMap.size());
        assertEquals("0.5:0.7", testMap.get(m1).toString());
//        testMap.entrySet().stream()
//                .forEach(e-> System.out.println(e.getKey().getModuleName() + ": " +e.getValue()));
    }
}
