import java.util.*;

public class 뉴스클러스터링_2018_BLIND {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str1 = "FRANCE";
		String str2 = "french";

		int result = solution(str1, str2);
		System.out.println(result);
	}

	public static int solution(String str1, String str2) {
		int answer = 0;
		str1 = str1.toUpperCase();
		str2 = str2.toUpperCase();
		List<String> list1 = new ArrayList<>();
		List<String> list2 = new ArrayList<>();
		for (int i = 0; i < str1.length() - 1; i++) {
			char c1 = str1.charAt(i);
			char c2 = str1.charAt(i + 1);
			if (!Character.isAlphabetic(c1) || !Character.isAlphabetic(c2))
				continue;
			String temp = c1 + "" + c2;
			list1.add(temp);
		}
		for (int i = 0; i < str2.length() - 1; i++) {
			char c1 = str2.charAt(i);
			char c2 = str2.charAt(i + 1);
			if (!Character.isAlphabetic(c1) || !Character.isAlphabetic(c2))
				continue;
			String temp = c1 + "" + c2;
			list2.add(temp);
		}
		int list1size = list1.size();
		int list2size = list2.size();
		int samecnt = 0;
		int totalcnt = list1size + list2size;
		for (int i = 0; i < list1size; i++) {
			String temp = list1.get(i);
			for (int j = 0; j < list2.size(); j++) {
				if (list2.get(j).equals(temp)) {
					samecnt++;
					list2.remove(j);
					break;
				}
			}
		}
		if (samecnt == 0 && totalcnt == 0) {
			samecnt = 1;
			totalcnt = 1;
		} else {
			totalcnt = totalcnt - samecnt;
		}
		double result = (double) samecnt / (double) totalcnt;
		answer = (int) (result * 65536);
		return answer;
	}
}