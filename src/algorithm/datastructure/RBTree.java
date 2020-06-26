package algorithm.datastructure;

public class RBTree extends BSTree{
	public enum Color {RED,BLOCK};
	public final RBNode nil = this.new RBNode();
	{
		nil.color = Color.BLOCK;
	}
	
	class RBNode extends BSTree.Node
	{
		Color color;
		/**
		 * 构造nil节点
		 */
		public RBNode()
		{
			super();
			this.color = null;
		}
		/**
		 * 构造内部节点
		 * @param data
		 */
		public RBNode(int data) {
			super(data);
			this.color = Color.RED;
			this.left = nil;
			this.right = nil;
			this.parent = nil;
		}
	}
	
	public void leftRotate(RBNode x)
	{
		Node y = x.right;
		x.right = y.left;
		if(y.left!=nil)
		{
			y.left.parent = x;
		}
		y.parent = x.parent;
		if(x.parent==nil)
		{
			this.root = y;
		}
		else if(x==x.parent.left)
		{
			x.parent.left = y;
		}
		else
		{
			x.parent.right = y;
		}
		y.left = x;
		x.parent = y;
	}
	public void rightRotate(RBNode x)
	{
		Node y = x.left;
		x.left = y.right;
		if(y.right!=nil)
		{
			y.right.parent = x;
		}
		y.parent = x.parent;
		if(x.parent==nil)
		{
			this.root = y;
		}
		else if(x==x.parent.left)
		{
			x.parent.left = y;
		}
		else
		{
			x.parent.right = y;
		}
		y.right = x;
		x.parent = y;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
