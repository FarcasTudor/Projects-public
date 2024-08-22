package org.example;

import java.util.Map;
import java.util.LinkedHashMap;

public class Utils {
    public static Map<String, Integer> getTopEntries(Map<String, Integer> map, int limit) {
        return map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(limit)
                .collect(LinkedHashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);
    }
}
