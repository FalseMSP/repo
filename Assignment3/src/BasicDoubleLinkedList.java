import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class BasicDoubleLinkedList<T> {
	protected Node<T> head;
	protected Node<T> tail;
	protected int size = 0;
	
	public void addToEnd(T data) {
		Node<T> temp = new Node<T>(data);
		if (head == null || tail == null) {
			head = temp;
			tail = temp;
			size++;
			return;
		}
		temp.prev = tail;
		tail.next = temp;
		tail = temp;
		size++;
	}
	
	public void addToFront(T data) {
		Node<T> temp = new Node<T>(data);
		if (head == null || tail == null) {
			head = temp;
			tail = temp;
			size++;
			return;
		}
		temp.next = head;
		head = temp;
		temp.next.prev = temp;
		size++;
	}
	
	public T getFirst() {
		if (head != null)
			return head.data;
		return null;
	}
	
	public T getLast() {
		if (tail != null)
			return tail.data;
		return null;
	}
	
	public int getSize() {
		return size;
	}
	
	public ListIterator<T> iterator() {
		return new DoubleLinkedListIterator<T>(head);
	}
	
	public Node<T> remove(T item, Comparator<T> comp) {
		Node<T> temp = head;
		while (temp != null) {
			if (comp.compare(temp.data, item) == 0) {
				if (temp.prev != null)
					temp.prev.next = temp.next;
				else
					head = temp.next;
				if (temp.next != null)
                    temp.next.prev = temp.prev;
                else
                    tail = temp.prev;
				size--;
				return new Node<T>(item);
			}
			temp = temp.next;
		}
		return null;
	}
	
	public T retrieveFirstElement() {
		head.next.prev = null;
		T temp = head.data;
		head = head.next;
		size--;
		return temp;
	}
	
	public T retrieveLastElement() {
		if (tail.prev != null)
			tail.prev.next = null;
		T temp = tail.data;
		tail = tail.prev;
		size--;
		return temp;
	}
	
	public ArrayList<T> toArrayList() {
        ArrayList<T> list = new ArrayList<>(size);
        Node<T> current = head;

        while (current != null) {
            list.add(current.data);
            current = current.next;
        }

        return list;
    }
	
	// -------- INNER CLASSES -------
	public class Node<T> {
		T data;
		Node<T> prev = null;
		Node<T> next = null;
		public Node(T data) {
			this.data = data;
		}
	}
	
	// ----------- Iterator ----------
	private class DoubleLinkedListIterator<T> implements ListIterator<T> {
		Node<T> head;
		Node<T> prevNode;
		
		public DoubleLinkedListIterator(Node<T> head) {
			this.head = head;
		}
		@Override
		public boolean hasNext() {
			return head != null;
		}

		@Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            prevNode = head;
            T data = head.data;
            head = head.next;
            return data;
        }

		@Override
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			return prevNode != null;
		}

		@Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            head = prevNode;
            prevNode = head.prev;
            return head.data;
        }

		@Override
		public int nextIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public int previousIndex() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
			
		}

		@Override
		public void set(T e) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
			
		}

		@Override
		public void add(T e) throws UnsupportedOperationException {
			throw new UnsupportedOperationException();
		}

	}
	
}
