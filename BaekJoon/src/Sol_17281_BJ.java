import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_17281_BJ {
	public static int N;
	public static int player[][];
	public static boolean flag[];
	public static int max;
	public static int ground[];
	public static int pos;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		player = new int[N][9];
		int order[] = new int[9];
		flag = new boolean[9];
		StringTokenizer st;
		for(int i = 0; i < N; i ++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9 ; j ++)
			{
				player[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		flag[0] = true;
		order[3] = 0;
		max = 0;
		Perm(order, 0);
		System.out.println(max);
	}
	private static void Perm(int[] order, int idx) {
		if(idx==3) {
			Perm(order,idx+1);
			return;
		}
		if(idx >8)
		{
			pos = 0;
			int score = 0;
			for(int t = 0; t < N ; t ++)
			{
				ground = new int[3];
				score += play(t, order);
			
			}
			if(max < score) max =score;
			return;
		}
		for(int i = 1 ; i < 9 ; i ++)
		{
			if(flag[i])continue;
			flag[i] = true;
			order[idx] = i;
			
			Perm(order, idx+1);
			flag[i] = false;
		
		}
		
	}
	private static int play(int t, int[] order) {
		int score = 0;
		int out = 0 ;
		while(out < 3)
		{
			if(pos == 9) pos = 0;
			int nowplay = order[pos];
			int playerresult = player[t][nowplay];
			int i = 0;
			aa : switch(playerresult)
			{
			case 0:
				out++;
				break aa;
			case 1:
				i = 1;
				if(ground[2] == 1) score++;
				ground[2] = ground[1];
				ground[1] = ground[0];
				ground[0] = 1;
				break aa;
			case 2:
				i = 2;
				for(int j = 1; j <3; j ++)
				{
					if(ground[j] == 1) score++;
					ground[j] = 0;
				}
				ground[2] = ground[0];
				ground[1] = 1;
				ground[0] = 0;
				break aa;
			case 3:
				i = 3;
				for(int j = 0; j <3; j ++)
				{
					if(ground[j] == 1) score++;
					ground[j] = 0;
				}
				ground[2] = 1;
				ground[1] = 0;
				ground[0] = 0;
				break aa;
			case 4:
				 i =4;
				 for(int j = 0 ; j < 3; j ++)
				 {
					 if(ground[j] == 1) score++;
					 ground[j] = 0;
				 }
				 score++;
				break aa;
			}
			
			pos ++;
		}
		return score;
	}

}
