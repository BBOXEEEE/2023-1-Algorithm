import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static String solve(int N) {
		String ret = "no";
		int sum = N+reverse(N);
		if(isPalindrome(String.valueOf(sum))) ret = "yes";
		return ret;
	}
	
	public static boolean isPalindrome(String N) {
		int forward = 0, backword = N.length()-1;
		while(backword>forward) {
			if(N.charAt(forward++) != N.charAt(backword--))
				return false;
		}
		return true;
	}
	
	public static int reverse(int N) {
		int ret = 0;
		while(N!=0) {
			ret = ret*10 + N%10;
			N/=10;
		}
		return ret;
	}
	public static void main(String[] args) {
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(br.readLine());
			for(int t=0; t<T; ++t) {
				int number = Integer.parseInt(br.readLine());
				System.out.println(solve(number));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
