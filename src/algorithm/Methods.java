package algorithm;

import java.util.ArrayList;

public class Methods {
	
	public static void swap(int[] array, int i, int j)
	{
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
	
	public static void swap(ArrayList<Integer> list, int i, int j)
	{
		Integer temp = list.get(i);
		list.set(i,list.get(j));
		list.set(j,temp);
	}
	public static void printArray(int[] array)
	{
		for(int i=0;i<array.length;i++)
		{
			System.out.print(array[i]+" ");
		}
	}
	public static void printlnArray(int[] array)
	{
		printArray(array);
		System.out.println();
	}
	public static void main(String[] args)
	{
		ArrayList<Integer> a = new ArrayList<>();
		a.add(3);
		a.add(5);
		Methods.swap(a,0,1);
		System.out.println(a.get(0)+" "+a.get(1));
		int[] x = {1,9};
		Methods.swap(x,0,1);
		System.out.println(x[0]+" "+x[1]);
		
	}
}
