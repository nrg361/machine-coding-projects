package org.anurag.rateLimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class GlobalRateLimiterRegistry {

    private static final GlobalRateLimiterRegistry INSTANCE = new GlobalRateLimiterRegistry();

    private final ConcurrentMap<String, RateLimiter> rateLimiterMap = new ConcurrentHashMap<>();

    private GlobalRateLimiterRegistry() {}

    public static synchronized GlobalRateLimiterRegistry getInstance() {
        return INSTANCE;
    }

    public RateLimiter getOrCreate(String key, RateLimiterStrategy strategy) {
        return rateLimiterMap.computeIfAbsent(key, k -> RateLimiterFactory.createRateLimiter(strategy));
    }
}

