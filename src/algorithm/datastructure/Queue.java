package algorithm.datastructure;

import java.util.logging.Logger;

import algorithm.Generator;

public class Queue {
	public static final int DEFAULT_CAPACITY = 30;
	public static final Logger log = Logger.getLogger("queue");
	
	private int[] q;
	private int head;
	private int tail;
	public Queue()
	{
		this(Queue.DEFAULT_CAPACITY);
	}
	public Queue(int capacity)
	{
		this.q = new int[capacity+1];
		this.head = 0;
		this.tail = 0;
	}
	public void enqueue(int x)
	{
		if((tail+1)%q.length!=head)
		{
			q[tail] = x;
			tail = (tail+1) % q.length;
		}
		else
		{
			log.warning("overflow");
		}
	}
	public int dequeue()
	{
		if(head!=tail)
		{
			int x = q[head];
			head = (head+1) % q.length;
			return x;
		}
		else
		{
			log.warning("empty");
			return -1;
		}
	}
	public boolean isEmpty()
	{
		return head==tail;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = Generator.randIntArray(10, 0, 30);
		Queue q = new Queue(4);
//		for(int i=0;i<10;i++)
//		{
//			q.enqueue(a[i]);
//		}
//		for(int i=0;i<10;i++)
//		{
//			System.out.println(q.dequeue());
//		}
		q.enqueue(3);
		q.dequeue();
		q.enqueue(4);
		q.enqueue(5);
		q.enqueue(6);
		q.enqueue(7);
		for(int i=0;i<4;i++)
		{
			System.out.println(q.dequeue());
			System.out.println(q.isEmpty());
		}
	}

}
