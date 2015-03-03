//3:30 PM : 45 Mins
package SelectedProblems;

public class TreeToDList {

	static boolean smallest = false;
	static BTree head = new BTree(0);

	public static void main(String[] args) {

		BTree root = new BTree(4);

		root.left = new BTree(2);

		root.left.right = new BTree(3);
		root.left.left = new BTree(1);

		root.right = new BTree(5);

		BTree lst = treeToDLl(root);

		BTree lststart=lst;
		int count=0;
		while(lst != null ){
			System.out.print(lst.data+"->");
			lst=lst.right;
			++count;
			if(count> 6) break;
		}


	}


	public static  BTree treeToDLl(BTree root){

		// At each current node, we are joining two lists.
		// The list we get from left, we are putting current pointer to its end 
		// The list we got from right, we are putting it at the end of current pointer.


		if( root.left == null && root.right == null){
			root.left=root;
			root.right=root;
			return root;
		}

		//If the lfetnode if null, then root itself is the begining of the list.
		BTree leftList=null;

		if(root.left != null){
			leftList = treeToDLl(root.left);
		}

		//Putting root at the end of list received from the left node.
		leftList.left.right =root;
		
		//setting the left pointer of root to the last element in the left list
		root.left=leftList.left ;  

		BTree rightList =null;

		if(root.right != null){
			rightList= treeToDLl(root.right);
		}

		//end of the right list points to the head of the left list.
		// and left of the left list should point to the last item which is end of the right list.
		//So creating the proper circular list over here.
		rightList.left.right=leftList;
		leftList.left=rightList.left;
		
		//Now put the root in between the list, aleady set to the 
		rightList.left=root;
		root.right = rightList;

		
		
		//System.out.println("Right Head "+root.right.data);
		return leftList;
	}


}
