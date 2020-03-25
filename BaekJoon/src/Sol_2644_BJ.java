import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_2644_BJ {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int man1 = Integer.parseInt(st.nextToken());
		int man2 = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(br.readLine());
		int [] visited = new int[N+1];
		int [][] map = new int[N+1][N+1];
		for(int i = 0; i < m; i ++)
		{
			st = new StringTokenizer(br.readLine(), " ");
			int p= Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[p][c] = map[c][p] = 1;
		}
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(man1);
		while(!q.isEmpty())
		{
			int v  = q.poll();
			for(int i = 0 ; i < map.length; i ++)
			{
				if(map[v][i] == 1 && visited[i] == 0)
				{
					q.offer(i);
					visited[i] = visited[v] + 1;
				}
			}
		}
		System.out.println(visited[man2] == 0 ? -1 : visited[man2]);

	}

}
