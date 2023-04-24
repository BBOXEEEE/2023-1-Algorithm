import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
	public static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
	
	public static int partition(int[]nums, int lo, int hi) {
		int pivot = nums[lo];
		int L = lo+1;
		for(int i=lo+1; i<hi+1; ++i)
			if(nums[i]<pivot) swap(nums, i, L++);
		swap(nums, lo, L-1);
		return L-1;
	}
	
	public static int RSelect(int[] nums, int lo, int hi, int k) {
		if(lo==hi) return nums[lo];
		int pivotLoc = ThreadLocalRandom.current().nextInt(hi-lo+1)+lo;
		swap(nums, lo, pivotLoc);
		pivotLoc = partition(nums, lo, hi);
		if(k==pivotLoc) return nums[pivotLoc];
		else if(k<pivotLoc) return RSelect(nums, lo, pivotLoc-1, k);
		else return RSelect(nums, pivotLoc+1, hi, k);
	}
	public static int findKElement(int[] nums, int k) {
		return RSelect(nums, 0, nums.length-1, k-1);
	}
	
	public static void main(String[] args) {
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(br.readLine());
			for(int t=0; t<T; ++t) {
				int[] info = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt).toArray();
				int[] nums = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt).toArray();
				System.out.println(findKElement(nums, info[1]));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
