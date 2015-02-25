
public class LargetSubsequence {

	public static void main(String[] args) {

		int arr[] = new int[] {1,-1,5,-6,20,35,-10,25};
		
		getMaxSubsequence(arr);

	}

	/**
	 * Kepp on adding the items and track the temp sum, if becomes negative discard otherwise if becomes
	 * greater then use.
	 * @param arr
	 * @return
	 */
	public static int getMaxSubsequence(int arr[] ){
		int maxSum=0;
		int tempSum=0;

		int tempstartIndex=0;
		int tempendIndex=0;

		int maxstartIndex=0;
		int maxendIndex=0;


		for(int i = 0; i < arr.length;++i){
			tempSum += arr[i];
			tempendIndex=i;

			//Update MaxSum
			if(tempSum > maxSum ){
				maxSum= tempSum;
				maxstartIndex = tempstartIndex;
				maxendIndex = tempendIndex;
			}
			//reset tempSum
			if( tempSum < 0 ){
				tempSum=0;
				tempstartIndex=i+1;
				//Expecting that next element will be +ve, and if it is not then temp index will be 
				//automatically reset to next.
			}

		}

		System.out.println("Sum = "+maxSum +" Start = "+ maxstartIndex + " end = " + maxendIndex);
		return 0;
	}
}
