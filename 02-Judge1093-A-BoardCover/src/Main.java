import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제2
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1093-A
 * BoardCover
 */

public class Main {
	private static class Node{
		int x,y;
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private static int coverCount(int H, int W, boolean[][] board, int startR, int startC) {
		Node next = findNextWhiteCell(H, W, board, startR, startC);
		if(next == null) return 1;
		int ret = 0;
		int[][][] L = { { { 0, 0 }, { 0, 1 }, { 1, 0 } }, 
						{ { 0, 0 }, { 0, 1 }, { 1, 1 } },
						{ { 0, 0 }, { 1, 0 }, { 1, -1 } }, 
						{ { 0, 0 }, { 1, 0 }, { 1, 1 } } };
		for(int i=0; i<4; ++i) {
			if(isCover(H, W, board, next, L[i])) {
				coverBoard(H, W, board, next, L[i], true);
				ret += coverCount(H, W, board, next.x, next.y+1);
				coverBoard(H, W, board, next, L[i], false);
			}
		}
		return ret;
	}
	
	private static boolean isCover(int H, int W, boolean[][] board, Node pos, int[][] L) {
		for(int i=0; i<3; ++i) {
			int nextR = pos.x + L[i][0];
			int nextC = pos.y + L[i][1];
			if(!isRange(H, W, nextR, nextC)) return false;
			if(board[nextR][nextC]) return false;
		}
		return true;
	}
	
	private static void coverBoard(int H, int W, boolean[][] board, Node pos, int[][] L, boolean value) {
		for(int i=0; i<3; ++i) {
			int nextR = pos.x + L[i][0];
			int nextC = pos.y + L[i][1];
			board[nextR][nextC] = value? true: false;
		}
	}
	
	private static int countWhiteCell(int H, int W, boolean[][] board) {
		int ret = 0;
		for(int i=0; i<H; ++i)
			for(int j=0; j<W; ++j)
				if(!board[i][j]) ++ret;
		return ret;
	}
	
	private static Node findNextWhiteCell(int H, int W, boolean[][] board, int startR, int startC){
		for(int i=0; i<H; ++i)
			for(int j=0; j<W; ++j)
				if(!board[i][j]) return new Node(i, j);
		return null;
	}
	
	private static boolean isRange(int H, int W, int R, int C) {
		if(R<0 || R>=H || C<0 || C>=W) return false;
		return true;
	}
	
	public static int coverCount(int H, int W, boolean[][] board) {
		if(countWhiteCell(H, W, board) % 3 != 0) return 0;
		return coverCount(H, W, board, 0, 0);
	}
	
	public static void main(String[] args) {
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(br.readLine().trim());
			for(int t=0; t<T; ++t) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int H = Integer.parseInt(st.nextToken());
				int W = Integer.parseInt(st.nextToken());
				boolean[][] board = new boolean[H][W];
				for(int i=0; i<H; ++i) {
					String line = br.readLine();
					for(int j=0; j<W; ++j) {
						board[i][j] = line.charAt(j) == '#'? true: false;
					}
				}
				System.out.println(coverCount(H, W, board));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
