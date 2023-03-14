import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제1
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1092-B
 * MergeSort
 */

public class Main {
	public static void sort(int[] nums) {
		mergeSort(nums, 0, nums.length-1);
	}
	
	private static void mergeSort(int[] N, int low, int high) {
		if(low<high){
			int mid = low+(high-low) / 2;
			mergeSort(N, low, mid);
			mergeSort(N, mid+1, high);
			merge(N, low, mid, high);
		}
	}
	
	private static void merge(int[] N, int low, int mid, int high) {
		int LeftSize = mid-low+1;
		int RightSize = high-mid;
		int[] Left = new int[LeftSize];
		int[] Right = new int[RightSize];
		System.arraycopy(N, low, Left, 0, LeftSize);
		System.arraycopy(N, mid+1, Right, 0, RightSize);

		int index = low, L = 0, R = 0;
		while(L<LeftSize && R<RightSize)
			N[index++] = Left[L] <= Right[R]? Left[L++]: Right[R++];
		while(L<LeftSize) N[index++] = Left[L++];
		while(R<RightSize) N[index++] = Right[R++];
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
				sort(nums);
				System.out.println(Arrays.stream(nums).mapToObj(String::valueOf)
						.collect(Collectors.joining(" ")));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}