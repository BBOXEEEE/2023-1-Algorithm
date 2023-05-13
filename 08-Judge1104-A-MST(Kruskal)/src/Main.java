import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제9
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1104-A
 * 최소신장트리 - Union-find 자료구조를 이용한 Kruskal 알고리즘 
 */

class UnionFind{
	int[] parent;
	
	public UnionFind(int N) {
		parent = new int[N];
		for(int i=0; i<N; ++i)
			parent[i] = i;
	}
	
	public int find(int x) {
		if(parent[x] == x) return x;
		return find(parent[x]);
	}
	
	public void union(int x, int y) {
		x = find(x); y = find(y);
		if(x<=y) parent[y] = x;
		else parent[x] = y;
	}
}
public class Main {
	public static class Edge implements Comparable<Edge>{
		int src, dest, weight;
		public Edge(int src, int dest, int weight) {
			this.src = src;
			this.dest = dest;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge other) {
			return this.weight - other.weight;
		}
	}
	
	public static int kruskal(List<Edge> G, int N) {
		UnionFind uf = new UnionFind(N);
		Collections.sort(G);
		
		int ret = 0;
		for(Edge edge: G) {
			if(uf.find(edge.src) != uf.find(edge.dest)) {
				uf.union(edge.src, edge.dest);
				ret += edge.weight;
			}
		}
		return ret;
	}
	
	public static void main(String[] args) {
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(br.readLine());
			for(int t=0; t<T; ++t) {
				int[] graphInfo = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt).toArray();
				int N = graphInfo[0];
				int E = graphInfo[1];
				if(E==0) System.out.println(0);
				else {
					int[] edgeInfo = Arrays.stream(br.readLine().split(" "))
							.mapToInt(Integer::parseInt).toArray();
					List<Edge> G = new ArrayList<>();
					for(int i=0; i<edgeInfo.length-2; i+=3) {
						G.add(new Edge(edgeInfo[i], edgeInfo[i+1], edgeInfo[i+2]));
					}
					System.out.println(kruskal(G, N));
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}