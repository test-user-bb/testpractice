import java.util.Stack;//as we need stack for the process
class LargestRectangleInHistrogram 
{
	//create a test case
	public static void main(String[] args)
	{
		int[] histos = {2,4,2,1};//use demo example, expect 6
		System.out.println("Largest rectangle size is "+LargestRectangle(histos));

		int[] histos2 = {2,4,2,1,10,6,10};//this time expect 18, with height 6 and width spans last 3 nodes
		System.out.println("Largest rectangle size is "+LargestRectangle(histos2));
	}

	public static int LargestRectangle(int[] histos)
	{
		//firstly define two stacks, one for heights the other for indexes
		Stack<Integer> heights = new Stack<Integer>();
		Stack<Integer> indexes = new Stack<Integer>();
		int largestSize = 0;//the return value
		//now process histos one by one
		for(int i=0; i<histos.length; i++)
		{
			//case 1, the height is larger thus can be candidate of rectangle start
			if(heights.isEmpty() || histos[i]>heights.peek())
			{
				heights.push(histos[i]);
				indexes.push(i);
			}
			else if(histos[i]<heights.peek())
			{
				//if the current height is shorter, thus we need pop those longer heights
				//and compute the candidate rectangle's area size
				int lastIndex = 0;
				//this index to keep track of the last index which 
				//will be replacing the current index for current height inserting

				while(!heights.isEmpty() && histos[i]<heights.peek())
				{
					//we need compute the size
					lastIndex = indexes.pop();
					int tempAreaSize = heights.pop()*(i-lastIndex);
					if(largestSize<tempAreaSize)
						largestSize = tempAreaSize;//update largest area size if necessary
				}

				//after popping those unqualified start positions including current index, add current
				heights.push(histos[i]);
				indexes.push(lastIndex);//notice this is not i but lastIndex, check our demo for why

			}
		}

		//after the process, there may still be values in stacks, pop out each and test size
		while(!heights.isEmpty())
		{
			//we need compute the size
			int tempAreaSize = heights.pop()*(histos.length - indexes.pop());//the width=currentIndex(last one) - stored index
			if(largestSize<tempAreaSize)
				largestSize = tempAreaSize;//update largest area size if necessary
		}

		return largestSize;
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
