package algorithm.datastructure;

import java.util.logging.Logger;

import algorithm.Generator;

public class Stack {
	public static final int DEFAULT_CAPACITY = 30;
	public static final Logger log = Logger.getLogger("stack");
	
	private int top;
	private int[] stack;
	
	public Stack()
	{
		this(Stack.DEFAULT_CAPACITY);
	}
	public Stack(int capacity)
	{
		this.top = -1;
		this.stack = new int[capacity];
	}
	public boolean isEmpty()
	{
		return top==-1;
	}
	public void push(int x)
	{
		if(top<stack.length-1)
		{
			stack[++top] = x;
		}
	}
	public int pop()
	{
		if(top>=0)
		{
			return stack[top--];
		}
		else
		{
			log.warning("overflow");
			return -1;
		}
	}
	public int getTop()
	{
		if(top>=0)
		{
			return stack[top];
		}
		else
		{
			log.warning("overflow");
			return -1;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = Generator.randIntArray(10, 0, 30);
		Stack stack = new Stack();
		for(int i=0;i<5;i++)
		{
			stack.push(a[i]);
		}
		for(int i=0;i<5;i++)
		{
			System.out.println(stack.pop());
		}
		System.out.println(stack.isEmpty());
		stack.getTop();

	}

}

