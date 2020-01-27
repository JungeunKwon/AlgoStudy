import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static int N;
	public static int arr[][];
	public static int move[];
	public static int dx[] = {-1,0,1,0};
	public static int dy[] = {0,-1,0,1};
	public static int max;
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr  = new int[N][N];
		move = new int[5];
		StringTokenizer st;
		for(int i = 0; i < N; i ++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j ++)
			{
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		max = 0;
		findmove(0);
		System.out.println(max);
	}
	private static void findmove(int idx) {
		if(idx >= 5)
		{
			int temp[][] = new int[N][N];
			for(int i = 0; i < N; i ++)
			{
				temp[i] = Arrays.copyOf(arr[i], N);
			}
			play(temp, move);
		
			return;
		}
		for(int i = 0; i < 4; i ++)
		{
			if(idx > 0)
			{
				if(move[idx-1] == i) continue;
			}
			move[idx]=i;
			findmove(idx+1);
		}
		
	}
	private static void play(int[][] temp, int[] move2) {
		for(int i = 0; i < 5; i ++)
		{
			if(move2[i] == 0)
			{
				left(temp);
			}else if(move2[i] == 1)
			{
				up(temp);
			}else if(move2[i] == 2)
			{
				right(temp);
			}else if(move2[i] == 3)
			{
				down(temp);
			}
		}
		

	}
	private static void down(int[][] temp) {
		for(int i =0; i <N; i ++)
		{
			int pos = N-1;
			for(int j = pos-1; j >=0 ; j--)
			{
				if(temp[j][i] == 0) continue;
				else {
					if(temp[pos][i] == temp[j][i])
					{
						temp[pos][i] = temp[pos][i] * 2;
						if(temp[pos][i] > max) max = temp[pos][i] ;
						temp[j][i] = 0;
						pos--;
					}else if(temp[pos][i] ==0)
					{
						temp[pos][i] = temp[j][i];
						temp[j][i] = 0;
						pos--;
					}else
					{
						boolean flag = false;
						for(int k = pos; k >= j; k--)
						{
							if(temp[k][i] == 0)
							{
								temp[k][i] = temp[j][i];
								temp[j][i] = 0;
								pos = k;
								flag = true;
								break;
							}
						}
						if(!flag) pos = j;
					}
						
				}
			}
		}
	}
	private static void right(int[][] temp) {
		for(int i =0; i <N; i ++)
		{
			int pos = N-1;
			for(int j = pos-1; j >=0 ; j--)
			{
				if(temp[i][j] == 0) continue;
				else {
					if(temp[i][pos] == temp[i][j])
					{
						temp[i][pos] = temp[i][pos] * 2;
						if(temp[i][pos] > max) max = temp[i][pos] ;
						temp[i][j] = 0;
						pos--;
					}else if(temp[i][pos] ==0)
					{
						temp[i][pos] = temp[i][j];
						temp[i][j] = 0;
						pos--;
					}else
					{
						boolean flag = false;
						for(int k = pos; k >= j; k--)
						{
							if(temp[i][k] == 0)
							{
								temp[i][k] = temp[i][j];
								temp[i][j] = 0;
								pos = k;
								flag = true;
								break;
							}
						}
						if(!flag) pos = j;
					}
						
				}
			}
			
		}
	}
	private static void up(int[][] temp) {

		for(int i =0; i < N ; i ++)
		{
			int pos = 0;
			for(int j = pos+1; j < N ; j ++)
			{
				if(temp[j][i] == 0) continue;
				else {
					if(temp[pos][i] == temp[j][i])
					{
						temp[pos][i] = temp[pos][i] * 2;
						if(temp[pos][i] > max) max = temp[pos][i];
						temp[j][i] = 0;
						pos++;
					}else if(temp[pos][i] ==0)
					{
						temp[pos][i] = temp[j][i];
						temp[j][i] = 0;
						pos++;
					}else
					{
						boolean flag = false;
						for(int k = pos; k < j; k++)
						{
							if(temp[k][i] == 0)
							{
								temp[k][i] = temp[j][i];
								temp[j][i] = 0;
								pos = k;
								flag = true;
								break;
							}
						}
						if(!flag) pos = j;
					}
						
				}
			}
			
		}
	}
	private static void left(int[][] temp) {
		
		for(int i =0; i < N ; i ++)
		{
			int pos = 0;
			for(int j = pos+1; j < N ; j ++)
			{
				if(temp[i][j] == 0) continue;
				else {
					if(temp[i][pos] == temp[i][j])
					{
						temp[i][pos] = temp[i][pos] * 2;
						if(temp[i][pos] > max) max = temp[i][pos] ;
						temp[i][j] = 0;
						pos++;
					}else if(temp[i][pos] ==0)
					{
						temp[i][pos] = temp[i][j];
						temp[i][j] = 0;
						pos++;
					}else
					{
						boolean flag = false;
						for(int k = pos; k < j; k++)
						{
							if(temp[i][k] == 0)
							{
								temp[i][k] = temp[i][j];
								temp[i][j] = 0;
								pos = k;
								flag = true;
								break;
							}
						}
						if(!flag) pos = j;
					}
						
				}
			}
			
		}
		
	}
	public static int opposite(int pos)
	{
		if(pos == 0)
		{
			return 2;
		}else if(pos == 1)
		{
			return 3;
		}else if(pos == 2)
		{
			return 0;
		}else {
			return 1;
		}
	}
}
