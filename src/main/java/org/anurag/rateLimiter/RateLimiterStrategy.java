package org.anurag.rateLimiter;

public enum RateLimiterStrategy {
    FIXED_WINDOW,
    SLIDING_WINDOW,
    TOKEN_BUCKET,
    LEAKY_BUCKET
}

