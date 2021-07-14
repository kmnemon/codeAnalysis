package util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TextUtil {
    public static List<String> linesToWords(List<String> lines){
        return lines.stream()
                .flatMap(line-> Arrays.stream(line.split(" ")))
                .map(line-> line.replace("{", ""))
                .map(line-> line.replace("}", ""))
                .filter(line-> !line.isEmpty())
                .collect(Collectors.toList());
    }

    public static boolean containWordsLastToFront(String typeStr, List<String> words){
        for(String word: words){
            if(typeStr.endsWith(word))
                return true;
        }
        return false;
    }


}
