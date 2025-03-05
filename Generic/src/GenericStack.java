
class GenericStack<E extends Comparable<E>> {
    private Node<E> top; 

    // nested Node class
    private static class Node<E> {
        private E data; 
        private Node<E> next; 

        public Node(E data) {
            this.data = data;
            this.next = null;
        }
    }
    public GenericStack() {
        top = null;
    }

    // method to push an element to the stack
    public void push(E element) {
        Node<E> newNode = new Node<>(element); 
        newNode.next = top; 
        top = newNode;
    }

    // method to pop an element from the stack
    public E pop() {
        if (isEmpty()) {
            return null;
        }
        E element = top.data;
        top = top.next; 
        return element; 
    }

    // method to peek the top element of the stack
    public E peek() {
        if (isEmpty()) {
            return null; 
        }
        return top.data; 
    }

    // method to check if the stack is empty
    public boolean isEmpty() {
        return top == null; 
    }

    // method to get the size of the stack
    public int size() {
        int count = 0; 
        Node<E> current = top;
        while (current != null) {
            count++; 
            current = current.next; 
        }
        return count; 
    }
}