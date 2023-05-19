import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
 
/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제9
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1105-A
 * CanSum(Tabulation)
 */
 
public class Main {
    public static boolean canSum(int m, int[] nums) {
        boolean[] table = new boolean[m+1];
        table[0] = true;
        for(int i=0; i<m; ++i)
            if(table[i])
                for(int x: nums)
                    if(i+x <= m) table[i+x] = true;
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
                System.out.println(canSum(M, nums));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}