package codeanalysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class Test {
    private static final Logger LOG = LoggerFactory.getLogger(Test.class);
    private int a;

    public static void main(String[] args) {
        Test t = new Test();
        Objects.requireNonNull()
        System.out.println(t.a);


    }
}
