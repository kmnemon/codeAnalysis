package codeanalysis;

import java.nio.file.Path;
import java.util.List;

import static util.FilePathService.getJavaFilesPathInProjectByCurrentPath;

public class FileInput {

    public static void main(String[] args) {
        List<Path> javaFiles = getJavaFilesPathInProjectByCurrentPath();
        ProjectDependency projectDep = new ProjectDependency(javaFiles);



    }
}
