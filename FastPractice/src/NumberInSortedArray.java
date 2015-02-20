import static org.junit.Assert.*;

import org.junit.Test;

//10:40 to 11:13
public class NumberInSortedArray {

	//static int numArray[] = {1,2,3,4,4,4,4,4,4,4,5,5,5,5,5,5,5,6,6,6,6,6,6,6,6,6,6,6,6,7,8,9};
	static int numArray[] = {1,1,1,2,2,2};
	
		public static void main(String[] args){
			System.out.println(findSortedArray(1,numArray,0,numArray.length-1));
		}
	
	public static int findSortedArray(int num, int[] array, int start, int end){
		if(array.length == 0) {
			return -1;
		}

		
		if ( array[start ] > num || array[end] < num){
			return -1;
		}
		
		if( array[start] == num ){
			return start;
		}
		
		int mid = (end-start)/2+start;
		
		//Now check for the best case;
		
		// If mid is equal to num, then we need to check for the 
		if( array[mid] == num ){
			
			int lefIndex= findSortedArray(num, array,start,mid-1);
			
			int pos=lefIndex == -1 ? mid :  lefIndex;
			return pos;
		}else if (num > array[mid]  ){
			return findSortedArray(num, array,mid+1,end);
		}else{
			return findSortedArray(num, array,start,mid-1);
		}

		//A Start with Binary search to get the number first.
		//For Binay search we need to pass the start and end in parameters

	}
}
