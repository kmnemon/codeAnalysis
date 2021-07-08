package codeanalysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static util.FilePathServiceImpl.*;

class ProjectDependencyInfo {
    private static final Logger LOG = LoggerFactory.getLogger(ProjectDependencyInfo.class);

    private final List<Path> filesPathInProject;
    private final List<ModuleDependencyInfo> modulesInProject;

    private final List<String> internalClasses;
    private List<String> importPaicClasses;


    public ProjectDependencyInfo(List<Path> filesPathInProject){
        this.filesPathInProject = filesPathInProject;
        this.modulesInProject = new ArrayList<>();
        this.internalClasses = classesPathToClassesName(this.filesPathInProject);
    }

    public List<ModuleDependencyInfo> getModulesInProject() {
        return modulesInProject;
    }

    public void setModulesInProject(){
        if( filesPathInProject.isEmpty())
            return;



    }

    public void setClassesInProject() {
        filesPathInProject.stream()
                .map( path->{
                    try{
                        return Files.readAllLines(path);
                    }catch (IOException e){
                        LOG.info("read java file content failed" + path.toString() + ":" + e.getMessage());
                    }
                    return null;
                        })
                .count();


    }

    @Override
    public String toString(){
        //need update
        return null;
    }

    public static void main(String[] args) {
        ProjectDependencyInfo project = new ProjectDependencyInfo(getJavaFilesPathInProjectByCurrentPath());
        project.setModulesInProject();

    }

}
