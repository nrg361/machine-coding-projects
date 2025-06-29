package org.anurag.rateLimiter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowRateLimiter implements RateLimiter {
    private final int limit;
    private final long windowSizeMillis;
    private final Map<String, Deque<Long>> userTimestamps = new ConcurrentHashMap<>();

    public SlidingWindowRateLimiter(int limit, long windowSizeMillis) {
        this.limit = limit;
        this.windowSizeMillis = windowSizeMillis;
    }

    @Override
    public synchronized boolean allowRequest(String clientId) {
        long now = System.currentTimeMillis();
        Deque<Long> timestamps = userTimestamps.computeIfAbsent(clientId, k -> new LinkedList<>());

        while (!timestamps.isEmpty() && now - timestamps.peekFirst() >= windowSizeMillis) {
            timestamps.pollFirst();
        }

        if (timestamps.size() >= limit) {
            return false;
        }

        timestamps.addLast(now);
        return true;
    }
}
