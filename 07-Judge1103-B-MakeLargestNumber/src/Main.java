import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제7
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1103-B
 * 가장 큰 수 만들기   
 */

public class Main {
	public static String[] makeLargestNumber(String[] nums) {
        Arrays.sort(nums, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s2 + s1).compareTo(s1 + s2);
            }
        });
        return nums;
	}
	
	public static void main(String[] args) {
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(br.readLine());
			for(int t=0; t<T; ++t) {
				br.readLine();
				String[] nums = Arrays.stream(br.readLine().split(" "))
						.map(n->Integer.toString(Integer.parseInt(n))).toArray(String[]::new);
				System.out.println(Arrays.stream(makeLargestNumber(nums))
						.collect(Collectors.joining()));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}