import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class æ–√‡_2018_BLIND {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String msg = "TOBEORNOTTOBEORTOBEORNOT";
		int result[] = solution(msg);
		System.out.println(Arrays.toString(result));
	}

	public static int[] solution(String msg) {
		int[] answer = {};
		List<Integer> list = new ArrayList<>();
		HashMap<String, Integer> map = new HashMap<>();
		int num = 1;
		char alpa = 'A';
		for (int i = 0; i < 26; i++) {
			map.put(Character.toString(alpa), num);
			alpa++;
			num++;
		}
		int findkey = 0;
		aa:for (int i = 0; i < msg.length(); i++) {
			findkey = 0;
			for (int j = i; j < msg.length() + 1; j++) {
				String temp = msg.substring(i, j);
				if (temp.equals(""))
					continue;
				if (map.containsKey(temp)) {
					findkey = map.get(temp);
					if(j >= msg.length())
					{
						list.add(findkey);
						int gap = j - i;
						if (gap > 2) {
							i = i + gap - 2;
						}
						break aa;
					}
					continue;
				} else {
					map.put(temp, num);
					num++;
					list.add(findkey);
					int gap = j - i;
					if (gap > 2) {
						i = i + gap - 2;
					}
					break;
				}
			}
			
		}

		answer = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			answer[i] = list.get(i);
		}
		return answer;
	}
}
