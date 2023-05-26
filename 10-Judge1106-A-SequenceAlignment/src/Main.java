import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제10
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1106-A
 * 유전자 염기서열 유사성 
 */

public class Main {
	public static int sequenceAlignment(String X, String Y, int gapP, int misP) {
		int m = X.length(), n = Y.length();
		int[][] table = new int[m+1][n+1];
		for(int i=1; i<=m; ++i)
			table[i][0] = i * gapP;
		for(int j=1; j<=n; ++j)
			table[0][j] = j * gapP;
		for(int i=1; i<=m; ++i) {
			for(int j=1; j<=n; ++j) {
				int p = X.charAt(i-1) == Y.charAt(j-1)? 0: misP;
				table[i][j] = Math.min(p + table[i-1][j-1], Math.min(gapP + table[i-1][j], gapP + table[i][j-1]));
			}
		}
		return table[m][n];
	}
	
	public static void main(String[] args) {
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(br.readLine());
			for(int t=0; t<T; ++t) {
				String[] data = Arrays.stream(br.readLine().split(" "))
						.toArray(String[]::new);
				int G = Integer.parseInt(data[0]);
				int M = Integer.parseInt(data[1]);
				String X = data[2];
				String Y = data[3];
				System.out.println(sequenceAlignment(X, Y, G, M));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
