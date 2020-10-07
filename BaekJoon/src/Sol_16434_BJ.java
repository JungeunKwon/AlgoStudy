import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Sol_16434_BJ {
	public static int N;
	public static long Hatk;
	public static long calhp;
	public static long potion;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		long initATK = Integer.parseInt(st.nextToken());
		List<Info> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			list.add(new Info(t, a, h));
		}
		long min = Long.MAX_VALUE;
		long start = 1;
		long end = Long.MAX_VALUE;

		while (start <= end) {
			long mid = (start + end) / 2;
			if (play(mid, mid, initATK, list, 0, N)) {
				if (mid < min)
					min = mid;
				else
					break;
				end = mid - 1;
			} else {
				start = mid + 1;
			}

		}
		System.out.println(min);
	}

	private static boolean play(long maxHp, long curHP, long atk, List<Info> list, int idx, int N) {
		for (int i = 0; i < N; i++) {
			Info temp = list.get(i);
			int t = temp.t;
			long a = temp.a;
			long h = temp.h;
			if (t == 1) {
				long q = (h / atk);
				if (h - (atk * q) <= 0) {
					if (curHP - (a * (q - 1)) <= 0)
						return false;
					curHP = curHP - (a * (q - 1));
				} else {
					if (curHP - (a * q) <= 0)
						return false;
					curHP = curHP - (a * q);
				}

			} else {
				if (curHP + h >= maxHp)
					curHP = maxHp;
				else
					curHP = curHP + h;
				atk = atk + a;
			}
		}
		return true;
	}

	public static class Info {
		int t;
		long a;
		long h;

		Info(int t, long a, long h) {
			this.t = t;
			this.a = a;
			this.h = h;
		}
	}
}
