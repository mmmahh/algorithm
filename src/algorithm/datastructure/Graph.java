package algorithm.datastructure;

import java.util.Random;

public class Graph {
	public static final int LIST = 1;
	public static final int MATRIX = 2;
	
	private Graphx g;
	
	public Graph(int v, int type)
	{
		this(v,type,0);
	}
	public Graph(int v, int type, int initWeight)
	{
		if(type==Graph.LIST)
		{
			g = new GraphList(v,initWeight);
		}
		else
		{
			g = new GraphMatrix(v,initWeight);
		}
	}
	public int getNumVertex()
	{
		return g.getNumVertex();
	}
	public int getNumEdge()
	{
		return g.getNumEdge();
	}
	public void setEdge(int v1, int v2)
	{
		g.setEdge(v1, v2);
	}
	public void setEdge(int v1, int v2, int weight)
	{
		g.setEdge(v1, v2, weight);
	}
	public void delEdge(int v1, int v2)
	{
		g.delEdge(v1, v2);
	}
	public void delEdge(int v1, int v2, int setWeight)
	{
		g.delEdge(v1, v2, setWeight);
	}
	public boolean isEdge(int v1, int v2)
	{
		return g.isEdge(v1, v2);
	}
	public int getWeight(int v1, int v2)
	{
		return g.getWeight(v1, v2);
	}
	public int getMark(int v)
	{
		return g.getMark(v);
	}
	public void setMark(int v, int val)
	{
		g.setMark(v, val);
	}
	
	public static void main(String[] args)
	{
		Graph g = new Graph(5,Graph.LIST,Integer.MIN_VALUE);
		Graph g2 = new Graph(5,Graph.MATRIX,Integer.MIN_VALUE);
		Random rand = new Random();
		for(int i=0;i<7;i++)
		{
			int v1 = rand.nextInt(5);
			int v2 = rand.nextInt(5);
			int weight = rand.nextInt(10);
			System.out.println(v1+"->"+v2+" weight:"+weight);
			g.setEdge(v1, v2, weight);
			g2.setEdge(v1, v2, weight);
		}
		System.out.println("g:"+g.getNumEdge()+" g2:"+g2.getNumEdge());
		
		for(int i=0;i<7;i++)
		{
			int v1 = rand.nextInt(5);
			int v2 = rand.nextInt(5);
			int w = g.getWeight(v1, v2);
			int w2 = g2.getWeight(v1, v2);
			System.out.println(v1+" "+v2+"\n   g:"+g.isEdge(v1, v2)+" weight:"+w+
										 "\n  g2:"+g2.isEdge(v1, v2)+" weight:"+w2);
		}
	}
		
}

interface Graphx
{
	int getNumVertex();
	int getNumEdge();
	
	void setEdge(int v1, int v2);
	void setEdge(int v1, int v2, int weight);
	void delEdge(int v1, int v2);
	void delEdge(int v1, int v2, int weight);
	boolean isEdge(int v1, int v2);
	int getWeight(int v1, int v2);
	int getMark(int v);
	void setMark(int v, int val);
	
}

class GraphList implements Graphx
{
	class Edge
	{
		int vertex;
		int weight;
		Edge next;
		public Edge()
		{
			vertex = 0;
			weight = 0;
			next = null;
		}
	}
	private int v;
	private int e;
	private Edge[] edges;
	private int[] mark;
	
	private int initWeight;
	
