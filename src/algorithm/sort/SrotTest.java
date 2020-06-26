package algorithm.sort;

import algorithm.Ch2;
import algorithm.Generator;
import algorithm.Methods;
import algorithm.Test;
import algorithm.datastructure.Heap;

public class SrotTest {

	public static void heapSort(int[] array)
	{
		Heap heap =new Heap(array,Heap.MAX_HEAP);
		for(int i=array.length-1;i>=0;i--)
		{
			array[i] = heap.getTop();
			heap.delTop();
		}
	}
	public static void heapSort(int[] array, int heaplifyBuild)
	{
		Heap heap = new Heap(array,Heap.MAX_HEAP,1);
		for(int i=array.length-1;i>=0;i--)
		{
			array[i] = heap.getTop();
			heap.delTop();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = Generator.randIntArray(80000,1,100);
		int[] b = new int[a.length];
		System.arraycopy(a,0,b, 0, a.length);
		int[] c = new int[a.length];
		System.arraycopy(a,0,c, 0, a.length);
//		Methods.printArray(a);
//		System.out.println();
//		Methods.printArray(b);
//		System.out.println();
//		heapSort(a);
//		System.out.println();
//		Methods.printArray(a);
		Test.runtime(()->{
			heapSort(a);
		});
		Test.runtime(()->{
			heapSort(c,1);
		});
		Test.runtime(()->{
			Ch2.insertionSort(b);
		});

	}

}
