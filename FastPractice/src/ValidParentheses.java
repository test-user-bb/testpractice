
//12:09

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * key : If left is greater than 0, we add a left parent and recurse.
 * 		Then if left is greater, then right we can add right, so we do that and recurse.
 *		if no left is remain we print all right.
 */

public class ValidParentheses {

	public static void main(String[] args){
		getParenth(1,1,"");
		//getParenth(3,3,"");
	}
	public static void getParenth(int leftRemain, int rightremain, String parenthString){

		/**
		 * First we decrease left and then we decrease right, that's why we check right for ending the
		 * calls.
		 */
		if(leftRemain > 0){
			getParenth(leftRemain-1, rightremain, parenthString+"(");
			//In the equation we can print the right parenthesis if the left is already printed.
			if(leftRemain < rightremain){
				getParenth(leftRemain,rightremain-1,parenthString+")");
			}
		}else{
			while(rightremain > 0){
				parenthString+=")";
				--rightremain;
			}
			System.out.println(parenthString);
		}
	}

}
