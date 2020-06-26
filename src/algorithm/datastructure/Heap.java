package algorithm.datastructure;

import java.util.ArrayList;
import java.util.Random;

import algorithm.Methods;

public class Heap {

	public static final int MIN_HEAP = 1;
	public static final int MAX_HEAP = 2;
	private Heapx heap;
	public Heap(int type)
	{
		this(Heapx.DEFAULT_SIZE,type);
	}
	public Heap(int capacity, int type)
	{
		if(type==Heap.MAX_HEAP)
		{
			heap = new MaxHeap(capacity);
		}
		else
		{
			heap = new MinHeap(capacity);
		}
	}
	public Heap(int[] array, int type)
	{
		if(type==Heap.MAX_HEAP)
		{
			heap = new MaxHeap(array);
		}
		else
		{
			heap = new MinHeap(array);
		}
	}
	public Heap(int[] array, int type, int heaplifyBuild)
	{
		if(type==Heap.MAX_HEAP)
		{
			heap = new MaxHeap(array,heaplifyBuild);
		}
		else
		{
			heap = new MinHeap(array,heaplifyBuild);
		}
	}
	public boolean isEmpty()
	{
		return heap.isEmpty(); 
	}
	public int getTop()
	{
		return heap.getTop();
	}
	public void delTop()
	{
		heap.delTop();
	}
	public void insert(int key)
	{
		heap.insert(key);
	}
	public void clear()
	{
		heap.clear();
	}
	public boolean changeKey(int i, int key)
	{
		return heap.changeKey(i, key);
	}
	
	public void print()
	{
		for(int i=0;i<heap.getSize();i++)
		{
			System.out.print(heap.get(i)+" ");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random rand = new Random();
		final int n = 100;
//		Heap min = new Heap(Heap.MIN_HEAP);
//		Heap max = new Heap(Heap.MAX_HEAP);
		int[] array = new int[n];
		int[] b = new int[6];
		for(int i=0;i<n;i++)
		{
			array[i] = rand.nextInt(20);
			System.out.print(array[i]+" ");
//			min.insert(k);
//			max.insert(k);
		}
		
//		min.print();
//		System.out.println();
//		max.print();
//		
//		System.out.println();
//		max.delTop();
//		max.print();
		
		
		algorithm.Test.runtime(()->{
			Heap min = new Heap(array,Heap.MIN_HEAP);
		});
		
		algorithm.Test.runtime(()->{
			Heap min2 = new Heap(array,Heap.MIN_HEAP,1);
		});
		
		
		System.arraycopy(array, 0, b, 0, b.length);
		Heap min2 = new Heap(b,Heap.MIN_HEAP,1);
		min2.print();
		
	}

}
/**
 * heap interface
 * @author Administrator
 *
 */
interface Heapx
{
	static final int DEFAULT_SIZE = 20;
	
	boolean isEmpty();
	int getTop();
	void delTop();
	void insert(int key);
	void clear();
	
	boolean changeKey(int i, int key);
	int get(int i);
	int getSize();
	
	public static int parent(int i)
	{
		return (i-1) / 2;
	}
	public static int left(int i)
	{
		return 2*i+1;
	}
	public static int right(int i)
	{
		return 2*i+2;
	}
	
