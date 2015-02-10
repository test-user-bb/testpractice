class MidList 
{
	//now let's create a test case
	public static void main(String[] args)
	{
		List myList = new List(1);
		myList.next = new List(2);
		myList.next.next = new List(3);
		myList.next.next.next = new List(4);
		myList.next.next.next.next = new List(5);//1,2,3,4,5 so the mid point is expected to be 3
		myList.next.next.next.next.next = new List(6);
		myList.next.next.next.next.next.next = new List(7);//1,2,3,4,5,6,7 so mid point expected to be 4
		MidList(myList);
	}

	//now we try to implement the method, as we discussed in slide we need two support variables
	static int myListLength = 0;//this is to keep track of length
	static int currentReverseIndex = 0;//this is to keep track of index in reverse order
	//notice, this method does not return the value, this is somehow similar as a traversal
	public static void MidList(List myList)
	{
		//firstly we proceed the length and do the recursion
		if(myList!=null)
		{
			myListLength++;
			MidList(myList.next);
			//after recursion, we can update the current index, and if you look at control flow, the next
			//statement won't be first executed until we reach last node
			currentReverseIndex++;
		}
		//now we only need to decide if currentReverseIndex/myListLength=1/2
		if(currentReverseIndex*2==myListLength || currentReverseIndex*2==myListLength+1)//myListLength can be even or odd
			System.out.println("Find mid point: "+myList.value);
	}
}	
//create a test linked list class
class List
{
	int value;
	List next;
	public List(int a)
	{
		value = a;
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
