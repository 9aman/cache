package evictionpolicy;

import java.util.HashMap;
import java.util.List;

public class LRUEviction<Key> implements EvictionPolicy<Key> {
    private DoublyLinkedList doublyLinkedList;
    private HashMap<Key, Node> keyToNodeMap;

    public LRUEviction() {
        this.doublyLinkedList = new DoublyLinkedList<>();
        this.keyToNodeMap = new HashMap<>();
    }

    @Override
    public Key evictKey() {
        Node<Key> nodeToEvict = doublyLinkedList.getFirstNode();
        if(nodeToEvict == null) return null;
        doublyLinkedList.deleteNode(nodeToEvict);
        return nodeToEvict.getValue();
    }

    @Override
    public void keyAccessed(Key key) {
        if(keyToNodeMap.containsKey(key)) doublyLinkedList.deleteNode(keyToNodeMap.get(key));
        keyToNodeMap.put(key, doublyLinkedList.addElementAtEnd(key));
    }
}
