package util;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.FilePathUtil.typePathToTypeName;

public class FilePathUtilTests {
    @Test
    public void getFilesPathInProjectTests(){
        List<Path> dirs = FilePathUtil.getFilesPathInProject("./src/test/resources/test_project/basicdata", "java");
        assertEquals(3, dirs.size());

        dirs = FilePathUtil.getJavaFilesPathInProjectByCurrentPath();
        assertTrue(dirs.size()>6);
    }

    @Test
    public void typePathToTypeNameTests(){
        Path path = Path.of("//test/abc/df.java");
        assertEquals("df", typePathToTypeName(path));
    }




}
