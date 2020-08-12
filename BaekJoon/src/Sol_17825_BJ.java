import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Sol_17825_BJ {
	public static int N;
	public static int max;

	public static int pan[] = { 0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 13, 16,
			19, 28, 27, 26, 22, 24, 25, 30, 35 };
	public static int nextpos[] = { 1, 2, 3, 4, 5, 21, 7, 8, 9, 10, 27, 12, 13, 14, 15, 24, 17, 18, 19, 20, -1, 22, 23,
			29, 25, 26, 29, 28, 29, 30, 31, 20 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = 10;
		int dice[] = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}
		max = 0;

		int[] yutarr = new int[10];
		play(dice, yutarr, 0);
		System.out.println(max);

	}

	private static void play(int[] dice, int[] yutarr, int idx) {
		if (idx >= N) {
			Yut[] yut = new Yut[4];
			for (int i = 0; i < 4; i++) {
				yut[i] = new Yut(0, false);
			}
			int score = 0;
			aa : for (int i = 0; i < yutarr.length; i++) {
				int yutnum = yutarr[i];
				if (yut[yutnum].isFinish)
					continue;
				int pos = yut[yutnum].num;
				int move = dice[i];
				int Inextpos = 0;
				for (int j = 0; j < move; j++) {
					if (nextpos[pos] == -1) {
						yut[yutnum].isFinish = true;
						continue aa;
					}
					if(j > 0 && j <= move -1 && pos == 5)
					{
						pos = 6;						
					}else if(j > 0 && j <= move -1 && pos == 10)
					{
						pos = 11;
					}else if(j > 0 && j <= move -1 && pos == 15)
					{
						pos = 16;
					}else
					{
						Inextpos = nextpos[pos];
						pos = Inextpos;
					}
					
				}

				for (int j = 0; j < 4; j++) {
					if(yutnum == j)continue;
					if(yut[j].isFinish) continue;
					if (yut[j].num == pos)
						return;
				}
				yut[yutnum].num = pos;
				score += pan[pos];
			}
			if (max < score)
			{
				max = score;
				
			}
			return;
		}
		for (int i = 0; i < 4; i++) {
			yutarr[idx] = i;
			play(dice, yutarr, idx + 1);

		}
	}

	public static class Yut {
		int num;
		boolean isFinish;
		Yut(int num, boolean isFinish) {
			this.num = num;
			this.isFinish = isFinish;
	
		}
		
		
	}

}
