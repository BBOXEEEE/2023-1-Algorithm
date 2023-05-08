import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * 한국기술교육대학교 2023학년도 1학기 알고리즘및실습 과제7
 * @author 2019136056 박세현 
 * @version 2023학년도 1학기
 * Judge1103-A
 * 마감 시간이 있는 최적 스케줄 짜기  
 */

public class Main {
	public static class Job implements Comparable<Job>{
		int id;
		int deadline, profit;
		public Job(int id, int deadline, int profit) {
			this.id = id;
			this.deadline = deadline;
			this.profit = profit;
		}
		@Override
		public int compareTo(Job o) {
			if(this.profit != o.profit)
				return o.profit-this.profit;
			return this.id-o.id;
		}
	}
	
	public static List<Integer> schedule(List<Job> jobs) {
        Collections.sort(jobs);
        List<Integer> selected = new ArrayList<>();
        boolean[] assigned = new boolean[jobs.size()+1];
        for(Job job: jobs) {
        	for(int i=job.deadline; i>0; --i) {
        		if(!assigned[i]) {
        			assigned[i] = true;
        			selected.add(job.id);
        			break;
        		}
        	}
        }
        Collections.sort(selected);
        return selected;
    }
	public static void main(String[] args) {
		try(
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		){
			int T = Integer.parseInt(br.readLine());
			for(int t=0; t<T; ++t) {
				br.readLine();
				int[] jobsInfo = Arrays.stream(br.readLine().split(" "))
						.mapToInt(Integer::parseInt).toArray();
				List<Job> jobs = new ArrayList<>();
				int id = 1;
				for(int i=0; i<jobsInfo.length-1; i+=2) {
					jobs.add(new Job(id++, jobsInfo[i], jobsInfo[i+1]));
				}
				System.out.println(schedule(jobs).stream().
						map(String::valueOf).collect(Collectors.joining(" ")));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}