package com.pyt.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * The Class CountedPairs.
 *
 * @author suresh
 */
public class CountedPairs {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		// Read input values using scanner class
		try (Scanner sc = new Scanner(System.in)) {
			boolean isValidInputs = true;
			System.out.println("Enter number n value");
			int n = sc.nextInt();
			if (n >= 1 && n <= (2 * Math.pow(10, 5))) {
				long numbers[] = new long[n];
				System.out.println("Enter values");
				for (int i = 0; i < n; i++) {
					numbers[i] = sc.nextInt();
					if (!(numbers[i] >= 0 && numbers[i] <= Math.pow(10, 9))) {
						System.out.println("Input constarints violation, Input value is between 1 to pow(10,9)");
						isValidInputs = false;
						break;
					}
				}
				long k = -1;
				if (isValidInputs) {
					System.out.println("Enter k value");
					k = sc.nextInt();
					if (!(k >= 0 && k <= Math.pow(10, 9))) {
						isValidInputs = false;
						System.out.println("Input constarints violation, K value is between 0 to " + Math.pow(10, 9));
					}
				}

				if (isValidInputs) {
					List<List<Long>> pairList = getCountPairs(numbers, k);

					System.out.println("Total Pairs:" + pairList.size());
					System.out.println("Count Pairs are:" + pairList);

				} else {
					System.out.println("Input constarints violation");
				}
			} else {
				System.out.println("Input constarints violation, Input value is between 1 to pow(10, 5)");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Gets the count pairs. Get distinct values from array Iterate distinct values
	 * and perform the following to each value increment by value k and check the
	 * result is present in distinct values if present add the pair to ArrayList
	 *
	 * @param numbers
	 *            the numbers
	 * @param k
	 *            the k
	 * @return the distinct count pairs
	 */
	private static List<List<Long>> getCountPairs(long[] numbers, Long k) throws Exception {
		try {
			System.out.println(Arrays.asList(numbers));
			List<List<Long>> countPairList = new ArrayList<>();
			long[] distinct = Arrays.stream(numbers).distinct().toArray();

			for (long a : distinct) {
				long b = a + k;
				if (isExist(distinct, b)) {
					List<Long> pair = new ArrayList<>();
					pair.add(a);
					pair.add(b);
					countPairList.add(pair);
				}
				b = 0;
			}
			return countPairList;
		} catch (Exception e) {
			System.out.print(e.getMessage());
			throw e;
		}
	}

	/**
	 * Checks the value b is present in list of values distinct
	 * 
	 * @param distinct
	 *            the distinct
	 * @param b
	 *            the b
	 * @return true, if is exist
	 */
	public static boolean isExist(long[] distinct, long b) {
		for (long val : distinct) {
			if (val == b) {
				return true;
			}
		}
		return false;
	}
}
