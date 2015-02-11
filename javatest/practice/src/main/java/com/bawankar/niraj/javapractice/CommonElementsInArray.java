package com.bawankar.niraj.javapractice;

import java.util.Arrays;

public class CommonElementsInArray {

	static int[] arr1 = {1,2,3,4,5,6,7,8,9};
	static int[] arr2 = {2,70,5,100,7,9,20};
	
	public static void main(String[] args){
		for(int i = 0 ; i <arr2.length;++i){
		int found=	Arrays.binarySearch(arr1, arr2[i]);
		if(found > -1){
			System.out.println("Found element: "+ arr2[i] + " at location "+found);
		}
		}
	}
}
