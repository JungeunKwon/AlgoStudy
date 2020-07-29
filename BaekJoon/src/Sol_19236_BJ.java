import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Sol_19236_BJ {
	public static final int N = 4;

	public static int dx[] = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	public static int dy[] = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	public static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] arr = new int[N][N];
		StringTokenizer st;
		max = 0;
		List<Fish> fishlist = new ArrayList<>();
		fishlist.add(new Fish(0, 0, 0, 0, false));
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				fishlist.add(new Fish(num, dir, i, j, true));
				arr[i][j] = num;
			}
		}
		Collections.sort(fishlist);
		Fish firstfish = fishlist.get(arr[0][0]);
		Shark shark = new Shark(0, 0, firstfish.dir, firstfish.num);
		
		firstfish.isAlive = false;
		arr[0][0] = 0;
		dfs(shark, arr, fishlist);
		System.out.println(max);
	}

	private static void dfs(Shark shark, int[][] arr, List<Fish> fishlist) {
		fishmove(fishlist, shark.i, shark.j, arr);
		int tx = shark.j;
		int ty = shark.i;

		for (int i = 0; i < N; i++) {
			tx = tx + dx[shark.dir];
			ty = ty + dy[shark.dir];
			if (tx < 0 || ty < 0 || tx >= N || ty >= N)
				continue;
			if (arr[ty][tx] == 0)
				continue;
			int newarr[][] = new int[N][N];
			for (int j = 0; j < N; j++) {
				newarr[j] = Arrays.copyOf(arr[j], N);
			}
			List<Fish> newlist = new ArrayList<>();
			for(int j = 0; j < fishlist.size(); j ++)
			{
				Fish temp = fishlist.get(j);
				newlist.add(new Fish(temp.num, temp.dir, temp.i, temp.j, temp.isAlive));
			}
			Fish fish = newlist.get(newarr[ty][tx]);
			fish.isAlive = false;
			newarr[ty][tx] = 0;
			
			dfs(new Shark(ty, tx, fish.dir, shark.count + fish.num), newarr, newlist);

		}
		if (max < shark.count) {
			max = shark.count;
		}
	}

	private static void fishmove(List<Fish> fishlist, int sharki, int sharkj, int[][] arr) {
		for (int i = 1; i <= N * N; i++) {
			Fish fish = fishlist.get(i);
			if (!fish.isAlive)
				continue;
			int tx = fish.j;
			int ty = fish.i;
			int dir = fish.dir;
			boolean isRotate = false;
			while (true) {
				tx = fish.j + dx[dir];
				ty = fish.i + dy[dir];
				if (tx < 0 || ty < 0 || tx >= N || ty >= N || (tx == sharkj && ty == sharki)) {
					dir = dir + 1;
					if (dir > 8)
						dir = 1;
					isRotate = true;
					continue;
				}
				break;
			}
			arr[fish.i][fish.j] = 0;
			if (dir == fish.dir && isRotate)
				continue;
			if (arr[ty][tx] != 0) {
				Fish swapfish = fishlist.get(arr[ty][tx]);
				swapfish.i = fish.i;
				swapfish.j = fish.j;
				arr[fish.i][fish.j] = swapfish.num;
			}
			fish.i = ty;
			fish.j = tx;
			fish.dir = dir;
			arr[ty][tx] = fish.num;

		}
	}

	public static class Shark {
		int i;
		int j;
		int dir;
		int count;
		

		Shark(int i, int j, int dir, int count) {
			this.i = i;
			this.j = j;
			this.dir = dir;
			this.count = count;
		
		}
	}

	public static class Fish implements Comparable<Fish> {
		int num;
		int dir;
		int i;
		int j;
		boolean isAlive;

		Fish(int num, int dir, int i, int j, boolean isAlive) {
			this.num = num;
			this.dir = dir;
			this.i = i;
			this.j = j;
			this.isAlive = isAlive;
		}

		@Override
		public int compareTo(Fish o) {
			return this.num - o.num;
		}
	}
}
