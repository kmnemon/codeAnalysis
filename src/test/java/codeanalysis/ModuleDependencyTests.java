package codeanalysis;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static codeanalysis.ModuleDependency.calcModuleFanIn;
import static codeanalysis.ModuleDependency.calcModuleFanOut;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.FilePathUtil.getFilesPathInProject;

public class ModuleDependencyTests {
    @BeforeAll
    static void initBasicInfo(){
    }

    @Test
    void calcModuleFanInTests(){
        List<Path> javaFiles = getFilesPathInProject("./src/test/resources/test_project/instabilitydata", "java");
        BasicInfo basicInfo = new BasicInfo(javaFiles);
        ProjectInfo p = new ProjectInfo(basicInfo);
        Map<TypeInfo, AtomicInteger> faninP = ProjectDependency.calcTypesFanInInProject(p);

        ModuleInfo mod = new ModuleInfo("ke.ke.otherModule");
        assertEquals(2, calcModuleFanIn(p.getModulesInProject().get(mod), faninP));
    }

    @Test
    void calcModuleFanOutTests(){
        List<Path> javaFiles = getFilesPathInProject("./src/test/resources/test_project/instabilitydata", "java");
        BasicInfo basicInfo = new BasicInfo(javaFiles);
        ProjectInfo p = new ProjectInfo(basicInfo);

        ModuleInfo mod = new ModuleInfo("ke.ke.acquireIP");
        assertEquals(2,calcModuleFanOut(p.getModulesInProject().get(mod), basicInfo));
    }
}
