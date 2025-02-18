import java.util.ArrayList;

/**
 * A generic queue implementation that provides basic queue operations such as enqueue, dequeue,
 * checking if the queue is empty or full, and returning the size of the queue.
 * 
 * @param <T> the type of elements in the queue
 */
public class MyQueue<T> implements QueueInterface<T> {
	private T[] queue;
    private int front, rear, size, capacity;

    /**
     * Default Constructor
     * 
     * @param capacity
     */
    public MyQueue(int capacity) {
        this.capacity = capacity;
        queue = (T[]) new Object[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }
	
	/**
	 *@return true if queue is empty
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	/**
	 *@return true if queue is full
	 */
	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return size == capacity;
	}

	/**
	 * Returns first element in queue
	 * 
	 *@return front of queue element
	 *@throws QueueUnderflowException if queue is empty
	 */
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

	/**
	 *@return size of queue
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	/**
	 * Adds element to queue
	 * 
	 *@param T element into queue
	 *@return true if queue isn't full
	 *@throws QueueOverflowException if queue is full
	 */
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

	/**
     * Returns a string representation of the queue, with the elements separated by the specified delimiter.
     *
     * @param delimiter the delimiter to be used between elements
     * @return a string representation of the queue
     */
	@Override
	public String toString(String delimiter) {
	    String result = "";
	    for (int i = 0; i < size; i++) {
	        result += queue[(front + i) % capacity] + delimiter;
	    }
	    if (result.equals("")) return "";
	    return result.substring(0, result.length() - delimiter.length()); // remove last character from string
	}
	
	/**
     * Standard toString with no delimiter
     *
     * @return a string representation of the queue
     */
	public String toString() {
		// TODO Auto-generated method stub
		return toString("");
	}
	/**
     * Fills the queue with elements from the specified list.
     *
     * @param list the list of elements to be added to the queue
     */
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