import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제4
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1096-C
 * WiggleSort
 */

public class Main {
	public static void swap(int[] N, int i, int j) {
		int tmp = N[i];
		N[i] = N[j];
		N[j] = tmp;
	}
	
	public static int newIndex(int index, int length) {
		return (2*index + 1) % (length | 1);
	}
	
	public static int partition(int[] N, int lo, int hi) {
		int pivot = N[hi];
		int L = lo;
		for(int i=lo; i<hi; ++i)
			if(N[i] < pivot) swap(N, i, L++);
		swap(N, L, hi);
		return L;
	}
	
	public static int quickSelect(int[] N, int lo, int mid, int hi) {
		if(lo==hi) return N[hi];
		int pivotLoc = partition(N, lo, hi);
		if(mid==pivotLoc) return N[mid];
		else if(mid<pivotLoc) return quickSelect(N, lo, mid, pivotLoc-1);
		else return quickSelect(N, pivotLoc+1, mid, hi);
	}
	
	public static void wiggleSort(int[] N) {
		int length = N.length;
		int median = quickSelect(N, 0, length/2, length-1);
		
		int L = 0, R = length-1, idx = 0;
		while(idx<=R) {
			int pos = newIndex(idx, length);
			if(N[pos] > median) swap(N, newIndex(L++, length), newIndex(idx++, length));
			else if(N[pos] < median) swap(N, newIndex(R--, length), newIndex(idx, length));
			else ++idx;
		}
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
				wiggleSort(nums);
				System.out.println(nums.length);
				System.out.println(Arrays.stream(nums).mapToObj(String::valueOf)
						.collect(Collectors.joining(" ")));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}