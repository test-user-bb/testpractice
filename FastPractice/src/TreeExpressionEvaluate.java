//9:25 AM
//9:52 AM


public class TreeExpressionEvaluate {

	ExpressionTree expressionTree;

	TreeExpressionEvaluate (ExpressionTree t){
		expressionTree=t;
	}

	//We evaluate expression for the child first then parent.
	// It should be evaluated for both the children first then for parent.

	public  String evaluateExpression(ExpressionTree root){
		//End of recursion, when we descend down to the lead nodes, their children return 0;
		if (root == null){
			return "0";
		}

		//also the leaf nodes, are only data node and all the other non leaf nodes, are the operator nodes.
		//So, if node if non operator means just return the values.

		if( isOperator(root.data)){

			return evaluate(evaluateExpression(root.left),root.data,evaluateExpression(root.right));

		}else{
			return root.data;
		}
	}

	private String evaluate(String leftTreevalue, String operator,
			String rightTreeValue) {

		switch (operator){
		case "+":
			return  (Integer.parseInt(leftTreevalue) + Integer.parseInt(rightTreeValue)) +"";
		case "-":
			return  (Integer.parseInt(leftTreevalue) - Integer.parseInt(rightTreeValue)) +"";
		case "*":
			return  (Integer.parseInt(leftTreevalue) * Integer.parseInt(rightTreeValue)) +"";
		case "/":
			return  (Integer.parseInt(leftTreevalue) / Integer.parseInt(rightTreeValue)) +"";
		default:
			return "";
		}

	}

	private boolean isOperator(String data) {
		switch (data){
		case "+":
		case "-":
		case "*":
		case "/":
			return true;
		default:
			return false;
		}
	}

	public static void main(String[] args){

		ExpressionTree expressionTree = new ExpressionTree("+");
		expressionTree.left = new ExpressionTree("30");
		expressionTree.right = new ExpressionTree("50");

		ExpressionTree expressionTree2 = new ExpressionTree("+");
		expressionTree2.left = new ExpressionTree("*");
		expressionTree2.right = new ExpressionTree("/");

		expressionTree2.left.left= new ExpressionTree("100");
		expressionTree2.left.right= new ExpressionTree("20");

		expressionTree2.right.left = new ExpressionTree("5000");
		expressionTree2.right.right = new ExpressionTree("1000");





		TreeExpressionEvaluate eval= new TreeExpressionEvaluate(expressionTree);
		System.out.println(eval.evaluateExpression(expressionTree));
		System.out.println(eval.evaluateExpression(expressionTree2));

	}

}
