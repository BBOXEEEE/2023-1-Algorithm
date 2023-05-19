import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제9
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1105-D
 * BestSum(Memoization)
 */

public class Main {
	public static List<Integer> bestSum(int m, int[] nums, Map<Integer, List<Integer>> memo) {
		if(m<0) return null;
		if(m==0) return new ArrayList<>();
		if(memo.containsKey(m)) return memo.get(m);
		
		List<Integer> best = null;
		for(int x: nums) {
			List<Integer> list = bestSum(m-x, nums, memo);
			if(list != null) {
				List<Integer> next = new ArrayList<>(list);
				next.add(x);
				if(best == null || best.size()>list.size()) {
					best = next;
				}
			}
		}
		memo.put(m, best);
		return memo.get(m);
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
				Map<Integer, List<Integer>> memo = new HashMap<>((int)(M*1.3));
				List<Integer> result = bestSum(M, nums, memo);
				if(result != null)
					System.out.println(result.size() + " " + result.stream()
							.map(String::valueOf).collect(Collectors.joining(" ")));
				else System.out.println(-1);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}