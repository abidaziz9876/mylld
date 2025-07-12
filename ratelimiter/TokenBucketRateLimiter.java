package ratelimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class TokenBucketRateLimiter implements RateLimiter {
    private final int capacity;
    private final double refillRatePerSecond;
    private final Map<String, Bucket> userBuckets = new ConcurrentHashMap<>();

    public TokenBucketRateLimiter(int capacity, double refillRatePerSecond) {
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
    }

    @Override
    public boolean allowRequest(String userId) {
        long currentTime = System.currentTimeMillis();
        userBuckets.putIfAbsent(userId, new Bucket(capacity, currentTime));
        Bucket bucket = userBuckets.get(userId);

        // Lock per user bucket (not global)
        synchronized (bucket) {
            refill(bucket, currentTime);

            if (bucket.tokens >= 1) {
                bucket.tokens--;
                return true;
            }
            return false;
        }
    }

    private void refill(Bucket bucket, long currentTime) {
        double elapsedSeconds = (currentTime - bucket.lastRefillTimestamp) / 1000.0;
        double tokensToAdd = elapsedSeconds * refillRatePerSecond;
        bucket.tokens = Math.min(capacity, bucket.tokens + tokensToAdd);
        bucket.lastRefillTimestamp = currentTime;
    }

    private static class Bucket {
        double tokens;
        long lastRefillTimestamp;
        
        Bucket(int tokens, long lastRefillTimestamp) {
            this.tokens = tokens;
            this.lastRefillTimestamp = lastRefillTimestamp;
        }
    }
}


/*
public class TokenBucketRateLimiter implements RateLimiter {
    private final int capacity;
    private final double refillRatePerSecond;
    private final Map<String, Bucket> userBuckets = new ConcurrentHashMap<>();

    public TokenBucketRateLimiter(int capacity, double refillRatePerSecond) {
        this.capacity = capacity;
        this.refillRatePerSecond = refillRatePerSecond;
    }

    @Override
    public boolean allowRequest(String userId) {
        long currentTime = System.currentTimeMillis();
        userBuckets.putIfAbsent(userId, new Bucket(capacity, currentTime));

        Bucket bucket = userBuckets.get(userId);

        if (bucket.lock.tryLock()) {
            try {
                refill(bucket, currentTime);
                if (bucket.tokens >= 1) {
                    bucket.tokens--;
                    return true;
                }
                return false;
            } finally {
                bucket.lock.unlock();
            }
        } else {
            // Could not acquire lock, skip or retry later
            return false;
        }
    }

    private void refill(Bucket bucket, long currentTime) {
        double elapsedSeconds = (currentTime - bucket.lastRefillTimestamp) / 1000.0;
        double tokensToAdd = elapsedSeconds * refillRatePerSecond;
        bucket.tokens = Math.min(capacity, bucket.tokens + tokensToAdd);
        bucket.lastRefillTimestamp = currentTime;
    }

    private static class Bucket {
        double tokens;
        long lastRefillTimestamp;
        final ReentrantLock lock = new ReentrantLock();
        Bucket(int tokens, long lastRefillTimestamp) {
            this.tokens = tokens;
            this.lastRefillTimestamp = lastRefillTimestamp;
        }
    }
}
*/