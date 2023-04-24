import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	private static final int[][][] Directions = {{{0, 1}, {0, 2}, {0, 3}},
            									{{1, 0}, {2, 0}, {3, 0}},
            									{{1, 1}, {2, 2}, {3, 3}},
            									{{1, -1}, {2, -2}, {3, -3}}};
	
	public static boolean isValid(int r, int c) {
		return r<0 || r>=6 || c<0 || c>=7;
	}
	public static char checkWinner(char[][] board) {
		for(int i=0; i<6; ++i) {
			for(int j=0; j<7; ++j) {
				if(board[i][j] != '*') {
					char user = board[i][j];
					for(int[][] direction: Directions) {
						int cnt = 1;
						for(int[] next: direction) {
							int nextR = i+next[0];
							int nextC = j+next[1];
							if(isValid(nextR, nextC)) break;
							if(board[nextR][nextC] == user) {
								++cnt;
								if(cnt==4) return user;
							}
							else break;
						}
					}
				}
			}
		}
		return 'D';
	}
	public static void main(String[] args) {
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(br.readLine());
			for(int t=0; t<T; ++t) {
				char[][] board = new char[6][7];
				for(int i=0; i<6; ++i) {
					String[] input = br.readLine().split(" ");
					for(int j=0; j<7; ++j)
						board[i][j] = input[j].charAt(0);
				}
				System.out.println(checkWinner(board));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
