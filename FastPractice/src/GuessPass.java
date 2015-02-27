//1:40 PM  2:12 PM (32 mins)
public class GuessPass {
	//Given based on certain criteria.
	public static double passProbality=1.0/4.0;
	public static double failProbality=3.0/4.0;

	final static int maxAttemps=100;
	final static int maxExpects = 100;
	//So at every stage we need  probability of expect out of attemps.
	//So let's store those values in a 2-D array or table.

	static double [][] probabilities = new double [maxAttemps][maxExpects];
	public static void main(String[] args){

		probabilities[1][0] = failProbality;  // out of 1 attempt, 0 success expected.
		probabilities[1][1] = passProbality;   // out of 1 attempt, 1 success expected.

		System.out.println("4 tries and 2 pass "+guess(2,1) );
	}

	// out of given number of "attempts" number of pass "expected", returns prob for that.
	public  static double guess(int attemps, int expect){

		//return guess(attempt-1,expect-1)*guess(1,1) + guess(attempt-1,expect)*guess(1,0);
		// i.e sum of prob of passing in this attempt  and failing in this attemp and passing in prev all.
		// To get the probalities till the point attemps and expects, we should loop till that.

		// In each attemmpt we will generate expect probalities from 0 to expect.

		// We start with 2 trials since for 1 trial, we already know the value of success and failure as
		// 1/4 and 3/4 respectively.
		// There is nothing as 0 tries or 0 number of experiments.

		for(int tries = 2; tries <= attemps; ++tries){
			for (int success=0; success<=expect;++success ){				if(success==0){
					System.err.println("prev: "+ probabilities[tries-1][0]);
					probabilities[tries][0]= probabilities[tries-1][0]*failProbality;
				}else{
					probabilities[tries][success]=probabilities[tries-1][success-1]*passProbality+probabilities[tries-1][success]*failProbality;	
				}
			}
		}
		return probabilities[attemps][expect];

	}
}
