import org.junit.jupiter.api.Test;
import util.FilePathServiceImpl;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilePathServiceImplTests {
    @Test
    public void test(){
        Optional<List<Path>> dirs = FilePathServiceImpl.getFilesPathInProject("/Users/keliu/IdeaProjects/acquireIP", "java");
        assertEquals(5, dirs.get().size());
    }
}
