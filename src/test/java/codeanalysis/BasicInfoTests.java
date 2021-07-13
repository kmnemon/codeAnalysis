package codeanalysis;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.FilePathService.getFilesPathInProject;

public class BasicInfoTests {
    static BasicInfo basicInfo;
    static ProjectInfo p;

    @BeforeAll
    public static void init(){
        List<Path> javaFiles = getFilesPathInProject("./src/test/resources/test_project/basicdata", "java");
        basicInfo = new BasicInfo(javaFiles);
//        ProjectInfo p = new ProjectInfo(basicInfo);

    }

    @Test
    public void getFilesPathInProjectTests(){
        List<Path> paths = basicInfo.getFilesPathInProject();
        assertEquals(3, paths.size());
    }


    @Test
    public void getImportPaicTypesTests(){
        List<String> s = basicInfo.getImportPaicTypes();

        assertEquals(2, s.size());
        assertEquals("org.paic.mail.SimpleMailMessage", s.get(0));
    }



    @Test
    public void getOtherTypesExclueSelfTests(){
        List<TypeInfo> excludeTypes = new ArrayList<>();
        TypeInfo t = new TypeInfo("type2", "ke.ke.other", null);
        excludeTypes.add(t);

        List<TypeInfo> ot = basicInfo.getOtherTypesInfoExclueSelf(basicInfo.getPublicTypesInfo(), excludeTypes);
        assertEquals(2, ot.size());

    }

    @Test
    public void getImportPaicTypesInfoTests(){
        List<TypeInfo> typeInfos = basicInfo.getImportPaicTypesInfo();
        assertEquals(2, typeInfos.size());
    }


}
