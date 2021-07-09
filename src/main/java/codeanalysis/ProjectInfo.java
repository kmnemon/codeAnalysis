package codeanalysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;


class ProjectInfo {
    private static final Logger LOG = LoggerFactory.getLogger(ProjectInfo.class);

    private final List<Path> filesPathInProject;
    private final Map<String, List<TypeInfo>> modulesInProject;

    protected ProjectInfo(List<Path> filesPathInProject){
        this.filesPathInProject = filesPathInProject;
        this.modulesInProject = new HashMap<>();
        setModulesInProject();
    }

    public void setModulesInProject(){
        if( filesPathInProject.isEmpty())
            return;

        filesPathInProject.stream()
                .map(TypeAnalysis::initType)
                .filter(Objects::nonNull)
                .forEach(this::initModules);
    }

    public void initModules(TypeInfo type){
            modulesInProject.computeIfAbsent(type.getPackageName(), k -> new ArrayList<>());
            modulesInProject.get(type.getPackageName()).add(type);

    }


    @Override
    public String toString(){
        //need update
        return null;
    }

}
