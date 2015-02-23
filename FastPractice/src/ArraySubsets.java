import java.util.ArrayList;
import java.util.List;


//9:03 AM.
//9:30 AM

/**
 * Given an array, print all subsets.
 * This is all possible combinations, so we will keep on including the elements.
 * Step 1: Form subset with only one element.
 * Step 2: form subset with only two element and include at a time any two element from the array elements.
 * step 3: for 3 elements etc.
 * continue..
 * 
 * @author niraj
 *
 */
public class ArraySubsets {

	public static void main(String[] args) {

		getTheSubsets(new int[] {1,2,3,4});
	}

	private static void printAll(List<Integer> lst) {
		System.out.print("{");
		for (Integer i : lst)
			System.out.print(i+","); // invokes Car.toString()
		System.out.print("}");
	}

	private static void printSubsetAllinOne(List<List<List<Integer>>> newSet ) {
		System.out.println();
		for(List<List<Integer>> subSet :  newSet ){
			printSubsetAll(subSet);
		}

	}

	private static void printSubsetAll(List<List<Integer>> subSet ) {

		for(List<Integer> subItem : subSet ){
			System.out.print("{");
			for (Integer i : subItem)
				System.out.print(i+","); // invokes Car.toString()
			System.out.print("}");
		}

	}

	public static void getTheSubsets(int[] arr){
		System.out.println();
		System.out.println("{}");
		if(arr.length == 0 ){
			return;

		}



		List<List<List<Integer>>> newSet = new ArrayList<List<List<Integer>>>();
		List<List<Integer>> firstMidSet = new ArrayList<List<Integer>>();


		for(int i = 0; i < arr.length;++i){
			List<Integer> firstSubItem = new ArrayList<Integer>();
			firstSubItem.add(arr[i]);
			firstMidSet.add(firstSubItem);
		}
		newSet.add(firstMidSet);

		for(int numberOfElement = 2; numberOfElement <= arr.length;++numberOfElement) { //number of items to be included in each iterations
			int level=numberOfElement-1;
			List<List<Integer>> prevMidSet = newSet.get(level-1);
			List<List<Integer>> newMidSet = new ArrayList<List<Integer>>(); 
			for( int endelement = numberOfElement-1; endelement <= arr.length-1; ++endelement) { // The starting element of the subset.
				int correpsondingSubet = 0;
				List<Integer> subsetItem = new ArrayList<Integer>(prevMidSet.get(correpsondingSubet));
				subsetItem.add(arr[endelement]);
				newMidSet.add(subsetItem);
				++correpsondingSubet;
				//printAll(subsetItem);
				//subsetItem.clear();
			}
			printSubsetAll(newMidSet);
			newSet.add(newMidSet);
		}
		
		printSubsetAllinOne(newSet);
	}

}
