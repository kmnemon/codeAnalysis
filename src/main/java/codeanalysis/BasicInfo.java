package codeanalysis;

import util.BasicTypeService;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static util.BasicTypeService.combineList;
import static util.FilePathService.typesPathToFullTypesName;

public class BasicInfo {
    final List<Path> filesPathInProject;

    final List<String> publicTypesFull;
    final List<String> importPaicTypes;
    final List<String> publicAndPaicTypesFull;

    final List<TypeInfo> allTypesInfo;

    public BasicInfo(List<Path> filesPathInProject){
        this.filesPathInProject = filesPathInProject;
        publicTypesFull = typesPathToFullTypesName(filesPathInProject);
        allTypesInfo = getAllTypesInfo(filesPathInProject);
        importPaicTypes = BasicTypeService.findAllimportPaicTypes(allTypesInfo);
        publicAndPaicTypesFull = combineList(publicTypesFull, importPaicTypes);

    }

    List<TypeInfo> getAllTypesInfo(List<Path> filesPathInProject){
        return filesPathInProject.stream()
                .map(TypeAnalysis::initType)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<Path> getFilesPathInProject() {
        return filesPathInProject;
    }

    public List<String> getPublicTypesFull() {
        return publicTypesFull;
    }

    public List<String> getImportPaicTypes() {
        return importPaicTypes;
    }

    public List<String> getPublicAndPaicTypesFull() {
        return publicAndPaicTypesFull;
    }

    public List<TypeInfo> getAllTypesInfo() {
        return allTypesInfo;
    }
}
