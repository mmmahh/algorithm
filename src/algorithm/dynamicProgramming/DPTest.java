package algorithm.dynamicProgramming;

public class DPTest {

	/***************************************
	 * 钢条切割
	 * 长度 n 的钢条最大收益 为 r(n)  
	 * 		r(0) = 0
	 * 		r(n) = max{p[i] + r(n-i) | 1<=i<=n}   
	 */
	/*
	 * p[0] should be 0
	 * p.length > n
	 * 
	 */
	public static int cutRod(int[] p, int n)
	{
		if(n==0)
			return 0;
		int q = Integer.MIN_VALUE;
		for(int i=1;i<=n;i++)
		{
			q = Math.max(q,p[i]+cutRod(p,n-i));
		}
		return q;
	}
	public static int memoizedCutRod(int[] p, int n)
	{
		int[] r = new int[n+1];
		r[0] = 0;
		for(int i=1;i<=n;i++)
		{
			r[i] = Integer.MIN_VALUE;
		}
		return memoizedCutRodAux(p,n,r);
	}
	private static int memoizedCutRodAux(int[] p,int n, int[] r)
	{
		int q = Integer.MIN_VALUE;
		if(r[n]>=0)
			return r[n];
		if(n==0)
			q = 0;
		else
		{
			for(int i=1;i<=n;i++)
			{
				q = Math.max(q,p[i]+cutRod(p,n-i));
			}
		}
		
		r[n] = q;
		return q;
	}
	
	public static int bottomUpCutRod(int[] p, int n)
	{
		int[] r = new int[n+1];
		r[0] = 0;
		for(int j=1;j<=n;j++) // calculate r[j] j from 1 to n
		{
			int q = Integer.MIN_VALUE;
			for(int i=1;i<=j;i++)
			{
				q = Math.max(q,p[i]+r[j-i]);
			}
			r[j] = q;
		}
		return r[n];
	}
	public static class Answer
	{
		int[] r;
		int[] s;
		public Answer(int[] r, int[] s)
		{
			this.r = r;
			this.s = s;
		}
	}
	public static Answer extendedBottomUpCutRod(int[] p, int n)
	{
		int[] r = new int[n+1];
		int[] s = new int[n+1]; 
		//s[j]  means: if the length of rod is j then the s[j] should be the length of rod cut(as a part of the solution), 
		//             and s[j-s[j]] should be the solution of the left length of rod(j-s[j])
		//             until the length is 0(s[0]=0), the cut over(don't need to cut) 
		r[0] = 0;
		for(int j=1;j<=n;j++) // calculate r[j] j from 1 to n
		{
			int q = Integer.MIN_VALUE;
			for(int i=1;i<=j;i++)
			{
				//q = Math.max(q,p[i]+r[j-i]);
				if(q<p[i]+r[j-i])
				{
					q = p[i]+r[j-i];
					s[j] = i;
				}
			}
			r[j] = q;
		}
		return new Answer(r,s);
	}
	public static void printCutRodSolution(int[] p, int n)
	{
		Answer ans = extendedBottomUpCutRod(p,n);
		System.out.println("max value:"+ans.r[n]);
		while(n>0)
		{
			System.out.print(ans.s[n]+" ");
			n -= ans.s[n];
		}
	}
	/***************************************/
	/***************************************
	 * 矩阵链乘法
	 * p0 p1...pn
	 * Ai is p(i-1) X p(i)
	 *  m[i,j] = 0                                               i=j
	 *  	   = min{m[i,k]+m[k+1,j]+p[i-1]*p[k]*p[j] | i<=k<j}  i<j
	 */
	public static class Answer2
	{
		int[][] m;
		int[][] s;
		public Answer2(int[][] m, int[][] s)
		{
			this.m = m;
			this.s = s;
		}
	}
	
	public static Answer2 matrixChainOrder(int[] p)
	{
		int n = p.length-1;
		int[][] m = new int[n+1][n+1];
		int[][] s = new int[n+1][n+1];
		for(int i=1;i<=n;i++)
		{
			m[i][i] = 0;
		}
		for(int l=2;l<=n;l++)
		{
			for(int i=1;i<=n-l+1;i++)
			{
				int j = i+l-1;
				m[i][j] = Integer.MAX_VALUE;
				for(int k=i;k<=j-1;k++)
				{
					int q = m[i][k] + m[k+1][j] + p[i-1]*p[k]*p[j];
					if(q<m[i][j])
					{
						m[i][j] = q;
						s[i][j] = k;
					}
				}
			}
		}
		return new Answer2(m,s);
	}
	public static void printMatrixChainOrderSolution(int[] p)
	{
		Answer2 ans = matrixChainOrder(p);
		System.out.println("min value:"+ans.m[1][p.length-1]);
		
		printOptimalParens(ans.s,1,p.length-1);
	}
	private static void printOptimalParens(int[][] s, int i, int j)
	{
		if(i==j)
			System.out.print("A"+i);
		else
		{
			System.out.print("(");
			printOptimalParens(s,i,s[i][j]);
			printOptimalParens(s,s[i][j]+1,j);
			System.out.print(")");
		}
	}

	/****************************************/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] p = {0,1,5,8,9,10,17,17,20,24,30};
		algorithm.Test.runtime(()->{
			cutRod(p,p.length-1);
		});
		System.out.println(cutRod(p,p.length-1));
		algorithm.Test.runtime(()->{
			memoizedCutRod(p,p.length-1);
		});
		System.out.println(memoizedCutRod(p,p.length-1));
		algorithm.Test.runtime(()->{
			bottomUpCutRod(p,p.length-1);
		});
		System.out.println(bottomUpCutRod(p,p.length-1));
		
		printCutRodSolution(p,p.length-1);
		
		int[] p1 = {5,10,3,12,5,50,6};
		int[] p2 = {30,35,15,5,10,20,25};
		printMatrixChainOrderSolution(p2);
	}

}
