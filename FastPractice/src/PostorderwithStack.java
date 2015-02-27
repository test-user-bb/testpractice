import java.util.Stack;

import javax.crypto.spec.PSource;


//9:55 AM  : 10:41 


// We descend through the tree through a while loop and each time push the elements on to the stack.
// We need to separate out the cases when we descend down and come up.
// When left or right child is not null, it is the case of descend down.
// But actuall we just put the left or right child on to the stack, the the peek it up, so 
// we identify the case of descend down by current is the Left or Righ child of prev.

// Descend up is opposite, previous is the left or right child of current. 

public class PostorderwithStack {

	public static void main(String[] args){

		BTree root = new BTree(100);
		root.left = new BTree(50);
		root.right = new BTree(200);
		
		postOrder(root);
	}

	public static void postOrder(BTree root){


		if (root==null ){
			System.out.println("Emprty tree");
			return;
		}
		System.out.println("Roor is  tree "+root.data);

		Stack<BTree> stack = new Stack<BTree>();
		stack.add(root);
		BTree previoudNode = null;

		while (!stack.isEmpty()){

			BTree currentNode = stack.peek();

			if(currentNode==null){
				//This will be the child of leaf, which gets pushed on to stakc, so just remove it 
				// and continue
				stack.pop();
			}else{
				//System.out.println("Picked from stack  "+currentNode.data);

				if(currentNode.left == null && currentNode.right == null){
					System.out.print(currentNode.data+" ");
					stack.pop();
					
				}else
				
				//First identify the condition, where  we are popping the node from stack, in the 
				// return path, so that we should not end up in pushing elements twice over stak.
				if(previoudNode == currentNode.left){
					//This means we completed the left side traversal.
					// So, push the right side node on to stack.
					//System.out.println("Pushing right of in  stack  "+currentNode.data);
					stack.push(currentNode.right);
					//Now right node and current node both are on the stack.
				}else if (previoudNode == currentNode.right){
					System.out.print(currentNode.data +" ");
					stack.pop();
					//take out the node from the stack.
				}else {
					//This means we are not in the return path
					// So we are descending down the tree.
					//We always descend down the tree by pushing the left child.
					//System.out.println("Pushing left of in  stack  "+currentNode.data);
					stack.push(currentNode.left);
				}

			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			previoudNode = currentNode;	
		}


	}
}
