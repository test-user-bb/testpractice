
public class SubArraySum {

	static int[] arr = new int[] {5,4,7,5,4,4,3,5};
	
	public static void main(String[] args){
		getSubArrya(arr,16);
	}
	
	public static void getSubArrya(int[] arr, int num){
		if( arr.length == 0 ) return;
		int sum=0; int j=0;
		for(int i = 0; i<arr.length;++i){
			sum +=arr[i];
			if( sum==num){
				for(int v=j;v<=i;++v){
					System.out.print(arr[v]+"  ");
				}
				System.out.println();
			}
			if(sum > num){
				sum -=arr[j];
				++j;
				if( sum==num){
					for(int v=j;v<=i;++v){
						System.out.print(arr[v]+"  ");
					}
					System.out.println();
				}
			}
			
		}
	}
	
	
	
}
