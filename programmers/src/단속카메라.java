import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 단속카메라 {

	public static void main(String[] args) {
		int routes[][] = {{-20,15},{-14,-5},{-18,-13},{-5,-3}};
		int result = solution(routes);
		System.out.println(result);
	}

	public static int solution(int[][] routes) {
		int answer = 0;
		List<info> list = new ArrayList<>();
		for(int i = 0; i < routes.length ; i++)
		{
			list.add(new info(routes[i][0], routes[i][1]));
		}
		Collections.sort(list);
		int end = -30001;
		for(int i = 0; i < list.size(); i ++)
		{
			if(end < list.get(i).start)
			{
				answer++;
				end = list.get(i).end;
			}
			if(end > list.get(i).end)
				end = list.get(i).end;
			
			
		}
		return answer;
	}
	public static class info implements Comparable<info>{
		int start;
		int end;
		@Override
		public String toString() {
			return "info [start=" + start + ", end=" + end + "]";
		}
		info(int start, int end)
		{
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(info o) {
			if(this.end == o.end)
			{
				return this.start - o.start;
			}else
				return this.end - o.end;
		}
	}
}
