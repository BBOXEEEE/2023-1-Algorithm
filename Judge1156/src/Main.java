import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static int longestSubString(String S) {
		char[] C = S.toCharArray();
		int L = 0, R = 1, ret = Integer.MIN_VALUE, cnt = 1;
		while(R<C.length) {
			if(C[L] == C[R]) {
				++cnt; ++R;
			}
			else {
				++R; L=R-1;
				ret = Math.max(ret, cnt);
				cnt = 1;
			}
		}
		return Math.max(ret, cnt);
	}
	public static void main(String[] args) {
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(br.readLine());
			for(int t=0; t<T; ++t) {
				String S = br.readLine();
				System.out.println(longestSubString(S));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
