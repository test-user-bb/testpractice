//5:05 PM  
//5:21 PM

public class CountRotationsInSortedArray {

	public static void main(String[] args){

		//int arr[] = {7,8,9,10,11,1,2,3,4,5,6};
		int arr[] = {3,1,2};
		countRotations(arr,0,arr.length-1);
	}

	public static void countRotations(int[] arr, int start, int end){

		if(start > end ){
			System.out.println("Wrong intput");
			return;
		}
		
		if(arr[end]>=arr[start]){
			System.out.println("Pivote is "+arr[end]);
			System.out.println(end-start+1);
			return;
		}

		//any one of the below condition can exist at a time
		
		int mid = (end-start)/2+start;
		
		if( !( arr[start] <= arr[mid]) ){
			countRotations(arr, start, mid-1);
		}else if (!( arr[mid] <= arr[end])){
			countRotations(arr, mid+1, end);
		}
	}

}
