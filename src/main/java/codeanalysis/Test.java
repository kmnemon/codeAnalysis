package codeanalysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Test {
    private static final Logger LOG = LoggerFactory.getLogger(Test.class);
    private int a;

    public static void main(String[] args) throws Exception{
        String a = "class Type";
        boolean s = a.contains("class ");
        System.out.println(s);
    }
}
