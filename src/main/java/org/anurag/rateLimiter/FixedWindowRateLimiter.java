package org.anurag.rateLimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FixedWindowRateLimiter implements RateLimiter {
    private final int limit;
    private final long windowSizeMillis;
    private final Map<String, RequestCounter> userRequestMap = new ConcurrentHashMap<>();

    public FixedWindowRateLimiter(int limit, long windowSizeMillis) {
        this.limit = limit;
        this.windowSizeMillis = windowSizeMillis;
    }

    @Override
    public synchronized boolean allowRequest(String clientId) {
        long currentTime = System.currentTimeMillis();
        RequestCounter counter = userRequestMap.getOrDefault(clientId, new RequestCounter(0, currentTime));

        if (currentTime - counter.windowStartTime >= windowSizeMillis) {
            counter = new RequestCounter(1, currentTime);
        } else {
            if (counter.count >= limit) return false;
            counter.count++;
        }

        userRequestMap.put(clientId, counter);
        return true;
    }

    private static class RequestCounter {
        int count;
        long windowStartTime;

        RequestCounter(int count, long time) {
            this.count = count;
            this.windowStartTime = time;
        }
    }
}
