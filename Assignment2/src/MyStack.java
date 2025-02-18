import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Mark Philip
 * A generic stack implementation that provides basic stack operations such as push, pop, 
 * top, checking if the stack is empty or full, and returning the size of the stack.
 *
 * @param <T> the type of elements in the stack
 */
public class MyStack<T> implements StackInterface<T> {
	private int top, capacity;
	private T[] stack;
	
	/**
	 * @param capacity
	 */
	public MyStack(int capacity) {
        this.capacity = capacity;
        stack = (T[]) new Object[capacity];
        top = -1;
    }
	
	/**
	 * Returns true if stack is empty
	 */
	@Override
	public boolean isEmpty() {
		return top == -1;
	}

	/**
	 * Returns true if full
	 */
	@Override
	public boolean isFull() {
		return top == capacity - 1;
	}

	/**
	 * returns top of stack and removes it from the stack.
	 * @return T
	 * @throws StackUnderflowException
	 */
	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty()) {
            throw new StackUnderflowException("Stack is empty");
        }
		return stack[top--];
	}

	/**
	 * returns top of stack
	 * @return T - returns top of stack
	 * @throws StackUnderflowException
	 */
	@Override
	public T top() throws StackUnderflowException {
		if (isEmpty()) {
			throw new StackUnderflowException("Stack is empty");
		}
		return stack[top];
	}

	/**
	 * returns size of stack
	 * @return size
	 */
	@Override
	public int size() {
		return top + 1;
	}

	/**
	 * Pushes T onto stack
	 * 
	 * @param T element
	 * @return true if pushed successfully
	 * @throws StackOverflowException
	 */
	@Override
	public boolean push(T e) throws StackOverflowException {
		if (isFull()) {
            throw new StackOverflowException("Stack is full");
        }
        stack[++top] = e;
        return true;
	}

	/**
	 * Standard toString method for stack
	 * 
	 * @param delim the delimiter to be used between elements
     * @return a string representation of the stack
	 */
	@Override
	public String toString(String delim) {
	    String result = "";
	    boolean firstElement = true;

	    for (int i = 0; i <= top; i++) {
	    	T element = stack[i];
	        if (element != null) {
	            if (!firstElement) {
	                result += delim;
	            }
	            result += element.toString();
	            firstElement = false;
	        }
	    }
	    return result;
	}
	
	
	/**
	 * Default toString method
	 * 
	 *@return string representation of stack with no delimiter
	 */
	public String toString() {
	    return toString("");
	}

	/**
     * Fills the stack with elements from the specified list.
     *
     * @param ArrayList T of elements to be added to the stack
	 */
	@Override
	public void fill(ArrayList<T> list) {
		// TODO Auto-generated method stub
		for (T i : list) {
			try {
				push(i);
			} catch (StackOverflowException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

class StackUnderflowException extends Exception {
    public StackUnderflowException(String message) {
        super(message);
    }
}

class StackOverflowException extends Exception {
    public StackOverflowException(String message) {
        super(message);
    }
}