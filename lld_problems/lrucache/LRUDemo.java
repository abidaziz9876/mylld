package lld_problems.lrucache;


/*
-Do we need the cache to be thread-safe?
-Should the cache support generic types, or will keys and values always be String, Integer, etc.
-Do we need to support expiration of entries after a certain time?
-Do we need a method to manually remove an entry from cache?
*/

public class LRUDemo {
    public static void main(String[] args) {
        LRUCache<String, Integer> cache = new LRUCache<>(3);

        cache.put("a", 1);
        cache.put("b", 2);
        cache.put("c", 3);

        System.out.println(cache.get("a")); // 1

        cache.put("d", 4);
        
        System.out.println(cache.get("b")); // null


        // LRUCache<String, String> cache = new LRUCache<>(3, 3000); // 3 entries, TTL = 3 sec

        // cache.put("A", "Alpha");
        // Thread.sleep(1000);
        // cache.put("B", "Beta");
        // Thread.sleep(1000);
        // cache.put("C", "Gamma");

    }
}




/*
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LRUCache(int capacity) {
        // accessOrder = true enables LRU ordering
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    // Automatically called by put and putAll after inserting a new entry
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // Remove the eldest entry if size exceeds capacity
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRUCache<Integer, String> lru = new LRUCache<>(3);

        lru.put(1, "A");
        lru.put(2, "B");
        lru.put(3, "C");
        System.out.println(lru); // {1=A, 2=B, 3=C}

        lru.get(1);              // Access 1, now most recently used
        lru.put(4, "D");         // Evicts key 2 (least recently used)
        System.out.println(lru); // {3=C, 1=A, 4=D}
    }
}

*/