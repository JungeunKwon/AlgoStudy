import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_2411_BJ {
	public static int N;
	public static int M;
	public static int A;
	public static int B;
	public static int dx[] = {0,1};
	public static int dy[] = {1,0};
	public static int arr[][];
	public static int dp[];
	public static int innercount=0;
	public static ArrayList<Info> items;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		arr = new int[N+1][M+1];
		dp = new int[A+2];
		items = new ArrayList<>();
		items.add(new Info(1, 1));
		for(int i = 1; i <= A; i ++)
		{
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			items.add(new Info(s, b));
		}
		items.add(new Info(N,M));
		Collections.sort(items, new Comparator<Info>() {

			@Override
			public int compare(Info o1, Info o2) {
				if(o1.i == o2.i)
				{
					return o1.j - o2.j;
				}
				return o1.i - o2.i;
			}
		});
		//Arrays.sort(items);
		for(int i = 0; i < B; i ++)
		{
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[s][b] = -1;
		}
		dp[0] = 1;
		find();
		/*
		 * for(int i = 0; i <= A; i ++) {
		 * 
		 * System.out.print(items[i] + " + " + dp[i] + " "); } System.out.println();
		 */
		System.out.println(dp[A+1]);
	}

	private static void find() {
		for(int i = 1; i <= A+1; i ++)
		{
			int t  = find2(items.get(i-1).i, items.get(i-1).j,items.get(i));
			dp[i] =  t * dp[i-1];
		}
		
	}
	private static int find2(int i , int j, Info next) {
		Queue<Info> q = new LinkedList<>();
		q.offer(new Info(i, j));
		int nextcount = 0;
		while(!q.isEmpty())
		{
			Info temp = q.poll();
			
			int tx = 0, ty =0;
			if(temp.i == next.i && temp.j == next.j)
			{
				nextcount ++;
				continue;
			}
			if(temp.i > next.i || temp.j > next.j)
			{
				continue;
			}
			for(int k = 0; k < 2; k ++)
			{
				tx = temp.j + dx[k];
				ty = temp.i + dy[k];
				if(tx <=0 || ty <= 0 || tx> M || ty > N) continue;
				if(arr[ty][tx] != -1)
				{
					q.offer(new Info(ty, tx));
				}
			}
		}
		return nextcount;
	}
	public static class Info{
		int i;
		int j;
		Info(int i, int j)
		{
			this.i  = i ;
			this.j = j;
		}
			
	}

}
