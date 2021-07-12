package codeanalysis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Test {
    private static final Logger LOG = LoggerFactory.getLogger(Test.class);
    private int a;

    public static void main(String[] args) throws Exception{
        Path path= Path.of("/Users/keliu/tmp/1.java");

        try {
            List<String> typeContent = Files.readAllLines(path).stream()
                    .filter(line -> !(line.contains("//") || line.contains("/*") || line.contains("*/")))
                    .collect(Collectors.toUnmodifiableList());

            System.out.println(typeContent.size());
        }catch (Exception e){
            System.out.println("df");
        }
    }
}