	/** which is the last inner node's index
	 * 
	 * n is the heap size (index from 0 to n-1)
	 * if A[k] is an inner node and A[k+1] is a leaf
	 * then
	 * 		left(k) <= n-1
	 * 		left(k+1) >= n
	 *  =>
	 * 		(n-3)/2 <= k <= (n-1)/2
	 *  =>
	 *  	k = floor((n-1)/2) 
	 */
	
}
/**
 * Min heap
 * @author Administrator
 *
 */
class MinHeap implements Heapx
{
	private ArrayList<Integer> heap;
	private int heapSize;
	public MinHeap()
	{
		this(Heapx.DEFAULT_SIZE);
	}
	public MinHeap(int capacity)
	{
		this.heapSize = 0;
		this.heap = new ArrayList<>(capacity);
	}
	public MinHeap(int[] array)
	{
		this(array.length);
		for(int i=0;i<array.length;i++)
		{
			this.insert(array[i]);
		}
	}
	public MinHeap(int[] array, int heaplifyBuild)
	{
		this(array.length);
		int n = array.length;
		this.heapSize = n;
		for(int i=0;i<n;i++)
		{
			this.heap.add(array[i]);
		}
		for(int i=(n-1)/2;i>=0;i--)
		{
			this.heaplify(i);
		}
	}
	@Override
	public boolean isEmpty()
	{
		return heapSize==0;
	}
	@Override
	public int getTop()
	{
		if(heapSize==0)
			return -1;
		return heap.get(0);
	}
	@Override
	public void delTop()
	{
		if(heapSize==0)
			return;
		Methods.swap(this.heap,0,--heapSize);
		this.heaplify(0);
	}
	@Override
	public void insert(int key)
	{
		heap.add(Integer.MAX_VALUE);
		this.changeKey(heapSize++, key);
	}
	@Override
	public void clear()
	{
		heapSize = 0;
	}
	@Override
	public boolean changeKey(int i, int key)
	{
		if(i<0 || i>=heapSize)
			return false;
		if(key > heap.get(i))
			return false;
		
		heap.set(i,key);
		while(i>0 && heap.get(Heapx.parent(i))>heap.get(i))
		{
			Methods.swap(this.heap,i,Heapx.parent(i));
			i = Heapx.parent(i);
		}
		return true;
			
	}
	@Override
	public int get(int i)
	{
		return heap.get(i);
	}
	@Override
	public int getSize()
	{
		return heapSize;
	}
	private void heaplify(int i)
	{
		int smallest = i;
		int l = Heapx.left(i);
		int r = Heapx.right(i);
		
		if(l<heapSize && heap.get(l)<heap.get(i))
		{
			smallest = l;
		}

		if(r<heapSize && heap.get(r)<heap.get(smallest))
		{
			smallest = r;
		}
		if(smallest!=i)
		{
			Methods.swap(this.heap,i,smallest);
			heaplify(smallest);
		}
	}

	
}
/**
 * Max heap
 * @author Administrator
 *
 */
class MaxHeap implements Heapx
{
	private ArrayList<Integer> heap;
	private int heapSize;
	public MaxHeap()
	{
		this(Heapx.DEFAULT_SIZE);
	}
	public MaxHeap(int capacity)
	{
		this.heapSize = 0;
		this.heap = new ArrayList<>(capacity);
	}
	public MaxHeap(int[] array)
	{
		this(array.length);
		for(int i=0;i<array.length;i++)
		{
			this.insert(array[i]);
		}
	}
	public MaxHeap(int[] array, int heaplifyBuild)
	{
		this(array.length);
		int n = array.length;
		this.heapSize = n;
		for(int i=0;i<n;i++)
		{
			this.heap.add(array[i]);
		}
		for(int i=(n-1)/2;i>=0;i--)
		{
			this.heaplify(i);
		}
	}
	@Override
	public boolean isEmpty()
	{
		return heapSize==0;
	}
	@Override
	public int getTop()
	{
		if(heapSize==0)
			return -1;
		return heap.get(0);
	}
	@Override
	public void delTop()
	{
		if(heapSize==0)
			return;
		Methods.swap(this.heap,0,--heapSize);
		this.heaplify(0);
	}
	@Override
	public void insert(int key)
	{
		heap.add(Integer.MIN_VALUE);
		this.changeKey(heapSize++, key);
	}
	@Override
	public void clear()
	{
		heapSize = 0;
	}
	@Override
	public boolean changeKey(int i, int key)
	{
		if(i<0 || i>=heapSize)
			return false;
		if(key < heap.get(i))
			return false;
		heap.set(i,key);
		while(i>0 && heap.get(Heapx.parent(i))<heap.get(i))
		{
			Methods.swap(this.heap,i,Heapx.parent(i));
			i = Heapx.parent(i);
		}
		return true;
	}
	@Override
	public int get(int i)
	{
		return heap.get(i);
	}
	@Override
	public int getSize()
	{
		return heapSize;
	}
	private void heaplify(int i)
	{
		int largest = i;
		int l = Heapx.left(i);
		int r = Heapx.right(i);
		
		if(l<heapSize && heap.get(l)>heap.get(i))
		{
			largest = l;
		}
		
		if(r<heapSize && heap.get(r)>heap.get(largest))
		{
			largest = r;
		}
		
		if(largest!=i)
		{
			Methods.swap(this.heap,i,largest);
			heaplify(largest);
		}
	}
}
