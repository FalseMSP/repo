import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

public class MyStackTest {
	public MyStack<String> stringS;
	public String a="a", b="b", c="c", d="d", e="e", f="f";
	public ArrayList<String> fill = new ArrayList<String>();
	
	// STUDENT: student tests will use the doubleS
	public MyStack<Double> doubleS;
	// STUDENT: add variables as needed for your student tests
	
	@BeforeEach
	public void setUp() throws Exception {
		stringS = new MyStack<String>(5);
		stringS.push(a);
		stringS.push(b);
		stringS.push(c);
		
		//STUDENT: add setup for doubleS for student tests
		doubleS = new MyStack<Double>(4);
		doubleS.push(3.0);
		doubleS.push(4.0);
		doubleS.push(5.0);
	}

	@AfterEach
	public void tearDown() throws Exception {
		stringS = null;
		doubleS = null;
	}

	@Test
	public void testIsEmpty() {
		assertEquals(false,stringS.isEmpty());
		try {
			stringS.pop();
			stringS.pop();
			stringS.pop();
		} catch (StackUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(true, stringS.isEmpty());
	}

	@Test
	public void testIsFull() {
		assertEquals(false, stringS.isFull());
		try {
			stringS.push(d);
			stringS.push(e);
		} catch (StackOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(true, stringS.isFull());
	}

	@Test
	public void testPop() {
		try {
			assertEquals(c, stringS.pop());
			assertEquals(b, stringS.pop());
			assertEquals(a, stringS.pop());
			//Queue is empty, next statement should cause QueueUnderFlowException
			stringS.pop();
			assertTrue("This should have caused an StackUnderflowException", false);
		}
		catch (StackUnderflowException e){
			assertTrue("This should have caused an StackUnderflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an StackUnderflowException", false);
		}
	}

	@Test
	public void testPopStudent() {
		assertEquals("3.0,4.0,5.0", doubleS.toString(","));
		try {
			doubleS.pop();
		} catch (StackUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("3.04.0", doubleS.toString());
	}
	
	@Test
	public void testTop() throws StackUnderflowException {
		assertEquals(c, stringS.top());
		try {
			stringS.push(d);
		} catch (StackOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(d, stringS.top());
		try {
			stringS.pop();
			stringS.pop();
		} catch (StackUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(b, stringS.top());		
	}

	@Test
	public void testSize() {
		assertEquals(3, stringS.size());
		try {
			stringS.push(d);
		} catch (StackOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(4, stringS.size());
		try {
			stringS.pop();
			stringS.pop();
		} catch (StackUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(2, stringS.size());
	}

	@Test
	public void testPush() {
		try {
			assertEquals(3, stringS.size());
			assertEquals(true, stringS.push(d));
			assertEquals(4, stringS.size());
			assertEquals(true, stringS.push(e));
			assertEquals(5, stringS.size());
			//Queue is full, next statement should cause QueueOverFlowException
			stringS.push(f);
			assertTrue("This should have caused an StackOverflowException", false);
		}
		catch (StackOverflowException e){
			assertTrue("This should have caused an StackOverflowException", true);
		}
		catch (Exception e){
			assertTrue("This should have caused an StackOverflowException", false);
		}
	}

	@Test
	public void testPushStudent() {
		assertEquals("3.0,4.0,5.0", doubleS.toString(","));
		try {
			doubleS.push(1.0);
		} catch (StackOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("3.04.05.01.0", doubleS.toString());
	}
	
	@Test
	public void testToString() {
		assertEquals("abc", stringS.toString());
		try {
			stringS.push(d);
		} catch (StackOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("abcd", stringS.toString());
		try {
			stringS.push(e);
		} catch (StackOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("abcde", stringS.toString());
	}

	@Test
	public void testToStringStudent() {
		assertEquals("3.0,4.0,5.0", doubleS.toString(","));
		try {
			doubleS.pop();
		} catch (StackUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("3.04.0", doubleS.toString());
	}
	
	@Test
	public void testToStringDelimiter() {
		assertEquals("a%b%c", stringS.toString("%"));
		try {
			stringS.push(d);
		} catch (StackOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("a&b&c&d", stringS.toString("&"));
		try {
			stringS.push(e);
		} catch (StackOverflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("a/b/c/d/e", stringS.toString("/"));
	}

	@Test
	public void testFill() {
		fill.add("apple");
		fill.add("banana");
		fill.add("carrot");
		//start with an empty queue
		stringS = new MyStack<String>(5);
		//fill with an ArrayList
		stringS.fill(fill);
		assertEquals(3,stringS.size());
		try {
			assertEquals("carrot", stringS.pop());
			assertEquals("banana", stringS.pop());
			assertEquals("apple", stringS.pop());		
		} catch (StackUnderflowException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
