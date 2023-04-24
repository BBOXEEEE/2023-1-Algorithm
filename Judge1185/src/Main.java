import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int picnic(boolean[][] friends, boolean[] check, int N) {
		int choose = -1;
		for(int i=0; i<N; ++i)
			if(!check[i]) {
				choose = i;
				break;
			}
		if(choose == -1) return 1;
		
		int ret = 0;
		for(int i=choose+1; i<N; ++i) {
			if(!check[i] && friends[choose][i]) {
				check[choose] = check[i] = true;
				ret += picnic(friends, check, N);
				check[choose] = check[i] = false;
			}
		}
		return ret;
	}
	public static void main(String[] args) {
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(br.readLine());
			for(int t=0; t<T; ++t) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int N = Integer.parseInt(st.nextToken());
				int M = Integer.parseInt(st.nextToken());
				boolean[][] friends = new boolean[N][N];
				st = new StringTokenizer(br.readLine());
				for(int i=0; i<M; ++i){
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					friends[a][b] = friends[b][a] = true;
				}
				boolean[] check = new boolean[N];
				System.out.println(picnic(friends, check, N));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
