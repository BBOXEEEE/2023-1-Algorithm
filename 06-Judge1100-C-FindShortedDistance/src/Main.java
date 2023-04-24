import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제6
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1100-C
 * 우선순위 큐 기반 다익스트라 알고리즘을 이용한 가중치 그래프에서 최단 거리 찾기 
 */

public class Main {
	public static final int INF = Integer.MAX_VALUE;
	
	public static class WeightedEdge implements Comparable<WeightedEdge>{
		int to, weight;
		public WeightedEdge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
		@Override
		public int compareTo(WeightedEdge o) {
			return this.weight - o.weight;
		}
	}
	public static class Graph{
		int from;
		List<List<WeightedEdge>> adj;
		public Graph(int from) {
			this.from = from;
			adj = new ArrayList<>();
			for(int i=0; i<from; ++i)
				adj.add(new ArrayList<>());
		}
		public void addEdge(int start, int end, int weight) {
			WeightedEdge edge = new WeightedEdge(end, weight);
			adj.get(start).add(edge);
		}
		public void printGraph() {
			for (int i = 0; i < from; i++) {
				List<WeightedEdge> list = adj.get(i);
				for (int j = 0; j < list.size(); j++) {
					System.out.println("vertex-"+i+" is connected to "+list.get(j).to+" with weight "+list.get(j).weight);
				}
			}
		}
	}
	
	public static StringBuilder dijkstra(Graph G, int start, int N, int[] dest) {
		int[] dist = new int[N];
		Arrays.fill(dist, INF);
		boolean[] visited = new boolean[N];
		PriorityQueue<WeightedEdge> pq = new PriorityQueue<>();
		pq.offer(new WeightedEdge(start, dist[start]));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			WeightedEdge node = pq.poll();
			int v = node.to;
			visited[v] = true;
			for(WeightedEdge edge: G.adj.get(v)) {
				int w = edge.to, weight = edge.weight;
				if(!visited[w] && dist[v]!=INF && dist[v]+weight<dist[w]) {
					dist[w] = dist[v] + weight;
					pq.offer(new WeightedEdge(w, dist[w]));
				}
			}
		}
		StringBuilder ret = new StringBuilder();
		for(int distance: dest) {
			if(dist[distance] == INF) ret.append(-1 + " ");
			else ret.append(dist[distance] + " ");
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
				int S = graphInfo[2];
				int K = graphInfo[3];
				int[] dest = new int[K];
				System.arraycopy(graphInfo, 4, dest, 0, K);
				Graph graph = new Graph(N);
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int i=0; i<E; ++i) {
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					int c = Integer.parseInt(st.nextToken());
					graph.addEdge(a, b, c);
				}
				System.out.println(dijkstra(graph, S, N, dest));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
