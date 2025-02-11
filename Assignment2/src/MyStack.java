import java.util.ArrayList;
import java.util.Arrays;

public class MyStack<T> implements StackInterface<T> {
	private int top, capacity;
	private T[] stack;
	
	public MyStack(int capacity) {
        this.capacity = capacity;
        stack = (T[]) new Object[capacity];
        top = -1;
    }
	
	@Override
	public boolean isEmpty() {
		return top == -1;
	}

	@Override
	public boolean isFull() {
		return top == capacity - 1;
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty()) {
            throw new StackUnderflowException("Stack is empty");
        }
		return stack[top--];
	}

	@Override
	public T top() {
		return stack[top];
	}

	@Override
	public int size() {
		return top + 1;
	}

	@Override
	public boolean push(T e) throws StackOverflowException {
		if (isFull()) {
            throw new StackOverflowException("Stack is full");
        }
        stack[++top] = e;
        return true;
	}

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
	
	public String toString() {
	    return toString("");
	}

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