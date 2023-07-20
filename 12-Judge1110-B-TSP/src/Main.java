import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제12
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1110-A
 * TSP(외판원 문제) - Branch
 */

public class Main {
	public static final int INF = 987654321;
	
	public static class Node implements Comparable<Node>, Cloneable{
		int level;
		int bound;
		int curr;
		boolean[] included;
		List<Integer> tour;

		public Node(int level, int bound, int curr, boolean[] included, List<Integer> tour) {
			this.level = level;
			this.bound = bound;
			this.curr = curr;
			this.included = included;
			this.tour = tour;
		}
		@Override
		public int compareTo(Node other) {
			return Integer.compare(bound, other.bound);
		}
		@Override
		public Node clone() throws CloneNotSupportedException{
			Node cloned = (Node) super.clone();
			cloned.tour = new ArrayList<>(this.tour);
			cloned.included = this.included.clone();
			return cloned;
		}
	}

	public static int computeBound(int[][] G, Node node, int N) {
		int bound = node.curr;
		int curr = node.tour.get(node.tour.size()-1);
		
		for(int i=0; i<N; ++i) {
			if(node.included[i] && i != curr) continue;
			int minEdge = INF;
			for(int j=0; j<N; ++j) {
				if(i == node.tour.get(node.tour.size()-1) && j == 0) continue;
				if(i == j || G[i][j] == -1 || (j != 0 && node.included[j])) continue;
				minEdge = Math.min(G[i][j], minEdge);
			}
			bound += minEdge;
		}
		return bound;
	}

	public static int TSP(int[][] G, int N) throws CloneNotSupportedException{
		Queue<Node> pq = new PriorityQueue<>();
		boolean[] included = new boolean[N];
		included[0] = true;
		List<Integer> tour = new ArrayList<>();
		tour.add(0);
		
		Node root = new Node(0, 0, 0, included, tour);
		root.bound = computeBound(G, root, N);
		pq.offer(root);
		
		int minLength = INF;
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			if(node.bound < minLength) {
				for(int i=0; i<N; ++i) {
					if(node.included[i] || G[node.tour.get(node.level)][i] == -1 
							||  G[node.tour.get(node.level)][i] == 0) continue;
					Node next = node.clone();
					++next.level;
					next.tour.add(i);
					next.curr += G[node.tour.get(node.tour.size()-1)][i];
					next.included[i] = true;
					
					if(next.level == N-2) {
						next.bound = computeBound(G, next, N);
						if(next.bound < minLength) minLength = next.bound;
					}
					else {
						next.bound = computeBound(G, next, N);
						if(next.bound < minLength) pq.offer(next);
					}
				}
			}
		}
		return minLength;
	}

	public static void main(String[] args) {
		try(
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(br.readLine());
			for(int t=0; t<T; ++t) {
				int N = Integer.parseInt(br.readLine());
				int[][] G = new int[N][N];
				for(int i=0; i<N; ++i) {
					int[] info = Arrays.stream(br.readLine().split(" "))
							.mapToInt(Integer::parseInt).toArray();
					for(int j=0; j<N; ++j) {
						G[i][j] = info[j];
					}
				}
				System.out.println(TSP(G, N));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}