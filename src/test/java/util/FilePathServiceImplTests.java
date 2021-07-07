package util;

import org.junit.jupiter.api.Test;
import util.FilePathServiceImpl;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilePathServiceImplTests {
    @Test
    public void getFilesPath(){
        Optional<List<Path>> dirs = FilePathServiceImpl.getFilesPathInProject("/Users/keliu/IdeaProjects/acquireIP", "java");
        assertEquals(5, dirs.get().size());

        dirs = FilePathServiceImpl.getJavaFilesPathInProjectByCurrentPath();
        assertTrue(dirs.get().size()>6);
    }
}
