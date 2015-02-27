import java.util.Arrays;

// 6:30 PM : 6:52 PM
public class RotateArrayInPlace {

	//Steps: 1. First we take the k elements and reverse the array for the first half for those element.
	// Step 2: Then we reverse the array for the remaing n-K elements.
	// Step 3 : then we reverse the array for entirely.
	
	
	public static int[] reverseArray(int[] arr, int start, int end){
		if( start >= end ) return arr;
		//1->2  => 2->1
		int count=0;
		for(int i = start; i <= (end-start)/2+start;++i){
			int tmp = arr[i];
			arr[i]= arr[end-count];
			arr[end-count]=tmp;
			System.out.println("i :"+ i + " swaped with " + (end-count));
			count++;
		}
		return arr;
	}
	
	public static void main(String[] args){
		int[] arr= {1,2,3,4,5,6,7,8};
		
		
		System.out.println(Arrays.toString(reverseArray(arr, 0, 1)));
		// 2+7 = 9 9/2 4    2,3,4  5 6 7
		System.out.println(Arrays.toString(reverseArray(arr, 2, arr.length-1)));
		
		System.out.println(Arrays.toString(reverseArray(arr, 0, arr.length-1)));
		
	}
	
	
}
