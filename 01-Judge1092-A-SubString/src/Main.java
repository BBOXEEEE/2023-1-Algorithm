import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제1
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1092-A
 * 길이가 3인 부분문자열 찾기 
 */

public class Main {
	public static int solve(String S) {
		char[] C = S.toCharArray();
		int ret = 0;
		for(int i=0; i<S.length()-2; ++i) {
			if(C[i]!=C[i+1] && C[i+1]!=C[i+2] && C[i]!=C[i+2])
				++ret;
		}
		return ret;
	}
	
	public static void main(String[] args) {
		try (
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(in.readLine());
			for(int t=0; t<T; ++t) {
				String S = in.readLine();
				System.out.println(solve(S));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}