import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_1260_BJ {
	public static int N;
	public static int M;
	public static int arr[][];
	public static boolean visited[];
	public static Queue<Info> q;
	public static int dx[] = {1,0,-1,0};
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		 N = Integer.parseInt(st.nextToken());
		 M = Integer.parseInt(st.nextToken());
		 q = new LinkedList<>();
		int V = Integer.parseInt(st.nextToken());
		arr = new int[N+1][N+1];
		visited = new boolean[N+1];
		for(int i = 0; i < M ; i ++)
		{
			st = new StringTokenizer(br.readLine());
			int fi = Integer.parseInt(st.nextToken());
			int se = Integer.parseInt(st.nextToken());
			arr[fi][se] = 1;
			arr[se][fi] = 1;
		}
		visited[V] = true;
		System.out.print(V + " ");
		dfs(V);
		visited = new boolean[N+1];
		visited[V] = true;
		System.out.println();
		System.out.print(V + " ");
		for(int i =1; i <= N; i ++)
		{
			if(i != V && arr[V][i] == 1)
			{
				q.offer(new Info(i));
				visited[i] = true;
				System.out.print(i + " ");
			}
		}
		bfs();

	}
	private static void bfs() {
		while(!q.isEmpty())
		{
			Info temp = q.poll();
			for(int i = 1 ; i <= N ;  i++)
			{
				if(temp.i != i && arr[temp.i][i] == 1)
				{
					if(visited[i])continue;
					visited[i] = true;
					q.offer(new Info(i));
					System.out.print(i+ " ");
				}
			}
		}
		
	}
	private static void dfs(int v) {
		
		for(int i = 1 ; i <= N ; i ++)
		{
			if(i != v && arr[v][i] == 1)
			{
				if(visited[i])continue;
				visited[i]= true;
				System.out.print(i + " ");
				dfs(i);
				
			}
		}
		
	}
	public static class Info{
		int i;
		Info(int i )
		{
			this.i = i;
			
		}
	}
}
