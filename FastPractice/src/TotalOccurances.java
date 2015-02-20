
public class TotalOccurances {

	public static void main(String[] args){
		int[] a =  {1,1,1,1,1,2,3,4,5};
		System.out.println(getTotalOccurances(1,a, 0,4));
	}
	static public int getTotalOccurances(int num, int[] arr, int start, int end){
		
		if(start> end ) return 0;
		
		if(arr[start] > num || arr[end]<num){
			return 0;
		}
		
		if( arr[start] == num && arr[end] == num){
			return end-start+1;
		}

		int mid=(end-start)/2;
		
		if(num < arr[mid]){
			return getTotalOccurances(num, arr, start, mid-1);
		}else if (num > arr[mid]){
			return getTotalOccurances(num, arr, mid+1, end);
		}else{
			return (1+getTotalOccurances(num, arr, start, mid-1) + getTotalOccurances(num, arr, mid+1, end));
		}
		
	}
	
}
