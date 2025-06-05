public class Node<K, V> {
    K key;
    V val;
    long expiryTime; // store expiry time in millis
    Node<K, V> next;
    Node<K, V> prev;

    public Node(K key, V val, long expiryTime) {
        this.key = key;
        this.val = val;
        this.expiryTime = expiryTime;
    }
}
