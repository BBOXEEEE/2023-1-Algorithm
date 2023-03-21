import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
	private static int[] Money = {50000, 10000, 5000, 1000, 500, 100, 50, 10};
	private static int[] Changes;
	
	public static void solve(int C, int R) {
		int change = R - C;
		for(int i=0; i<Money.length; ++i) {
			if(change/Money[i] > 0) {
				++Changes[i];
				change-=Money[i];
				--i;
			}
		}
	}
	
	public static void main(String[] args) {
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(br.readLine());
			for(int t=0; t<T; ++t) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int C = Integer.parseInt(st.nextToken());
				int R = Integer.parseInt(st.nextToken());
				Changes = new int[8];
				solve(C, R);
				System.out.println(Arrays.stream(Changes).mapToObj(String::valueOf)
						.collect(Collectors.joining(" ")));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
