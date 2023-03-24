import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.List;
import java.util.ArrayList;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제2
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1093-F
 * 3자리 짝수 찾기 
 */

public class Main {
	public static boolean isEven(int N) {
		return N%2==0? true: false;
	}

	public static List<Integer> findUniqueEvenNum(int[] numsFreq){
		List<Integer> ret = new ArrayList<>();
		List<Integer> C = new ArrayList<>();
		findUniqueEvenNum(numsFreq, 0, ret, C);
		return ret;
	}
	
	public static void findUniqueEvenNum(int[] numsFreq, int idx, List<Integer> ret, List<Integer> C) {
		if(idx == 3) {
			ret.add(C.get(0)*100 + C.get(1)*10 + C.get(2));
			return;
		}
		
		for(int i=0; i<10; ++i) {
			if(numsFreq[i] > 0) {
				if(idx == 0 & i!=0) combination(numsFreq, idx, i, ret, C);
				else if(idx == 1) combination(numsFreq, idx, i, ret, C);
				else if (idx == 2 && isEven(i)) combination(numsFreq, idx, i, ret, C);
			}
		}
	}
	
	public static void combination(int[] numsFreq, int idx, int value, List<Integer> ret, List<Integer> C) {
		C.add(idx, value);
		--numsFreq[value];
		findUniqueEvenNum(numsFreq, idx+1, ret, C);
		C.remove(idx);
		++numsFreq[value];
	}
	
	public static void main(String[] args) {
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(br.readLine());
			for(int t=0; t<T; ++t) {
				int N = Integer.parseInt(br.readLine().trim());
				StringTokenizer st = new StringTokenizer(br.readLine());
				int[] numsFreq = new int[10];
				for(int i=0; i<N; ++i) {
					int value = Integer.parseInt(st.nextToken());
					if(value == 0 && numsFreq[value] < 2) 
						++numsFreq[value];
					else if(isEven(value) && value!=0 && numsFreq[value] < 3) 
						++numsFreq[value];
					else if(!isEven(value) && numsFreq[value] < 2) 
						++numsFreq[value];
				}
				List<Integer> result = findUniqueEvenNum(numsFreq);
				result.stream().forEach((x) -> System.out.print(x+" "));
				System.out.println();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}

/* 거꾸로 100부터 for loop를 통해 해결하는 방
public class Main {
	public static StringBuilder solve(int[] N) {
		StringBuilder ret = new StringBuilder();
		for(int i=100; i<=998; i+=2) {
			int[] digit = new int[10];
			for(int curr=i; curr>0; curr/=10)
				++digit[curr%10];
			boolean check = true;
			for(int j=0; j<10; ++j) {
				if(!(N[j] >= digit[j])) check = false;
			}
			if(check) ret.append(i).append(" ");
		}
		return ret;
	}
	public static void main(String[] args) {
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(br.readLine());
			for(int t=0; t<T; ++t) {
				int N = Integer.parseInt(br.readLine().trim());
				StringTokenizer st = new StringTokenizer(br.readLine());
				int[] numsFreq = new int[10];
				for(int i=0; i<N; ++i)
					++numsFreq[Integer.parseInt(st.nextToken())];
				System.out.println(solve(numsFreq));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
} */