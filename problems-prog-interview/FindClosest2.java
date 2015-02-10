//Authored by Xin Yao <singerdmx@gmail.com> 
public static int getClosetTreeNode(TreeNode t, int v) {
  if (t == null)  {
   throw new IllegalArgumentException("Null input");
  }
  
  int closetTreeNodeValue = 0;
  int minDist = Integer.MAX_VALUE;
  while(true) {
   if (t == null) break;
   if (t.value == v) { // no need to traverse down further
    return v;
   }
   else if (t.value < v) {

    if ((v - t.value) < minDist) {
     minDist = v - t.value;
     closetTreeNodeValue = t.value;
    }
    t = t.right;
   }
   else { // t.value > v

    if ((t.value - v) < minDist) {
     minDist = t.value - v;
     closetTreeNodeValue = t.value;
    }
    t = t.left;
   }
  }
  
  return closetTreeNodeValue;
 }