
public class AllSubSets {

	static int arr[] = {1,2,3,4,5,6};

	public static void main(String[] args){
		getSubsets(arr,0,arr.length,"",3);
	}

	public static void getSubsets(int[] arr, int start, int end, String str, int remaining){
		if( remaining == 0 ){
			System.out.println("The subset is :"+str);
			return;
		}

		if((start+remaining) > end ){
			System.out.print("For call with start ="+start +" end = "+end +" remaining ="+ remaining +" call returned without action");
			return;
		}
		for(int i= start; i < end - remaining; ++i){
			String str1= new String(str+arr[i]);
			getSubsets(arr,i+1, end,str1 , remaining-1);
		}

	}
}
