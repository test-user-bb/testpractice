import java.util.ArrayList;
import java.util.List;


//4:15 AM.
// No DP, but a general recursive descending solution.



/**
 * Given an array, print all subsets.
 * 
 * Given an array, element to start from, and number of elements in the subset.
 * 
 * Each subset is the, extension of previous set, i.e. ab is extension of a, abc extension of ab
 * 
 *  we use the above property.
 * 
 * @author niraj
 *
 */
public class ArraySubsets {

	public static void main(String[] args) {

		getTheSubsets(new int[] {1,2,3,4});
	}

	public static void getTheSubsets(int[] arr){
		getTheSubsets(arr,0,1,null);
	}

	public static void getTheSubsets(int[] arr, int start, int itemsToAddInSubset, List<Integer> subsetItem){
	
		if(subsetItem == null){
			subsetItem = new ArrayList<Integer>();
		}
		
		//When there is no more element remaining to recurse i.e items in subset ==0, we print the list 
		// and simply return.
		
		if( itemsToAddInSubset == 0 ){
			System.out.print("{");
			for( int i : subsetItem){
				System.out.print(i+" ");
			}
			System.out.println("}");
			return ;
		}
		
		//If there are items to print in subset then we pick up the current  items 
		// one by one from the start location , decrease the count of remaining items and 
		// descend the calls.
		
		
		for(int startIndex=start; startIndex< arr.length;++startIndex){
			//Since we need to cinlude the last element in the subset, we must traverse till last element.
			// That element will get included but them when we descne subsequenet will not get included.
			//
			Integer value= new Integer(arr[startIndex]);
			subsetItem.add(value);
			//Added the current element, now make a recursive call to fill the remaining number of 
			//itemsToAddInSubset -1 elements after the current element.
			
			//if there are no more items in the array to add then there is no need to make a call.
			if(startIndex+itemsToAddInSubset -1 <= arr.length-1 )
			getTheSubsets(arr,startIndex+1,itemsToAddInSubset-1,subsetItem);
			
			//Before starting the next loop, we should remove the existing element since no we will add the
			//remaining number of itemsToAddInSubset in the subset starting from the next positions if the startIndex.
			subsetItem.remove(value);
			
		}
		
		
	}

}
