import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제3
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1094-C
 * ClosetPair
 */

public class Main {
	private static class Point{
		private int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static double distance(Point p1, Point p2) {
		double dx = (p1.x - p2.x);
        double dy = (p1.y - p2.y);
        return dx*dx + dy*dy;
	}
	
	public static double findClosetPair(Point[] P) {
		double min = Double.MAX_VALUE;
		for(int i=0; i<P.length-1; ++i)
			for(int j=i+1; j<P.length; ++j)
				min = Math.min(min, distance(P[i], P[j]));
		return min;
	}
	
	public static double closetSplitPair(Point[] Px, Point[] Py, double delta) {
		int midX = Px[Px.length/2].x;
		Point[] Sy = new Point[Py.length];
		int idx = 0;
		for(Point Y: Py)
			if((Y.x > midX - delta) && (Y.x < midX + delta))
				Sy[idx++] = Y;
		
		for(int i=0; i<idx-1; ++i) {
			for(int j=i+1; j<Math.min(i+7, idx); ++j) {
				double dist = distance(Sy[i], Sy[j]);
				delta = Math.min(delta, dist);
			}
		}
		return delta;
	}
	
	public static double closetPair(Point[] Px, Point[] Py) {
		if(Px.length <= 3) return findClosetPair(Px);
		int mid = Px.length/2;
		Point[] Lx = Arrays.copyOfRange(Px, 0, mid);
		Point[] Rx = Arrays.copyOfRange(Px, mid, Px.length);
		Point[] Ly = new Point[Lx.length];
		Point[] Ry = new Point[Rx.length];
		
		int leftIdx = 0, rightIdx = 0;
		for(Point Y: Py) {
			if(Y.x < Px[mid-1].x) Ly[leftIdx++] = Y;
			else if(Y.x > Px[mid-1].x) Ry[rightIdx++] = Y;
			else {
				if(Y.y <= Px[mid-1].y && leftIdx < Lx.length) Ly[leftIdx++] = Y;
				else Ry[rightIdx++] = Y;
			}
		}
		double delta = Math.min(closetPair(Lx, Ly), closetPair(Rx, Ry));
		return closetSplitPair(Px, Py, delta);
	}
	
	public static double closetPair(Point[] P) {
		if(P.length<2) return 0;
		Point[] Py = P.clone();
		Arrays.sort(P, new Comparator<Point>(){
			@Override
			public int compare(Point o1, Point o2) {
				if(o1.x == o2.x) return o1.y - o2.y;
				else return o1.x - o2.x;
			}
		});
		Arrays.sort(Py, new Comparator<Point>(){
			@Override
			public int compare(Point o1, Point o2) {
				if(o1.y == o2.y) return o1.x - o2.x;
				else return o1.y - o2.y;
			}
		});
		return closetPair(P, Py);
	}
	public static void main(String[] args) {
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(br.readLine());
			for(int t=0; t<T; ++t) {
				int N = Integer.parseInt(br.readLine().trim());
				StringTokenizer st = new StringTokenizer(br.readLine());
				Point[] points = new Point[N];
				for(int i=0; i<N; ++i)
					points[i] = new Point(Integer.parseInt(st.nextToken()),
							Integer.parseInt(st.nextToken()));
				System.out.printf("%.2f%n", Math.sqrt(closetPair(points)));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}