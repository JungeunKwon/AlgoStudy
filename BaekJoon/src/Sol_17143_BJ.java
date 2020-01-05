import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Sol_17143_BJ {
	public static int R;
	public static int C;
	public static int M;
	public static int dx[] = {0,0,0,1,-1};
	public static int dy[] = {0,-1,1,0,0};
	public static List<Shark> sharklist;
	public static void main(String[] args) throws Exception{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sharklist = new ArrayList<>();
		for(int i = 0 ;i < M ; i ++)
		{
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			sharklist.add(new Shark(r, c, s, d, z));
		}
		int size =0;
		for(int i = 1; i <= C; i ++)
		{
			//System.out.println("****************************");
			Collections.sort(sharklist, new Comparator<Shark>() {

				@Override
				public int compare(Shark o1, Shark o2) {
					if(o1.j == o2.j)
					{
						return o1.i - o2.i;
					}
					else
					{
						return o1.j - o2.j;
					}
				}
			});
			aa : for(int j  = 0; j < sharklist.size(); j ++)
			{
				Shark temp = sharklist.get(j);
				if(temp.j == i)
				{
					sharklist.remove(j);
					size += temp.z;
					
					break aa;
				}
			}
			
			
			sharkmove();
			
		}
		System.out.println(size);
	}
	private static void sharkmove() {
		int sharkarr[][] = new int[R+1][C+1];
		for(int i  = 0; i < sharklist.size(); i++)
		{
			Shark temp = sharklist.get(i);
			int tx = temp.j , ty = temp.i, d = temp.d;
			for(int j = 0; j < temp.s; j ++)
			{
				tx = tx + dx[d];
				ty = ty + dy[d];
				if(tx <= 0 || ty <= 0 || tx >C || ty >R)
				{
					tx = tx - dx[d];
					ty = ty - dy[d];
					if(d == 1)
					{
						d = 2;
					}else if(d ==2)
					{
						d = 1;
					}else if(d==3)
					{
						d = 4;
					}else if(d==4)
					{
						d =3;
					}
					j--;
				}
			}
			if(sharkarr[ty][tx] > temp.z)
			{
				sharklist.remove(i);
				
				i--;
			}else
			{
				sharklist.get(i).d = d;
				sharklist.get(i).i = ty;
				sharklist.get(i).j = tx;
				if(sharkarr[ty][tx] != 0)
				{
					for(int j = 0; j < sharklist.size(); j++)
					{
						Shark innertemp = sharklist.get(j);
						if(innertemp.i == ty && innertemp.j == tx && innertemp.z == sharkarr[ty][tx])
						{
							sharklist.remove(j);
							i--;
						}
					}
				}
				sharkarr[ty][tx] = temp.z;
			}
			
		}
		
	}
	public static class Shark{
		int i;
		int j;
		int s;
		int d;
		int z;
		Shark(int i, int j, int s, int d, int z)
		{
			this.i = i;
			this.j = j;
			this.s = s;
			this.d = d;
			this.z = z;
			
		}
		@Override
		public String toString() {
			return "Shark [i=" + i + ", j=" + j + ", s=" + s + ", d=" + d + ", z=" + z + "]";
		}
		
	}
}
