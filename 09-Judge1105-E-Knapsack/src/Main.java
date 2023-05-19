import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
 
/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제9
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1105-E
 * Knapsack (0-1 배낭 채우기)
 */
 
public class Main {
    public static class Item{
        public int profit;
        public int weight;
        public Item(int profit, int weight) {
            this.profit = profit;
            this.weight = weight;
        }
    }
     
    public static int knapsack(List<Item> items, int w, int n) {
        int[][] table = new int[n+1][w+1];
         
        for(int i=1; i<n+1; i++) {
            for(int x=0; x<w+1; x++) {
                Item item = items.get(i-1);
                if(item.weight > x) table[i][x] = table[i-1][x];
                else table[i][x] = Math.max(table[i-1][x], table[i-1][x-item.weight]+item.profit);
            }
        }
        return table[n][w];
    }
 
    public static void main(String[] args) {
        try(
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ){
            int T = Integer.parseInt(br.readLine());
            for(int t=0; t<T; ++t) {
                int[] info = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();
                int W = info[0];
                int N = info[1];
                int[] itemsInfo = Arrays.stream(br.readLine().split(" "))
                        .mapToInt(Integer::parseInt).toArray();
                List<Item> items = new ArrayList<>();
                for(int i=0; i<itemsInfo.length-1; i+=2){
                    items.add(new Item(itemsInfo[i], itemsInfo[i+1]));
                }
                System.out.println(knapsack(items, W, N));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}