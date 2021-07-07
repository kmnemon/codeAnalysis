package util;

import codeanalysis.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class FilePathServiceImpl{
    private static final Logger LOG = LoggerFactory.getLogger(FilePathServiceImpl.class);

    private FilePathServiceImpl(){}

    public static Optional<List<Path>> getFilesPathInProject(String searchDir, String fileExt){
        try {
            Path searchPath = Paths.get(searchDir);
            PathMatcher matcher = FileSystems.getDefault()
                    .getPathMatcher("glob:**/*.{" + fileExt + "}");

            return Optional.of(Files.walk(searchPath)
                    .filter(matcher::matches)
                    .collect(toList()));
        }catch (InvalidPathException  e){
            LOG.error("failed convert searchDir to path" + e.getMessage());
        } catch (Exception e){
            LOG.error("something wrong with path operate" + e.getMessage());
        }

        return Optional.empty();
    }

    public static Optional<List<Path>> getJavaFilesPathInProjectByCurrentPath(){
        return getFilesPathInProject("", "java");
    }

}
