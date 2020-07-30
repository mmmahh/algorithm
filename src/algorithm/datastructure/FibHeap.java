package algorithm.datastructure;

public class FibHeap {
	
	public static final int NEGATIVE_INFILITY = Integer.MIN_VALUE; 
	
	class FibNode {
		int key;
		int degree;
		FibNode parent;
		FibNode child;
		FibNode left;
		FibNode right;
		boolean mark;
		public FibNode(int key)
		{
			this.key = key;
			this.degree = 0;
			this.parent = null;
			this.child = null;
			this.left = null;
			this.right = null;
			this.mark = false;
		}
	}
	
	private FibNode min;
	private int n;
	public FibHeap()
	{
		this.min = null;
		this.n = 0;
	}
	private FibHeap(FibNode min, int n)
	{
		this.min = min;
		this.n = n;
	}
	/**
	 * insert FibNode into root list
	 * @param x
	 */
	public void insert(FibNode x)
	{
		x.degree = 0;
		x.parent = null;
		x.child = null;
		x.mark = false;
		if(this.min==null)
		{
			x.left = x;
			x.right = x;
			this.min = x;
		}
		else
		{
			x.right = this.min.right;
			this.min.right.left = x;
			this.min.right = x;
			x.left = this.min;
			if(x.key<this.min.key)
			{
				this.min = x;
			}
		}
		this.n += 1;
	}

	public static FibHeap union(FibHeap H1, FibHeap H2)
	{
		return H1.union(H2);
	}
	/**
	 * union this and H2, 
	 * then the objects of this and H2 shoudn't be used;
	 * @param H2
	 * @return
	 */
	public FibHeap union(FibHeap H2)
	{
		// H1 = this
		
		if(H2.min==null)
		{
			return this;
		}
		
		FibNode H_min = this.min;
		if(H_min==null)
		{
			H_min = H2.min;
		}
		else
		{
//			H2.min.left.right = H_min.right;
//			H_min.right.left = H2.min.left;
//			H_min.right = H2.min;
//			H2.min.left = H_min;
			this.listConcatenate(H_min, H2.min);
		}
		
		if(this.min==null || (H2.min!=null && H2.min.key<this.min.key))
		{
			H_min = H2.min;
		}
		return new FibHeap(H_min,this.n+H2.n);
	}
	/**
	 * delete and return this.min;
	 * @return
	 */
	public FibNode extractMin()
	{
		FibNode z = this.min;
		if(z!=null)
		{
			FibNode child = z.child;
			if(child!=null)
			{
				FibNode temp = child;
				do {
					temp.parent = null;
					temp = temp.right;
				}while(temp!=child);
				
				this.listConcatenate(this.min, child);
			}
			this.listRemove(this.min, z);
			
			if(z==z.right) //actually the method remove has done this job
			{
				this.min = null;
			}
			else
			{
				this.min = z.right;
				this.consolidate();
			}
			this.n -= 1;
		}
		return z;
	}
	/**
	 * decrease x.key to k
	 * k should less than x.key
	 * @param x
	 * @param k
	 */
	public void decreaseKey(FibNode x, int k)
	{
		if(k>x.key)
		{	
			System.err.println("new key is greater than current key");
			return;
		}
		x.key = k;
		FibNode y = x.parent;
		if(y!=null && x.key<y.key)
		{
			this.cut(x,y);
			this.cascadingCut(y);  //cascade: ¼¶Áª
		}
		if(x.key<this.min.key)
		{
			this.min = x;
		}
	}
	/**
	 * 
	 * @param x
	 */
	public void delete(FibNode x)
	{
		this.decreaseKey(x, NEGATIVE_INFILITY);
		this.extractMin();
	}
	/**
	 * remove x from the child list of y, decrementing y.degree
	 * add x to the root list
	 * @param x
	 * @param y
	 */
	private void cut(FibNode x, FibNode y)
	{
		this.listRemove(y.child, x);
		y.degree -= 1;
		this.listInsert(this.min, x);
		x.parent = null;
		x.mark = false;
	}
	/**
	 * 
	 * @param y
	 */
	private void cascadingCut(FibNode y)
	{
		FibNode z = y.parent;
		if(z!=null)
		{
			if(y.mark==false)
			{
				y.mark = true;
			}
			else
			{
				this.cut(y,z);
				this.cascadingCut(z);
			}
		}
	}
	/**
	 * 
	 */
	private void consolidate()
	{
		//first thing: calculate D(this.n)
		//assume D = this.n
		
		int D = this.n;
		FibNode[] A = new FibNode[D+1];
		for(int i=0;i<=D;i++)
		{
			A[i] = null;
		}
		
		FibNode w = this.min;
		FibNode x = null;
		//for each node w in the root list
		do {
			x = w;
			int d = x.degree;
			while(A[d]!=null)
			{
				FibNode y = A[d];
				if(x.key>y.key)
				{
					FibNode temp = x;
					x = y;
					y = temp;
				}
				this.link(y,x);
				A[d] = null;
				d += 1;
			}
			A[d] = x;
			
			w = w.right;
		}while(w!=this.min);
	}
	/**
	 * remove y from the root list
	 * make y a child of x, incrementing x.degree
	 * @param y: a FibNode in root list
	 * @param x: a FibNode in root list
	 */
	private void link(FibNode y, FibNode x)
	{
		this.listRemove(this.min, y);
		this.listInsert(x.child, y);
		y.parent = x;
		x.degree += 1;
		y.mark = false;
		
	}
	/**
	 * concatenate list H1 with list H2
	 * @param H1: the head of list
	 * @param H2: the head of list
	 */
	private void listConcatenate(FibNode H1, FibNode H2)
	{
		if(H1==null || H2==null)
			return;
		H2.left.right = H1.right;
		H1.right.left = H2.left;
		H1.right = H2;
		H2.left = H1;
	}
	/**
	 * remove FibNode z from list H
	 * @param H: the head of list
	 * @param z
	 */
	private void listRemove(FibNode H, FibNode z)
	{
		//make sure z is in list H
		//I didn't check it
		
		FibNode beforeZ = z.left;
		if(beforeZ==z)
		{
			H = null;
		}
		else
		{
			beforeZ.right = z.right;
			z.right.left = beforeZ;
		}
	}
	/**
	 * insert FibNode x into list H
	 * @param H: the head of list
	 * @param x
	 */
	private void listInsert(FibNode H, FibNode x)
	{
		if(H==null)
		{
			H = x;
			x.right = x;
			x.left = x;
		}
		else
		{
			x.right = H.right;
			H.right.left = x;
			H.right = x;
			x.left = H;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
