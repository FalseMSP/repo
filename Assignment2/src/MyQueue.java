import java.util.ArrayList;

public class MyQueue<T> implements QueueInterface<T> {
	private T[] queue;
    private int front, rear, size, capacity;

    public MyQueue(int capacity) {
        this.capacity = capacity;
        queue = (T[]) new Object[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return size == capacity;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) {
            throw new QueueUnderflowException("Queue is empty");
        }
        T element = queue[front];
        front = (front + 1) % capacity;
        size--;
        return element;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean enqueue(T e) throws QueueOverflowException {
		if (size == capacity) {
            throw new QueueOverflowException("Queue is full");
        }
        queue[rear] = e;
        rear = (rear + 1) % capacity;
        size++;
        return true;
	}

	@Override
	public String toString(String delimiter) {
	    String result = "";
	    for (int i = 0; i < size; i++) {
	        result += queue[(front + i) % capacity] + delimiter;
	    }
	    if (result.equals("")) return "";
	    return result.substring(0, result.length() - delimiter.length()); // remove last character from string
	}
	
	public String toString() {
		// TODO Auto-generated method stub
		return toString("");
	}

	@Override
	public void fill(ArrayList<T> list) {
		for (T i : list) {
			try {
				enqueue(i);
			} catch (QueueOverflowException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
    
}
class QueueUnderflowException extends Exception {
    public QueueUnderflowException(String message) {
        super(message);
    }
}

class QueueOverflowException extends Exception {
    public QueueOverflowException(String message) {
        super(message);
    }
}