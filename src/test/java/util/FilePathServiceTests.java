package util;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.FilePathService.classPathToClassName;

public class FilePathServiceTests {
    @Test
    public void getFilesPathInProjectTests(){
        List<Path> dirs = FilePathService.getFilesPathInProject("/Users/keliu/IdeaProjects/acquireIP", "java");
        assertEquals(5, dirs.size());

        dirs = FilePathService.getFilesPathInProject("/Users/keliu/tmp", "java");
        assertEquals(0, dirs.size());

        dirs = FilePathService.getJavaFilesPathInProjectByCurrentPath();
        assertTrue(dirs.size()>6);
    }

    @Test
    public void classPathToClassNameTests(){
        Path path = Path.of("/test/abc/df");
        assertEquals("test.abc.df", classPathToClassName(path));
    }




}
