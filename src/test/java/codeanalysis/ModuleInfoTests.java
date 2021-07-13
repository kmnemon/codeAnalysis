package codeanalysis;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.FilePathUtil.getJavaFilesPathInProjectByCurrentPath;

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
        ModuleInfo m2 = new ModuleInfo("aaa");

        Map<ModuleInfo, Integer> a = new HashMap<>();
        a.put(m1, 3);

        assertEquals(m1, m2);
    }


}
