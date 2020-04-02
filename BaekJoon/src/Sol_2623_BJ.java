import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_2623_BJ {
	public static int N;
	public static int M;
	public static int indegree[];
	public static boolean visited[];
	public static Queue<Integer>q;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		StringBuilder sb=  new StringBuilder();
		indegree = new int[N+1];
		visited = new boolean[N+1];
		q = new LinkedList<Integer>();
		List<Integer> conn[] = new List[N+1];
		for(int i = 0; i <= N ; i ++)
		{
			conn[i] = new ArrayList<Integer>();
		}
		for(int i = 0; i < M ; i ++)
		{
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int fi =0 , se = 0;
			fi = Integer.parseInt(st.nextToken());
			for(int j = 0; j < num - 1 ; j++)
			{
				se = Integer.parseInt(st.nextToken());
				conn[fi].add(se);
				indegree[se] ++;
				fi = se;
			}
		
		}
		int count = 0;
		for(int i = 1; i <= N ; i ++)
		{
			if(indegree[i] == 0)
			{
				
				q.offer(i);
			}
		}
		while(!q.isEmpty())
		{
			int size = q.size();
			for(int i = 0; i < size; i ++)
			{
				int t = q.poll();
				int innersize = conn[t].size();
				for(int j = 0; j <innersize; j++)
				{
					indegree[conn[t].get(j)] --;
					if(indegree[conn[t].get(j)] <= 0)
					{
						q.offer(conn[t].get(j));
					}
				}
				visited[t] = true;
				count++;
				sb.append(t+"\n");
			}
		}
		if(count!=N)
		{
			System.out.println(0);
		}else
		{
			System.out.println(sb.toString());
		}
	}

}
