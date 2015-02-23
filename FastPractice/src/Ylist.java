
public class Ylist {
	
	public static int listLenght(Node head){
		int count=0;
		while(head != null){
			++count;
			head=head.next;
		}
		
		return count;
	}
	
	public static void main(String[] args){
		Node head = new Node(10);
		head.next = new Node(20);
		head.next.next = new Node(30);
		head.next.next.next = new Node(40);
		head.next.next.next.next = new Node(50);
		
		Node head2 = new Node(100);
		head2.next = new Node(200);
		head2.next.next = new Node(300);
		head2.next.next.next = head.next.next.next;

		System.out.println(head);
		System.out.println(head2);
		
		int l1=listLenght(head);
		int l2=listLenght(head2);
		int diff=l1-l2;
		 if ( l2 > l1){
			 Node tmp=head;
			 head=head2;
			 head2=tmp;
			 diff = l2-l1;
		 }
		 
		 for(int i = 0; i < diff;++i){
			 head=head.next;
		 }
		 
		while ( head != head2){
			head=head.next;
			head2=head2.next;
		}
				
		System.out.println("Intersection is "+head.data);
	}
}
