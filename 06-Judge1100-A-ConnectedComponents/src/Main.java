import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제6
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1100-A
 * BFS를 이용한 무방향 그래프의 연결 여부 
 */

public class Main {
	public static class Graph{
		int V;
		List<ArrayList<Integer>> edge;
		public Graph(int V) {
			this.V = V;
			edge = new ArrayList<>();
			
			for(int i=0; i<V; ++i)
				edge.add(new ArrayList<>());
		}
		public void addEdge(int a, int b) {
			edge.get(a).add(b);
			edge.get(b).add(a);
		}
	}
	
	public static int BFS(Graph G, int s, boolean[] visited) {	
		Queue<Integer> queue = new LinkedList<>();
		visited[s] = true;
		queue.add(s);
		
		int ret = 0;
		while(!queue.isEmpty()) {
			int v = queue.poll();
			++ret;
            for(int w: G.edge.get(v)) {
            	if(!visited[w]) {
            		visited[w] = true;
            		queue.add(w);
            	}
            }
		}
		return ret;
	}
	
	public static StringBuilder connectedComponents(Graph G, int N) {
		boolean[] visited = new boolean[N];
		int subGraph = 0, maxNode = 0;
		for(int v=0; v<N; ++v) {
			if(!visited[v]) {
				maxNode = Math.max(maxNode, BFS(G, v, visited));
				++subGraph;
			}
		}
		return new StringBuilder(subGraph + " " + maxNode);
	}
	
	public static void main(String[] args) {
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(br.readLine());
			for(int t=0; t<T; ++t) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int N = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				Graph graph = new Graph(N);
				st = new StringTokenizer(br.readLine());
				for(int i=0; i<E; ++i) {
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					graph.addEdge(a, b);
				}
				System.out.println(connectedComponents(graph, N));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
