package algorithm.datastructure;

import algorithm.datastructure.RBTree.Color;

/**expand structure
 * Order Statistic Tree
 *   x.size = x.left.size + x.right.size + 1
 *   nil.size = 0
 *   (the order begin with 1)
 * @author ma
 *
 */
public class OSTree {
	
	class OSNode {
		int data;
		int size;
		Color color;
		OSNode parent;
		OSNode left;
		OSNode right;
		public OSNode()
		{
			this(0);
		}
		public OSNode(int data)
		{
			this.data = data;
			this.size = 0;          //new information
			this.color = Color.RED;
			this.parent = null;
			this.left = null;
			this.right = null;
		}
	}
	public final OSNode nil;
	private OSNode root;
	public OSTree()
	{
		this.nil = new OSNode();
		nil.color = Color.BLACK;
		this.root = this.nil;
	}
	public OSNode select(int i)
	{
		return this.select(this.root,i);
	}
	/**
	 * return the i-th keyword's OSNode with the root as x
	 * @param x : subtree's root node
	 * @param i : the order in subtree x
	 * @return
	 */
	public OSNode select(OSNode x, int i)
	{
		int r = x.left.size + 1;
		if(i==r)
		{
			return x;
		}
		else if(i<r)
		{
			return this.select(x.left, i);
		}
		else
		{
			return this.select(x.right, i-r);
		}
	}
	/**
	 * return the rank of x
	 * @param x
	 * @return
	 */
	public int rank(OSNode x)
	{
		int r = x.left.size + 1;
		OSNode y = x;
		while(y!=this.root)
		{
			if(y==y.parent.right)
			{
				r += y.parent.left.size + 1;
			}
			y = y.parent;
		}
		return r;
	}
	
	// fix: maintain size
	public void leftRotate(OSNode x)
	{
		OSNode y = x.right;

		y.size = x.size;                             //fix code
		
		x.right = y.left;
		
		x.size = x.left.size + x.right.size + 1;     //fix code
		
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
	// fix: maintain size
	public void rightRotate(OSNode x)
	{
		OSNode y = x.left;
		
		y.size = x.size;                             //fix code
		
		x.left = y.right;
		
		x.size = x.left.size + x.right.size + 1;     //fix code
		
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
		OSNode z = this.new OSNode(key);
		this.insert(z);
	}
	
	// fix: maintain size
	public void insert(OSNode z)
	{
		z.size = 1;          //fix code
		
		//keep y is the parent of x
		OSNode y = this.nil;
		OSNode x = this.root;
		
		// y is the path from root to leaf node's parent
		// or you can use x to maintain size
		while(x!=this.nil)
		{
			y = x;
			
			//x.size += 1    //fix code2
			
			if(z.data<x.data)
			{
				x = x.left;
			}
			else
			{
				x = x.right;
			}
			
			y.size += 1;     //fix code
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
	private void insertFixup(OSNode z)
	{
		while(z.parent.color==Color.RED)
		{
			if(z.parent==z.parent.parent.left)
			{
				OSNode y = z.parent.parent.right;
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
				OSNode y = z.parent.parent.left;
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
	
	private void transplant(OSNode u, OSNode v)
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
	
	// fix: maintain size
	public void delete(OSNode z)
	{
		OSNode y = z;
		OSNode x = null;
		Color yOriginalColor = y.color;
		if(z.left==this.nil)
		{
			x = z.right;
			this.transplant(z, z.right);
			
			//y.parent is still in right place and y!=nil
			//y=z is the node deleted, its size is not important
			this.decSizeToRoot(y);     //fix code
		}
		else if(z.right==this.nil)
		{
			x = z.left;
			this.transplant(z, z.left);
			
			//y.parent is still in right place and y!=nil
			//y=z is the node deleted, its size is not important
			this.decSizeToRoot(y);     //fix code
		}
		else
		{
			y = this.treeMinimum(z.right);
			
			//from the original place of y to decSizeToRoot
			//y's size need to update after
			this.decSizeToRoot(y); //fix code
			
			yOriginalColor = y.color;
			x = y.right;
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
			
			//update y.size
			y.size = y.left.size + y.right.size + 1; //fix code
		}
		if(yOriginalColor==Color.BLACK)
		{
			this.deleteFixup(x);
		}
	}
	/**
	 * decrease size in the path from y to root
	 * @param y
	 */
	private void decSizeToRoot(OSNode y)
	{
		// make sure y != nil, or it will make some trouble 
		// unless you make nil.parent in right place
		while(y!=this.nil)
		{
			y.size -= 1;
			y = y.parent;
		}
	}
	private void deleteFixup(OSNode x)
	{
		while(x!=this.root && x.color==Color.BLACK)
		{
			if(x==x.parent.left)
			{
				OSNode w = x.parent.right;
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
				OSNode w = x.parent.left;
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
	
	public OSNode treeMaximum(OSNode x)
	{
		while(x.right!=this.nil)
		{
			x = x.right;
		}
		return x;
	}
	public OSNode treeMinimum(OSNode x)
	{
		while(x.left!=this.nil)
		{
			x = x.left;
		}
		return x;
	}
	public OSNode search(OSNode x, int k)
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
	public OSNode search(int k)
	{
		return this.search(this.root,k);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
