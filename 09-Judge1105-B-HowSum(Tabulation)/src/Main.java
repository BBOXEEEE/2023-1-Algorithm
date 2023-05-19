import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제9
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1105-B
 * HowSum(Tabulation)
 */

public class Main {
	public static List<Integer> howSum(int m, int[] nums) {
		List<List<Integer>> table = new ArrayList<>(Collections.nCopies(m+1, null));
		table.set(0, new ArrayList<>());
		
		for(int i=0; i<m-1; ++i) {
			if(table.get(i) != null) {
				for(int x: nums) {
					if(i+x <= m && table.get(i+x) == null) {
						List<Integer> next = new ArrayList<>(table.get(i));
						next.add(x);
						table.set(i+x, next);
					}
				}
			}
		}
		return table.get(m);
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
				List<Integer> result = howSum(M, nums);
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