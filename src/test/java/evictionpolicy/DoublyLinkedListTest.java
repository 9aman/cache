package evictionpolicy;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {

    @Test
    void addElementAtEnd() {
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
        List<Integer> stlList = new LinkedList<>();
        for(int i=0;i<5;i++){
            dll.addElementAtEnd(i);
            stlList.add(i);
            verifyDLL(dll, stlList);
        }
    }

    @Test
    void deleteNode() {
        Node<Integer> node1 = new Node<>(1, null, null);
        Node<Integer> node2 = new Node<>(2, null, null);
        Node<Integer> node3 = new Node<>(3, null, null);
        Node<Integer> node4 = new Node<>(4, null, null);
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();
        dll.addNodeAtEnd(node1);
        dll.addNodeAtEnd(node2);
        dll.addNodeAtEnd(node3);
        dll.addNodeAtEnd(node4);
        verifyDLL(dll, ImmutableList.of(1, 2, 3, 4));
        dll.deleteNode(node2);
        verifyDLL(dll, ImmutableList.of(1, 3, 4));
        dll.deleteNode(node1);
        verifyDLL(dll, ImmutableList.of(3, 4));
        dll.deleteNode(node3);
        verifyDLL(dll, ImmutableList.of(4));
        dll.deleteNode(node4);
        assertNull(dll.getFirstNode());
    }

    private void verifyDLL(DoublyLinkedList<Integer> dll, List<Integer> expectedDll){
        Node<Integer> currentNode = dll.getFirstNode();
        for(Integer expectedNodeValue: expectedDll){
            assertNotNull(currentNode);
            assertEquals(currentNode.getValue(), expectedNodeValue);
            currentNode = currentNode.getNext();
        }
        assertNull(currentNode.next);
    }
}