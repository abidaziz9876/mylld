package lruwithdllandmap;

public class Node<K,V> {
    K key;
    V value;
    Node<K, V> prev, next;
    public Node(K key, V value){
        this.key=key;
        this.value=value;
    }
}

/*
public class Node<K, V> {
    K key;
    V value;
    long timestamp;
    Node<K, V> prev, next;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.timestamp = System.currentTimeMillis();
    }
}

*/