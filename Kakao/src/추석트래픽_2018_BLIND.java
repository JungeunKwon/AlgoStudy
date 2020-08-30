import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import java.util.List;
import java.util.StringTokenizer;

public class 추석트래픽_2018_BLIND {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String lines[] = { "2016-09-15 01:00:04.002 2.0s", "2016-09-15 01:00:07.000 2s" };
		int result = solution(lines);
		System.out.println(result);
	}


	public static int solution(String[] lines) throws Exception {
		int answer = 0;
		StringTokenizer st;
		List<Info> list = new ArrayList<>();
		for (int i = 0; i < lines.length; i++) {
			st = new StringTokenizer(lines[i]);
			st.nextToken();
			String times = st.nextToken();
			String secdiff = st.nextToken().replace("s", "");
			st = new StringTokenizer(times, ":");
			double hour = Double.parseDouble(st.nextToken());
			double min = Double.parseDouble(st.nextToken());
			double sec = Double.parseDouble(st.nextToken());
			double f = Double.parseDouble(secdiff);
			Info temp = new Info(hour, min, sec);
			temp.end = hour * 3600 + min * 60 + sec;
			temp.start = temp.end - f + 0.001;
			list.add(temp);

		}
		int cnt = 1;
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			double end = Math.ceil(list.get(i).end + 0.999);
			cnt = 0;
			for (int j = i; j < list.size(); j++) {
				if (list.get(j).start <= end)
					cnt++;
			}
			if (cnt > answer)
				answer = cnt;
		}
		return answer;
	}

	public static class Info implements Comparable<Info> {
		double hour;
		double min;
		double sec;
		double start;
		double end;

		public Info(double hour, double min, double sec) {
			super();
			this.hour = hour;
			this.min = min;
			this.sec = sec;

		}

		@Override
		public int compareTo(Info o) {
			if (this.end == o.end) {
				if (this.start < o.start)
					return -1;
				else
					return 1;
			} else if (this.end > o.end)
				return 1;
			else
				return -1;
		}
	}
}