/*Used ChatGPT prompt:
 * make this jdoc into a prototype for a class (with javadoc comments)
Class Notation
java.lang.Object
Notation
public class Notation
extends java.lang.Object
A utility class that converts an infix expression to a postfix expression, a postfix expression to an infix expression and evaluates a postfix expression.
Constructor Summary
Constructors 
Constructor	Description
Notation()	 
Method Summary
All Methods Static Methods Concrete Methods 
Modifier and Type	Method	Description
static java.lang.String	convertInfixToPostfix​(java.lang.String infix)	
Convert an infix expression into a postfix expression
static java.lang.String	convertPostfixToInfix​(java.lang.String postfix)	
Convert the Postfix expression to the Infix expression
static double	evaluatePostfixExpression​(java.lang.String postfixExpr)	
Evaluates a postfix expression from a string to a double
Methods inherited from class java.lang.Object
equals, getClass, hashCode, notify, notifyAll, toString, wait, wait, wait
Constructor Detail
Notation
public Notation()
Method Detail
evaluatePostfixExpression
public static double evaluatePostfixExpression​(java.lang.String postfixExpr)
                                        throws InvalidNotationFormatException
Evaluates a postfix expression from a string to a double
Parameters:
postfixExpr - the postfix expression in String format
Returns:
the evaluation of the postfix expression as a double
Throws:
InvalidNotationFormatException - if the postfix expression format is invalid
convertPostfixToInfix
public static java.lang.String convertPostfixToInfix​(java.lang.String postfix)
                                              throws InvalidNotationFormatException
Convert the Postfix expression to the Infix expression
Parameters:
postfix - the postfix expression in string format
Returns:
the infix expression in string format
Throws:
InvalidNotationFormatException - if the postfix expression format is invalid
convertInfixToPostfix
public static java.lang.String convertInfixToPostfix​(java.lang.String infix)
                                              throws InvalidNotationFormatException
Convert an infix expression into a postfix expression
Parameters:
infix - the infix expression in string format
Returns:
the postfix expression in string format
Throws:
InvalidNotationFormatException - if the infix expression format is invalid
 * 
 * To generate prototype with Jdocs because I was too lazy to copy/paste from jdoc into this file lol, if this isn't allowed lmk so I can waste my time writing ts by hand.
 */

/**
 * A utility class that provides methods for converting between infix and postfix expressions 
 * and evaluating a postfix expression.
 * <p>
 * This class supports the following functionalities:
 * <ul>
 * <li>Converting an infix expression to a postfix expression</li>
 * <li>Converting a postfix expression to an infix expression</li>
 * <li>Evaluating a postfix expression</li>
 * </ul>
 * </p>
 * <p>
 * The methods throw an {@link InvalidNotationFormatException} if the expression format is invalid.
 * </p>
 * 
 * @author Mark Philip
 * @version 1.0
 */

public class Notation {
	/**
	 * Returns the priority of the PEMDAS
	 * 
	 * @param c - the operator that needs to get order defined of
	 * @return Priority of the operator
	 */
	private static int pemdasOrder(char c) {
        if (c == '^') // E
            return 3;
        else if (c == '/' || c == '*')  // MD
            return 2;
        else if (c == '+' || c == '-') // AS
            return 1;
        else
            return -1;
    }
	
    /**
     * Default constructor for the Notation class.
     * This constructor is used to create an instance of the Notation utility class.
     */
    public Notation() {
        // Constructor implementation here (if needed)
    }

