import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import java.util.List;

import java.util.StringTokenizer;

public class Sol_1948_BJ {
	public static int N;
	public static int arr[][];
	public static int indegree[];
	public static int maxcount;
	public static int maxtime;
	public static List<Integer> conn[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		StringTokenizer st;
		arr = new int[N+1][N+1];
		conn = new List[N+1];
		for(int i = 0; i <= N ; i ++)
		{
			conn[i] = new ArrayList<Integer>();
		}
		for(int i = 0 ; i < M ;  i ++ )
		{
			st = new StringTokenizer(br.readLine());
			int fi = Integer.parseInt(st.nextToken());
			int se = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			arr[fi][se] = weight;
			conn[fi].add(se);
		}
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		maxtime = 0;
		maxcount = 0;
		find(start, end, 1, 0);
		System.out.println(maxtime);
		System.out.println(maxcount);
	}
	private static void find(int start, int end, int count, int time) {
		if(start == end)
		{
			if(time > maxtime) maxtime= time;
			if(count > maxcount) maxcount = count;
			return;
		}
		for(int i = 0; i < conn[start].size(); i++)
		{
			int t = conn[start].get(i);
			find( t, end, count+1, time + arr[start][t] );
		}
		
	}

}
