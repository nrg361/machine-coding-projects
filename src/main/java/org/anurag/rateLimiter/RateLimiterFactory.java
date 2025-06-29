package org.anurag.rateLimiter;

public class RateLimiterFactory {

    public static RateLimiter createRateLimiter(RateLimiterStrategy strategy) {
        switch (strategy) {
            case FIXED_WINDOW:
                return new FixedWindowRateLimiter(10, 1000);
            case SLIDING_WINDOW:
                return new SlidingWindowRateLimiter(10, 1000);
            case TOKEN_BUCKET:
                return new TokenBucketRateLimiter(10, 5);
            case LEAKY_BUCKET:
                return new LeakyBucketRateLimiter(10, 5);
            default:
                throw new IllegalArgumentException("Unknown strategy");
        }
    }
}

