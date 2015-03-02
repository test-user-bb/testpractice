class PringAllChangeCombination 
{
	//now we create a test case
	public static void main(String[] args)
	{
		int[] coins = {25,10,5};//set as usual coins we use
		int[] counts = new int[coins.length];//set the default counts array
		System.out.print("All possible coin combinations of 25 cents are: ");
		PringCombination(coins, counts, 0, 25);//let's test the case of 25 cents, and notice default initial startIndex = 0
	}


	//define the recursive method header
	//notice we have 4 arguments in this method
	//coins are the sorted coins in descending order, larger positioned more front
	//counts record the number of coins at certain location
	//start index is keep tracking of from which coin we start processing after choosing one larger coin amount
	//total amount keep track of remaining amount left processing
	public static void PringCombination(int[] coins, int[] counts, int startIndex, int totalAmount)
	{
		System.out.println("\n Entry Remaining amount:" +totalAmount);
		System.out.println("Entry Start index:"+startIndex);
		
		
		//firstly decide if we should proceed or not by track startIndex
		if(startIndex>=coins.length)//we have processed all coins
		{
			//format the print out as "amount=?*25 + ?*10+..."
			for(int i=0; i<coins.length; i++)
				System.out.print(""+counts[i]+"*"+coins[i]+"+");
			//need a new line per case
			System.out.print("\n");
			return;
		}

		//other wise we need proceed
		//but notice if startIndex is the last one, we need check if it can be dividable by the smallest coin
		//if so, this is a good combination, otherwise, this is not possible combination thus discarded
		if(startIndex == coins.length-1)
		{
			if(totalAmount%coins[startIndex]==0)//good combination
			{
				//set the counts of coins at start index
				counts[startIndex] = totalAmount/coins[startIndex];
				//proceed to recursive call
				PringCombination(coins, counts, startIndex+1, 0);//notice startIndex+1 and remaining amount = 0
			}
		}
		else//we still have option to choose 0-N larger coins
		{
			for(int i=0; i<=totalAmount/coins[startIndex]; i++)
			{
				//for every cycle in a loop, we choose an arbitrary number of larger coins and proceed next
				counts[startIndex] = i;
				int x= totalAmount-coins[startIndex]*i;
				System.out.println("\n Loop Remaining amount:" +x);
				int y = startIndex+1;
				System.out.println("Loop Start index:"+ y);
				PringCombination(coins, counts, startIndex+1, totalAmount-coins[startIndex]*i);
				//notice we need update the remaining amount
			}
		}
	}
}


/**
* Please watch at http://www.youtube.com/user/ProgrammingInterview
* Contact: haimenboy@gmail.com
*
* Step by step to crack programming interview questions.
* 1. All questions were searched publicly from Google, Glassdoor, Careercup and StackOverflow.
* 2. All codes were written from scratch and links to download the source files are provided in each video's description. All examples were written in java, and tools I have used include Editplus, Eclipse and IntelliJ.
* 3. All videos were made without using any non-authorized material. All videos are silent sorry. Text comment is provided during coding as additional explanations.
* Thank you very much. 
*/
