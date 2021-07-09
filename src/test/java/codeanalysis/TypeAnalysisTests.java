package codeanalysis;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static codeanalysis.JavaType.CLASS_TYPE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static codeanalysis.TypeAnalysis.*;

public class TypeAnalysisTests {
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
    public void findImportPaicTypesPerTypeTests(){
        List<String> testContent = new ArrayList<>();
        testContent.add("adfasdfa");
        testContent.add("package com.aaa.bbb.ccc");
        testContent.add(" import  aaa.bbb");
        testContent.add("import ccc.ddd");
        testContent.add("package com.aaa.bbb.ccc1");
        testContent.add("import  com.paic.xxx.xxx");

        assertEquals(1, findImportPaicTypesPerType(testContent).size());
        assertEquals("com.paic.xxx.xxx", findImportPaicTypesPerType(testContent).get(0));

        List<String> noContents = new ArrayList<>();
        noContents.add("dfsdf");
        assertEquals(0, findImportPaicTypesPerType(noContents).size());
    }


}
