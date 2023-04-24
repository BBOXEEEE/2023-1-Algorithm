import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	private static final char[] VOWELS = {'a', 'e', 'i', 'o', 'u'};
	
	public static boolean isVowels(char c) {
		for(char v: VOWELS)
			if(c==v) return true;
		return false;
	}
	
	public static int findSubString(String S, int k) {
		if(S.length()<k) return 0;
		int ret = 0, curr = 0;
		for(int i=0; i<k; ++i) {
			if(isVowels(S.charAt(i))) ++curr;
		}
		ret = curr;
		for(int i=k; i<S.length(); ++i) {
			if(isVowels(S.charAt(i-k))) --curr;
			if(isVowels(S.charAt(i))) ++curr;
			ret = Math.max(ret, curr);
		}
		return ret;
	}
	
	public static void main(String[] args) {
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(br.readLine());
			for(int t=0; t<T; ++t) {
				String S = br.readLine();
				int K = Integer.parseInt(br.readLine());
				System.out.println(findSubString(S, K));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
