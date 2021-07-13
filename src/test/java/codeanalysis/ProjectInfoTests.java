package codeanalysis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static util.FilePathUtil.getFilesPathInProject;

public class ProjectInfoTests {
    public ProjectInfo p;

    @BeforeEach
    public void initProject(){
        List<Path> filesPathInProject = getFilesPathInProject("./src/test/resources/test_project", "java");
        BasicInfo basicInfo = new BasicInfo(filesPathInProject);
        p = new ProjectInfo(basicInfo);
    }

    @Test
    public void initModulesInProjectTests(){
        Map<ModuleInfo, List<TypeInfo>> modulesInProject = p.getModulesInProject();
        assertEquals(4, modulesInProject.size());
    }



}
