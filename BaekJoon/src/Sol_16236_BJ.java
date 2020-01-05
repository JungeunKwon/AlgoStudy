import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_16236_BJ {
	public static int N;
	public static Queue<Info> q;
	public static ArrayList<nextfish> fishlist;
	public static boolean[][]visited;
	public static int dx[] = {0,0,-1,1};
	public static int dy[] = {-1,1,0,0};
	public static int arr[][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		StringTokenizer st;
		Info babyshark = null;
		q = new LinkedList<>();
		fishlist = new ArrayList<>();
		for(int i = 0; i < N ; i ++)
		{
			st  = new StringTokenizer(br.readLine());
			for(int j = 0; j < N ; j ++)
			{
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 9)
				{
					babyshark = new Info(i, j, 2, 0);
					arr[i][j] = 0;
				}
			}
		}
		int time = 0;
		while(true) {
			visited = new boolean[N][N];
			q.offer(babyshark);
			findfish(babyshark.size);
			if(fishlist.isEmpty()) break;
			Collections.sort(fishlist, new Comparator<nextfish>() {
				@Override
				public int compare(nextfish o1, nextfish o2) {
					if(o1.dist == o2.dist)
					{
						if(o1.i == o2.i)
						{
							return o1.j - o2.j;
							
						}else
						{
							return o1.i - o2.i;
						}
					}else
					{
						return o1.dist - o2.dist;
					}
					
				}

			});
			babyshark.count ++;
			nextfish ne = fishlist.get(0);
			time += ne.dist;
			if(babyshark.count == babyshark.size)
			{
				babyshark.size ++;
				babyshark.count =0;
			}
			arr[ne.i][ne.j] = 0;
			babyshark = new Info(ne.i, ne.j, babyshark.size, babyshark.count);
			fishlist.clear();
		}
		System.out.println(time);
		
	}
	private static void findfish(int sharksize) {
		int tx = 0, ty = 0;
		int count =1;
		while(!q.isEmpty())
		{
			int size = q.size();
			for(int i = 0; i <size; i ++)
			{
				Info temp = q.poll();
				visited[temp.i][temp.j] = true;
				for(int j = 0; j < 4; j++)
				{
					tx = temp.j + dx[j];
					ty = temp.i + dy[j];
					if(tx<0 || ty<0 || tx>=N || ty>=N) continue;
					if(visited[ty][tx])continue;
					if(arr[ty][tx] > sharksize)continue;
					if(arr[ty][tx]!= 0 && arr[ty][tx] < sharksize)
					{
						fishlist.add(new nextfish(ty,tx, count));
					}
					q.offer(new Info(ty, tx, 0, 0));
					visited[ty][tx] = true;
				}
			}
			count++;
		}
	}
	public static class nextfish{
		int i;
		int j;
		int dist;
		nextfish(int i, int j, int dist){
			this.i = i;
			this.j = j;
			this.dist = dist;
		}
	}
	public static class Info{
		int i;
		int j;
		int size;
		int count;
		Info(int i, int j, int size, int count){
			this.i = i;
			this.j = j ;
			this.size = size;
			this.count = count;
		}
	}
}
