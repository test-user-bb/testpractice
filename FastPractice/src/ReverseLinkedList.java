import java.util.List;
import static org.junit.Assert.*;

//9:00 AM.  Time : Aprox 30 mins

//Given a Linked List from the user, reverse it using the recursion.


class Node{
	Node next;
	int data;

	public Node(int data){
		this.data = data;
	}

	@Override
	public  String toString() {
		String data=this.data+"";
		Node next=this.next;
		while(next != null){
			data+=" "+next.data+"";
			next=next.next;
		}
		return data;
	}
};


/*
 * We recurse, reached to the end of list, swapped the last two nodes, make current as the last and then
 * it points to the null, and made the next point to the current.
 * and returned the tail as new heed.
 * 
 */

public class ReverseLinkedList {

	//Since we can not afford to loose the acccess to next like as below, we first travel till the end then swap pointer

	static Node  reverseList(Node current){

		//No node or only one node so can not reverse the list.
		//Also this mean that we reached the end of the list.

		if(current==null || current.next==null){
			return current;
		}

		Node tailFromLastCall = reverseList(current.next);

		//When we are here, if we reached end then we get the tail of the list.
		//Now we need to maintain this tail of the list.

		current.next.next=current; //Reversed the node.

		//but now current is pointing to the next, and next.next pointing to the current so it is a loop
		//break the loop.

		current.next=null;

		//This is also fine if it is the two node list, then for thesecond call this first node will be end of
		// the list
		//otherwise any way we are going to set this in the next call.
		//Secondly, we should understand that current node is always going to be the tail of the list
		//so it should always point to the null.

		return tailFromLastCall;
	}
	
	public static void main(String[] args){

		Node head = new Node(10);
		head.next = new Node(20);
		head.next.next = new Node(30);
		head.next.next.next = new Node(40);
		head.next.next.next.next = new Node(50);
		System.out.println(head);
		
		Node newHead= reverseList(head);
		System.out.println(newHead);
	}

}

//9:40 AM