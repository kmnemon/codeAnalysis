package codeanalysis;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static util.FilePathService.getFilesPathInProject;

public class ProjectDependencyInfoTests {
    @Test
    public void setModuleTests(){
        ProjectDependencyInfo project = new ProjectDependencyInfo(getFilesPathInProject("/Users/keliu/tmp", "java"));
        assertDoesNotThrow(()->project.setModulesInProject());
    }



}
