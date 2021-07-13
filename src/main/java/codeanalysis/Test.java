package codeanalysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Double.NaN;


public class Test {
    private static final Logger LOG = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) throws Exception{
        Double a = NaN;
        System.out.println(a.isNaN());
    }
}
