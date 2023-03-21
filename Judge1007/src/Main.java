import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static int solve(int[] N) {
		int ret = 0;
		for(int i=0; i<N.length; ++i)
			ret = ret ^ N[i];
		return ret;
	}
	public static void main(String[] args) {
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(br.readLine());
			for(int t=0; t<T; ++t) {
				br.readLine();
				int[] nums = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt).toArray();
				System.out.println(solve(nums));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}