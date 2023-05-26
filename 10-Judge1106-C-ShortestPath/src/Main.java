import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제10
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1106-C
 * 방향 그래프에서 최단 경로 찾기 
 */

public class Main {
	public static final int INF = 987654321;

	public static int[] shortestPath(int[][] G, int N) {
		int[][] table = floydWarshall(G, N);
		if(table == null) return new int[] {-1};
		
		int[] path = new int[3];
		int start = 0, end = 0, max = Integer.MIN_VALUE;
		for(int i=0; i<N; ++i) {
			for(int j=0; j<N; ++j) {
				if(table[i][j] != INF && table[i][j] > max) {
					start = i; end = j;
					max = table[i][j];
				}
			}
		}
		path[0] = start; path[1] = end; path[2] = max;
		return path;
	}
	public static int[][] floydWarshall(int[][] G, int N) {
		int[][] table = G;
		for(int k=0; k<N; ++k) {
			for(int i=0; i<N; ++i) {
				for(int j=0; j<N; ++j) {
					if(table[i][k] == INF || table[k][j] == INF) continue; 
					table[i][j] = Math.min(table[i][j], table[i][k] + table[k][j]);
				}
			}
		}
		for(int i=0; i<N; ++i)
			if(table[i][i] < 0) return null;
		return table;
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
				String lineInput = br.readLine();
				if(E==0) System.out.println("0 0 0");
				else {
					int[] edgeInfo = Arrays.stream(lineInput.split(" "))
							.mapToInt(Integer::parseInt).toArray();
					int[][] G = new int[N][N];
					for(int i=0; i<N; ++i) {
						Arrays.fill(G[i], INF);
						G[i][i] = 0;
					}
					int j = 0;
					for(int i=0; i<E; ++i)
						G[edgeInfo[j++]][edgeInfo[j++]] = edgeInfo[j++];
					System.out.println(Arrays.stream(shortestPath(G, N))
							.mapToObj(String::valueOf).collect(Collectors.joining(" ")));
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
