package com.SchoolManagementSystem.Helpers;

import java.util.HashMap;
import java.util.Map;

public class CachingService {
    private static final Map<String, Object> cache = new HashMap<>();

    public static void put(String key, Object value) { cache.put(key, value); }

    public static Object get(String key) { return cache.get(key); }

    public static void clear() { cache.clear(); }
}
