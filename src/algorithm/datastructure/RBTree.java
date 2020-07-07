package algorithm.datastructure;

public class RBTree {
	public enum Color {RED,BLACK};
	public final RBNode nil;
	
	class RBNode 
	{
		Color color;
		RBNode left;
		RBNode right;
		RBNode parent;
		int data;
		public RBNode()
		{
			this(0);
		}
		public RBNode(int data) {
			this.color = Color.RED;
			this.left = null;
			this.right = null;
			this.parent = null;
			this.data = data;
		}
	}
	
	private RBNode root;
	
	public RBTree()
	{
		this.nil = new RBNode();
		this.nil.color = Color.BLACK;
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
	public void insert(RBNode z)
	{
		RBNode y = this.nil;
		RBNode x = this.root;
		while(x!=this.nil)
		{
			y = x;
			if(z.data<x.data)
			{
				x = x.left;
			}
			else
			{
				x = x.right;
			}
		}
		z.parent = y;
		if(y==this.nil)
		{
			this.root = z;
		}
		else if(z.data<y.data)
		{
			y.left = z;
		}
		else
		{
			y.right = z;
		}
		z.left = this.nil;
		z.right = this.nil;
		z.color = Color.RED;
		insertFixup(z);
	}
	private void insertFixup(RBNode z)
	{
		while(z.parent.color==Color.RED)
		{
			if(z.parent==z.parent.parent.left)
			{
				RBNode y = z.parent.parent.right;
				if(y.color==Color.RED)
				{
					z.parent.color = Color.BLACK;
					z.parent.color = Color.BLACK;
					z.parent.parent.color = Color.RED;
					z = z.parent.parent;
				}
				else
				{
					if(z==z.parent.right)
					{
						z = z.parent;
						this.leftRotate(z);
					}
					z.parent.color = Color.BLACK;
					z.parent.parent.color = Color.RED;
					this.rightRotate(z.parent.parent);
				}
			}
			else
			{
				RBNode y = z.parent.parent.left;
				if(y.color==Color.RED)
				{
					z.parent.color = Color.BLACK;
					z.parent.color = Color.BLACK;
					z.parent.parent.color = Color.RED;
					z = z.parent.parent;
				}
				else
				{
					if(z==z.parent.left)
					{
						z = z.parent;
						this.rightRotate(z);
					}
					z.parent.color = Color.BLACK;
					z.parent.parent.color = Color.RED;
					this.leftRotate(z.parent.parent);
				}
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("hello world");
		RBTree rbt = new RBTree();
		System.out.println(rbt.nil.color);
	}

}
