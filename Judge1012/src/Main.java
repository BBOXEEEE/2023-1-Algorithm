import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
	
	public static void sort(int[] N, int left, int right) {
		for(int i=left+1; i<right; ++i) {
			int tmp = N[i];
			int j = i-1;
			while(j>=left && N[j]>tmp) {
				N[j+1] = N[j];
				--j;
			}
			N[j+1] = tmp;
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
				sort(nums,0,nums.length);
				System.out.println(Arrays.stream(nums).mapToObj(String::valueOf)
						.collect(Collectors.joining(" ")));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
