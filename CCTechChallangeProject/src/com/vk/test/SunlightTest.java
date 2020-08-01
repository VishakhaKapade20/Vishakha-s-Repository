package com.vk.test;

// Java program to count buildings that can  see sunlight. 

public class SunlightTest {

	// Returns count buildings that can see sunlight
	static int countBuildings(int[][] arr,int n) {
		// Initialuze result (Note that first building
		// always sees sunlight)
		int count = 1;

		// Start traversing element
		int curr_max = arr[0][0];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				// If curr_element is maximum,
				// update maximum and increment count
				if (arr[i][j] > curr_max) {
					count++;
					curr_max = arr[i][j];
				}
			}

		}

		return count;
	} // end of method

	public static void main(String[] args) {
		int arr[][] = { { 4, 0 }, { 4, 5 }, { 7, 5 }, { 7, 0 } };

		System.out.println(countBuildings(arr, arr.length));

	} // end of main method
}// end of class
