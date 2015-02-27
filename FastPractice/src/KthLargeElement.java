import java.util.Arrays;

//2:30 PM   to 4 PM, stuck bit:


public class KthLargeElement {

	static int start=0;
	
	public static void main(String[] args){
		int arr[] = {4,2,3,1};
		
		getKthLarge(arr, 2);
	}
	
	public static void getKthLarge(int[] arr,int k){
		
		//In qucik sort approach, we keep on partitioning the array based on pivote, and it is pivote 
		// which occupies the right position first in the array.
		//So, if we keep on partitioning and if it happens that if any element occupies the position of pivote
		// at kth place then that element will be the kth large element in the array, how ever till 
		// we get pivote at the kth element we wil keep on repeating.
		
		int pivotePosition=-1;
		k=k-1;
		
		//Idea is we call partition on the subarray, and we should know the pivote position of sub array.
		
		int end=arr.length;
		
		// In the first call we pass the entire array and will get a new pivote, based on the 
		// new pivote, we wil either break or choose subsequent half of the arry.
		while(true){
			int newPivote = partition(start,end-1,arr);
			if( newPivote == k){
				pivotePosition=newPivote;
				break;
			}else{
				if ( newPivote > k){
					end= newPivote;
				}else{
					start=newPivote+1;
				}
			
			
		}
	
		
	}
		//System.out.println("Kth large element is: "+pivotePosition);
		System.out.println("Kth large element is: "+arr[pivotePosition]);
}

	private static void swap(int i, int j, int[] arr){
		int tmp= arr[i];
		arr[i]= arr[j];
		arr[j]= tmp;
	}
	private static int partition(int start, int end, int[] arr) {

			int front = start+1;
			int back = end;
			
			System.out.println("Start : "+start +  "  end: "+ end);
			while( front < back){
				
				while(front <= end && arr[front] < arr[start]){
					System.out.println("front : "+front +  "  end: "+ end);
					++front;
				}
				
				while(back >= start && arr[back] > arr[start] ){
					System.out.println("back : "+back  +  "  start: "+ start);
					--back;
				}
				
				if(front < back)
				swap(front,back,arr);
				
			}
			System.out.println("Before Swapping pivote :");
			System.out.println(Arrays.toString(arr));
			
			System.out.println("Swapping pivote at :");
			//Blindly swapping is not right. This step is wrong, without complete knowledge doing something leads into trouble.
			swap(start,back,arr);
			System.out.println(Arrays.toString(arr));
			
			
			
		return back;
	}
}