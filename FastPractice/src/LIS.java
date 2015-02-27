
//2:15 PM   : 2:32 PM  (17 Mins).

public class LIS {
	
	public static void main(String[] args){
		int[] arr = {1,5,7,2,3,20,12,27,29,5,7,8,9,12,56,100,200};
		getLIS(arr);
	}



	public static int getLIS(int[] arr){
		// to Generate LIS, we iterate through each element, to generate the maximum subsequence over
		// there from the first element, and we advance one unit each time, by generating the max LIS
		// at that element from the context of all the elements.

		int[] lis = new int[arr.length];
	
		String[] lisList = new String[arr.length];

		//each element individually is equivalent to subsequence of 1

		for(int i = 0; i < lis.length;++i){
			lis[i]=1;
			lisList[i]=arr[i]+"";
		}

		int maxSubSeqVal=0,maxSubIndex=0;
		for(int lisElement = 1; lisElement < arr.length;++lisElement){
			for(int contextElement=0; contextElement<lisElement ; ++contextElement){
				//just need to check with one element before the lisElement
				//Maintain the highest lis at given point 
				
				if(arr[lisElement] > arr[contextElement]){
					if(lis[lisElement]< (lis[contextElement]+1) ){
						lis[lisElement]=lis[contextElement]+1;
						lisList[lisElement]=lisList[contextElement]+ " " +arr[lisElement];
						
						if(lis[lisElement] >maxSubSeqVal ){
							maxSubSeqVal=lis[lisElement];
							maxSubIndex=lisElement;
						}
					}
				}

			}
		}

		System.out.println("Max SubSeq: "+lis[maxSubIndex]);
		System.out.println("Max SubSeq List : "+lisList[maxSubIndex]);
		
		return -1;
	}
}
