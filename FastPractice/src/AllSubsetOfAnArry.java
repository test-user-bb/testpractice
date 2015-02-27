// 11 AM to 12 PM

public class AllSubsetOfAnArry {

	static int arr[] = {1,2,3,4,5,6};

	public static void main(String[] args){
		for(int i = 0 ; i < arr.length;++i ){
			System.out.println("Subste with items : "+i);
			getSubsets(arr,0,arr.length,"",i);
		}
	}

	public static void getSubsets(int[] arr, int start, int end, String str, int remaining){
		if( remaining == 0 ){
			System.out.println("The subset is :"+str);
			return;
		}
		for(int i= start; i < end - remaining; ++i){
			String str1= new String(str+arr[i]);
			getSubsets(arr,i+1, end,str1 , remaining-1);
		}

	}
}
