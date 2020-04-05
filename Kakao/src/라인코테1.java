
public class 라인코테1 {

	public static void main(String[] args) throws Exception {
		String inputString = ">_<";
		int ans = solution(inputString);
		System.out.println(ans);
	}

	public static int solution(String inputString) {
		int answer = 0;
		int count[] = new int[4];
		// (= 0 { = 1 [ = 2 < = 3
		for (int i = 0; i < inputString.length(); i++) {
			char temp = inputString.charAt(i);
			if (temp == '(') {
				count[0]++;
			} else if (temp == ')') {
				if (count[0] > 0) {
					count[0]--;
					answer++;
				} else {
					answer = -1;
					break;
				}
			} else if (temp == '{') {
				count[1]++;
			} else if (temp == '}') {
				if (count[1] > 0) {
					count[1]--;
					answer++;
				} else {
					answer = -1;
					break;
				}
			} else if (temp == '[') {
				count[2]++;

			} else if (temp == ']') {
				if (count[2] > 0) {
					count[2]--;
					answer++;
				} else {
					answer = -1;
					break;
				}
			} else if (temp == '<') {
				count[3]++;

			} else if (temp == '>') {
				if (count[3] > 0) {
					count[3]--;
					answer++;
				} else {
					answer = -1;
					break;
				}
			}
		}
		for (int i = 0; i < count.length; i++) {
			if (count[i] > 0) {
				answer = -1;
				break;
			}

		}
		return answer;
	}
}
