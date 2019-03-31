/*
 * 
 */
package com.pyt.test;

import java.util.Scanner;

/**
 * The Class MinimumUinqueSumArray.
 *
 * @author suresh
 */
public class MinimumUniqueSumArray {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in)) {
			boolean isValidInputs = true;
			System.out.println("Enter n value");
			int n = sc.nextInt();
			if (n >= 1 && n <= 2000) {
				int values[] = new int[n];
				System.out.println("Enter values");
				for (int i = 0; i < n; i++) {
					values[i] = sc.nextInt();
					if (values[i] < 1 || values[i] > 3000) {
						System.out.println("Input constarints violation, Input value is between 1 to 3000");
						isValidInputs = false;
						break;
					}
				}
				if (isValidInputs) {
					int minimumUniqueSum = getMinimumUniqueSum(values);
					System.out.println("The minimum unique sum of given values :" + minimumUniqueSum);
				}
			} else {
				System.out.println("Input constarints violation, Input value is between 1 to 2000");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Gets the minimum unique sum. Iterate values perform following steps on each
	 * value find duplicates If duplicates present then increment it by 1 and check
	 * result also present in values If not present then set the result to the
	 * duplicate value position If present then repeat the steps until it is not
	 * present in values
	 * 
	 * @param values
	 *            the values
	 * @return the minimum unique sum
	 */
	public static int getMinimumUniqueSum(int[] values) throws Exception {
		try {
			for (int i = 0; i < values.length; i++) {
				int duplicateCount = 0;
				for (int j = 0; j < values.length; j++) {
					if (values[j] == values[i]) {
						duplicateCount++;
					}
					if (duplicateCount > 1) {
						boolean isExist = false;
						int temp = values[j];
						do {
							temp++;
							isExist = isExist(values, temp);

						} while (isExist);
						values[j] = temp;
						duplicateCount--;
					}
				}
			}

			System.out.print("Unique Array is");

			display(values);
			return sum(values);
		} catch (Exception e) {
			System.out.print(e.getMessage());
			throw e;
		}
	}

	/**
	 * Checks if is exist.
	 *
	 * @param values
	 *            the values
	 * @param checkValue
	 *            the check value
	 * @return true, if is exist
	 */
	public static boolean isExist(int[] values, int checkValue) {
		for (int value : values) {
			if (value == checkValue) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Display the values.
	 *
	 * @param values
	 *            the values
	 */
	public static void display(int[] values) {
		for (int value : values) {
			System.out.print(value + " ");
		}
		System.out.println();
	}

	/**
	 * Get total of all values
	 *
	 * @param values
	 *            the values
	 * @return the int
	 */
	public static int sum(int[] values) {
		int sum = 0;
		for (int value : values) {
			sum = value + sum;
		}
		return sum;
	}
}
