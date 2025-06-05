public class Demo {
    public static void main(String[] args) {
        EvictionPolicy<String, String> cache = new LRUCache<>(2, 2000); // 2 second TTL

        cache.put("a", "Apple");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // restore interrupt flag
            System.out.println("Sleep interrupted");
        }

        cache.put("b", "Banana");

        System.out.println(cache.get("a")); // Apple

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Sleep interrupted");
        }

        System.out.println(cache.get("a")); // null (expired)
        System.out.println(cache.get("b")); // Banana
    }
}
