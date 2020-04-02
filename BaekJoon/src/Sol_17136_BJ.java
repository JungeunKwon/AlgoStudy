import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_17136_BJ {
	public static int arr[][];
	public static boolean visited[][];
	public static int N;
	public static int min;
	public static int paper[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = 10;
		arr = new int[N][N];
		visited = new boolean[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		paper = new int[6];
		for(int i = 0; i < 5; i ++)
		{
			paper[i] = 5;
		}
			min = -1;
		paste(0, 0,paper, 0);
		System.out.println(min);
	}

	private static void paste(int nexti, int nextj, int paper[], int count) {
		for(int i = 0; i < 5; i ++)
		{
			if(paper[i] < 0)
				return;
		}
		if (nextj == 10) {
			nextj = 0;
			nexti++;
		}
		if (nexti == 10) {
			if (min != -1) {
				if (min > count)
					min = count;
			} else {
				min = count;
			}
			return;
		}
		if (arr[nexti][nextj] != 1) {
			paste(nexti, nextj + 1, paper, count);
			return;
		}

		for (int i = 0; i < 5; i++) {
			boolean flag = true;
			for(int k = nexti; k <= nexti+i; k ++)
			{
				for(int l = nextj ; l <=nextj+i; l ++)
				{
					if(k >= N || l >= N) 
					{
						flag = false;
						continue;
					}
					if(arr[k][l] == 0)
					{
						flag = false;
					}
				}
			}
			if(flag)
			{
				for(int k = nexti; k <= nexti+i; k ++)
				{
					for(int l = nextj ; l <=nextj+i; l ++)
					{
						arr[k][l] = 0;
					}
				}
				paper[i] --;
				paste(nexti, nextj+1, paper, count+1 );
				paper[i] ++;
				for(int k = nexti + i; k >= nexti; k --)
				{
					for(int l = nextj+i ; l >=nextj; l --)
					{
						arr[k][l] = 1;
					}
				}
			}
			
			
		}
	}

}
