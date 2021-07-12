package codeanalysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.*;

import static util.FilePathService.getJavaFilesPathInProjectByCurrentPath;


class ProjectInfo {
    private static final Logger LOG = LoggerFactory.getLogger(ProjectInfo.class);

    protected final Map<ModuleInfo, List<TypeInfo>> modulesInProject;
    protected BasicInfo basicInfo;

    public ProjectInfo(BasicInfo basicInfo){
        this.modulesInProject = new HashMap<>();
        this.basicInfo = basicInfo;
        initModulesInProject();
    }

    public void initModulesInProject(){
        basicInfo.getAllTypesInfo().forEach(this::initModules);
    }

    private void initModules(TypeInfo type){
            modulesInProject.computeIfAbsent(new ModuleInfo(type.getPackageName(), basicInfo), k -> new ArrayList<>());
            modulesInProject.get(new ModuleInfo(type.getPackageName(), basicInfo)).add(type);
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
