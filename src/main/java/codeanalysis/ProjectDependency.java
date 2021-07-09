package codeanalysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;
import java.util.List;

import static codeanalysis.TypeAnalysis.findAllimportPaicTypes;
import static util.FilePathService.typesPathToFullTypesName;

public class ProjectDependency extends ProjectInfo{
    private static final Logger LOG = LoggerFactory.getLogger(ProjectDependency.class);

    private List<String> internalTypes;
    private List<String> importPaicTypes;

    ProjectDependency(List<Path> filesPathInProject){
        super(filesPathInProject);
        internalTypes = typesPathToFullTypesName(filesPathInProject);
        importPaicTypes = findAllimportPaicTypes();
    }


}
