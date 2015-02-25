 class MyTree2{
		
		int data;
		public MyTree2 left,right;

		MyTree2(int data){
			this.data=data;
			left=null;
			right=null;

		}
		
	};
//3:23 PM
	
//3:58 PM
	
public class CloseValueBST {

	
	
	public static void main(String[] args){
		
		MyTree2 root = new MyTree2(100);
		
		root.left=new MyTree2(50);
		root.right=new MyTree2(200);
		
		root.left.left=new MyTree2(25);
		root.left.right=new MyTree2(60);
		
		root.right.left=new MyTree2(150);
		
		root.right.right=new MyTree2(300);
		
		System.out.println("Close Value "+ getCloseValue(root,280,Integer.MAX_VALUE));
	}

	public static double getCloseValue(MyTree2 tree, double value, double minDiff){
		
		if(tree == null){
			return minDiff;
		}

		System.out.println(tree.data);
		
		
		double localDiff= Math.abs(tree.data-value);
		
		if(localDiff < minDiff ){
			minDiff=localDiff;
		}

		if( tree.data < value ) {
			return getCloseValue(tree.right,value,minDiff);
		}else{
			return getCloseValue(tree.left,value,minDiff);
		}

	}
}
