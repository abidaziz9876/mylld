package lruwithdllandmap;

public class DoublyLinkedList<K,V> {
    Node<K,V> head;
    Node<K,V> tail;

    public DoublyLinkedList(){
        head=new Node<K,V>(null,null);
        tail=new Node<K,V>(null,null);
        head.next=tail;
        tail.prev=head;
    }

    public void addFirst(Node<K,V> node){
        Node<K,V> Next=head.next;
        node.next=Next;
        Next.prev=node;
        node.prev=head;
        head.next=node;
    }

    public Node<K,V> removeLast(){
        if(tail.prev==head){
            return null;
        }
        Node<K,V> last=tail.prev;
        remove(last);
        return last;
    }

    public void remove(Node<K,V> node){
        node.prev.next=node.next;
        node.next.prev=node.prev;
    }

    public void moveToFront(Node<K,V> node){
        remove(node);
        addFirst(node);
    }
}
