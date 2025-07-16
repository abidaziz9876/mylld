package lld_problems.lfucache;
import java.util.HashMap;


class Node{
    int key,val,cnt;
    Node next;
    Node prev;
    public Node(int key, int val){
        this.key=key;
        this.val=val;
        next=null;
        prev=null;
        cnt=1;
    }
}


class List{
    int size;
    Node head= new  Node(-1, -1);
    Node tail=new Node(-1, -1);
    List(){
        head.next=tail;
        tail.prev=head;
        size=0;
    }

    void addNode(Node node){
        Node temp=head.next;
        node.prev=head;
        node.next=temp;
        temp.prev=node;
        head.next=node;
        size++;
    }

    void deleteNode(Node node){
        Node prevv=node.prev;
        Node nextt=node.next;
        prevv.next=nextt;
        nextt.prev=prevv;
        size--;
    }

}

class LFUCache{
    int cap;
    Node head= new  Node(-1, -1);
    Node tail=new Node(-1, -1);
    int minFreq;
    int currSize;
    int maxSize;
    HashMap<Integer,Node> mp=new HashMap<>();
    HashMap<Integer,List> freqListMap=new HashMap<>();
    LFUCache(int capacity){
        currSize=0;
        maxSize=capacity;
        minFreq=0;
    }

    public void updateFreqListMap(Node node){
        int freq=node.cnt;
        List list=freqListMap.get(node.cnt);
        list.deleteNode(node);
        if(freq==minFreq && freqListMap.get(freq).size==0){
            minFreq++;
        }
        node.cnt++;
        List newList;
        if(freqListMap.containsKey(node.cnt)){
            newList=freqListMap.get(node.cnt);
        }
        else{
            newList=new List();
            freqListMap.put(node.cnt, newList);
        }

        newList.addNode(node);
        mp.put(node.key, node);
    }
    public int get(int key){
        if(mp.containsKey(key)){
            Node node=mp.get(key);
            int ans=node.val;
            updateFreqListMap(node);
            return ans;
        }
        return -1;
    }

    public void put(int key,int val){
        if(mp.containsKey(key)){
            Node node=mp.get(key);
            node.val=val;
            updateFreqListMap(node);
        }
        else{
            if(currSize==maxSize){
                List list=freqListMap.get(minFreq);
                Node nodeTobeDeleted=list.tail.prev;
                list.deleteNode(nodeTobeDeleted);
                mp.remove(nodeTobeDeleted.key);
                currSize--;
            }
            currSize++;
            minFreq=1;
            Node newNode=new Node(key, val);
            List newList;
            if(freqListMap.containsKey(minFreq)){
                newList=freqListMap.get(minFreq);
            }
            else{
                newList=new List();
                freqListMap.put(minFreq, newList);
            }
            newList.addNode(newNode);
            mp.put(key, newNode);
        }
    }

    public void printState(){
        Node temp=head.next;
        while(temp!=tail){
            System.out.println("key->"+temp.key+" val->"+temp.val);
            temp=temp.next;
        }
    }
}
public class LfuCache {
    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2); // capacity = 2

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println("get(1): " + cache.get(1)); // returns 1

        cache.put(3, 3); // evicts key 2 (both 1 and 2 have freq=1, but 2 is LRU)

        System.out.println("get(2): " + cache.get(2)); // returns -1 (not found)
        System.out.println("get(3): " + cache.get(3)); // returns 3

        cache.put(4, 4); // evicts key 1 (freq=2) vs 3 (freq=1) → 3 is evicted

        System.out.println("get(1): " + cache.get(1)); // returns -1 (evicted)
        System.out.println("get(3): " + cache.get(3)); // returns -1 (evicted)
        System.out.println("get(4): " + cache.get(4)); // returns 4
    }
}
