import java.util.*;

public class 캐시_2018_BLIND {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int cacheSize = 3;
		String[] cities = { "Jeju", "Pangyo","Seoul" ,"Jeju", "Pangyo","Seoul","Jeju", "Pangyo","Seoul"};
		int result = solution(cacheSize, cities);
		System.out.println(result);
	}

	public static int solution(int cacheSize, String[] cities) {
		int answer = 0;
		String cache[] = new String[cacheSize];
		int cachetime[] = new int[cacheSize];
		for (int i = 0; i < cities.length; i++) {
			String city = cities[i].toLowerCase();
			boolean flag = false;
			int min = i;
			int pos = 0;
			for (int j = 0; j < cacheSize; j++) {
				if (cache[j] != null &&cache[j].equals(city)) {
					flag = true;
					cachetime[j] = i;
					break;
				}
				if(cache[j] == null) {
					min = -1;
					pos = j;
				}
				if (min > cachetime[j]) {
					min = cachetime[j];
					pos = j;
				}
			}
			if (flag) {
				answer++;
			} else {
				answer = answer + 5;
				if(cacheSize!=0) {
					cache[pos] = city;
					cachetime[pos] = i;
				}
			
			}
		}
		return answer;
	}
}