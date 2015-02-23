
public class SumPairValues {
	static int[] arr = new int[] {1,4,5,8,17,20};

	public  static void getPais(int[] arr, int num){
		for( int i = 0,j = arr.length-1; i <j;){
			if(arr[i]+arr[j]==num){
				System.out.println("Give pair is "+arr[i] + "  "+arr[j]);
				++i;--j;  //you should move from both the sides.
			}else if(arr[i]+arr[j] >num){
				--j;
			}else{
				++i;
			}
		}
	}

	public static void main(String[] args) {

		getPais(arr,9);
		getPais(arr,21);

	}

}
