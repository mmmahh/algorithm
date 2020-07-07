package algorithm.datastructure;

public class RBTree {
	public enum Color {RED,BLOCK};
	public final RBNode nil;
	
	class RBNode 
	{
		Color color;
		RBNode left;
		RBNode right;
		RBNode parent;
		public RBNode()
		{
			this(0);
		}
		public RBNode(int data) {
			this.color = Color.RED;
			this.left = nil;
			this.right = nil;
			this.parent = nil;
		}
	}
	
	private RBNode root;
	
	public RBTree()
	{
		this.nil = new RBNode();
		nil.color = Color.BLOCK;
		this.root = this.nil;
	}
	
	public void leftRotate(RBNode x)
	{
		RBNode y = x.right;
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
		RBNode y = x.left;
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
		//System.out.println("hello world");
		RBTree rbt = new RBTree();
		System.out.println(rbt.nil.color);
	}

}
