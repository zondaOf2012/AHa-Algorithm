package com.zonda.algorithm.graph;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * <p>
 * 邻接矩阵实现图，并实现深度优先搜索和广度优先搜索
 * </p>
 * 
 * @author zonda
 * 
 */
public class AdjMatrixGraph {

	// 用于表示图的顶点
	private ArrayList<Object> vertexList;

	// 邻接矩阵，用于表示图的边
	private int[][] edges;

	// 当前图的边的总数
	private int numOfEdges;

	/**
	 * @param n
	 *            顶点总数
	 */
	public AdjMatrixGraph(int n) {

		vertexList = new ArrayList<Object>(n);

		edges = new int[n][n];

		numOfEdges = 0;
	}

	// 获取边数
	public int getNumOfEdges() {
		return numOfEdges;
	}

	// 获取顶点数
	public int getNumOfVertex() {
		return vertexList.size();
	}

	// 获取结点i的数据
	public Object getValueByIndex(int i) {
		return vertexList.get(i);
	}

	// 获取边v1,v2的权值
	public int getWeight(int v1, int v2) {
		return edges[v1][v2];
	}

	// 插入结点
	public void insertVertex(Object vertex) {
		vertexList.add(vertex);
	}

	// 插入边及对应的权值
	public void insertEdge(int v1, int v2, int weight) {
		edges[v1][v2] = weight;
		numOfEdges++;
	}

	// 删除边
	public void deleteEdge(int v1, int v2) {
		edges[v1][v2] = 0;
		numOfEdges--;
	}

	// 获取第index个结点的第一个邻接点
	public int getFirstNeighbor(int index) {
		for (int j = 0; j < vertexList.size(); j++) {
			if (edges[index][j] > 0) {
				return j;
			}
		}
		return -1;
	}

	/**
	 * 根据v1的前一个邻接点v2，来取得下一个邻接节点
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public int getNextNeighbor(int v1, int v2) {
		for (int j = v2 + 1; j < vertexList.size(); j++) {
			if (edges[v1][j] > 0) {
				return j;
			}
		}
		return -1;
	}

	// 深度优先遍历
	public void depthFirstSearch() {

		// 记录结点是否被访问的数值
		boolean[] isVisited = new boolean[getNumOfVertex()];

		for (int i = 0; i < isVisited.length; i++) {

			// 把所有结点置为未访问
			isVisited[i] = false;
		}

		for (int i = 0; i < isVisited.length; i++) {

			// 因为对于非连通图来说，并不是通过一个结点就一定可以遍历所有结点
			if (!isVisited[i]) {

				depthFirstSearch(isVisited, i);
			}
		}
	}

	/**
	 * <p>
	 * <li>1，访问初始结点v，并标记结点v为已访问。
	 * <li>2，查找结点v的第一个邻接结点w。
	 * <li>3，若w存在，则继续执行4，否则算法结束。
	 * <li>4，若w未被访问，对w进行深度优先遍历递归（即把w当做另一个v，然后进行步骤123）。
	 * <li>5，查找结点v的w邻接结点的下一个邻接结点，转到步骤3。
	 * </p>
	 * 
	 * @param isVisited
	 * @param i
	 */
	private void depthFirstSearch(boolean[] isVisited, int i) {

		System.out.println(getValueByIndex(i) + " -> ");

		isVisited[i] = true;

		int w = getFirstNeighbor(i);

		while (w != -1) {

			if (!isVisited[w]) {

				depthFirstSearch(isVisited, w);
			}
			w = getNextNeighbor(i, w);
		}
	}

	// 广度优先遍历
	public void breadthFirstSearch() {

		boolean[] isVisited = new boolean[getNumOfVertex()];

		for (int i = 0; i < isVisited.length; i++) {

			isVisited[i] = false;
		}

		for (int i = 0; i < isVisited.length; i++) {

			if (!isVisited[i]) {

				breadthFirstSearch(isVisited, i);
			}
		}
	}

	/**
	 * <p>
	 * <li>1，访问初始结点v并标记结点v为已访问。
	 * <li>2，结点v入队列
	 * <li>3，当队列非空时，继续执行，否则算法结束。
	 * <li>4，出队列，取得队头结点u。
	 * <li>5，查找结点u的第一个邻接结点w。
	 * <li>6，若结点u的邻接结点w不存在，则转到步骤3；否则循环执行以下三个步骤：
	 * 
	 * <pre>
	 * <li>①若结点w尚未被访问，则访问结点w并标记为已访问。
	 * <li>②结点w入队列
	 * <li>③查找结点u的继w邻接结点后的下一个邻接结点w，转到步骤6。
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param isVisited
	 * @param i
	 */
	private void breadthFirstSearch(boolean[] isVisited, int i) {

		int u, w;

		LinkedList<Integer> queue = new LinkedList<Integer>();

		System.out.println(getValueByIndex(i) + " -> ");
		// 访问结点i,置为已访问
		isVisited[i] = true;
		// 结点入队列
		queue.add(i);
		while (!queue.isEmpty()) {

			u = queue.removeFirst();

			w = getFirstNeighbor(u);

			while (w != -1) {

				if (!isVisited[w]) {

					System.out.println(getValueByIndex(w) + " -> ");

					isVisited[w] = true;

					queue.add(w);
				}
				w = getNextNeighbor(u, w);
			}
		}
	}
}
