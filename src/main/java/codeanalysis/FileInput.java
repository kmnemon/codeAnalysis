package codeanalysis;

import codedisplay.CommandDisplay;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static codeanalysis.ArthAnalysis.calcModuleDistance;
import static codeanalysis.ArthAnalysis.combineAbsAndInsToPairMap;
import static codeanalysis.ProjectDependency.calcModulesAbstractness;
import static codeanalysis.ProjectDependency.calcModulesInstability;
import static util.FilePathUtil.getFilesPathInProject;

public class FileInput {

    public static void main(String[] args) {
//        List<Path> javaFiles = getJavaFilesPathInProjectByCurrentPath();
        List<Path> javaFiles = getFilesPathInProject("./src/test/resources/end_to_end_test", "java");
        BasicInfo basicInfo = new BasicInfo(javaFiles);
        ProjectInfo p = new ProjectInfo(basicInfo);

        Map<ModuleInfo, Double> abs =  calcModulesAbstractness(p);
        Map<ModuleInfo, Double> instab = calcModulesInstability(p);

//        System.out.println("abs~~~~~");
//        abs.forEach((k,v)-> System.out.println(k.getModuleName() + " : " + v));
//        System.out.println("ins~~~~~");
//        instab.forEach((k,v)-> System.out.println(k.getModuleName() + " : " + v));

        Map<ModuleInfo, AbsAndInsPair> absAndInsInMod= combineAbsAndInsToPairMap(abs, instab);
        Map<ModuleInfo, Double> modDistance = calcModuleDistance(absAndInsInMod);
        CodeDisplay dis = new CommandDisplay();
        dis.display(modDistance, absAndInsInMod);
//        CodeDisplay dis = new CoordinateDisplay();
//        dis.display(modDistance, absAndInsInMod);




    }
}
