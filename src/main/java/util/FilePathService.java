package util;

import java.util.List;

public interface FilePathService {
    public List<String> getFilesPathInProject(String searchPath, String fileExt);
    public List<String> getJavaFilesPathInProjectByCurrentPath();

}
