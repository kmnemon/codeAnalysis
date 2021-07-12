package util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TextService {
    public static List<String> linesToWords(List<String> lines){
        return lines.stream()
                .flatMap(line-> Arrays.stream(line.split(" ")))
                .collect(Collectors.toList());
    }

    public static boolean containWords(String typeStr, List<String> words){
        for(String word: words){
            if(typeStr.contains(word))
                return true;
        }
        return false;
    }
}
