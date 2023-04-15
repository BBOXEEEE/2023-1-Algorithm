import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제5
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1098-A
 * 최댓값, 최솟값 동시에 찾기 
 */

public class Main {
	public static StringBuilder SelectMinMax(int[] nums) {
		StringBuilder ret = new StringBuilder();
		int max = 0, min = 0;
		int start = 0;
		if(nums.length%2==0) {
			max = nums[0]<nums[1]? nums[1]: nums[0];
			min = nums[0]<nums[1]? nums[0]: nums[1];
			start += 2;
		}
		else {
			max = min = nums[0];
			++start;
		}
		
		for(int i=start; i<nums.length-1; i+=2) {
			if(nums[i] < nums[i+1]) {
				if(nums[i] < min) min = nums[i];
				if(nums[i+1] > max) max = nums[i+1];
			}
			else {
				if(nums[i+1] < min) min = nums[i+1];
				if(nums[i] > max) max = nums[i];
			}
		}
		ret.append(max).append(' ').append(min);
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
				System.out.println(SelectMinMax(nums).toString());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
