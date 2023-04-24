import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int countSum(int[] nums, int M, int start) {
		if(M<0) return 0;
		if(M==0) return 1;
		int cnt = 0;
		for(int i=start; i<nums.length; ++i)
			cnt += countSum(nums, M-nums[i], i+1);
		return cnt;
	}
	public static int countSum(int M, int[] N) {
		return countSum(N, M, 0);
	}
	public static void main(String[] args) {
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(br.readLine());
			for(int t=0; t<T; ++t){
				StringTokenizer st = new StringTokenizer(br.readLine());
				int M = Integer.parseInt(st.nextToken());
				int[] nums = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt).toArray();
				System.out.println(countSum(M, nums));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
