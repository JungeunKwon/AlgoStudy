import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class 파일명정렬_2018_BLIND {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String files[] = { "F15", "F-15", "foo010bar020.zip", "img1.png", "IMG01.GIF", "img2.JPG" };
		String result[] = solution(files);
		System.out.println(Arrays.toString(result));
	}

	public static String[] solution(String[] files) {
		String[] answer = {};
		List<Info> list = new ArrayList<>();
		for (int i = 0; i < files.length; i++) {
			String line = files[i];
			boolean ischar = true;
			boolean getnum = false;
			int start = 0;
			String[] units = new String[3];
			int cnt = 0;
			int numbercnt = 0;
			for (int j = 1; j < line.length(); j++) {
				char c = line.charAt(j);
				if (!Character.isDigit(c) && ischar || Character.isDigit(c) && getnum) {

				} else if (Character.isDigit(c) && ischar && !getnum) {
					ischar = false;
					units[cnt] = line.substring(start, j);
					start =j;
					numbercnt++;
					cnt++;
				} else if (Character.isDigit(c) && !ischar && numbercnt < 5) {
					numbercnt++;
				} else if (Character.isDigit(c) && !ischar || numbercnt >= 5) {
					getnum = true;
					ischar = true;
					units[cnt] = line.substring(start, j);
					start = j;
					cnt++;
				} else if (!Character.isDigit(c) && !ischar) {
					getnum = true;
					ischar = true;
					units[cnt] = line.substring(start, j);
					start = j;
					cnt++;
				}
			}
			units[cnt] = line.substring(start, line.length());
			list.add(new Info(units[0], units[1], units[2] == null ? "" : units[2]));
		}
		Collections.sort(list);
		answer = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			answer[i] = list.get(i).toString();
		}
		return answer;
	}

	public static class Info implements Comparable<Info> {
		String head;
		String number;
		String tail;

		public Info(String head, String number, String tail) {
			super();
			this.head = head;
			this.number = number;
			this.tail = tail;
		}

		@Override
		public String toString() {
			return head + number + tail;
		}

		@Override
		public int compareTo(Info o) {
			String tempthis = this.head.toLowerCase();
			String tempo = o.head.toLowerCase();
			if (tempthis.equals(tempo)) {
				int tempnum = Integer.parseInt(this.number);
				int temponum = Integer.parseInt(o.number);
				if (tempnum == temponum)
					return 0;
				if (tempnum < temponum)
					return -1;
				else
					return 1;
			}
			if (tempthis.compareTo(tempo) < 0)
				return -1;
			else
				return 1;
		}

	}
}