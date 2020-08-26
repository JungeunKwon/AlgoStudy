
public class 문자열압축_2020_BLIND {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "aabbaccc";
		int result = solution(s);
		System.out.println(result);
	}

	public static int solution(String s) {
		int answer = s.length();
		int length = s.length();
		for (int i = 1; i < length; i++) {
			String temp = s;
			long count = 1;
			String result = "";
			String sub1 = "";
			String sub2 = "";
			for (int j = 0; j < length - i; j++) {
				sub1 = s.substring(j, j + i);
				sub2 = "";
				if (j + i + i > length) {
					sub2 = s.substring(j + i, length);
				} else {
					sub2 = s.substring(j + i, j + i + i);
				}
				if (sub1.equals(sub2)) {
					count++;
				} else {
					if(count == 1) {
						result = result +  sub1;
					}else {
						result = result + count + sub1;
					}
					
					count = 1;
				}

				j = j + i - 1;
			}
			if (sub1.equals(sub2)) {
				result = result + count + sub1;
			} else {
				if (count == 1) {
					result = result + sub2;

				} else {
					result = result + count + sub1 + sub2;

				}
				count = 1;
			}
			if (result.length() < answer) {
				answer = result.length();
				
			}System.out.println(result);
		}
		return answer;
	}
}
