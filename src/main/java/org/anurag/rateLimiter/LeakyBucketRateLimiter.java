package org.anurag.rateLimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LeakyBucketRateLimiter implements RateLimiter {
    private final int capacity;
    private final int leakRatePerSecond;
    private final Map<String, LeakyBucket> buckets = new ConcurrentHashMap<>();

    public LeakyBucketRateLimiter(int capacity, int leakRatePerSecond) {
        this.capacity = capacity;
        this.leakRatePerSecond = leakRatePerSecond;
    }

    @Override
    public synchronized boolean allowRequest(String clientId) {
        LeakyBucket bucket = buckets.computeIfAbsent(clientId, k -> new LeakyBucket(capacity, leakRatePerSecond));
        return bucket.allow();
    }

    private static class LeakyBucket {
        int size = 0;
        final int capacity;
        final int leakRatePerSecond;
        long lastLeakTimestamp;

        LeakyBucket(int capacity, int leakRate) {
            this.capacity = capacity;
            this.leakRatePerSecond = leakRate;
            this.lastLeakTimestamp = System.currentTimeMillis();
        }

        boolean allow() {
            leak();
            if (size < capacity) {
                size++;
                return true;
            }
            return false;
        }

        void leak() {
            long now = System.currentTimeMillis();
            long elapsed = now - lastLeakTimestamp;
            int leaked = (int) (elapsed * leakRatePerSecond / 1000);
            if (leaked > 0) {
                size = Math.max(0, size - leaked);
                lastLeakTimestamp = now;
            }
        }
    }
}

