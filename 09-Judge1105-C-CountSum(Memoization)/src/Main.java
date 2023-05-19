import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제9
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1105-C
 * CountSum(Memoization)
 */

public class Main {
	public static long countSum(int m, int[] nums, Map<Integer, Long> memo) {
		if(m<0) return 0;
		if(m==0) return 1;
		if(memo.containsKey(m)) return memo.get(m);
		
		long count = 0;
		for(int x: nums)
			count += countSum(m-x, nums, memo);
		memo.put(m, count);
		return count;
	}
	
	public static void main(String[] args) {
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(br.readLine());
			for(int t=0; t<T; ++t) {
				int[] info = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt).toArray();
				int M = info[0];
				int[] nums = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt).toArray();
				Map<Integer, Long> memo = new HashMap<>((int)(M*1.3));
				System.out.println(countSum(M, nums, memo));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}