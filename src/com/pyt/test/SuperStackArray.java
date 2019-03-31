package com.pyt.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * The Class SuperStackArray.
 *
 * @author suresh
 */
public class SuperStackArray {

	/** The top. */
	private int top = -1;

	/** The size. */
	private long size = 0;

	/** The stack. */
	private long[] stack = null;

	/**
	 * Instantiates a new super stack array.
	 *
	 * @param height
	 *            the height
	 */
	SuperStackArray(int height) {
		stack = new long[height];
	}

	/**
	 * Push.
	 *
	 * @param value
	 *            the value
	 * @return true, if successful
	 */
	public boolean push(long value) {
		boolean isPushed = false;
		if (!this.isFull()) {
			this.stack[++this.top] = value;
			this.size++;
			isPushed = true;
		}
		return isPushed;
	}

	/**
	 * Pop.
	 *
	 * @return true, if successful
	 */
	public boolean pop() {
		boolean isPoped = false;
		if (!this.isEmpty()) {
			this.stack[this.top--] = 0;
			this.size--;
			isPoped = true;
		}
		return isPoped;
	}

	/**
	 * Checks if is full.
	 *
	 * @return true, if is full
	 */
	public boolean isFull() {
		if (this.size == this.stack.length)
			return true;
		return false;
	}

	/**
	 * Checks if is empty.
	 *
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		if (this.size == 0)
			return true;
		return false;
	}

	/**
	 * Gets the top value.
	 *
	 * @return the top value
	 */
	public String getTopValue() {
		if (!this.isEmpty())
			return String.valueOf(this.stack[top]);
		return "EMPTY";
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		SuperStackArray s = new SuperStackArray(200000);
		try (Scanner sc = new Scanner(System.in)) {
			InputStreamReader r = new InputStreamReader(System.in);
			BufferedReader br = new BufferedReader(r);

			System.out.println("Enter n value");
			int n = sc.nextInt();

			if (n >= 1 && n <= (2 * Math.pow(10, 5))) {
				String operations[] = new String[n];
				System.out.println("Enter operations");
				for (int i = 0; i < n; i++) {
					operations[i] = br.readLine();// sc.nextLine();
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
	 * Process super stack operations.
	 *
	 * @param operations
	 *            the operations
	 * @throws Exception 
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
			if ((noOfElements >= 1 && noOfElements <= this.size)
					&& (value >= -Math.pow(10, 9) && value <= Math.pow(10, 9))) {
				for (int i = 0; i < noOfElements; i++) {
					this.stack[i] = this.stack[i] + value;
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
		System.out.println("operation:" + operation);
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
		for (int i = 0; i < this.size; i++) {
			System.out.println(this.stack[i]);
		}
	}
}
