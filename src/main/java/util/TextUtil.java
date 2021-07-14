package util;

import java.util.*;
import java.util.stream.Collectors;

public class TextUtil {
    public static List<String> linesToWords(List<String> lines) {
        return lines.stream()
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .map(line -> line.replace("{", ""))
                .map(line -> line.replace("}", ""))
                .filter(line -> !line.isEmpty())
                .collect(Collectors.toList());
    }

    public static boolean containWordsLastToFront(String typeStr, List<String> words) {
        for (String word : words) {
            if (typeStr.endsWith(word))
                return true;
        }
        return false;
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }


}
