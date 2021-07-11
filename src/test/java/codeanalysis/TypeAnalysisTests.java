package codeanalysis;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
    public void calcAndGetDepTypesTests(){
        List<String> aa = new ArrayList<>();
        aa.add("aaa bbb ccc");
        aa.add("sdfsdf");
        aa.add("xxx 7yy");

//        calcAndGetDepTypes(aa);

    }




}
