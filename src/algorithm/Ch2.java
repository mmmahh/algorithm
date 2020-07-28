package algorithm;

import java.util.Arrays;

public class Ch2 {

	public static int bSearch(int[] a, int k)
	{
		int i = 0;
		int j = a.length-1;
		while(i<=j)
		{
			int q = (i+j)/2;
			if(a[q]<k)
			{
				i = q+1;
			}
			else if(a[q]>k)
			{
				j = q-1;
			}
			else
			{
				return q;
			}
		}
		return -1;
	}
	public static void insertionSort(int[] a)
	{
		for(int j=1;j<a.length;j++)
		{
			int key = a[j];
			int i = j-1;
			while(i>=0 && a[i]>key)
			{
				a[i+1] = a[i];
				i--;
			}
			a[i+1] = key;
		}
	}
	public static void insertionSort(int[] a, int p, int r)
	{
		for(int j=p+1;j<=r;j++)
		{
			int key = a[j];
			int i = j-1;
			while(i>=p && a[i]>key)
			{
				a[i+1] = a[i];
				i--;
			}
			a[i+1] = key;
		}
	}
	public static void merge(int[] a, int[] temp, int p, int q, int r)
	{
		for(int i=p;i<=r;i++)
		{
			temp[i] = a[i];
		}
		int j = p;
		int k = q+1;
		for(int i=p;i<=r;i++)
		{
			if(j>q)
			{
				a[i] = temp[k++];
			}
			else if(k>r)
			{
				a[i] = temp[j++];
			}
			else if(temp[j]<temp[k])
			{
				a[i] = temp[j++];
			}
			else
			{
				a[i] = temp[k++];
			}
		}
	}
	public static void mergeSort(int[] a,int[] temp, int p, int r)
	{
		if(p>=r)
			return;
		int q = (p+r)/2;
		mergeSort(a,temp,p,q);
		mergeSort(a,temp,q+1,r);
		merge(a,temp,p,q,r);
	}
	public static void mergeSort2(int[] a, int[] temp, int p, int r)
	{
		if((r-p)<=24)
		{
			insertionSort(a,p,r);
			return;
		}
		int q = (p+r)/2;
		mergeSort(a,temp,p,q);
		mergeSort(a,temp,q+1,r);
		merge(a,temp,p,q,r);
	}
	public static int[][] matrixMultiply(int[][] a, int[][] b)
	{
		int ra = a.length;
		int ca = a[0].length;
		int rb = b.length;
		int cb = b[0].length;
		if(ca!=rb)
			return null;
		int[][] c = new int[ra][cb];
		for(int i=0;i<ra;i++)
		{
			for(int j=0;j<cb;j++)
			{
				int temp = 0;
				for(int k=0;k<ca;k++)
				{
					temp += a[i][k] * b[k][j];
				}
				c[i][j] = temp;
			}
		}
		return c;
	}
//	public static int[][] matrixMultiplyThread(int[][] a, int[][] b)
//	{
//		int ra = a.length;
//		int ca = a[0].length;
//		int rb = b.length;
//		int cb = b[0].length;
//		if(ca!=rb)
//			return null;
//		int[][] c = new int[ra][cb];
//		
//		class Task extends Thread {
//			int i2;
//			int j2;
//			int[] a2;
//			int[] b2;
//			int c2;
//			public Task(int i, int j, int[] a, int[] b)
//			{
//				this.i2 = i;
//				this.j2 = j;
//				this.a2 = a;
//				this.b2 = b;
//				this.c2 = 0;
//			}
//			@Override
//			public void run()
//			{
//				c2 = 0;
//				for(int i=0;i<a2.length;i++)
//				{
//					c2 
//				}
//			}
//		}
//		for(int i=0;i<ra;i++)
//		{
//			for(int j=0;j<cb;j++)
//			{
//				
//				Thread t = new Thread(new Runnable() {
//					@Override
//					public void run() {
//						int temp = 0;
//						for(int k=0;k<ca;k++)
//						{
//							temp += a[i][k]*b[k][j];
//						}
//						c[i][j] = temp;
//					}
//				});
//				
//			}
//		}
//		return c;
//	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final int N = 20000000;
		final int MIN = 0;
		final int MAX = 100000000;
		
//		int[] a = Generator.randIntArray(N, MIN, MAX);
//		Test.runtime(()->{Ch2.insertionSort(a);});
//		Test.isorderd(a, Test.INCREASE_ORDER);
		
		
//		int[] b = Generator.randIntArray(N, MIN, MAX);
//		int[] temp = new int[N];
//		Test.isorderd(b,Test.INCREASE_ORDER);
//		Test.runtime(()->{Ch2.mergeSort(b,temp, 0, b.length-1);});
//		Test.isorderd(b,Test.INCREASE_ORDER);
//		
//		int[] c = Generator.randIntArray(N, MIN, MAX);
//		Test.isorderd(c, Test.INCREASE_ORDER);
//		Test.runtime(()->{Ch2.mergeSort2(c, temp, 0, c.length-1);});
//		Test.isorderd(c, Test.INCREASE_ORDER);
//		double[] x = {
//			56.1, 42.9, 56.2, 46.3, 80.3, 50.1, 42.9, 41.8, 61.2, 66.2,
//			61.9, 65.1, 64.3, 71.6, 46.2, 53.9, 56.6, 67.3, 66.5, 70.4,
//			40.2, 65.1, 29.3, 58.7, 52.1, 55.6, 58.2, 67.9, 65.1, 68.2,
//			54.6, 53.0, 74.2, 82.7, 60.2, 57.3, 62.2, 68.8, 67.1, 61.2,
//			41.5, 78.1, 55.6, 57.2, 78.3, 38.9, 75.8, 58.5, 51.2, 74.3
//		};
//		Arrays.sort(x);
//		for(int i=0;i<x.length;i++)
//		{
//			if(i!=0 && i%10==0)
//			{
//				System.out.println();
//			}
//			System.out.print(x[i]+" ");
//		}
		int[][] a = {{1,1,2},
					 {2,1,2}};
		int[][] b = {{1,1},
					 {2,2},
					 {3,3}};
		
		int[][] c = Ch2.matrixMultiply(a, b);
		for(int i=0;i<c.length;i++)
		{
			for(int j=0;j<c[0].length;j++)
			{
				System.out.print(c[i][j]+" ");
			}
			System.out.println();
		}
	}

}
