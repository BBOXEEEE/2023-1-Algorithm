import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제9
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1104-B
 * 최소신장트리 - 핵심 간선 찾기  
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
		int src, dest, weight, idx;
		public Edge(int src, int dest, int weight, int idx) {
			this.src = src;
			this.dest = dest;
			this.weight = weight;
			this.idx = idx;
		}
		@Override
		public int compareTo(Edge other) {
			return this.weight - other.weight;
		}
	}
	
	public static int kruskal(List<Edge> G, int N, int ignoreIndex) {
		UnionFind uf = new UnionFind(N);
		
		int weight = 0;
		for(Edge edge: G) {
			if(edge.idx == ignoreIndex) continue;
			if(uf.find(edge.src) != uf.find(edge.dest)) {
				uf.union(edge.src, edge.dest);
				weight += edge.weight;
			}
		}
		return weight;
	}
	
	public static List<Integer> findCoreEdges(List<Edge> G, int N){
		Collections.sort(G);
		int mstWeight = kruskal(G, N, -1);
		
		List<Integer> coreEdges = new ArrayList<>();
		for(Edge edge: G) {
			int weight = kruskal(G, N, edge.idx);
			if(weight != mstWeight) coreEdges.add(edge.idx);
		}
		
		if(coreEdges.size() == 0) coreEdges.add(-1);
		Collections.sort(coreEdges);
		return coreEdges;
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
					int idx = 0;
					for(int i=0; i<edgeInfo.length-2; i+=3) {
						G.add(new Edge(edgeInfo[i], edgeInfo[i+1], edgeInfo[i+2], idx++));
					}
					System.out.println(findCoreEdges(G, N).stream().
							map(String::valueOf).collect(Collectors.joining(" ")));
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}