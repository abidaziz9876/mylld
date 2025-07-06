package ratelimiter;

public interface RateLimiter {
    boolean allowRequest(String userId);
}

