package com.zonda.algorithm.graph;

import java.util.Arrays;

public class DijkstraAlgorithm {

	//表示两个点之间无法直接连通
	private static final int inf = Integer.MAX_VALUE;
	
	/**
	 * <p>求无向图中节点u到所有节点的最短距离</p>
	 * @param graph 图的相邻矩阵
	 * @param n 总节点数
	 * @param u 
	 * @return
	 */
	public static final int[] dijkstra(int[][] graph, int n, int u){
		
		//记录节点u到对应下标节点的权值
		int dist[] = new int[n];
		//用于记录小标对应节点是否已被访问
		boolean s[] = new boolean[n];
		Arrays.fill(s, false);
		//默认所有节点不通
		Arrays.fill(dist, inf);
		int min,v;
		for (int i = 0; i < n; i++) {
			//节点u到i的权值
			dist[i] = graph[u][i];
		}
		s[u] = true;
		while(true){
			min  = inf;
			//v用来记录当前节点的下一个节点里最小权值的节点
			v = -1;
			//找到最小的权值
			for(int i = 0; i < n; i++){
				
				if(!s[i]){
					if(dist[i] < min){
						
						min = dist[i];
						v = i;
					}
				}
			}
			//找不到更短的路径了
			if(v == -1){
				break;
			}
			//更新最短路径
			s[v] = true;
			for (int i = 0; i < n; i++) {
				
				if(!s[i] && graph[v][i] != inf
						 && dist[v] + graph[v][i] < dist[i]){
					dist[i] = dist[v] + graph[v][i];
				}
			}
		}// end of while
		return dist;
	}// end of dijkstra()
	
	public static void main(String[] args) {
		
		final int[][] UNDIGRAPH = {
				
				{  0,   1,   4, inf, inf, inf},
				{  1,   0,   2,   7,   5, inf},
				{  4,   2,   0, inf,   1, inf},
				{inf,   7, inf,   0,   3,   2},
				{inf,   5,   1,   3,   0,   6},
				{inf, inf, inf,   2,   6,   0}
		};
		
		final int[][] DIGRAPH = {
				
				{  0,  inf,   4, inf, inf, inf},
				{  1,    0, inf,   7,   5, inf},
				{inf,    2,   0, inf, inf, inf},
				{inf,  inf, inf,   0,   3, inf},
				{inf,  inf,   1,   3,   0, inf},
				{inf,  inf, inf, inf,   6,   0}
		};
		
		int[] dist = dijkstra(DIGRAPH, 6, 1);
		
		for (int i : dist) {
			
			System.out.println(i + " ");
		}
	}
}
