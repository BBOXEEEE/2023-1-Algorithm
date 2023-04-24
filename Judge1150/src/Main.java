import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Main {
	public static int organizeCard(char[] cards, int length) {
		List<Character> vowels = Arrays.asList('A', 'E', 'I', 'O', 'U');
		int ret = 0;
		int idx = 0;
		for(int i=0; i<length; ++i) {
			if(vowels.contains(cards[i])) ret += length-i;
			else cards[idx++] = cards[i];
		}
		return ret;
	}
	public static int organizeCard(String S) {
		char[] cards = S.toCharArray();
		return organizeCard(cards, cards.length-1);
	}
	
	public static void main(String[] args) {
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(br.readLine());
			for(int t=0; t<T; ++t) {
				String S = br.readLine();
				System.out.println(organizeCard(S));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
