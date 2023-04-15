import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.StringTokenizer;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제5
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1098-C
 * 가장 많이 등장한 k개 요소 찾기 
 */

public class Main {
	public static int partition(ArrayList<Integer> freq, int left, int right) {
        int pivot = freq.get(left);
        int L = left+1;
        for(int i = left+1; i<right+1; ++i)
        	if(freq.get(i) > pivot) Collections.swap(freq, i, L++);
        Collections.swap(freq, left, L-1);
        return L-1;
    }
	
	public static int RSelect(ArrayList<Integer> freq, int left, int right, int k) {
		if(left==right) return freq.get(left);
		int pivotLoc = ThreadLocalRandom.current().nextInt(right-left+1)+left;
		Collections.swap(freq, left, pivotLoc);
		pivotLoc = partition(freq, left, right);
		if(k == pivotLoc) return freq.get(pivotLoc);
		else if(k < pivotLoc) return RSelect(freq, left, pivotLoc-1, k);
		else return RSelect(freq, pivotLoc+1, right, k);
	}
	
	public static StringBuilder findElement(int[] nums, int K) {
		Map<Integer, Integer> freqMap = new HashMap<>();
		for(int num: nums)
			freqMap.put(num, freqMap.getOrDefault(num, 0)+1);
		ArrayList<Integer> freqValue = new ArrayList<>(freqMap.values());
		int element = RSelect(freqValue, 0, freqValue.size()-1, K-1);
		StringBuilder ret = new StringBuilder();
		int count = 1;
		for(int key: freqMap.keySet())
			if(freqMap.get(key) >= element && count++ <= K) ret.append(key + " ");
		return ret;
	}
	
	public static void main(String[] args) {
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(br.readLine());
			for(int t=0; t<T; ++t) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				st.nextToken();
				int K = Integer.parseInt(st.nextToken());
				int[] nums = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt).toArray();
				System.out.println(findElement(nums, K));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
