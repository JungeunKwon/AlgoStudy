
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class 보석쇼핑_2020_인턴 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String gems[] = { "ZZZ", "YYY", "NNNN", "YYY", "BBB" };
		int[] ans = solution(gems);
		System.out.println(Arrays.toString(ans));
	}

	public static int[] solution(String[] gems) {
		int[] answer = new int[2];
		HashMap<String, Integer> map = new HashMap<>();
		int j = 0;
		for (int i = 0; i < gems.length; i++) {
			if (!map.containsKey(gems[i])) {
				map.put(gems[i], j);
				j++;
			}
		}
		int minstart = 0;
		int minend = 0;
		int min = gems.length + 1;
		HashMap<String, Integer> resultmap = new HashMap<>();
		int start = 0, end = 0;
		int count[] = new int[map.size()];
		count[end] = 0;

		start = 0;
		int zerocnt = 0;
		while (start <= end) {

			if (zerocnt < map.size() && end < gems.length) {
				int gem = map.get(gems[end]);
				if (count[gem] == 0) {
					zerocnt++;
				}
				count[gem]++;

				end++;

			} else if (zerocnt == map.size()) {
				if (min > end - start) {
					min = end - start;
					minstart = start + 1;
					minend = end;
				}
				if (count[map.get(gems[start])] == 1) {
					zerocnt--;
				}
				count[map.get(gems[start])]--;
				start++;
			} else
				break;

		}
		answer[0] = minstart;
		answer[1] = minend;

		return answer;
	}

}
