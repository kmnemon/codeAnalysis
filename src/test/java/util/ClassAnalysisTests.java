package util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static codeanalysis.ClassType.CLASS_TYPE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static util.ClassAnalysis.*;

public class ClassAnalysisTests {
    @Test
    public void getPackageNameTests(){
        List<String> testContent = new ArrayList<>();
        testContent.add("adfasdfa");
        testContent.add("package com.aaa.bbb.ccc");
        testContent.add("1111");
        testContent.add("package com.xxx.xxx.xxx");

        assertEquals("package com.aaa.bbb.ccc", getPackageName(testContent));

        List<String> noContents = new ArrayList<>();
        noContents.add("dfsdf");

        assertEquals("", getPackageName(noContents));
    }

    @Test
    public void getImportCLassTests(){
        List<String> testContent = new ArrayList<>();
        testContent.add("adfasdfa");
        testContent.add("package com.aaa.bbb.ccc");
        testContent.add("import aaa.bbb");
        testContent.add("import ccc.ddd");
        testContent.add("package com.aaa.bbb.ccc1");

        assertEquals(2, getImportCLass(testContent).size());
        assertEquals("import aaa.bbb", getImportCLass(testContent).get(0));

        List<String> noContents = new ArrayList<>();
        noContents.add("dfsdf");
        assertEquals(0, getImportCLass(noContents).size());
    }

    @Test
    public void getCLassTypeTests(){
        List<String> testContent = new ArrayList<>();
        testContent.add("adfasdfa");
        testContent.add("package com.aaa.bbb.ccc");
        testContent.add("public class AA");

        assertEquals(Optional.of(CLASS_TYPE), getCLassType(testContent, "AA"));

    }
}
