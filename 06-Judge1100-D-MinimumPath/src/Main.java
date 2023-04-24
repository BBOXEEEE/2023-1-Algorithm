import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제6
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1100-D
 * BFS를 이용한 경로 합이 최소인 경로 찾기 
 */

public class Main {
	public static boolean isValid(int[][] grid, int r, int c) {
		return r>=0 && r<grid.length && c>=0 && c<grid[0].length;
	}
	
	public static int BFS(int[][] grid, boolean[][] visited, int[][] cost, int col, int row) {	
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[]{col, row});
		visited[col][row] = true;
		cost[col][row] = grid[col][row];
		
		int[] dx = {0, 1}, dy = {1, 0};
		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			for(int i=0; i<2; ++i) {
				int nx = curr[0] + dx[i];
				int ny = curr[1] + dy[i];
				if(isValid(grid, nx, ny)) {
					if(!visited[nx][ny]) {
						cost[nx][ny] = cost[curr[0]][curr[1]] + grid[nx][ny];
						queue.offer(new int[]{nx, ny});
						visited[nx][ny] = true;
					}
					else {
						cost[nx][ny] = Math.min(cost[nx][ny], cost[curr[0]][curr[1]] + grid[nx][ny]);
					}
				}
			}
		}
		return cost[cost.length-1][cost[0].length-1];
	}
	
	public static int minimumPath(int[][] grid, int M, int N) {
		boolean[][] visited = new boolean[M][N];
		int[][] cost = new int[M][N];
		return BFS(grid, visited, cost, 0, 0);
	}
	
	public static void main(String[] args) {
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(br.readLine());
			for(int t=0; t<T; ++t) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int M = Integer.parseInt(st.nextToken());
				int N = Integer.parseInt(st.nextToken());
				int[][] grid = new int[M][N];
				for(int i=0; i<M; ++i)
					grid[i] = Arrays.stream(br.readLine().split(" "))
							.mapToInt(Integer::parseInt).toArray();
				System.out.println(minimumPath(grid, M, N));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
