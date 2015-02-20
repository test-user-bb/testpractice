
/**
 * Given rotated palindrome.
 * 
 * Palindome, when cut in half, gives same chars on laft and right.
 * 
 * Since, rotated, we cut it in each location and join the left with right, and test for palindrome.
 * 
 *  ACBCA
 *  WHen rotated ANti clockwise
 *  A ACBC
 *  CA ACBC
 *  
 *  So, when cut at 3rd char it is CA seprated out and out it at the end of right part.
 * 
 *
 */
public class VerifyRotatedPalindrome {

	public static void main(String[] args){
		String palindomre="AAZZQPMNECS";
		System.out.println(verifyRoratedPalindrome(palindomre));
	//	System.out.println(checkIsPalindrome(palindomre));
	}
	
	public static boolean verifyRoratedPalindrome(String arr){

		int len=arr.length()-1;

		//till the last but one element.
		for(int i =1; i <= len-1;++i){
			String leftPart= arr.substring(0, i); // i exlcusive
			String rightPart= arr.substring(i); // i inclusiev 

			if(checkIsPalindrome(rightPart+leftPart)){
				return true;
			}
		}
		return false;

	}

	private static boolean checkIsPalindrome(String string) {
		char[] str= string.toCharArray();
		int len=str.length;
		for(int i = 0; i <len /2-1; ++i ){
			if ( str[i] != str[len-i-1]){
				return false;
			}
		}
		return true;
	}
}