	public GraphList(int v)
	{
		this(v,0);
	}
	public GraphList(int v, int initWeight)
	{
		this.v = v;
		this.e = 0;
		this.edges = new Edge[v];
		this.mark = new int[v];
		for(int i=0;i<v;i++)
		{
			edges[i] = new Edge();
		}
		this.initWeight = initWeight;
	}
	@Override
	public int getNumVertex() {
		return v;
	}
	@Override
	public int getNumEdge() {
		return e;
	}
	@Override
	public void setEdge(int v1, int v2) {
		this.setEdge(v1, v2, 1);
	}
	@Override
	public void setEdge(int v1, int v2, int weight) {
		if(isVertex(v1) && isVertex(v2))
		{
			if(!isEdge(v1,v2))
			{
				Edge v1_v2 = new Edge();
				v1_v2.vertex = v2;
				v1_v2.weight = weight;
				
				Edge temp = edges[v1];
				while(temp.next!=null)
				{
					if(v2<temp.next.vertex)
					{
						v1_v2.next = temp.next;
						temp.next = v1_v2;
						this.e++;
						return;
					}
					else
					{
						temp = temp.next;
					}
				}
				
				temp.next = v1_v2;
				this.e++;
			}
		}
	}
	@Override
	public void delEdge(int v1, int v2) {
		if(this.isVertex(v1) && this.isVertex(v2))
		{
			Edge temp = this.edges[v1];
			while(temp.next!=null)
			{
				if(temp.next.vertex==v2)
				{
					temp.next = temp.next.next;
					this.e--;
					return;
				}
				else if(temp.next.vertex<v2)
				{
					temp = temp.next;
				}
				else
				{
					return;
				}
			}
		}
		
	}
	@Override
	public void delEdge(int v1, int v2, int weight)
	{
		this.delEdge(v1, v2);
	}
	@Override
	public boolean isEdge(int v1, int v2) {
		if(this.isVertex(v1) && this.isVertex(v2))
		{
			Edge temp = this.edges[v1];
			while(temp.next!=null)
			{
				if(temp.next.vertex==v2)
					return true;
				else if(temp.next.vertex<v2)
					temp = temp.next;
				else
					return false;
			}
		}
		return false;
	}
	@Override
	public int getWeight(int v1, int v2) {
		if(this.isVertex(v1) && this.isVertex(v2))
		{
			Edge temp = this.edges[v1];
			while(temp.next!=null)
			{
				if(temp.next.vertex==v2)
				{
					return temp.next.weight;
				}
				else if(temp.next.vertex<v2)
				{
					temp = temp.next;
				}
				else
				{
					return this.initWeight;
				}
			}
		}
		return this.initWeight;
	}
	@Override
	public int getMark(int v) {
		return this.mark[v];
	}
	@Override
	public void setMark(int v, int val) {
		this.mark[v] = val;
	}
	private boolean isVertex(int v)
	{
		return v>=0 && v<this.v;
	}
}
class GraphMatrix implements Graphx
{
	private int v;
	private int e;
	private int[][] matrix;
	private boolean[][] edge;
	private int[] mark;
	public GraphMatrix(int v)
	{
		this(v,0);
	}
	public GraphMatrix(int v, int initWeight)
	{
		this.v = v;
		this.e = 0;
		matrix = new int[v][v];
		edge = new boolean[v][v];
		mark = new int[v];
		for(int i=0;i<v;i++)
		{
			for(int j=0;j<v;j++)
			{
				this.matrix[i][j] = initWeight;
				this.edge[i][j] = false;
			}
		}
	}
	@Override
	public int getNumVertex() {
		return this.v;
	}
	@Override
	public int getNumEdge() {
		return this.e;
	}
	@Override
	public void setEdge(int v1, int v2) {
		this.setEdge(v1,v2,1);
		
	}
	@Override
	public void setEdge(int v1, int v2, int weight) {
		this.matrix[v1][v2] = weight;
		if(edge[v1][v2]==false)
		{
			this.edge[v1][v2] = true;
			this.e++;
		}
	}
	@Override
	public void delEdge(int v1, int v2) {
		this.delEdge(v1, v2, 0);
	}
	@Override
	public void delEdge(int v1, int v2, int weight)
	{
		this.matrix[v1][v2] = weight;
		this.edge[v1][v2] = false;
		this.e--;
	}
	@Override
	public boolean isEdge(int v1, int v2) {
		return this.edge[v1][v2];
	}
	@Override
	public int getWeight(int v1, int v2) {
		return this.matrix[v1][v2];
	}
	@Override
	public int getMark(int v) {
		return this.mark[v];
	}
	@Override
	public void setMark(int v, int val) {
		this.mark[v] = val;
	}
}