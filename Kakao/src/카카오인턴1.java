import java.util.LinkedList;
import java.util.Queue;

public class 카카오인턴1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int numbers[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
		String hand = "right";
		String ans = solution(numbers, hand);
		System.out.println(ans);
	}

	public static int dx[] = { 0, -1, 0, 1 };
	public static int dy[] = { 1, 0, -1, 0 };
	public static Queue<Info> q;

	public static String solution(int[] numbers, String hand) {
		String answer = "";
		StringBuilder sb = new StringBuilder();
		int arr[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { -1, 0, -1 } };
		Info right = new Info(3, 2);
		Info left = new Info(3, 0);
		int d1 = 0, d2 = 0;
		q = new LinkedList<>();
	
		for (int i = 0; i < numbers.length; i++) {
			q.clear();
			switch (numbers[i]) {
			case 1:
				sb.append("L");
				left.setI(0);
				left.setJ(0);
				break;

			case 2:
				q.offer(left);
				d1 = find(left, 2, arr);
				q.clear();
				q.offer(right);
				d2 = find(right, 2, arr);
				if (d1 == d2) {
					if (hand.equals("right")) {
						sb.append("R");
						right.setI(0);
						right.setJ(1);
					} else {
						sb.append("L");
						left.setI(0);
						left.setJ(1);
					}

				} else if (d1 < d2) {
					sb.append("L");
					left.setI(0);
					left.setJ(1);

				} else {
					sb.append("R");
					right.setI(0);
					right.setJ(1);
				}
				break;

			case 3:
				sb.append("R");
				right.setI(0);
				right.setJ(2);
				break;

			case 4:
				sb.append("L");
				left.setI(1);
				left.setJ(0);

				break;

			case 5:
				q.offer(left);
				d1 = find(left, 5, arr);
				q.clear();
				q.offer(right);
				d2 = find(right, 5, arr);
				if (d1 == d2) {
					if (hand.equals("right")) {
						sb.append("R");
						right.setI(1);
						right.setJ(1);
					} else {
						sb.append("L");
						left.setI(1);
						left.setJ(1);
					}

				} else if (d1 < d2) {
					sb.append("L");
					left.setI(1);
					left.setJ(1);
				} else {
					sb.append("R");
					right.setI(1);
					right.setJ(1);
				}
				break;

			case 6:
				sb.append("R");
				right.setI(1);
				right.setJ(2);

				break;

			case 7:
				sb.append("L");
				left.setI(2);
				left.setJ(0);

				break;

			case 8:
				q.offer(left);
				d1 = find(left, 8, arr);
				q.clear();
				q.offer(right);
				d2 = find(right, 8, arr);
				if (d1 == d2) {
					if (hand.equals("right")) {
						sb.append("R");
						right.setI(2);
						right.setJ(1);
					} else {
						sb.append("L");
						left.setI(2);
						left.setJ(1);
					}

				} else if (d1 < d2) {
					sb.append("L");
					left.setI(2);
					left.setJ(1);
				} else {
					sb.append("R");
					right.setI(2);
					right.setJ(1);
				}
				
				break;
			case 9:
				sb.append("R");
				right.setI(2);
				right.setJ(2);

				break;
			case 0:
				q.offer(left);
				d1 = find(left, 0, arr);
				q.clear();
				q.offer(right);
				d2 = find(right, 0, arr);
				if (d1 == d2) {
					if (hand.equals("right")) {
						sb.append("R");
						right.setI(3);
						right.setJ(1);
					} else {
						sb.append("L");
						left.setI(3);
						left.setJ(1);
					}

				} else if (d1 < d2) {
					sb.append("L");
					left.setI(3);
					left.setJ(1);
				} else {
					sb.append("R");
					right.setI(3);
					right.setJ(1);
				}
				break;
			}

		}
		return sb.toString();
	}

	private static int find(Info left, int num, int arr[][]) {
		int tx = 0, ty = 0;
		int count = 0;
		boolean visited[][] = new boolean[4][3];
		visited[left.i][left.j] = true;
		if(arr[left.i][left.j] == num) return 0;
		while (!q.isEmpty()) {
			int size = q.size();
			count++;
			for (int i = 0; i < size; i++) {
				Info temp = q.poll();
				for (int j = 0; j < 4; j++) {
					tx = temp.j + dx[j];
					ty = temp.i + dy[j];
					if (tx < 0 || ty < 0 || tx > 2 || ty > 3)
						continue;
					if (visited[ty][tx])
						continue;
					if (arr[ty][tx] == -1)
						continue;
					if (arr[ty][tx] == num)
						return count;
					visited[ty][tx] = true;
					q.offer(new Info(ty, tx));

				}

			}
		}
		return count;
	}

	public static class Info {
		int i;
		int j;

		public int getI() {
			return i;
		}

		public void setI(int i) {
			this.i = i;
		}

		public int getJ() {
			return j;
		}

		public void setJ(int j) {
			this.j = j;
		}

		Info(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
