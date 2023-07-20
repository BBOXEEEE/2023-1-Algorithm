import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제12
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1110-B
 * Knapsack - Branch
 */

public class Main {
	public static class Node implements Comparable<Node>{
		int level;
		int currProfit;
		int currWeight;
		int bound;
		boolean[] included;

		public Node(int level, int currProfit, int currWeight, int bound, boolean[] included) {
			this.level = level;
			this.currProfit = currProfit;
			this.currWeight = currWeight;
			this.bound = bound;
			this.included = included;
		}
		@Override
		public int compareTo(Node other) {
			return Integer.compare(other.bound, bound);
		}
	}

	public static class Item implements Comparable<Item>{
		int profit; 
		int weight; 
		double score;

		public Item(int profit, int weight) {
			this.profit = profit;
			this.weight = weight;
			this.score = (double) profit/weight;
		}
		@Override
		public int compareTo(Item other) {
			return Double.compare(other.score, this.score);
		}
	}

	public static int computeBound(Item[] items, int W, int index, int currProfit, int currWeight) {
		if(currWeight >= W) return 0;
		int bound = currProfit;
		int totalWeight = currWeight;
		while(index < items.length && totalWeight + items[index].weight <= W) {
			bound += items[index].profit;
			totalWeight += items[index].weight;
			++index;
		}
		if(index < items.length)
			bound += (int)((W-totalWeight) * items[index].score);
		return bound;
	}

	public static int knapsack(Item[] items, int W) {
		Arrays.sort(items);
		int maxProfit = 0;
		Queue<Node> pq = new PriorityQueue<>();
		Node root = new Node(-1, 0, 0, computeBound(items, W, 0, 0, 0), new boolean[items.length]);
		pq.offer(root);

		while(!pq.isEmpty()) {
			Node node = pq.poll();
			++node.level;
			node.currProfit += items[node.level].profit;
			node.currWeight += items[node.level].weight;
			node.included[node.level] = true;
			if(node.currWeight <= W && node.currProfit > maxProfit) 
				maxProfit = node.currProfit;
			
			for(int i=1; i<=2; ++i) {
				node.bound = computeBound(items, W, 
						node.level+1, node.currProfit, node.currWeight);
				if(node.bound > maxProfit) 
					pq.offer(new Node(node.level, node.currProfit, node.currWeight, node.bound, node.included));
				node.currProfit -= items[node.level].profit;
				node.currWeight -= items[node.level].weight;
				node.included[node.level] = false;
			}
		}

		return maxProfit;
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