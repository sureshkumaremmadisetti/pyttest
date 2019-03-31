package com.pyt.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Class SuperStackArrayList.
 *
 * @author suresh
 */
public class SuperStackArrayList {

	/** The top. */
	private int top = -1;

	/** The stack. */
	private List<Long> stack = null;

	/**
	 * Instantiates a new super stack array list.
	 */
	SuperStackArrayList() {
		stack = new ArrayList<>();
	}

	/**
	 * Push.
	 *
	 * @param value
	 *            the value
	 * @return true, if successful
	 */
	public boolean push(long value) {
		this.stack.add(++this.top, value);
		return true;
	}

	/**
	 * Pop.
	 *
	 * @return true, if successful
	 */
	public boolean pop() {
		boolean isPoped = false;
		if (!this.stack.isEmpty()) {
			this.stack.remove(this.top--);
			isPoped = true;
		}
		return isPoped;
	}

	/**
	 * Gets the top value.
	 *
	 * @return the top value
	 */
	public String getTopValue() {
		if (!this.stack.isEmpty())
			return String.valueOf(this.stack.get(top));
		return "EMPTY";
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		SuperStackArrayList s = new SuperStackArrayList();
		try (Scanner sc = new Scanner(System.in)) {
			InputStreamReader r = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(r);

			System.out.println("Enter n value");
			int n = sc.nextInt();
			if (n >= 1 && n <= (2 * Math.pow(10, 5))) {
				String operations[] = new String[n];
				System.out.println("Enter operations");
				for (int i = 0; i < n; i++) {
					operations[i] = br.readLine();
					if (!s.isValidOperation(operations[i])) {
						System.out.println("Input constarints violation, Input operation push,pop, inc");
						break;
					}
				}
				s.processSuperStackOperations(operations);
			} else {
				System.out.println("Input constarints violation, Input value is between 1 to 200000");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Process super stack operations. The operations in the form push value, pop,
	 * inc value1 value2 It extracts the stack operation, values from the operations
	 * and then execute it.
	 *
	 * @param operations
	 *            the operations
	 */
	public void processSuperStackOperations(String[] operations) throws Exception {
		try {
			for (String operation : operations) {
				String[] valueFromOperation = this.getValueFromOperation(operation);
				if (valueFromOperation != null && valueFromOperation.length >= 1) {
					if ("push".equalsIgnoreCase(valueFromOperation[0])) {
						this.push(Long.parseLong(valueFromOperation[1]));
						System.out.println(this.getTopValue());
					} else if ("pop".equalsIgnoreCase(valueFromOperation[0])) {
						this.pop();
						System.out.println(this.getTopValue());
					} else if ("inc".equalsIgnoreCase(valueFromOperation[0])) {
						this.performIncrementOperation(Long.parseLong(valueFromOperation[1]),
								Long.parseLong(valueFromOperation[2]));
						System.out.println(this.getTopValue());
					}
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
			throw e;
		}
	}

	/**
	 * Perform increment operation.
	 *
	 * @param noOfElements
	 *            the no of elements
	 * @param value
	 *            the value
	 * @return true, if successful
	 */
	public boolean performIncrementOperation(long noOfElements, long value) throws Exception {
		try {
			if ((noOfElements >= 1 && noOfElements <= this.stack.size())
					&& (value >= -Math.pow(10, 9) && value <= Math.pow(10, 9))) {
				for (int i = 0; i < noOfElements; i++) {
					this.stack.set(i, this.stack.get(i) + value);
				}
				return true;
			} else {
				System.out.println("Constraints violation for inc operation");
				return false;
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
			throw e;
		}
	}

	/**
	 * Checks if is valid operation.
	 *
	 * @param operation
	 *            the operation
	 * @return true, if is valid operation
	 */
	public boolean isValidOperation(String operation) {
		boolean isValidOperation = false;
		// System.out.println("operation:" + operation);
		if (operation != null) {
			if (operation.toLowerCase().startsWith("push")) {
				isValidOperation = true;
			} else if (operation.toLowerCase().startsWith("pop")) {
				isValidOperation = true;
			} else if (operation.toLowerCase().startsWith("inc")) {
				isValidOperation = true;
			}
		}
		return isValidOperation;
	}

	/**
	 * Gets the value from operation.
	 *
	 * @param operation
	 *            the operation
	 * @return the value from operation
	 */
	public String[] getValueFromOperation(String operation) {
		if (operation != null) {
			return operation.toLowerCase().split(" ");
		} else {
			return null;
		}
	}

	/**
	 * Display.
	 */
	public void display() {
		System.out.println(this.stack);
	}
}
