import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.BufferedReader;

public class Sol_13460_BJ {
	public static int N;
	public static int M;
	public static char arr[][];
	public static int move[];
	public static int dx[] = {-1,1,0,0};
	public static int dy[] = {0,0,-1,1};
	public static int ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		move = new int[10];
		ans = -1;
		for(int i = 0; i < N ; i ++)
		{
			String line = br.readLine();
			for(int j = 0; j < M ; j ++)
			{
				arr[i][j] = line.charAt(j);
			}
		}
		findmove(0);
		System.out.println(ans);
	}
	private static void findmove(int index) {
		if(index == 10)
		{
			turn();
			return;
		}
		for(int i= 0; i < 4; i ++)
		{
			if(index> 0)
			{
				if(move[index - 1] == i || move[index-1] == opposite(i)) continue;
			}
			move[index] = i;
			findmove(index + 1);
		}
	}
	private static void turn() {
		char[][] newmap = new char[N][M];
		Position red = null;
		Position blue = null;
		for(int i = 0; i < N ; i++)
		{
			newmap[i] = Arrays.copyOf(arr[i], M);
			for(int j = 0; j <M ; j ++)
			{
				if(newmap[i][j] == 'R') {
					red = new Position(i, j);
				}else if(newmap[i][j] == 'B') {
					blue = new Position(i, j);
				}
			}
		}
		for(int i = 0; i < 10; i++)
		{
			int dir = move[i];
			while(true)
			{
				Result redresult = move(newmap, red, dir);
				Result bluereulst = move(newmap,blue, dir);
				if(bluereulst.hole)
				{
					return;
				}
				if(redresult.hole)
				{
					if(ans==-1 || ans > i+1)
					{
						ans = i+1;
					}
					return;
				}
				if(redresult.moved == false && bluereulst.moved == false)
				{
					break;
				}
			}
		}
	}
	private static Result move(char[][] map, Position p, int dir) {
		if(map[p.i][p.j] == '.') {
			return new Result(false, false);
		}
		boolean moved = false;
		while(true)
		{
			int tx = p.j + dx[dir];
			int ty = p.i + dy[dir];
			if(map[ty][tx] == '.') {
				moved =true;
				char temp = map[p.i][p.j];
				map[p.i][p.j] = map[ty][tx];
				map[ty][tx] = temp;
			}else if(map[ty][tx] == 'O') {
				map[p.i][p.j] = '.';
				return new Result(true, moved);
			}else {
				return new Result(false, moved);
			}
			p.j = tx;
			p.i = ty;
		}
	}
	public static int opposite(int num)
	{
		if(num == 0)
		{
			return 1;
		}else if(num == 1)
		{
			return 0;
		}else if(num == 2)
		{
			return 3;
			
		}else 
			return 2;
	}
	public static class Position
	{
		int i;
		int j;
		public Position(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	public static class Result
	{
		boolean hole;
		boolean moved;
		Result(boolean hole, boolean moved)
		{
			this.hole = hole;
			this.moved = moved;
		}
	}
}
