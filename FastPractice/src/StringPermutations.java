import java.util.Arrays;


// 1:05 PM : 1:30 1:47

public class StringPermutations {

	/**
	 * Permutations, means each possible char in the array will occupy each positions and while happening
	 * that all others will occupy each possible positions.
	 * 
	 */

	public static void swap(char[] arr, int pos1, int pos2){
		char tmp=arr[pos1];
		arr[pos1]=arr[pos2];
		arr[pos2]=tmp;
	}
	//Swap all the other string positions with the swap pos and call recursively. 
	public static void  getStringPermutations(char[] str, int swapPos){

		int arrLen=str.length;
		if( swapPos == arrLen-1){
			System.out.println(String.valueOf(str));
			return;
		}
		//Keep the original guy in place and trigger swap in the remainning places.
		getStringPermutations(str, swapPos+1);

		for(int i = swapPos+1 ; i < arrLen;++i){
			char[] newArray = Arrays.copyOf(str,str.length);
			if(newArray[i] == newArray[swapPos]){
				continue;
			}
			swap(str, swapPos, i);
			//Swap original guy and then trigger swap in remaining places.
			getStringPermutations(str, swapPos+1);
			//Inspite of creating the new array, we can swap back also.
			//Also check for duplicates if the char are same.
			swap(str, swapPos, i);
		}
	}

	public static void main(String[] args){
		char[] str= "ABBC".toCharArray();
		//System.out.println(str);
		getStringPermutations(str, 0);


	}
}
