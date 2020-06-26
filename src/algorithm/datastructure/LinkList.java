package algorithm.datastructure;

public class LinkList {
	public static final int SINGLE = 1;
	public static final int DOUBLE = 2;
	
	private int type;
	private LinkListx linklist;
	public LinkList()
	{
		this(LinkList.SINGLE);
	}
	public LinkList(int type)
	{
		if(type==LinkList.SINGLE)
		{
			this.type = LinkList.SINGLE;
			linklist = new SingleLinkList();
		}
		else
		{
			this.type = LinkList.DOUBLE;
			linklist = new DoubleLinkList();
		}
	}
	public Node search(int key)
	{
		if(this.type==LinkList.SINGLE)
		{
			return linklist.searchS(key);
		}
		else
		{
			return linklist.searchD(key);
		}
	}
	public void insert(int key)
	{
		linklist.insert(key);
	}
	public void delete(int key)
	{
		linklist.delete(key);
	}
	public void print()
	{
		linklist.print();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

interface LinkListx
{
	SingleLinkList.SNode searchS(int key);
	DoubleLinkList.DNode searchD(int key);
	void insert(int key);
	void delete(int key);
	void print();
}
interface Node
{
	//no more
}
class SingleLinkList implements LinkListx
{
	class SNode implements Node
	{
		int key;
		SNode next;
		public SNode()
		{
			this(0);
		}
		public SNode(int key)
		{
			this.key = key;
			next = null;
		}
	}
	private SNode head;
	
	public SingleLinkList()
	{
		this.head = null;
	}
	@Override
	public SingleLinkList.SNode searchS(int key)
	{
		SNode temp = this.head;
		while(temp!=null && temp.key!=key)
		{
			temp = temp.next;
		}
		return temp;
	}
	@Override
	public void insert(int key)
	{
		SNode temp = new SNode(key);
		temp.next = head;
		head = temp;
	}
	@Override
	public void delete(int key)
	{
		//单向链表，删除需找到删除节点的前驱
		if(head!=null && head.key==key)
		{
			this.head = head.next;
		}
		else
		{
			SNode temp = this.head;
			while(temp.next!=null && temp.next.key!=key)
			{
				temp = temp.next;
			}
			if(temp!=null)
			{
				temp.next = temp.next.next;
			}
		}
	}
	@Override
	public void print()
	{
		SNode temp = this.head;
		while(temp!=null)
		{
			System.out.print(head.key+" ");
			temp = temp.next;
		}
	}
	@Override
	public DoubleLinkList.DNode searchD(int key)
	{
		return null;
	}
}
class DoubleLinkList implements LinkListx
{
	class DNode implements Node
	{
		int key;
		DNode prev;
		DNode next;
		public DNode()
		{
			this(0);
		}
		public DNode(int key)
		{
			this.key = key;
			this.prev = null;
			this.next = null;
		}
	}
	private DNode head;
	
	public DoubleLinkList()
	{
		this.head = null; 
	}
	@Override
	public DoubleLinkList.DNode searchD(int key)
	{
		DNode temp = this.head;
		while(temp!=null && temp.key!=key)
		{
			temp = temp.next;
		}
		return temp;
	}
	@Override
	public void insert(int key)
	{
		DNode temp = new DNode(key);
		temp.next = head;
		if(this.head!=null)
		{
			head.prev = temp;
		}
		this.head = temp;
		temp.prev = null;
	}
	@Override
	public void delete(int key)
	{
		DNode temp = this.searchD(key);
		if(temp!=null)
		{
			//temp 前驱的后向引用
			if(temp.prev!=null)
			{
				temp.prev.next = temp.next;
			}
			else
			{
				this.head = temp.next;
			}
			//temp 后驱的前向引用
			if(temp.next!=null)
			{
				temp.next.prev = temp.prev;
			}
		}
	}
	@Override
	public void print()
	{
		DNode temp = this.head;
		while(temp!=null)
		{
			System.out.print(temp.key+" ");
			temp = temp.next;
		}
	}
	@Override
	public SingleLinkList.SNode searchS(int key)
	{
		return null;
	}
}
