import java.util.*;

public class 셔틀버스_2018_BLIND {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 2;
		int t = 1;
		int m = 2;
		String[] timetable = { "09:00", "09:00", "09:00", "09:00" };
		String result = solution(n, t, m, timetable);
		System.out.println(result);
	}

	public static String solution(int n, int t, int m, String[] timetable) {
		String answer = "";
		List<Info> list = new ArrayList<>();
		StringTokenizer st;
		for (int i = 0; i < timetable.length; i++) {
			st = new StringTokenizer(timetable[i], ":");
			list.add(new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(list);
		int max = 0;
		int time = 900;
		int last = m;
		for (int i = 0; i < n; i++) {
			last = m;
			for (int j = 0; j < list.size(); j++) {
				Info temp = list.get(j);
				if (temp.time <= time) {
					last--;
					list.remove(j);
					j--;
				} else
					break;
				if (last == 0) {
					int hh = temp.h;
					int mm = temp.m;

					if (mm - 1 < 0) {
						mm = 59;
						hh--;
					} else
						mm--;
					if (max < hh * 100 + mm)
						max = hh * 100 + mm;
					break;
				}
			}
			if (last > 0) {
				if (time > max)
					max = time;
			}
			time = time + (t / 60) * 100 + (t % 60);
		}
		answer = String.format("%02d" + ":" + "%02d", max / 100, max % 100);
		return answer;
	}

	public static class Info implements Comparable<Info> {
		int h;
		int m;
		int time;

		public Info(int h, int m) {
			super();
			this.h = h;
			this.m = m;
			this.time = h * 100 + m;

		}

		@Override
		public int compareTo(Info o) {
			return this.time - o.time;
		}
	}
}