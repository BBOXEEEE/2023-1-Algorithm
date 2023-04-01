import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제3
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1094-B
 * LongestSubString
 */

public class Main {
	public static int longestSubstring(String S, int k) {
		if(k>S.length()) return 0;
		if(k==1) return S.length();
		int[] charFreq = new int[26];
		for(char c: S.toCharArray())
			++charFreq[c-'a'];
		
		boolean valid = true;
		for(var value: charFreq)
			if(value>0 && value<k) valid = false;
		if(valid) return S.length();
        
		int L = 0, R = 0, ret = 0;
		while(R<S.length()) {
			if(charFreq[S.charAt(R)- 'a'] < k) {
				ret = Math.max(ret, longestSubstring(S.substring(L, R), k));
				L = R+1;
			}
			++R;
		}
		ret = Math.max(ret, longestSubstring(S.substring(L), k));
		return ret;
	}
	
	public static void main(String[] args) {
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(br.readLine());
			StringBuilder result = new StringBuilder();
			for(int t=0; t<T; ++t) {
				String S = br.readLine();
				int K = Integer.parseInt(br.readLine());
				result.append(longestSubstring(S, K)).append('\n');
			}
			System.out.println(result);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}