package algorithm.datastructure;

import java.util.logging.Logger;

/**
 * 双向链表
 *  数组实现
 *  freelist 空间申请和释放
 * @author Administrator
 *
 */
public class AllocatorDLinkList {
	public static final int DEFAULT_SIZE = 100;
	public static final Logger log = Logger.getLogger("allocator double link list");
	/**
	 * L = -1 => L = null
	 * free = -1 => free = null
	 */
	
	private int[] prev;
	private int[] next;
	private int[] key;
	private int L;  // double link list head
	private int free; // free list head
	public AllocatorDLinkList()
	{
		this(AllocatorDLinkList.DEFAULT_SIZE);
	}
	public AllocatorDLinkList(int size)
	{
		prev = new int[size];
		next = new int[size];
		key = new int[size];
		L = -1;
		free = 0;
		int tempFree = free;
		for(int i=0;i<size-1;i++)
		{
			next[tempFree] = i+1;
			tempFree = next[tempFree];
		}
		next[size-1] = -1;
	}
	public int allocate()
	{
		if(free!=-1)
		{
			int x = free;
			free = next[free];
			return x;
		}
		else
		{
			log.warning("out of space");
			return -1;
		}
	}
	public void free(int x)
	{
		next[x] = free;
		free = x;
	}
	public void insert(int key)
	{
		int store = this.allocate();

		if(store!=-1)
		{
			this.key[store] = key;
			next[store] = -1;
			prev[store] = -1;
			if(L!=-1)
			{
				next[store] = L;
				prev[L] = store;
				L = store;
			}
			else
			{
				L = store;
			}
		}
	}
	public void delete(int key)
	{
		int x = this.search(key);
		if(x!=-1)
		{
			if(prev[x]!=-1)
			{
				next[prev[x]] = next[x];
			}
			else
			{
				L = next[x];
			}
			if(next[x]!=-1)
			{
				prev[next[x]] = prev[x];
			}
			free(x);
		}
	}
	public int search(int key)
	{
		int temp = L;
		while(temp!=-1 && this.key[temp]!=key)
		{
			temp = next[temp];
		}
		return temp;
	}
	public int getKey(int pointer)
	{
		return key[pointer];
	}
	public int getPrev(int pointer)
	{
		return prev[pointer];
	}
	public int getNext(int pointer)
	{
		return next[pointer];
	}
	public void print(String title)
	{
		System.out.println(title);
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<prev.length;j++)
			{
				if(i==0)
					System.out.print(prev[j]+" ");
				else if(i==1)
					System.out.print(key[j]+" ");
				else
					System.out.print(next[j]+" ");
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AllocatorDLinkList list = new AllocatorDLinkList(5);
		list.insert(0);
		list.insert(1);
		list.insert(2);
		list.insert(3);
		list.insert(4);
		list.insert(5);
		int pointer = list.search(3);
		System.out.println(pointer);
		list.print("---");
		list.delete(4);
		list.print("---");
		list.insert(6);
		list.print("---");
		for(int key=0;key<8;key++)
		{
			list.delete(key);
		}
	
		
	}

}
