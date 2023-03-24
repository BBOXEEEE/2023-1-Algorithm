import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제2
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1093-C
 * HowSum
 */

public class Main {
	public static List<Integer> howSum(int M, int[] N) {
		if(M<0) return null;
		if(M==0) return new ArrayList<>();
		for(var x: N) {
			List<Integer> ret = howSum(M-x, N);
			if(ret != null) {
				ret.add(x);
				return ret;
			}
		}
		return null;
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
				List<Integer> result = howSum(M, nums);
				if(result != null) {
					result.add(0, result.size());
					result.stream().forEach((x) -> System.out.print(x+" "));
					System.out.println();
				}
				else System.out.println(-1);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
