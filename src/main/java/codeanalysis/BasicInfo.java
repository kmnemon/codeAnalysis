package codeanalysis;

import util.BasicTypeService;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static util.BasicTypeService.combineList;

public class BasicInfo {
    final List<Path> filesPathInProject;

    final List<String> importPaicTypes;

    final List<TypeInfo> publicTypesInfo;
    final List<TypeInfo> importPaicTypesInfo;
    final List<TypeInfo> publicAndPaicTypesInfo;

    public BasicInfo(List<Path> filesPathInProject){
        this.filesPathInProject = filesPathInProject;
        publicTypesInfo = calcPublicTypesInfoFromPaths(filesPathInProject);

        importPaicTypes = BasicTypeService.findAllimportPaicTypes(publicTypesInfo);

        importPaicTypesInfo = getImportPaicTypesInfo();
        publicAndPaicTypesInfo = combineList(publicTypesInfo, importPaicTypesInfo);
    }

    List<TypeInfo> calcPublicTypesInfoFromPaths(List<Path> filesPathInProject){
        return filesPathInProject.stream()
                .map(TypeAnalysis::initType)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    List<TypeInfo> getImportPaicTypesInfo(){
        return importPaicTypes.stream()
                .map(TypeAnalysis::initTypeWithStr)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    public List<Path> getFilesPathInProject() {
        return filesPathInProject;
    }


    public List<String> getImportPaicTypes() {
        return importPaicTypes;
    }



    public List<TypeInfo> getPublicTypesInfo() {
        return publicTypesInfo;
    }

    public List<TypeInfo> getPublicAndPaicTypesInfo() {
        return publicAndPaicTypesInfo;
    }

    public List<TypeInfo> getOtherTypesInfoExclueSelf(List<TypeInfo> originTypesInfo, List<TypeInfo> excludeTypesInfo){
        return originTypesInfo.stream()
                .filter(type-> !isContainTypes(type, excludeTypesInfo))
                .collect(Collectors.toUnmodifiableList());
    }
    private boolean isContainTypes(TypeInfo type, List<TypeInfo> excludeTypes){
        return excludeTypes.stream()
                .anyMatch(type::equals);
    }
}
