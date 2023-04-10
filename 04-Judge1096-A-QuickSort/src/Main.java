import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.concurrent.ThreadLocalRandom;
 
/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제4
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1096-A
 * QuickSort
 */

public class Main {
	public static void swap(int[]N, int i, int j) {
		int tmp = N[i];
		N[i] = N[j];
		N[j] = tmp;
	}
	
	public static void choosePivot(int[] N, int lo, int hi) {
		int pivotLoc = ThreadLocalRandom.current().nextInt(hi-lo+1)+lo;
		swap(N, pivotLoc, hi);
	}
	
	public static int partition(int[] N, int lo, int hi) {
		choosePivot(N, lo, hi);
        int pivot = N[hi];
        int L = lo;
        for(int i = lo; i<hi; ++i)
        	if(N[i] < pivot) swap(N, i, L++);
        swap(N, L, hi);
        return L;
    }
	
	public static void quickSort(int[] N, int lo, int hi) {
		if(lo<hi) {
			int pivotLoc = partition(N, lo, hi);
			quickSort(N, lo, pivotLoc-1);
			quickSort(N, pivotLoc+1, hi);
		}
	}
	
	public static void sort(int[] nums) {
		quickSort(nums, 0, nums.length-1);
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