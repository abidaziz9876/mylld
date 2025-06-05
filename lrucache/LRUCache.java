import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> implements EvictionPolicy<K, V> {

    private final int capacity;
    private final long ttlMillis;
    private final Map<K, Node<K, V>> mp;
    private final Node<K, V> head = new Node<>(null, null, 0);
    private final Node<K, V> tail = new Node<>(null, null, 0);

    public LRUCache(int capacity, long ttlMillis) {
        this.capacity = capacity;
        this.ttlMillis = ttlMillis;
        this.mp = new HashMap<>();
        tail.prev = head;
        head.next = tail;
    }

    private void addNode(Node<K, V> node) {
        Node<K, V> temp = head.next;
        node.prev = head;
        node.next = temp;
        temp.prev = node;
        head.next = node;
    }

    private void deleteNode(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private boolean isExpired(Node<K, V> node) {
        return System.currentTimeMillis() > node.expiryTime;
    }

    @Override
    public V get(K key) {
        if (!mp.containsKey(key)) return null;

        Node<K, V> node = mp.get(key);
        if (isExpired(node)) {
            deleteNode(node);
            mp.remove(key);
            return null;
        }

        deleteNode(node);
        addNode(node); // move to front (recently used)
        return node.val;
    }

    @Override
    public void put(K key, V val) {
        if (mp.containsKey(key)) {
            Node<K, V> existing = mp.get(key);
            if (isExpired(existing)) {
                deleteNode(existing);
                mp.remove(key);
            } else {
                existing.val = val;
                existing.expiryTime = System.currentTimeMillis() + ttlMillis;
                deleteNode(existing);
                addNode(existing);
                return;
            }
        }

        // Clean up expired entries if capacity is full
        if (mp.size() >= capacity) {
            Node<K, V> toRemove = tail.prev;
            while (toRemove != head && isExpired(toRemove)) {
                Node<K, V> prev = toRemove.prev;
                deleteNode(toRemove);
                mp.remove(toRemove.key);
                toRemove = prev;
            }

            if (mp.size() >= capacity) {
                deleteNode(toRemove);
                mp.remove(toRemove.key);
            }
        }

        Node<K, V> newNode = new Node<>(key, val, System.currentTimeMillis() + ttlMillis);
        addNode(newNode);
        mp.put(key, newNode);
    }
}
