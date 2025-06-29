package org.anurag.rateLimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TokenBucketRateLimiter implements RateLimiter {
    private final int capacity;
    private final int refillRatePerSecond;
    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    public TokenBucketRateLimiter(int capacity, int refillRatePerSecond) {
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
    }

    @Override
    public synchronized boolean allowRequest(String clientId) {
        Bucket bucket = buckets.computeIfAbsent(clientId, k -> new Bucket(capacity, refillRatePerSecond));
        return bucket.tryConsume();
    }

    private static class Bucket {
        int tokens;
        final int capacity;
        final int refillRatePerSecond;
        long lastRefillTimestamp;

        Bucket(int capacity, int refillRate) {
            this.capacity = capacity;
            this.refillRatePerSecond = refillRate;
            this.tokens = capacity;
            this.lastRefillTimestamp = System.currentTimeMillis();
        }

        boolean tryConsume() {
            refill();
            if (tokens == 0) return false;
            tokens--;
            return true;
        }

        void refill() {
            long now = System.currentTimeMillis();
            long millisSinceLast = now - lastRefillTimestamp;
            int tokensToAdd = (int) (millisSinceLast * refillRatePerSecond / 1000);
            if (tokensToAdd > 0) {
                tokens = Math.min(capacity, tokens + tokensToAdd);
                lastRefillTimestamp = now;
            }
        }
    }
}
