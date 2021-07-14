package codedisplay;

import codeanalysis.AbsAndInsPair;
import codeanalysis.CodeDisplay;
import codeanalysis.ModuleInfo;

import java.util.Map;

public class CommandDisplay implements CodeDisplay {
    @Override
    public void display(Map<ModuleInfo,Double> distance, Map<ModuleInfo, AbsAndInsPair> pair){
        distance.forEach((k,v)-> System.out.println(k.getModuleName() + " : " + String.format("%.2f",v) + " (" + pair.get(k)+")"));
    }
}
