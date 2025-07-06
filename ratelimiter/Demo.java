package ratelimiter;

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
