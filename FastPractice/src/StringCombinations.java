import com.sun.prism.sw.SWPipeline;

//1:24 PM //1:43 PM
// String length "n".
// For each place there are n choices, 
public class StringCombinations {

	public static void main(String[] args) {
		
		StringPermutations("nira".toCharArray(),0);
	}

	private static void swapChars(int i, int j, char[] str){
		char tmp = str[i];
		str[i]=str[j];
		str[j]=tmp;
	}

	//Given a string, prints all the possible permutations of given characters.

	// e.g: NIR : NIR, NRI, INR, IRN,  RNI, RIN
	public static void  StringPermutations(char[] str, int swapIndex ){
		if(swapIndex == str.length){
			System.out.println(str);
		}
		//Idea is pick up char position char and permute all the words in other places.
		for(int i = swapIndex; i <= str.length-1; ++i){   // First loop n, second loop n-1, third n -2
			swapChars(swapIndex, i, str);				  // So, first N calls then each of the N call, triggered 
			StringPermutations(str,swapIndex+1);			// N-1 Calls, then each N-1 call Triggered N-2 calls.
															// N*(N-1)*(N-2)*(N-3)*(N-4).. N power N.
			swapChars(swapIndex, i, str);
		}
		return ;
	}

}
