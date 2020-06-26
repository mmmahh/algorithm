package algorithm.division;

public class DivisionTest {

	/************************************************************
	 * 最大子数组
	 * version 1 子数组长度>0
	 * version 2 子数组长度>=0
	 */
	public static class Answer
	{
		int low;
		int high;
		int sum;
		public Answer(int l, int h, int s)
		{
			low = l;
			high = h;
			sum = s;
		}
	}
	public static Answer maxSubArrayCrossMid(int[] A, int low, int mid, int high)
	{
		int leftSum = Integer.MIN_VALUE;
		int rightSum = Integer.MIN_VALUE;
		int max_i = mid;
		int max_j = mid+1;
		
		int temp = 0;
		for(int i=mid;i>=low;i--)
		{
			temp += A[i];
			if(temp>leftSum)
			{
				leftSum = temp;
				max_i = i;
			}
		}
		temp = 0;
		for(int j=mid+1;j<=high;j++)
		{
			temp += A[j];
			if(temp>rightSum)
			{
				rightSum = temp;
				max_j = j;
			}
		}
		return new DivisionTest.Answer(max_i,max_j,leftSum+rightSum);
	}
	public static Answer maxSubArray(int[] A, int low , int high)
	{
		if(low>high)
			return new Answer(low,high,0);
		if(low==high)
		{
			if(A[low]>0)
				return new Answer(low,high,A[low]);
			else
				return new Answer(-1,0,0);
		}
		
		int mid = (low+high)/2;
		
		Answer left = maxSubArray(A,low,mid);
		Answer right = maxSubArray(A,mid+1,high);
		Answer crossMid = maxSubArrayCrossMid(A,low,mid,high);
		
		if(left.sum>=right.sum && left.sum>=crossMid.sum)
			return left;
		else if(right.sum>=crossMid.sum)
			return right;
		else
			return crossMid;
	}
	/************************************************************/
	/************************************************************
	 * 矩阵乘法
	 * 
	 */
	
	
	/************************************************************/
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = {13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
//		int[] B = {-1,-2,-3,-1,-5,-3,-8,-3,-4,-2};
		Answer ans = DivisionTest.maxSubArray(A, 0, A.length-1);
		System.out.println(ans.low + " "+ans.high+" "+ans.sum);
	}

}
