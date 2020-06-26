package algorithm;

import java.util.Random;

public class Generator {
	public static Random rand = new Random();
	public static int[] randIntArray(int n, int min, int max)
	{
		int[] a = new int[n];
		for(int i=0;i<n;i++)
		{
			a[i] = rand.nextInt(max-min+1) + min;
		}
		return a;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
