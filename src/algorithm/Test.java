package algorithm;

public class Test {
	public static final int INCREASE_ORDER = 1;
	public static final int DECREASE_ORDER = 2;
	public static void runtime(Function f)
	{
		double start = System.nanoTime();
		f.run();
		double end = System.nanoTime();
		System.out.printf("cost time: %,.6fms\n",(end-start)/1000000);
	}
	public static void isorderd(int[] a, int type)
	{
		if(type==INCREASE_ORDER)
		{
			for(int i=0;i<a.length-1;i++)
			{
				if(a[i+1]<a[i])
				{
					System.out.println("false");
					return;
				}
			}
			System.out.println("true");
		}
		else if(type==DECREASE_ORDER)
		{
			for(int i=0;i<a.length-1;i++)
			{
				if(a[i+1]>a[i])
				{
					System.out.println("false");
					return;
				}
			}
			System.out.println("true");
		}
		else
		{
			System.out.println("error");
		}
	}
}
