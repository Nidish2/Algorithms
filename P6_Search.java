package daa;

import java.util.Scanner;

public class P6_Search {

	public int linearSearch(int[] arr, int key) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == key) {
				return i; // Return the index where key is found
			}
		}
		return -1; // Key not found
	}

//	This applies only for Sorted List
	public int binarySearch(int[] arr, int start, int end, int key) {
		while (start <= end) {
			int mid = start + (end - start) / 2; // Calculate the middle index

			if (arr[mid] == key) {
				return mid; // Return the index where key is found
			} else if (arr[mid] < key) {
				start = mid + 1; // Search in the right half
			} else {
				end = mid - 1; // Search in the left half
			}
		}
		return -1; // Key not found
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		P6_Search p = new P6_Search();
		P5_Sorting p5 = new P5_Sorting();

		System.out.println("Enter the size of the array: ");
//		int n = sc.nextInt();
		int n = 4000;
		int arr[] = new int[n];

		arr = p5.insertDirectlyRandom(arr, n);
		p5.display(arr, n);

		System.out.print("\nEnter the key to search: ");
		int key = sc.nextInt();
		sc.close();

		// Perform Linear Search - O(n)
		long start = System.nanoTime();
		int posLinear = p.linearSearch(arr, key);
		long end = System.nanoTime();

		if (posLinear == -1) {
			System.out.println("Element not found using Linear search");
		} else {
			System.out.println("Element found at index " + posLinear + " using Linear search");
		}
		System.out.println("Time taken using Linear search: " + (end - start) + " nanoseconds");

		// Perform Binary Search (requires sorted array) - O(log n)
		start = System.nanoTime();
		arr = p5.quickSort(arr);
		end = System.nanoTime();
		System.out.println("\nThe time taken to Quick Sort " + n + " items is " + (end - start) + " ns\n");
		p5.display(arr, n);
		start = System.nanoTime();
		int posBinary = p.binarySearch(arr, 0, n - 1, key);
		end = System.nanoTime();

		if (posBinary == -1) {
			System.out.println("\nElement not found using Binary search");
		} else {
			System.out.println("\nElement found at index " + posBinary + " using Binary search");
		}
		System.out.println("Time taken using Binary search: " + (end - start) + " nanoseconds");
	}
}