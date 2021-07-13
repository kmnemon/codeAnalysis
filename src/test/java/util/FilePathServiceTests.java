package util;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.FilePathService.typePathToTypeName;

public class FilePathServiceTests {
    @Test
    public void getFilesPathInProjectTests(){
        List<Path> dirs = FilePathService.getFilesPathInProject("/Users/keliu/IdeaProjects/acquireIP", "java");
        assertEquals(5, dirs.size());

        dirs = FilePathService.getFilesPathInProject("/Users/keliu/tmp", "java");
        assertEquals(1, dirs.size());

        dirs = FilePathService.getJavaFilesPathInProjectByCurrentPath();
        assertTrue(dirs.size()>6);
    }

    @Test
    public void typePathToTypeNameTests(){
        Path path = Path.of("//test/abc/df.java");
        assertEquals("df", typePathToTypeName(path));
    }




}
