import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class 호텔방배정_2019_인턴 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long k = 10;
		long[] room_number = { 1, 3, 4, 1, 3, 1 };
		long[] ans = solution(k, room_number);
		System.out.println(Arrays.toString(ans));
	}

	public static long[] solution(long k, long[] room_number) {
		long[] answer = new long[room_number.length];
		TreeMap<Long, Long> map = new TreeMap<>();
		for (int i = 0; i < room_number.length; i++) {
			long num = room_number[i];
			if (map.containsKey(num)) {
				List<Long> numlist = new ArrayList<>();
				while (true) {
					long temp = map.get(num);
					numlist.add(num);
					if (map.containsKey(temp)) {
						num = temp;
					} else {
						for (int j = 0; j < numlist.size(); j++) {
							map.put(numlist.get(j), temp + 1);
						}
						map.put(temp, temp + 1);
						answer[i] = temp;
						break;
					}
				}
			} else {
				map.put(num, num + 1);
				answer[i] = num;
			}
		}

		return answer;

	}

}
