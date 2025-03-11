import java.util.Comparator;
import java.util.ListIterator;

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {

    // Constructor that takes a comparator to ensure sorted insertion
    public SortedDoubleLinkedList(Comparator<T> comparableObject) {
        super(); // Call the constructor of the parent class to initialize the list
        this.comparator = comparableObject;
    }

    private Comparator<T> comparator;

    public void add(T data) {
        if (getSize() == 0) {
            Node<T> temp = new Node<T>(data);
            head = temp;
            tail = temp;
            size++;
            return;
        }
        if (comparator.compare(data, head.data) <= 0) {
        	super.addToFront(data);
        	return;
        }
        else if (comparator.compare(data, tail.data) >= 0) {
        	super.addToEnd(data);
        	return;
        }
        Node<T> current = head;
        while (current != null && comparator.compare(data, current.data) > 0) {
            current = current.next;
        }
        
        Node<T> newNode = new Node<T>(data);
        Node<T> prev = current.prev;
        newNode.next = current;
        current.prev = newNode;

        prev.next = newNode;
        newNode.prev = prev;
        
        size++;
    }

    @Override
    public void addToEnd(T data) {
        throw new UnsupportedOperationException("Invalid operation for sorted list");
    }

    @Override
    public void addToFront(T data) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<T> iterator() {
        return super.iterator();
    }

    @Override
    public Node<T> remove(T data, Comparator<T> comparator) {
        return super.remove(data,comparator);
    }
}
