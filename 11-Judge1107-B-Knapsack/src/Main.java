import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제11
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1107-B
 * Knapsack - BackTracking
 */

public class Main {
	public static class Item implements Comparable<Item>{
		int v; int w; 
		double score;
		public Item(int v, int w) {
			this.v = v;
			this.w = w;
			this.score = (double) v/w;
		}
		@Override
		public int compareTo(Item other) {
			return Double.compare(other.score, this.score);
		}
	}
	public static class Result{
		int max; boolean[] sol;
		public Result(int max, boolean[] sol) {
			this.max = max;
			this.sol = sol;
		}
		
	}
	
	public static int computeBound(Item[] items, int W, int index, int currProfit, int currWeight) {
		int bound = currProfit;
		int totalWeight = currWeight;
		while(index < items.length && totalWeight + items[index].w <= W) {
			bound += items[index].v;
			totalWeight += items[index].w;
			++index;
		}
		if(index < items.length)
			bound += (int)((W-totalWeight) * items[index].score);
		return bound;
	}
	
	public static boolean promising(Item[] items, int W, int index, int currProfit, int currWeight, Result ret) {
		return (currWeight < W) && (computeBound(items, W, index+1, currProfit, currWeight) > ret.max);
	}
	
	public static void knapsack(Item[] items, boolean[] included, int W, int index, int currProfit, int currWeight, Result ret) {
		if(currWeight <= W && currProfit > ret.max) {
			ret.max = currProfit;
			System.arraycopy(included, 0, ret.sol, 0, included.length);
		}
		if(promising(items, W, index, currProfit, currWeight, ret)) {
			included[index+1] = true;
			knapsack(items, included, W, index+1, 
					currProfit + items[index+1].v, currWeight + items[index+1].w, ret);
			included[index+1] = false;
			knapsack(items, included, W, index+1, currProfit, currWeight, ret);
		}
	}
	
	public static int knapsack(Item[] items, int W) {
		Arrays.sort(items);
		Result ret = new Result(0, new boolean[items.length]);
		boolean[] included = new boolean[items.length];
		knapsack(items, included, W, -1, 0, 0, ret);
		return ret.max;
	}
	
	public static void main(String[] args) {
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(br.readLine());
			for(int t=0; t<T; ++t) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int W = Integer.parseInt(st.nextToken());
				int N = Integer.parseInt(st.nextToken());
				int[] itemsInfo = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt).toArray();
				Item[] items = new Item[N];
				int j = 0;
				for(int i=0; i<N; ++i)
					items[i] = new Item(itemsInfo[j++], itemsInfo[j++]);
				System.out.println(knapsack(items, W));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}