import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sol_17406_BJ {
	public static int N;
	public static int M;
	public static Info op[];
	public static int result[];
	public static boolean flag[];
	public static int arr[][];
	public static int dx [] = {1,0,-1,0};
	public static int dy [] = {0,1,0,-1} ;
	public static int min ;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		op = new Info[K];
		min = Integer.MAX_VALUE;
		result = new int[K];
		flag = new boolean [K];
		arr = new int[N+1][M+1];
		for(int i =1; i <= N ; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= M ; j ++)
			{
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0; i < K; i++)
		{
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			op[i] = new Info(r, c, s);
		}
		perm(op, 0, K);
		System.out.println(min);
	}
	private static void perm(Info[] op2, int idx, int k) {
		if(idx >= k)
		{
			int temp[][] = new int[N+1][M+1];
			for(int i = 1; i <=N; i ++ )
			{
				temp[i] = Arrays.copyOf(arr[i], M+1);
			}
			for(int i = 0; i < k ; i ++)
			{
				rotate(temp,op2[result[i]]);
			}
			aa : for(int i = 1; i <= N ; i ++) {
				int innermin = 0;
				for(int j = 1; j <=M ;j  ++)
				{
					innermin += temp[i][j];
					if(innermin > min) continue aa;
				}
				if(min > innermin) min = innermin;
			}
			return;
		}
		for(int i = 0; i < k ; i ++)
		{
			if(flag[i])continue;
			flag[i] = true;
			result[idx] = i;
			perm(op2, idx + 1, k);
			flag[i] = false;
		}
	}
	private static void rotate(int[][] temp, Info info) {
		int s = 1;
		int ti = info.i;
		int tj = info.j;
		while(s <= info.s)
		{
			int first = right(temp,ti-s, tj-s, ti-s, tj+s);
		
			int second = down(temp,ti-s, tj+s, ti+s, tj+s, first);
			
			int third = left(temp,ti+s, tj+s, ti+s, tj-s,second);
			
			int fourth = up(temp,ti+s, tj-s, ti-s, tj-s,third);
		
			
			s++;
		}
	}
	
	

	private static int up(int[][] temp, int i, int j, int k, int l, int third) {
		
		//int tempnum = temp[k][l];
		for(int m = k; m < i ; m ++)
		{
			temp[m][j] = temp[m+1][j];
		}
		temp[i-1][l] = third;
		return 0;
	}
	private static int left(int[][] temp, int i, int j, int k, int l, int second) {
		int tempnum = temp[k][l];
		for(int m = l; m < j-1 ; m++)
		{
			temp[i][m] = temp[i][m+1];
		}
		temp[i][j-1] = second;
		return tempnum;
	}
	private static int down(int[][] temp, int i, int j, int k, int l, int first) {
		int tempnum = temp[k][l];
		for(int m = k; m > i+1; m --)
		{
			temp[m][j] = temp[m-1][j];
		}
		temp[i+1][j] = first;
		return tempnum;
		
	}
	private static int right(int[][] temp, int i, int j, int k, int l) {
		int tempnum = temp[k][l];
		for(int m = l; m > j ; m--)
		{
			temp[i][m] = temp[i][m-1];
		}
		return tempnum;
	}



	public static class Info
	{
		int i;
		int j;
		int s;
		Info(int i, int j, int s)
		{
			this.i = i;
			this.j = j;
			this.s = s;
		}
	}
}
