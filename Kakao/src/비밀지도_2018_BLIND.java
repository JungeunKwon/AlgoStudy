import java.util.Arrays;

public class 비밀지도_2018_BLIND {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 5;
		int arr1[] = {0, 0, 0 ,0, 0};
		int arr2[] = {30,1,21,17,28 };

		String result[] = solution(n, arr1, arr2);
		System.out.println(Arrays.toString(result));
	}

	public static String[] solution(int n, int[] arr1, int[] arr2) {
		String[] answer = new String[n];
		for (int i = 0; i < n; i++) {
			int a1 = arr1[i];
			int a2 = arr2[i];
			int a3 = a1 | a2;
			String result = Integer.toBinaryString(a3);
			String line = "";
			if(result.length() < n) {
				int size = result.length();
				for (int j = 0; j < n-size; j++) {
					result = "0" + result; 
				}
			}
			for (int j = 0; j < n; j++) {
				char c = result.charAt(j);
				if (c == '0') {
					line = line + " ";
				} else {
					line = line + "#";

				}
			}
			answer[i] = line;
			// Integer.toBinaryString(a1)
		}
		return answer;
	}
}
