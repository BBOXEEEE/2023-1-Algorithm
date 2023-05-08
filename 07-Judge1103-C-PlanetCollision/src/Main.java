import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Deque;
import java.util.ArrayDeque;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제7
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1103-C
 * 행성 충돌  
 */

public class Main {
	public static Deque<Integer> planetCollision(int[] planets) {
		Deque<Integer> deque = new ArrayDeque<>();
		for(int planet: planets) {
			boolean destoryed = false;
			while(!deque.isEmpty() && deque.peekLast()>0 && planet<0) {
				int tmp = deque.peekLast()+planet;
				if(tmp <= 0) deque.pollLast();
				if(tmp >= 0) {
					destoryed = true;
					break;
				}
			}
			if(!destoryed) deque.addLast(planet);
		}
		return deque;
	}
	public static void main(String[] args) {
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(br.readLine());
			for(int t=0; t<T; ++t) {
				br.readLine();
				int[] planets = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt).toArray();
				System.out.println(planetCollision(planets).stream()
						.map(Object::toString).collect(Collectors.joining(" ")));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}