import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제6
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1100-B
 * DFS를 이용한 방향 그래프에서 주기 찾기 
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
		}
		public void printGraph() {
			for(int i=0; i<V; ++i) {
				System.out.print(i);
				for(int node: edge.get(i))
					System.out.print(" -> " + node);
				System.out.println();
			}
		}
	}
	
	public static boolean DFS(Graph G, int s, boolean[] visited, Stack<Integer> stack) {
		visited[s] = true;
		stack.push(s);
		while(!stack.isEmpty()) {
			int curr = stack.peek();
			boolean found = false;
			for(int w: G.edge.get(curr)) {
				if(!visited[w]) {
					stack.push(w);
					visited[w] = true;
					found = true;
					break;
				}
				else if(stack.contains(w)) return true;
			}
			if(!found) stack.pop();
		}
		return false;
	}
	
	public static boolean findCycle(Graph G, int N) {
		boolean[] visited = new boolean[N];
		for(int v=0; v<N; ++v) {
			if(!visited[v]) {
				Stack<Integer> stack = new Stack<>();
				if(DFS(G, v, visited, stack)) return true;
			}
		}
		return false;
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
				System.out.println(findCycle(graph, N));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
