import java.util.*;

public class 다트게임_2018_BLIND {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String dartResult = "1S*2T*3S";

		int result = solution(dartResult);
		System.out.println(result);
	}

	public static int solution(String dartResult) {
		int answer = 0;
		int score[] = new int[3];
		for (int i = 0, j = 0; i < dartResult.length(); i++) {
			String temp = "";
			String tempscore = "" + dartResult.charAt(i);
			int k = i + 1;
			while (k <= dartResult.length() - 1) {
				char c = dartResult.charAt(k);
				if (Character.isDigit(c) && k - i > 1)
					break;
				else if (Character.isDigit(c)) {
					tempscore = tempscore + c;
				} else {
					temp = temp + c;
				}
				k++;
			}
			i = k - 1;
			score[j] = Integer.parseInt(tempscore);
			for (int m = 0; m < temp.length(); m++) {
				char c = temp.charAt(m);
				switch (c) {
				case 'S':
					score[j] = (int) Math.pow(score[j], 1);
					break;
				case 'D':
					score[j] = (int) Math.pow(score[j], 2);
					break;
				case 'T':
					score[j] = (int) Math.pow(score[j], 3);
					break;
				case '*':
					if (j > 0) {
						score[j - 1] = score[j - 1] * 2;
						score[j] = score[j] * 2;
					} else {
						score[j] = score[j] * 2;
					}
					break;
				case '#':
					score[j] = score[j] * -1;
					break;
				}
			}
			j++;
		}
		answer = score[0] + score[1] + score[2];
		return answer;
	}
}
