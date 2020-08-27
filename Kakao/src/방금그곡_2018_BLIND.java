import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class 방금그곡_2018_BLIND {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String m = "A#";
		String musicinfos[] = { "13:00,13:02,HAPPY,B#A#", "12:00,12:14,WORLD,CDCDF" };

		String result = solution(m, musicinfos);
		System.out.println(result);
	}

	public static String solution(String m, String[] musicinfos) {

		String answer = "";
		StringTokenizer st;
		m = changemusic(m);
		int max = 0;
		for (int i = 0; i < musicinfos.length; i++) {
			st = new StringTokenizer(musicinfos[i], ",");
			String start = st.nextToken();
			String end = st.nextToken();
			String title = st.nextToken();
			String code = st.nextToken();

			String[] time1 = start.split(":");
			String[] time2 = end.split(":");
			int hour = Integer.parseInt(time2[0]) - Integer.parseInt(time1[0]);
			int minute = Integer.parseInt(time2[1]) - Integer.parseInt(time1[1]);
			int totalTime = minute + 60 * hour;
			// System.out.println(totalTime);
			String temp = changemusic(code);
			while (totalTime >= temp.length()) {
				temp = temp + temp;
			}
			temp = temp.substring(0, totalTime);
			if (temp.contains(m) && max < totalTime) {
				max = totalTime;
				answer = title;
			}
		}

		if (answer.equals(""))
			answer = "(None)";
		return answer;

	}

	private static String changemusic(String m) {
		m = m.replaceAll("C#", "c");
		m = m.replaceAll("D#", "d");
		m = m.replaceAll("F#", "f");
		m = m.replaceAll("G#", "g");
		m = m.replaceAll("A#", "a");
		m = m.replaceAll("B#", "b");
		return m;

	}

}