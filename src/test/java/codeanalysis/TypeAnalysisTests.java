package codeanalysis;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static codeanalysis.TypeAnalysis.*;

public class TypeAnalysisTests {
    private static TypeInfo typeInfo;

    @BeforeAll
    public static void initTypeTests(){
        Path path = Path.of("./src/test/resources/test_project/subTypeTest.java");
        typeInfo = initType(path);
    }

    @Test
    public void getPackageNameTests(){
        List<String> testContent = new ArrayList<>();
        testContent.add("adfasdfa");
        testContent.add("package com.aaa.bbb.ccc");
        testContent.add("1111");
        testContent.add("package com.xxx.xxx.xxx");

        assertEquals("com.aaa.bbb.ccc", getPackageName(testContent));

        List<String> noContents = new ArrayList<>();
        noContents.add("dfsdf");

        assertEquals("", getPackageName(noContents));
    }

    @Test
    public void calcAndGetDepTypesTests(){
        List<String> aa = new ArrayList<>();
        aa.add("aaa bbb ccc");
        aa.add("sdfsdf");
        aa.add("xxx 7yy");

//        calcAndGetDepTypes(aa);

    }

    @Test
    public void subTypeSplitTests(){
        Map<String, List<String>> subTypes = subTypeContentSplit(typeInfo);
        assertEquals(3, subTypes.size());

    }




}
