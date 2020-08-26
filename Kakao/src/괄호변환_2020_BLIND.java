import java.util.Stack;

public class 괄호변환_2020_BLIND {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = ")(";
		String result = solution(s);
		System.out.println(result);
	}

	public static String temp = "";

	public static String solution(String p) {
		String answer = "";

		String u = "";
		String v = p;
		if (isCorrect(p))
			return p;
		answer = dfs(u, v);

		return answer;
	}

	private static String dfs(String u, String v) {
		if (isCorrect(v)) {
			return v;
		}
		int opencnt = 0;
		int closecnt = 0;
		int N = v.length();
		String tempu = "";
		String tempv = "";

		for (int i = 0; i < N; i++) {
			char c = v.charAt(i);
			if (c == '(')
				opencnt++;
			else
				closecnt++;
			if (opencnt != 0 && opencnt == closecnt) {
				tempu = v.substring(0, i + 1);
				tempv = v.substring(i + 1, N);
				break;
			}
		}
		if (isCorrect(tempu)) {
			return tempu + dfs(tempu, tempv);
		} else {
			String empty ="("+ dfs(tempu, tempv) + ")";
			tempu = tempu.substring(1, tempu.length()-1);
			for(int i = 0; i < tempu.length(); i++) {
				char c = tempu.charAt(i);
				if (c == '(')
					empty = empty + ")";
				else
					empty = empty + "(";
			}
			return empty;
		}
	}

	private static boolean isCorrect(String p) {
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < p.length(); i++) {
			char c = p.charAt(i);
			if (c == '(')
				stack.push(c);
			else {
				if (stack.isEmpty())
					return false;
				if (stack.peek() == '(') {
					stack.pop();
				}
			}
		}
		return stack.isEmpty();
	}
}
