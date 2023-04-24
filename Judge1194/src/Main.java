import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static int splitSum(int[] nums, int lo, int mid, int hi) {
		int leftSum = 0, leftMax = Integer.MIN_VALUE;
		for(int i=mid; i>=lo; --i) {
			leftSum += nums[i];
			leftMax = Math.max(leftMax, leftSum);
		}
		int rightSum = 0, rightMax = Integer.MIN_VALUE;
		for(int i=mid+1; i<=hi; ++i) {
			rightSum += nums[i];
			rightMax = Math.max(rightMax, rightSum);
		}
		return leftMax + rightMax;
	}
	
	public static int maxSubArray(int[] nums, int lo, int hi) {
		if(lo==hi) return Math.abs(nums[lo]);
		int mid = lo+(hi-lo)/2;
		int leftSum = maxSubArray(nums, lo, mid);
		int rightSum = maxSubArray(nums, mid+1, hi);
		int splitSum = splitSum(nums, lo, mid, hi);
		return Math.max(splitSum, Math.max(leftSum, rightSum));
	}
	
	public static int maxSubArray(int[] nums) {
		return maxSubArray(nums, 0, nums.length-1);
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
				System.out.println(maxSubArray(nums));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
