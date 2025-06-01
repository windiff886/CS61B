package deque;

public class LinkedListDeque<T> {

    private class Node {
        private T data;
        private Node next;
        private Node prev;

        private Node(T data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel; // Points to itself
        sentinel.prev = sentinel; // Points to itself
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void addFirst(T item) {
        Node newNode = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    public void addLast(T item) {
        Node newNode = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    public T get(int index){
        if (index < 0 || index >= size) {
            return null; // Index out of bounds
        }
        Node current = sentinel.next;
        while( index != 0){
            current = current.next; // Move to the next node
            index--; // Decrement index
        }
        return current.data; // Return the data at the current node
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null; // Index out of bounds
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node current, int index) {
        if (index == 0) {
            return current.data; // Base case: return the data at the current node
        }
        return getRecursiveHelper(current.next, index - 1); // Recursive case: move to the next node
    }
}
