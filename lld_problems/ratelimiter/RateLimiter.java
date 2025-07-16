package lld_problems.ratelimiter;

public interface RateLimiter {
    boolean allowRequest(String userId);
}

