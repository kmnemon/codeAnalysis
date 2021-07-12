package codeanalysis;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.FilePathService.getJavaFilesPathInProjectByCurrentPath;

public class ModuleInfoTests {
    static BasicInfo basicInfo;
    @BeforeAll
    static void initBasicInfo(){
        List<Path> javaFiles = getJavaFilesPathInProjectByCurrentPath();
        basicInfo = new BasicInfo(javaFiles);
    }

    @Test
    void sameModuleTests(){
        ModuleInfo m1 = new ModuleInfo("aaa", basicInfo);
        ModuleInfo m2 = new ModuleInfo("aaa", basicInfo);

        assertEquals(m1, m2);
    }


}
