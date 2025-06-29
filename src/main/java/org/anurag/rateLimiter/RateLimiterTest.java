package org.anurag.rateLimiter;

public class RateLimiterTest {

    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = RateLimiterFactory.createRateLimiter(RateLimiterStrategy.TOKEN_BUCKET);

        for (int i = 0; i < 20; i++) {
            boolean allowed = limiter.allowRequest("user1");
            System.out.println("Request " + i + ": " + (allowed ? "Allowed" : "Blocked"));
            Thread.sleep(70);
        }
    }
}