    /**
     * Converts an infix expression to a postfix expression.
     * <p>
     * The method takes an infix expression as input and returns the corresponding postfix 
     * expression.
     * </p>
     * 
     * @param infix The infix expression as a string.
     * @return The postfix expression as a string.
     * @throws InvalidNotationFormatException If the infix expression format is invalid.
     */
    public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
    	MyQueue<String> queue = new MyQueue<String>(infix.length());
    	MyStack<Character> stack = new MyStack<Character>(infix.length());
    	for (int i = 0; i < infix.length(); i++) {
    		char c = infix.charAt(i);
    		if (c == ' ') {
    			continue;
    		} if (Character.isDigit(c)) {
    			try {
					queue.enqueue(Character.toString(c));
				} catch (QueueOverflowException e) {
					throw new InvalidNotationFormatException("Invalid Format");
				}
    		} else if (c == '(') {
    			try {
					stack.push(c);
				} catch (StackOverflowException e) {
					throw new InvalidNotationFormatException("Invalid Format");
				}
    		} else if (pemdasOrder(c) != -1) {
    			while (!stack.isEmpty()) {
    				try {
						if (pemdasOrder(stack.top()) <= pemdasOrder(c)) {
							break;
						}
						queue.enqueue(String.valueOf(stack.pop()));
    				} catch (StackUnderflowException e) {
    					throw new InvalidNotationFormatException("Invalid Format");
					} catch (QueueOverflowException e) {
						throw new InvalidNotationFormatException("Invalid Format");
					}
    			}
    			try {
					stack.push(c);
				} catch (StackOverflowException e) {
					throw new InvalidNotationFormatException("Invalid Format");
				}
    		} else if (c == ')') {
    			char temp;
				try {
					temp = stack.pop();
	    			while (temp != '(') {
	    				queue.enqueue(String.valueOf(temp));
	    				temp = stack.pop();
	    			}
				} catch (StackUnderflowException e) {
					throw new InvalidNotationFormatException("Invalid Format");
				} catch (QueueOverflowException e) {
					throw new InvalidNotationFormatException("Invalid Format");
				}
    		}
    	}
    	if (stack.size() != 0) throw new InvalidNotationFormatException("Invalid Format");
		return queue.toString();
    }

    /**
     * Converts a postfix expression to an infix expression.
     * <p>
     * The method takes a postfix expression as input and returns the corresponding infix 
     * expression.
     * </p>
     * 
     * @param postfix The postfix expression as a string.
     * @return The infix expression as a string.
     * @throws InvalidNotationFormatException If the postfix expression format is invalid.
     */
    public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
    	MyStack<String> stack = new MyStack<String>(postfix.length());
    	for (int i = 0; i < postfix.length(); i++) {
    		char c = postfix.charAt(i);
    		if (c == ' ') {
    			continue;
    		} if (Character.isDigit(c)) {
    			try {
					stack.push(Character.toString(c));
				} catch (StackOverflowException e) {
					// TODO Auto-generated catch block
					throw new InvalidNotationFormatException("Invalid Format");
				}
    		} else {
    			try {
					String operand1 = stack.pop();
					String operand2 = stack.pop();
					String res = "(" + operand2 + c + operand1 + ")";
					stack.push(res);
				} catch (StackOverflowException e) {
					throw new InvalidNotationFormatException("Invalid Format");
				} catch (StackUnderflowException e) {
					throw new InvalidNotationFormatException("Invalid Format");
				}
    		}
    	}
    	try {
    		if (stack.size() != 1) throw new InvalidNotationFormatException("Invalid Format");
			return stack.top();
		} catch (StackUnderflowException e) {
			throw new InvalidNotationFormatException("Invalid Format");
		}
    }

    /**
     * Evaluates a postfix expression and returns the result as a double.
     * <p>
     * The method processes the postfix expression and computes its result.
     * </p>
     * 
     * @param postfixExpr The postfix expression as a string.
     * @return The evaluation of the postfix expression as a double.
     * @throws InvalidNotationFormatException If the postfix expression format is invalid.
     */
    public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {
    	MyStack<String> stack = new MyStack<String>(postfixExpr.length());
    	for (int i = 0; i < postfixExpr.length(); i++) {
    		char c = postfixExpr.charAt(i);
    		if (c == ' ') {
    			continue;
    		} if (Character.isDigit(c) || c == '(') {
    			try {
					stack.push(Character.toString(c));
				} catch (StackOverflowException e) {
					throw new InvalidNotationFormatException("Invalid Format");
				}
    		} else {
    			try {
					double operand1 = Double.parseDouble(stack.pop());
					double operand2 = Double.parseDouble(stack.pop());
					double res;
					if (c == '+') {
						res = operand2 + operand1;
					} else if (c == '-') {
						res = operand2 - operand1;
					} else if (c == '*') {
						res = operand2 * operand1;
					} else if (c == '/') {
						res = operand2 / operand1;
					} else if (c == '^') {
						res = Math.pow(operand2, operand1);
					} else {
						throw new InvalidNotationFormatException("Invalid Format");
					}
					stack.push(String.valueOf(res));
				} catch (StackOverflowException e) {
					throw new InvalidNotationFormatException("Invalid Format");
				} catch (StackUnderflowException e) {
					throw new InvalidNotationFormatException("Invalid Format");
				}
    		}
    	}
    	try {
    		if (stack.size() != 1) throw new InvalidNotationFormatException("Invalid Format");
			return Double.parseDouble(stack.top());
		} catch (StackUnderflowException e) {
			throw new InvalidNotationFormatException("Invalid Format");
		}
    }

}


class InvalidNotationFormatException extends Exception {
    public InvalidNotationFormatException(String message) {
        super(message);
    }
}