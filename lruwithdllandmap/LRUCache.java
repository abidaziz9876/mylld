package lruwithdllandmap;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K,V> {
    int capacity;
    Map<K,Node<K,V>> map;
    DoublyLinkedList<K,V> dll;
    
    public LRUCache(int capacity){
        this.capacity=capacity;
        this.map=new HashMap<>();
        this.dll=new DoublyLinkedList<>();
    }

    public synchronized V get(K key){
        if(!map.containsKey(key)){
            return null;
        }

        Node<K,V> node=map.get(key);
        dll.moveToFront(node);
        return node.value;
    }

    public synchronized void put(K key, V value){
        if(map.containsKey(key)){
            Node<K,V> node=map.get(key);
            node.value=value;
            dll.moveToFront(node);
        }
        else{
            if(map.size()==capacity){
                Node<K, V> lru = dll.removeLast();
                if (lru != null) map.remove(lru.key);
            }
            Node<K,V> newNode=new Node<K,V>(key,value);
            dll.addFirst(newNode);
            map.put(key, newNode);
        }
    }
    public synchronized void remove(K key) {
        if (!map.containsKey(key)) return;
        Node<K, V> node = map.get(key);
        dll.remove(node);
        map.remove(key);
    }
}

/*

public class LRUCache<K, V> {
    private final int capacity;
    private final Map<K, Node<K, V>> map;
    private final DoublyLinkedList<K, V> dll;
    private final ReentrantLock lock = new ReentrantLock();

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.dll = new DoublyLinkedList<>();
    }

    public V get(K key) {
        boolean acquired = false;
        try {
            acquired = lock.tryLock(50, TimeUnit.MILLISECONDS); // wait up to 50ms
            if (!acquired) return null; // fail fast if lock not acquired

            if (!map.containsKey(key)) return null;

            Node<K, V> node = map.get(key);
            dll.moveToFront(node);
            return node.value;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // preserve interrupt status
            return null;
        } finally {
            if (acquired) lock.unlock();
        }
    }

    public boolean put(K key, V value) {
        boolean acquired = false;
        try {
            acquired = lock.tryLock(50, TimeUnit.MILLISECONDS);
            if (!acquired) return false;

            if (map.containsKey(key)) {
                Node<K, V> node = map.get(key);
                node.value = value;
                dll.moveToFront(node);
            } else {
                if (map.size() == capacity) {
                    Node<K, V> lru = dll.removeLast();
                    if (lru != null) map.remove(lru.key);
                }
                Node<K, V> newNode = new Node<>(key, value);
                dll.addFirst(newNode);
                map.put(key, newNode);
            }
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } finally {
            if (acquired) lock.unlock();
        }
    }

    public boolean remove(K key) {
        boolean acquired = false;
        try {
            acquired = lock.tryLock(50, TimeUnit.MILLISECONDS);
            if (!acquired) return false;

            if (!map.containsKey(key)) return false;
            Node<K, V> node = map.get(key);
            dll.remove(node);
            map.remove(key);
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } finally {
            if (acquired) lock.unlock();
        }
    }
}
*/