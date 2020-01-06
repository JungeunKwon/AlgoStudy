import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_17837_BJ {
	public static int N;
	public static int K;
	public static int arr[][];
	public static int dx[] = { 0, 1, -1, 0, 0 };
	public static int dy[] = { 0, 0, 0, -1, 1 };
	public static List<Info> map[][];
	public static Info[] order;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N+1][N+1];
		map = new ArrayList[N+1][N+1];
		order = new Info[K+1];
		for(int i = 1 ; i <= N ; i ++)
		{
			st = new StringTokenizer(br.readLine());
			
			for(int j = 1; j <= N ; j ++)
			{
				map[i][j] = new ArrayList<>();
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0, j =1; i < K; i ++, j++)
		{
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			Info temp = new Info(x, y, d, j);
			order[j] = temp;
			map[x][y].add(temp);
		}
		int count = 1;
	
		aa: while(count <1000) {
			for(int i = 1; i <= K; i++)
			{
				Info temp = order[i];
				int d = temp.dir;
				int tx = temp.j + dx[d];
				int ty = temp.i + dy[d];
			
				if(ty <= 0 ||tx <=0 ||ty>N ||tx >N || arr[ty][tx] == 2)
				{
					d = getopposite(d);
				}
				tx = temp.j + dx[d];
				ty = temp.i + dy[d];
				if(ty > 0 && tx > 0 && tx <=N && ty <=N && arr[ty][tx] != 2)
				 {
					Iterator<Info> it = map[temp.i][temp.j].iterator();
					List<Info> newtemp = new ArrayList<>();
					while(it.hasNext())
					{
						newtemp.add(it.next().clone());
					}
					map[temp.i][temp.j].clear();
					if(arr[ty][tx] == 0)
					{
						boolean flag = false;
						for(int j = 0; j < newtemp.size(); j ++)
						{
							Info innertemp = newtemp.get(j);
							if(innertemp.num == temp.num)
							{
								flag = true;
								order[innertemp.num] = new Info(ty, tx, d, innertemp.num);
								map[ty][tx].add(new Info(ty, tx, d, innertemp.num));
								continue;
							}
							if(flag)
							{
								order[innertemp.num] = new Info(ty, tx, innertemp.dir, innertemp.num);
								map[ty][tx].add(new Info(ty, tx, innertemp.dir, innertemp.num));
							}else
							{
								map[temp.i][temp.j].add(innertemp);
							}
						}
		
					}else if(arr[ty][tx] == 1)
					{
						boolean flag = false;
						List<Info> reorder= new ArrayList<>();
	
						for(int j = 0; j < newtemp.size(); j ++)
						{
							Info innertemp = newtemp.get(j);
							if(innertemp.num == temp.num)
							{
								flag = true;
								reorder.add(new Info(ty, tx, d, innertemp.num));
								continue;
							}
							if(flag)
							{
								
								reorder.add(new Info(ty, tx, innertemp.dir, innertemp.num));
							}else
							{
								map[temp.i][temp.j].add(innertemp);
							}
						}
		
						for(int j = reorder.size() -1 ; j >= 0 ; j --)
						{
							Info tempj = reorder.get(j);
							order[tempj.num] = new Info(ty, tx,tempj.dir, tempj.num);
							map[ty][tx].add( new Info(ty, tx,tempj.dir, tempj.num));
						}
					}
					
					if(map[ty][tx].size() >= 4) break aa;
				}else
				{
					Iterator<Info> it = map[temp.i][temp.j].iterator();
					List<Info> newtemp = new ArrayList<>();
					while(it.hasNext())
					{
						newtemp.add(it.next().clone());
					}
					map[temp.i][temp.j].clear();
					for(int j = 0; j < newtemp.size(); j ++)
					{
						Info innertemp = newtemp.get(j);
						if(innertemp.num == temp.num)
						{
							map[temp.i][temp.j].add(new Info(temp.i, temp.j, d, temp.num));
						}
						else
						{
							map[temp.i][temp.j].add(innertemp);
						}
					}
					
				}
			}
			
		
			count++;
		}
		if(count >= 1000)
		{
			System.out.println(-1);
		}else
		{
			System.out.println(count);
		}
		
	}

	private static int getopposite(int d) {
		if (d == 1) {
			return 2;
		} else if (d == 2) {
			return 1;

		} else if (d == 3) {
			return 4;
		} else {
			return 3;
		}
	}

	public static class Info implements Cloneable {
		int i;
		int j;
		int dir;
		int num;

		Info(int i, int j, int dir, int num) {
			this.i = i;
			this.j = j;
			this.dir = dir;
			this.num = num;
		}
		@Override
		public Info clone() {
			Info clone = null;
			try {
				clone = (Info)super.clone();
			}catch(CloneNotSupportedException e) {
				throw new RuntimeException(e);
			}
			return clone;
		}

	}
}
