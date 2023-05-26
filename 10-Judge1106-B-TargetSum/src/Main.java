import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Arrays;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제10
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1106-B
 * 목표합 찾기 - Tabulation
 */

/**
 * <점화식>
 * 1. newTarget
 * 		sum(P): 양수로 더하는 값들의 합, sum(N): 음수로 더하는 값들의 합
 * 		sum(P) - sum(N) = target => sum(P) - sum(N) + sum(P) + sum(N) = target + sum(P) + sum(N)
 * 		sum(P) = (target + sum) / 2 => sum(P) 를 newTarget으로 한다면, newTarget에 대한 Subset Sum 문제!
 * 2. newTarget을 찾는 Subset Sum의 테뷸레이션 점화식
 * 		table[i] += table[i-num]
 * 		table의 뒤에서부터 num까지 감소하며 table[i]에 table[i-num]을 더함.
 * 		이전까지 경우의 수에 현재 num을 더해 i를 만들 수 있는 경우의 수를 계산 
 */

public class Main {
	public static int findTargetSum(int[] nums, int target) {
		int sum = Arrays.stream(nums).sum();
		if(sum < target || (sum+target) < 0 || (sum + target)%2 != 0) return 0;
		
		int newTarget = (sum + target) / 2;
		int[] table = new int[newTarget+1];
		table[0] = 1;
		
		for(int num: nums) {
			for(int i=newTarget; i>=num; --i)
				table[i] += table[i-num];
		}
		return table[newTarget];
	}
	
	
	public static void main(String[] args) {
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(br.readLine());
			for(int t=0; t<T; ++t) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int W = Integer.parseInt(st.nextToken());
				int[] nums = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt).toArray();
				System.out.println(findTargetSum(nums, W));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
