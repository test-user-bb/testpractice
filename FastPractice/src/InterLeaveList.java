//5:51 PM  : 6:28 PM  ( 37 Mins)


/**
 * 
 * Take the list 1-2-3-4-5-6-7-8
 * 
 * Need to make 1-8-2-7-3-6-4-5
 * 
 * How to do ?
 * 
 * Cut the list at center, then reverse the second half, then merge them one by one.
 *
 */

class MyList{
	MyList next;
	int data;

	MyList(int data){
		this.data=data;
	}
	@Override
	public String toString() {

		StringBuilder b1 = new StringBuilder();
		MyList m1 = this;
		while(m1 != null){
			b1.append(m1.data+"->");
			m1 = m1.next;
		}
		return b1.toString();
	}
};

// We will have 3 supporting methods.
//1 . Break list in tow parts.
//2. . Reverse the list.
//3. Then merge the list.
public class InterLeaveList {

	public static void main(String[] args){
		//Crrate a new list and check the funcations.

		MyList head = new MyList(1);
		head.next = new MyList(2);
		head.next.next = new MyList(3);
		head.next.next.next = new MyList(4);
		head.next.next.next.next = new MyList(5);
		head.next.next.next.next.next = new MyList(6);
		head.next.next.next.next.next.next = new MyList(7);
		head.next.next.next.next.next.next.next = new MyList(8);
		System.out.println(head);

		MyList secondPart = breakList(head);

		System.out.println("first Part"+head);
		System.out.println("Second Part"+secondPart);

		MyList reversedList= reverseList(secondPart);

		System.out.println("Reversed Second Part: "+reversedList);
		
		System.out.println("Interleaved List: "+interLeaveList(head, reversedList));


	}

	public static MyList interLeaveList(MyList first, MyList second){

		if(first == null ) return second;
		if( second == null ) return first;

		MyList inteLeaved = new MyList(0);
		MyList inteLeavedHead = inteLeaved;

		boolean firstTerm=true;

		while( first != null && second != null){
			if(firstTerm){
				inteLeaved.next = first;
				first=first.next;
				firstTerm=false;

			}else
				if(!firstTerm){
					inteLeaved.next = second;
					second=second.next;
					firstTerm=true;
				}
			inteLeaved = inteLeaved.next;
		}

		while( first != null){
			inteLeaved.next = first;
			first=first.next;
		}
		return inteLeavedHead.next;
				
	}

	public static MyList reverseList(MyList lst){
		// No or Single item
		if(lst == null || lst.next == null){
			return lst;
		}

		//descend to the end.
		MyList  pendingList = reverseList(lst.next);

		/// Reverse current links.

		// A is current, B is next and C is current.next,next
		//A->B
		// Assuming that C would be taken care in the previous call to B, and now C would be pointing to the B,
		// Hense changing the links between A and B

		lst.next.next = lst;
		//Changed B point to A.
		// Issue but A, is till now pinting to B and A could be the now reverse end of the list, so change it.
		lst.next=null;

		return pendingList;

	}
	public static MyList breakList(MyList lst){
		MyList slow=lst,fast=lst;

		//1-2->null  at max a list with 2 items, so nothign to interleave;
		// put this condition in the main interleave function.

		if(lst== null || lst.next == null || lst.next.next == null){
			return lst;
		}
		//1->2->3->4  in first iteration fast will be at 3 and slow will be at 2
		// Since fast will move to 3, the loop will break adn slow will return as 2, which is correct ?


		//1->2->3->4->5  in first iteration fast will be at 3 and slow will be at 2
		// Then second, fast at 5 and slow at 3., there loop will break.
		//So, to break the list, we will return the break point as next to slow.s

		// 1->2-3
		// Slow at 2, so break point will be 3.


		while(fast.next != null && fast.next.next != null){
			fast=fast.next.next;
			slow = slow.next;
		}

		MyList tmp = slow.next; 
		slow.next = null; // break the list.
		return tmp;

	}
}















