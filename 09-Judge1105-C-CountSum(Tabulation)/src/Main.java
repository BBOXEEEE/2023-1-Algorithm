import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
 
/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제9
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1105-C
 * CountSum(Tabulation)
 */
 
public class Main {
    public static long countSum(int m, int[] nums) {
        long[] table = new long[m+1];
        table[0] = 1;
        for(int i=0; i<m; ++i)
            if(table[i] != 0)
                for(int x: nums)
                    if(i+x <= m) table[i+x] += table[i];
        return table[m];
    }
     
    public static void main(String[] args) {
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ){
            int T = Integer.parseInt(br.readLine());
            for(int t=0; t<T; ++t) {
                int[] info = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();
                int M = info[0];
                int[] nums = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();
                System.out.println(countSum(M, nums));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}