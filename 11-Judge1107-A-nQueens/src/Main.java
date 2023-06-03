import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제11
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1107-A
 * n-Queens
 */

public class Main {
	public static int count;
	public static void printBoard(int[] cols, int N) {
		for (int row = 1; row <= N; row++) {
			for (int col = 1; col <= N; col++) {
				if (cols[row] == col) {
					System.out.print("Q");
				} else {
					System.out.print("X");
				}
			}
			System.out.println();
		}
	}

	public static boolean promising(int[] cols, int row) {
		for(int r=1; r<row; ++r)
			if(cols[r] == cols[row] || Math.abs(cols[r] - cols[row]) == row-r)
				return false;
		return true;
	}

	public static void nQueens(int[] cols, int N, int row) {
		if(row==N) {
			++count;
			printBoard(cols, N);
			return;
		}
		for(int col=1; col<=N; ++col) {
			cols[row+1] = col;
			if(promising(cols, row+1)) {
				nQueens(cols, N, row+1);
			}
		}
	}

	public static void solve(int[] cols, int N){
		for(int col=1; col<=N; ++col) {
			cols[1] = col;
			nQueens(cols, N, 1);
		}
	}

	public static void main(String[] args) {
		try(
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				){
			int T = Integer.parseInt(br.readLine());
			for(int t=0; t<T; ++t) {
				int N = Integer.parseInt(br.readLine());
				int[] cols = new int[N+1];
				count = 0;
				solve(cols, N);
				if(count==0) System.out.println();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}