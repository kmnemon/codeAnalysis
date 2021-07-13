package codeanalysis;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static codeanalysis.ProjectDependency.calcModulesAbstractness;
import static codeanalysis.ProjectDependency.calcModulesInstability;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.BasicTypeUtil.findAllimportPaicTypes;
import static util.FilePathUtil.getFilesPathInProject;

public class ProjectDependencyTests {
    static BasicInfo basicInfo;
    static ProjectInfo p;
    @BeforeAll
    static void initBasicInfo(){
        List<Path> javaFiles = getFilesPathInProject("./src/test/resources/test_project", "java");
        basicInfo = new BasicInfo(javaFiles);
        p = new ProjectInfo(basicInfo);

    }

    @Test
    void findAllimportPaicTypesTests(){
        List<String> con1 = Arrays.asList("asdf","import com.paic.aaa");
        List<String> con2 = Arrays.asList("asdf","import com.paic.bbb");
        List<String> con3 = Arrays.asList("asdf","import com.xxx.aaa");
        List<String> con4 = Arrays.asList("asdf"," com.paic.ccc");
        List<String> con5 = Arrays.asList("asdf","import com.paixxc.aaa");
        List<String> con6 = Arrays.asList("asdf","import  com.paic.ddd");

        TypeInfo t1 = new TypeInfo("x1", "packA", con1);
        TypeInfo t2 = new TypeInfo("x2", "packB", con2);
        TypeInfo t3 = new TypeInfo("x3", "packC", con3);
        TypeInfo t4 = new TypeInfo("x4", "packD", con4);
        TypeInfo t5 = new TypeInfo("x5", "packE", con5);
        TypeInfo t6 = new TypeInfo("x6", "packF", con6);

        List<TypeInfo> ti = Arrays.asList(t1,t2,t3,t4,t5,t6);

        assertEquals(3, findAllimportPaicTypes(ti).size());
        assertEquals("com.paic.aaa", findAllimportPaicTypes(ti).get(0));
    }

    @Test
    void calcModulesAbstractnessTests(){
        List<Path> javaFiles = getFilesPathInProject("./src/test/resources/test_project/abstractnesstestdata", "java");
        basicInfo = new BasicInfo(javaFiles);
        ProjectInfo p = new ProjectInfo(basicInfo);
        Map<ModuleInfo, Double> modAbs = calcModulesAbstractness(p);
        assertEquals(2, modAbs.size());
        assertEquals(0.5, modAbs.get(new ModuleInfo("ke.ke.acquireIP")));
    }

    @Test
    void calcTypesFanInInProjectTests(){
        List<Path> javaFiles = getFilesPathInProject("./src/test/resources/test_project/instabilitydata", "java");
        basicInfo = new BasicInfo(javaFiles);
        ProjectInfo p = new ProjectInfo(basicInfo);
        Map<TypeInfo, AtomicInteger> faninP = ProjectDependency.calcTypesFanInInProject(p);

        assertEquals(2, faninP.get(new TypeInfo("type2","ke.ke.otherModule",null )).get());
    }

    @Test
    public void calcModulesInstabilityTests(){
        List<Path> javaFiles = getFilesPathInProject("./src/test/resources/test_project/instabilitydata", "java");
        basicInfo = new BasicInfo(javaFiles);
        ProjectInfo p = new ProjectInfo(basicInfo);

        Map<ModuleInfo, Double> i = calcModulesInstability(p);
        assertEquals(2, i.size());
    }


}
