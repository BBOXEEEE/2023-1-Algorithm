import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제3
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1094-A
 * Inversion Count
 */

public class Main {
	private static int[] Left = null;
	private static int[] Right = null;
	
	public static int countInversion(int[] nums) {
		Left = new int[nums.length/2+1];
		Right = new int[nums.length/2+1];
		return countInversion(0, nums.length-1, nums);
	}
	
	private static int countInversion(int lo, int hi, int[] N) {
		if(lo<hi) {
			int mid = lo+(hi-lo)/2;
			int leftInv = countInversion(lo, mid, N);
			int rightInv = countInversion(mid+1, hi, N);
			int splitInv = countSplitInversion(lo, mid, hi, N);
			return leftInv+rightInv+splitInv;
		}
		return 0;
	}
	
	private static int countSplitInversion(int lo, int mid, int hi, int[] N) {
		int LeftSize = mid-lo+1;
		int RightSize = hi-mid;
		
		System.arraycopy(N, lo, Left, 0, LeftSize);
		System.arraycopy(N, mid+1, Right, 0, RightSize);
		
		int L = 0, R = 0, idx = lo, ret = 0;
		while(L<LeftSize && R<RightSize) {
			if(Left[L] <= Right[R]) {
				N[idx++] = Left[L++];
			}
			else {
				N[idx++] = Right[R++];
				ret += LeftSize - L;
			}
		}
		while(L<LeftSize) N[idx++] = Left[L++];
		while(R<RightSize) N[idx++] = Right[R++];
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
				System.out.println(countInversion(nums));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}