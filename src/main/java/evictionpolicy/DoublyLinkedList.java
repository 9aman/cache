package evictionpolicy;


import lombok.Getter;
import org.jetbrains.annotations.NotNull;

@Getter
class Node<Key> {
    Key value;
    Node<Key> prev;
    Node<Key>  next;

    public Node(Key value, Node<Key> prev, Node<Key> next) {
        this.value = value;
        this.prev = prev;
        this.next = next;
    }
}

public class DoublyLinkedList<Key> {
    private Node<Key> dummyHead;
    private Node<Key> dummyTail;

    public DoublyLinkedList() {
        dummyHead = new Node(null, null, null);
        dummyTail = new Node(null, null, null);
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
    }
    @NotNull
    private void addNodeAtEnd(Node<Key> node){
        dummyTail.prev.next = node;
        node.prev = dummyTail.prev;
        node.next = dummyTail;
        dummyTail.prev = node;
    }
    public Node addElementAtEnd(Key key){
        Node newNode = new Node<>(key, null, null);
        addNodeAtEnd(newNode);
        return newNode;
    }
    @NotNull
     public void deleteNode(Node<Key> node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
     }

     public Node getFirstNode(){
        if(!isListEmpty()) return dummyHead.next;
        return null;
     }

     private boolean isListEmpty(){
        return dummyHead.next == dummyTail;
     }
}
