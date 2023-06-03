import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제11
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1107-C
 * 2진법 시계 BinaryClock 
 */

public class Main {
	public static int getHour(int index) {
		if(index == 0) return 8;
		else if(index == 1) return 4;
		else if(index == 2) return 2;
		else return 1;
	}
	
	public static int getMinute(int index) {
		if(index == 4) return 32;
		else if(index == 5) return 16;
		else if(index == 6) return 8;
		else if(index == 7) return 4;
		else if(index == 8) return 2;
		else return 1;
	}
	
	public static int binaryClock(int[] bits, int index, int remain, int hour, int minute) {
		if(remain == 0) {
			if(hour > 11 || minute > 59) return 0;
			return 1;
		}
		if(index == bits.length) return 0;
		int ret = 0;
		
		if(index < 4 && hour + getHour(index) <= 11) {
			bits[index] = 1;
			ret += binaryClock(bits, index+1, remain-1, hour+getHour(index), minute);
			bits[index] = 0;
		}
		else if(index >=4 && minute + getMinute(index) <= 59){
			bits[index] = 1;
			ret += binaryClock(bits, index+1, remain-1, hour, minute+getMinute(index));
			bits[index] = 0;
		}
		ret += binaryClock(bits, index+1, remain, hour, minute);
		return ret;
	}
	
	public static int binaryClock(int[] bits, int K) {
		return binaryClock(bits, 0, K, 0, 0);
	}
	
	public static void main(String[] args) {
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(br.readLine());
			for(int t=0; t<T; ++t) {
				int K = Integer.parseInt(br.readLine());
				int[] bits = new int[10];
				System.out.println(binaryClock(bits, K));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}