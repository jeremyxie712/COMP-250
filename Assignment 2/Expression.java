package assignments2017.A2PostedCode;

/*
    Name: Jeremy Xie 
    McGill ID: 260660974
    Course: COMP250 
    Instructor: Michael Langer 
 */

import java.util.Stack;
import java.util.ArrayList;
import java.util.List;

public class Expression {
	private ArrayList<String> tokenList;

	// Constructor
	/**
	 * The constructor takes in an expression as a string and tokenizes it (breaks
	 * it up into meaningful units) These tokens are then stored in an array list
	 * 'tokenList'.
	 */

	Expression(String expressionString) throws IllegalArgumentException {
		tokenList = new ArrayList<String>();
		StringBuilder token = new StringBuilder();

		// ADD YOUR CODE BELOW HERE
		// ..
		for (int i = 0; i < expressionString.length(); i++) {
			char c = expressionString.charAt(i);
			if (c == '(') {
				tokenList.add(Character.toString(c));
			} else if (c == '+') {
				if (expressionString.charAt(i + 1) == '+') {
					tokenList.add("++");
					i++;
				} else {
					tokenList.add(Character.toString(c));
				}
			} else if (c == '-') {
				if (expressionString.charAt(i + 1) == '-') {
					tokenList.add("--");
					i++;
				} else {
					tokenList.add(Character.toString(c));
				}
			} else if (c == '*') {
				tokenList.add(Character.toString(c));
			} else if (c == '/') {
				tokenList.add(Character.toString(c));
			} else if (c == '[') {
				tokenList.add(Character.toString(c));
			} else if (c == ']') {
				tokenList.add(Character.toString(c));
			} else if (c == ' ') {
				continue;
			} else if (isInteger(Character.toString(c))) {
				int oldIndex = i;
				while (isInteger(Character.toString(expressionString.charAt(i + 1)))) {
					i++;
				}
				String s1 = expressionString.substring(oldIndex, i + 1);
				tokenList.add(s1);
			} else if (expressionString.charAt(i) == ')') {
				tokenList.add(Character.toString(c));
			}
		}

		// ..
		// ADD YOUR CODE ABOVE HERE
	}

	/**
	 * This method evaluates the expression and returns the value of the expression
	 * Evaluation is done using 2 stack ADTs, operatorStack to store operators and
	 * valueStack to store values and intermediate results. - You must fill in code
	 * to evaluate an expression using 2 stacks
	 */
	public Integer eval() {
		Stack<String> operatorStack = new Stack<String>();
		Stack<Integer> valueStack = new Stack<Integer>();

		// ADD YOUR CODE BELOW HERE
		// ..
		Integer result = 0;
		for (int j = 0; j < tokenList.size(); j++) {
			if (isInteger(tokenList.get(j))) {
				valueStack.push(Integer.parseInt(tokenList.get(j)));
			} else if (!isInteger(tokenList.get(j)) && !"(".equals(tokenList.get(j)) && !")".equals(tokenList.get(j))
					&& !"[".equals(tokenList.get(j)) && !"]".equals(tokenList.get(j))) {
				operatorStack.push(tokenList.get(j));
			}

			if (tokenList.get(j).equals(")") && !operatorStack.isEmpty()) {
				if (operatorStack.peek().equals("+")) {
					operatorStack.pop();
					result = valueStack.pop() + valueStack.pop();
					valueStack.push(result);
				} else if (operatorStack.peek().equals("*")) {
					operatorStack.pop();
					result = valueStack.pop() * valueStack.pop();
					valueStack.push(result);
				} else if (operatorStack.peek().equals("-")) {
					operatorStack.pop();
					Integer tempOne = valueStack.pop();
					Integer tempTwo = valueStack.pop();
					result = tempTwo - tempOne;
					valueStack.push(result);
				} else if (operatorStack.peek().equals("/")) {
					operatorStack.pop();
					Integer tempThree = valueStack.pop();
					Integer tempFour = valueStack.pop();

					if (tempThree == 0) {
						throw new ArithmeticException("Divisor cannot be zero");
					}

					result = tempFour / tempThree;
					valueStack.push(result);
				} else if (operatorStack.peek().equals("++")) {
					operatorStack.pop();
					result = valueStack.pop() + 1;
					valueStack.push(result);
				} else if (operatorStack.peek().equals("--")) {
					operatorStack.pop();
					result = valueStack.pop() - 1;
					valueStack.push(result);
				}
			} else if (tokenList.get(j).equals("]")) {
				result = Math.abs(valueStack.pop());
				valueStack.push(result);
			}

		}
		return result;

		// ..
		// ADD YOUR CODE ABOVE HERE

	}

	// Helper methods
	/**
	 * Helper method to test if a string is an integer Returns true for strings of
	 * integers like "456" and false for string of non-integers like "+" - DO NOT
	 * EDIT THIS METHOD
	 */
	private boolean isInteger(String element) {
		try {
			Integer.valueOf(element);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * Method to help print out the expression stored as a list in tokenList. - DO
	 * NOT EDIT THIS METHOD
	 */

	@Override
	public String toString() {
		String s = new String();
		for (String t : tokenList)
			s = s + "~" + t;
		return s;
	}

}
