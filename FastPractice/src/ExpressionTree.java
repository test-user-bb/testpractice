
public class ExpressionTree {
		ExpressionTree left,right;
		String data;

		ExpressionTree(String data){
			this.data=data;
			left=null;
			right=null;

		}
		ExpressionTree(String data, ExpressionTree left,ExpressionTree right){
			this.data=data;
			this.left=left;
			this.right=right;

		}
};
