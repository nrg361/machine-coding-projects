package org.anurag.rateLimiter;

public interface RateLimiter {
    boolean allowRequest(String clientId);
}
