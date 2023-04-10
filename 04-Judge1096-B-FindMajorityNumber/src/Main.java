import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제4
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1096-B
 * FindMajorityNumber
 */

public class Main {
	public static int findMajorityNumber(int[] nums) {
		while(true) {
			int ret = nums[ThreadLocalRandom.current().nextInt(nums.length)];
			int count = 0;
			for(int num: nums)
				if(ret == num) ++count;
			if(count > nums.length/2) return ret;
		}
	}
	
	public static void main(String[] args) {
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(br.readLine());
			StringBuilder ret = new StringBuilder();
			for(int t=0; t<T; ++t) {
				br.readLine();
				int[] nums = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt).toArray();
				ret.append(findMajorityNumber(nums)).append('\n');
			}
			System.out.println(ret);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}

/** 성능분석 
 * 항상 절반이 넘는 수로 구성된 N개의 배열에서 절반이 넘는 수를 랜덤으로 선택할 확률은 50%이다.
 * 무작위로 선택된 수가 배열에서 몇번 등장하는지 순회하는 비용은 O(N)이다.
 * 따라서 이 알고리즘의 시간복잡도는 O(N)이다.
 */