package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class FilePathService {
    private static final Logger LOG = LoggerFactory.getLogger(FilePathService.class);

    private FilePathService(){}

    public static List<Path> getFilesPathInProject(String searchDir, String fileExt){
        try {
            Path searchPath = Paths.get(searchDir);
            PathMatcher matcher = FileSystems.getDefault()
                    .getPathMatcher("glob:**/*.{" + fileExt + "}");

            return Files.walk(searchPath)
                    .filter(matcher::matches)
                    .collect(toList());
        }catch (InvalidPathException  e){
            LOG.error("failed convert searchDir to path" + e.getMessage());
        }catch (Exception e){
            LOG.error("something wrong with path operate" + e.getMessage());
        }
            return new ArrayList<>();
    }

    public static List<Path> getJavaFilesPathInProjectByCurrentPath(){
        return getFilesPathInProject("", "java");
    }

    public static String classPathToClassName(Path path){
        return path.toString().replace("/", ".").substring(1);
    }

    public static List<String> classesPathToClassesName(List<Path> paths){
        return paths.stream().map(FilePathService::classPathToClassName).collect(toList());
    }

}
