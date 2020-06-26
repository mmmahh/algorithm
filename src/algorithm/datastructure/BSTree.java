package algorithm.datastructure;

public class BSTree {

	Node root;
	class Node{
		int data;
		Node parent;
		Node left;
		Node right;
		public Node()
		{
			data = 0;
			parent = null;
			left = null;
			right = null;
		}
		public Node(int data)
		{
			this.data = data;
			this.parent = null;
			this.left = null;
			this.right = null;
		}
	}
	public BSTree()
	{
		this.root = null;
	}
	/**
	 * ���ҽڵ�
	 * @param x �� x Ϊ����ʼ
	 * @param k 
	 * @return
	 */
	public Node search(Node x, int k)
	{
		if(x==null || x.data==k)
		{
			return x;
		}
		if(k<x.data)
		{
			return search(x.left,k);
		}
		return search(x.right,k);
	}
	/**
	 * ���ҽڵ�
	 * @param k
	 * @return
	 */
	public Node search(int k)
	{
		return this.search(this.root,k);
	}
	/**
	 * ���ҽڵ�
	 * 	������ʽ
	 * @param x �� x Ϊ����ʼ
	 * @param k
	 * @return
	 */
	public Node iterativeSearch(Node x, int k)
	{
		while(x!=null && x.data!=k)
		{
			if(x.data<k)
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
	/**
	 * ���ҽڵ�
	 * 	������ʽ
	 * @param k
	 * @return
	 */
	public Node iterativeSearch(int k)
	{
		return this.iterativeSearch(this.root,k);
	}
	/**
	 * ��xΪ������������С�ڵ�
	 * @param x
	 * @return
	 */
	public Node treeMinimum(Node x)
	{
		while(x.left!=null)
		{
			x = x.left;
		}
		return x;
	}
	/**
	 * ��xΪ�������������ڵ�
	 * @param x
	 * @return
	 */
	public Node treeMaximum(Node x)
	{
		while(x.right!=null)
		{
			x = x.right;
		}
		return x;
	}
	/**
	 * x�ĺ�̽ڵ㣨��������£�
	 * @param x
	 * @return
	 */
	public Node successor(Node x)
	{
		if(x.right!=null)
		{
			return this.treeMinimum(x.right);
		}
		Node y = x.parent;
		while(y!=null && x==y.right)
		{
			x = y;
			y = y.parent;
		}
		return y;
	}
	/**
	 * x��ǰ���ڵ㣨��������£�
	 * @param x
	 * @return
	 */
	public Node predecessor(Node x)
	{
		if(x.left!=null)
		{
			return this.treeMaximum(x.left);
		}
		Node y = x.parent;
		while(y!=null && x==y.left)
		{
			x = y;
			y = y.parent;
		}
		return y;
	}
	/**
	 * ����ڵ�
	 * @param z
	 */
	public void insert(Node z)
	{
		Node y = null;
		Node x = this.root;
		while(x!=null)
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
		if(y==null)
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
	}
	/**
	 * v �滻 u
	 * @param u
	 * @param v
	 */
	public void transplant(Node u, Node v)
	{
		if(u.parent==null)
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
		if(v!=null)
		{
			v.parent = u.parent;
		}
	}
	/**
	 * ɾ���ڵ�
	 * @param z
	 */
	public void delete(Node z)
	{
		if(z.left==null)
		{
			this.transplant(z, z.right);
		}
		else if(z.right==null)
		{
			this.transplant(z, z.left);
		}
		else
		{
			Node y = this.treeMinimum(z.right);
			if(y.parent!=z)
			{
				this.transplant(y,y.right);
				y.right = z.right;
				y.right.parent = y;
			}
			this.transplant(z, y);
			y.left = z.left;
			y.left.parent = y;
		}
	}
	/**
	 * ���������xΪ��������
	 * @param x
	 */
	public void inorderTraversal(Node x)
	{
		if(x==null)
			return;
		inorderTraversal(x.left);
		System.out.print(x.data+" ");
		inorderTraversal(x.right);
	}
	public void inorderTraversal()
	{
		this.inorderTraversal(this.root);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] datas = {15,2,4,3,6,7,13,9,17,18,20};
		BSTree tree = new BSTree();
		for(int i=0;i<datas.length;i++)
		{
			Node x = tree.new Node(datas[i]);
			tree.insert(x);
		}
//		Node temp = tree.search(tree.root,17);
//		Node pre = tree.predecessor(temp);
//		System.out.println(pre.data);
		Node temp = tree.search(15);
		tree.inorderTraversal(tree.root);
		System.out.println("delete 15:");
		tree.delete(temp);
		tree.inorderTraversal(tree.root);
	}

}
