package evictionpolicy;

import lombok.Getter;

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
