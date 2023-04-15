import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제5
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1098-B
 * 두번쨰로 큰 수 찾기 
 */

public class Main {
	public static int secondMax(int[] nums, int N) {
		int[] S = new int[2*N-1];
		System.arraycopy(nums, 0, S, N-1, N);
		for(int i=N-2, j=S.length-1; i>=0; --i, j-=2)
			S[i] = S[j]>S[j-1]? S[j]: S[j-1];
		
		int max = S[0], ret = Integer.MIN_VALUE, left = 1;
		int curr = 0, maxLoc = 0;
		while(left < S.length) {
			curr = S[left] == max? S[left+1]: S[left];
			maxLoc = S[left] == max? left: left+1;
			if(curr==max) return max;
			ret = ret>curr? ret: curr;
			left = 2*maxLoc+1;
		}
		return ret;
	}
	
	public static void main(String[] args) {
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(br.readLine());
			for(int t=0; t<T; ++t) {
				int N = Integer.parseInt(br.readLine());
				int[] nums = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt).toArray();
				System.out.println(secondMax(nums, N));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
