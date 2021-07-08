package codeanalysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.*;
import java.util.Objects;

public class Test {
    private static final Logger LOG = LoggerFactory.getLogger(Test.class);
    private int a;

    public static void main(String[] args) throws Exception{
        try {
            Path test = Paths.get("/Users/keliu/tmp");
//            System.out.println(test.toAbsolutePath());
            //        Directories.refreshTestDir();
//        Directories.populateTestDir();
            // Creating a *directory*, not a file:
//            Files.createDirectory(test.resolve("dir.tmp"));
            System.out.println(test);
            System.out.println(test.getParent());

            System.out.println("***************");
        }catch (InvalidPathException  e){
            System.out.println("waht");
        } catch (Exception e){
            System.out.println("sdsfs");
        }

    }
}
