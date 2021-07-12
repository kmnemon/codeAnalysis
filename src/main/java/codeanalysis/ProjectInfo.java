package codeanalysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.*;


class ProjectInfo {
    private static final Logger LOG = LoggerFactory.getLogger(ProjectInfo.class);

    private final List<Path> filesPathInProject;
    protected final Map<ModuleInfo, List<TypeInfo>> modulesInProject;

    public ProjectInfo(List<Path> filesPathInProject){
        this.filesPathInProject = filesPathInProject;
        this.modulesInProject = new HashMap<>();
        initModulesInProject();
    }

    public void initModulesInProject(){
        if( filesPathInProject.isEmpty())
            return;

        filesPathInProject.stream()
                .map(TypeAnalysis::initType)
                .filter(Objects::nonNull)
                .peek(System.out::println)
                .forEach(this::initModules);
    }

    private void initModules(TypeInfo type){
            modulesInProject.computeIfAbsent(new ModuleInfo(type.getPackageName()), k -> new ArrayList<>());
            modulesInProject.get(new ModuleInfo(type.getPackageName())).add(type);
    }

    public Map<ModuleInfo, List<TypeInfo>> getModulesInProject() {
        return modulesInProject;
    }

    @Override
    public String toString(){
        //need update
        return null;
    }

}
