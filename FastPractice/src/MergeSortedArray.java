//2:05
public class MergeSortedArray {

	public static void main(String[] args){
		int [] newarr =  mergeArrays(new int[]{1,2,3,4,0,0,0} , new int[]{2,9});
		
		for(int i = 0; i <newarr.length;++i) System.out.println(newarr[i]);
	}
	public static int[] mergeArrays(int[] a, int[] b){
		if(a.length == 0 ) return b;
		if(b.length == 0 ) return a;
		
		if ( b.length > a.length){
			int[] tmp = a;
			a = b;
			b = tmp;
		}	
		
		int aIndex=a.length-1;
		int bIndex=b.length-1;
		
		while(a[aIndex]==0)--aIndex;
				
		for(int i = aIndex+bIndex ; bIndex >=0 && i >=0;--i ){
			if(a[aIndex] > b[bIndex]){
				//System.out.println(a[aIndex]);
				a[i]=a[aIndex--];
			}else{
				a[i]=b[bIndex--];
			}
		}
		return a;
	}
}
