import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제1
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1092-B
 * TimSort
 */

public class Main {
	private static int RUN = 8;
	
	public static void sort(int[] N) {
		for(int i=0; i<N.length; i+=RUN)
			insertionSort(N, i, Math.min((i+RUN-1), (N.length-1)));
		for(int size=RUN; size<N.length; size*=2) {
			for(int low=0; low<N.length; low+=2*size) {
				int mid = low + size -1;
				int high = Math.min((low + 2*size - 1), (N.length-1));
				if(mid<high)
					merge(N, low, mid, high);
			}
		}
	}
	
	public static void insertionSort(int[] N, int left, int right) {
		for(int i=left+1; i<=right; ++i) {
			int tmp = N[i];
			int j = i-1;
			while(j>=left && N[j]>tmp) {
				N[j+1] = N[j];
				--j;
			}
			N[j+1] = tmp;
		}
	}
	
	public static void merge(int[] N, int low, int mid, int high) {
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
