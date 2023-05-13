import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제9
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1104-A
 * 최소신장트리 - 우선순위 큐를 이용한 Prim 알고리즘 
 */

public class Main {
	public static class Edge implements Comparable<Edge>{
		int dest, weight;
		public Edge(int dest, int weight) {
			this.dest = dest;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge other) {
			return this.weight - other.weight;
		}
	}
	
	public static int prim(List<List<Edge>> G) {
		int start = 0;
		boolean[] visited = new boolean[G.size()];
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		visited[start] = true;
		for(Edge e: G.get(start))
			pq.offer(e);
		
		int ret = 0;
		while(!pq.isEmpty()) {
			Edge curr = pq.poll();
			if(!visited[curr.dest]) {
				visited[curr.dest] = true;
				ret += curr.weight;
				for(Edge e: G.get(curr.dest))
					pq.offer(e);
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
					List<List<Edge>> G = new ArrayList<>();
					for(int i=0; i<N; ++i) G.add(new ArrayList<>());
					for(int i=0; i<edgeInfo.length-2; i+=3) {
						G.get(edgeInfo[i]).add(new Edge(edgeInfo[i+1], edgeInfo[i+2]));
						G.get(edgeInfo[i+1]).add(new Edge(edgeInfo[i], edgeInfo[i+2]));
					}
					System.out.println(prim(G));
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}