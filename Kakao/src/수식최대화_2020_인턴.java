
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import java.util.TreeSet;

public class 수식최대화_2020_인턴 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String expression = "100-200*300-500+20";

		long ans = solution(expression);
		System.out.println(ans);
	}

	public static boolean flag[];
	public static long max = 0;

	public static long solution(String expression) {
		long answer = 0;
		max = 0;
		TreeSet<String> op = new TreeSet();
		ArrayList<String> q = new ArrayList<>();
		String num = "";
		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);
			if (Character.isDigit(c)) {
				num = num + c;
			} else {
				op.add(c + "");
				q.add(num);
				q.add(c + "");
				num = "";
			}
		}
		q.add(num);
		String[] order = new String[op.size()];
		String[] neworder = new String[op.size()];
		flag = new boolean[op.size()];

		Iterator<String> itr = op.iterator();
		int i = 0;
		while (itr.hasNext()) {
			order[i] = itr.next();
			i++;
		}

		Perm(q, order, neworder, 0);

		return max;
	}

	private static void Perm(ArrayList<String> q, String[] order, String[] neworder, int idx) {
		if (idx >= order.length) {
			LinkedList<String> newq = new LinkedList<>();

			for (int i = 0; i < q.size(); i++) {
				String temp = q.get(i);
				newq.addLast(temp);
			}
			long t = Carculate(newq, neworder);
			if (max < t)
				max = t;
			return;
		}

		for (int i = 0; i < order.length; i++) {
			if (flag[i])
				continue;
			flag[i] = true;
			neworder[idx] = order[i];
			Perm(q, order, neworder, idx + 1);
			flag[i] = false;

		}

	}

	private static long Carculate(LinkedList<String> q, String[] neworder) {
		for (int i = 0; i < neworder.length; i++) {
			for (int j = 0; j < q.size(); j++) {
				String temp = q.get(j);
				if (temp.equals(neworder[i])) {
					String num = q.get(j - 1);
					String num2 = q.get(j + 1);
					String newnum = cal(num, temp, num2);
					q.set(j - 1, newnum);
					q.remove(j + 1);
					q.remove(j);

					j--;
					j--;
				}

			}
		}

		Long result = Math.abs(Long.parseLong(q.get(0)));
		return result;
	}

	private static String cal(String num, String op, String num2) {
		long numI = Long.parseLong(num);
		long num2I = Long.parseLong(num2);
		long result = 0;
		switch (op) {
		case "*":
			result = numI * num2I;
			break;
		case "-":
			result = numI - num2I;

			break;
		case "+":
			result = numI + num2I;
			break;
		}
		return Long.toString(result);
	}
}
