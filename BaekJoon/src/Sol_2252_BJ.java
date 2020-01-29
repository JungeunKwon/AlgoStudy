import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_2252_BJ {
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
			int fi = Integer.parseInt(st.nextToken());
			int se = Integer.parseInt(st.nextToken());
			conn[fi].add(se);
			indegree[se] ++;
		}
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
					if(indegree[conn[t].get(j)] <= 0 && !visited[i])
					{
						q.offer(i);
					}
				}
				visited[t] = true;
				System.out.print(t + " ");
			}
			
		}
	}

}
