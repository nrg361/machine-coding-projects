package org.anurag.rateLimiter;

public class RateLimiterTestUsingRegistry {

    public static void main(String[] args) throws InterruptedException {
        GlobalRateLimiterRegistry registry = GlobalRateLimiterRegistry.getInstance();

        // Create or fetch a global rate limiter for client "user1" with a chosen strategy
        RateLimiter userLimiter = registry.getOrCreate("user1", RateLimiterStrategy.SLIDING_WINDOW);

        for (int i = 0; i < 20; i++) {
            boolean allowed = userLimiter.allowRequest("user1");
            System.out.println("Request " + i + ": " + (allowed ? "Allowed" : "Blocked"));
            Thread.sleep(100);
        }

        // Reuse the same rate limiter elsewhere in your app
        RateLimiter again = registry.getOrCreate("user1", RateLimiterStrategy.SLIDING_WINDOW);
        System.out.println("Same instance reused? " + (userLimiter.equals(again))); // should print true
    }
}

