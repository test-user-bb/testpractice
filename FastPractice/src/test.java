import static org.junit.Assert.*;

import org.junit.Test;


public class test {

	@Test
	public void test() {
		System.out.println("Test");
	}

	
	
	//@Test
	public static void tst(){
		int numArray[] = {1,2,3,4,5,6,7,8,9};
		assertEquals(5, findSortedArray(6,numArray,0,numArray.length));;
	}

	public static int findSortedArray(int num, int[] array, int start, int end){
		if(array.length == 0) {
			return -1;
		}

		if ( start > end ){
			return -1;
		}
		
		if ( array[start ] > num || array[end] < num){
			return -1;
		}
		
		if( start == end)  {
			if(array[start] == num)
				return start;
			else
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
			return findSortedArray(num, array,mid,end);
		}else{
			return findSortedArray(num, array,start,mid);
		}

		//A Start with Binary search to get the number first.
		//For Binay search we need to pass the start and end in parameters

	}
	
}
