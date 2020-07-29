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
	public RBNode getRoot()
	{
		return this.root;
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
	public void insert(int key)
	{
		RBNode z = this.new RBNode(key);
		this.insert(z);
	}
	public void insert(RBNode z)
	{
		//keep y is the parent of x
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
					z.parent.color = Color.BLACK;           //case 1
					y.color = Color.BLACK;                  //case 1
					z.parent.parent.color = Color.RED;      //case 1
					z = z.parent.parent;                    //case 1
				}
				else
				{
					if(z==z.parent.right)
					{
						z = z.parent;                       //case 2
						this.leftRotate(z);                 //case 2
					}
					z.parent.color = Color.BLACK;           //case 3
					z.parent.parent.color = Color.RED;      //case 3
					this.rightRotate(z.parent.parent);      //case 3
				}
			}
			else //(same as then clause with "right" and "left" exchanged)
			{
				RBNode y = z.parent.parent.left;
				if(y.color==Color.RED)
				{
					z.parent.color = Color.BLACK;
					y.color = Color.BLACK;
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
		this.root.color = Color.BLACK;
	}
	
	private void transplant(RBNode u, RBNode v)
	{
		if(u.parent==this.nil)
		{
			this.root = v;
		}
		else if(u==u.parent.left)
		{
			u.parent.left = v;
		}
		else
		{
			u.parent.right = v;
		}
		v.parent = u.parent;
	}
	public void delete(RBNode z)
	{
		RBNode y = z;
		RBNode x = null;
		Color yOriginalColor = y.color;
		if(z.left==this.nil)
		{
			x = z.right;
			this.transplant(z, z.right);
		}
		else if(z.right==this.nil)
		{
			x = z.left;
			this.transplant(z, z.left);
		}
		else
		{
			y = this.treeMinimum(z.right);
			yOriginalColor = y.color;
			x = y.right;
			//???
			if(y.parent==z)
			{
				x.parent = y;
			}
			else
			{
				this.transplant(y, y.right);
				y.right = z.right;
				y.right.parent = y;
			}
			this.transplant(z, y);
			y.left = z.left;
			y.left.parent = y;
			y.color = z.color;
		}
		if(yOriginalColor==Color.BLACK)
		{
			this.deleteFixup(x);
		}
	}
	private void deleteFixup(RBNode x)
	{
		while(x!=this.root && x.color==Color.BLACK)
		{
			if(x==x.parent.left)
			{
				RBNode w = x.parent.right;
				if(w.color==Color.RED)
				{
					w.color = Color.BLACK;                        //case 1
					x.parent.color = Color.RED;                   //case 1
					this.leftRotate(x.parent);                    //case 1
					w = x.parent.right;                           //case 1
				}
				if(w.left.color==Color.BLACK && w.right.color==Color.BLACK)
				{
					w.color = Color.RED;                          //case 2
					x = x.parent;                                 //case 2
				}
				else
				{
					if(w.right.color==Color.BLACK)
					{
						w.left.color = Color.BLACK;               //case 3
						w.color = Color.RED;                      //case 3
						this.rightRotate(w);                      //case 3
						w = x.parent.right;                       //case 3
					}
					w.color = x.parent.color;                     //case 4
					x.parent.color = Color.BLACK;                 //case 4
					w.right.color = Color.BLACK;                  //case 4
					this.leftRotate(x.parent);                    //case 4
					x = this.root;                                //case 4
				}
			}
			else //(same as then clause with "right" and "left" exchanged)
			{
				RBNode w = x.parent.left;
				if(w.color==Color.RED)
				{
					w.color = Color.BLACK;
					x.parent.color = Color.RED;
					this.rightRotate(x.parent);
					w = x.parent.left;
				}
				if(w.right.color==Color.BLACK && w.left.color==Color.BLACK)
				{
					w.color = Color.RED;
					x = x.parent;
				}
				else
				{
					if(w.right.color==Color.BLACK)
					{
						w.right.color = Color.BLACK;
						w.color = Color.RED;
						this.leftRotate(w);
						w = x.parent.left;
					}
					w.color = x.parent.color;
					x.parent.color = Color.BLACK;
					w.left.color = Color.BLACK;
					this.rightRotate(x.parent);
					x = this.root;
				}
			}
		}
		x.color = Color.BLACK;
	}
	
	public RBNode treeMaximum(RBNode x)
	{
		while(x.right!=this.nil)
		{
			x = x.right;
		}
		return x;
	}
	public RBNode treeMinimum(RBNode x)
	{
		while(x.left!=this.nil)
		{
			x = x.left;
		}
		return x;
	}
	public RBNode search(RBNode x, int k)
	{
		while(x!=this.nil && x.data!=k)
		{
			if(k<x.data)
			{
				x = x.left;
			}
			else
			{
				x = x.right;
			}
		}
		return x;
	}
	public RBNode search(int k)
	{
		return this.search(this.root,k);
	}
	public void inorder()
	{
		this.inorder(this.root);
	}
	public void preorder()
	{
		this.preorder(this.root);
	}
	private void inorder(RBNode r)
	{
		if(r==this.nil)
			return;
		this.inorder(r.left);
		System.out.print(r.data+"("+r.color+") ");
		this.inorder(r.right);
	}
	private void preorder(RBNode r)
	{
		if(r==this.nil)
			return;
		System.out.print(r.data+"("+r.color+") ");
		this.preorder(r.left);
		this.preorder(r.right);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[] data = {11,2,1,7,5,4,8,14,15,2};
		RBTree rbt = new RBTree();
		int n = 6;
		for(int i=0;i<n;i++)
		{
			int key = (int)(Math.random()*n*10);
			rbt.insert(key);
			System.out.print(key+" ");
		}
		System.out.println();
		rbt.preorder();
		System.out.println();
		
		int key = (int)(Math.random()*n*10);
		RBNode t = rbt.search(key);
		if(t!=rbt.nil)
		{
			System.out.println("delete key:"+key);
			rbt.delete(t);
			rbt.preorder();
		}
		else
		{
			System.out.println("not find key:"+key);
		}
	}

}
