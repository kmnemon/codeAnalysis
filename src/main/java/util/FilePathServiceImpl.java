package util;

import java.util.List;

public class FilePathServiceImpl implements FilePathService{
    @Override
    public List<String> getFilesPathInProject(String searchPath, String fileExt){

        return null;
    }

    @Override
    public List<String> getJavaFilesPathInProjectByCurrentPath(){
        return getFilesPathInProject(".", ".java");
    }

}
