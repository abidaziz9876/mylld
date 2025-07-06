package ratelimiter;

import java.util.concurrent.ConcurrentHashMap;

public class FixedWindowRateLimiter implements RateLimiter {
    private final int maxRequests;
    private final long windowSizeMillis;

    // userId -> Window
    private final ConcurrentHashMap<String, Window> userWindows = new ConcurrentHashMap<>();

    public FixedWindowRateLimiter(int maxRequests, long windowSizeMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeMillis = windowSizeMillis;
    }

    @Override
    public boolean allowRequest(String userId) {
        long currentTime = System.currentTimeMillis();
        userWindows.putIfAbsent(userId, new Window(0, currentTime));

        synchronized (userWindows.get(userId)) {
            Window window = userWindows.get(userId);

            if (currentTime - window.startTime >= windowSizeMillis) {
                // Reset window
                window.startTime = currentTime;
                window.requestCount = 1;
                return true;
            }

            if (window.requestCount < maxRequests) {
                window.requestCount++;
                return true;
            }

            return false;
        }
    }

    private static class Window {
        int requestCount;
        long startTime;

        Window(int count, long time) {
            this.requestCount = count;
            this.startTime = time;
        }
    }
}

