package lld_problems.ratelimiter;

/*
-Should the rate limiting be applied per user, per IP, or globally
-Is the refill rate specified in tokens per second, per minute, or any other time unit
-If multiple threads request tokens for the same user simultaneously, should the system 
block, fail fast, or retry?(Helps decide between synchronized, tryLock(), or lock-free alternatives)
-Should the solution be thread-safe, and are we expecting high concurrency?
*/
public class Demo {
    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = new FixedWindowRateLimiter(5, 10000); // 5 req per 10 sec

        String user = "user1";

        for (int i = 0; i < 10; i++) {
            System.out.println("Request " + i + ": " + limiter.allowRequest(user));
            Thread.sleep(1000);
        }
    }
}
