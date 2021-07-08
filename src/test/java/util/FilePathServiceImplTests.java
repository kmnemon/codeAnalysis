package util;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.FilePathServiceImpl.classPathToClassName;

public class FilePathServiceImplTests {
    @Test
    public void getFilesPathTests(){
        List<Path> dirs = FilePathServiceImpl.getFilesPathInProject("/Users/keliu/IdeaProjects/acquireIP", "java");
        assertEquals(5, dirs.size());

        dirs = FilePathServiceImpl.getJavaFilesPathInProjectByCurrentPath();
        assertTrue(dirs.size()>6);
    }

    @Test
    public void classPathToClassNameTests(){
        Path path = Path.of("/test/abc/df");
        assertEquals("test.abc.df", classPathToClassName(path));
    }




}
