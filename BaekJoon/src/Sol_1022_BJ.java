import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_1022_BJ {
	public static int dx [] = {-1,0,1,0};
	public static int dy [] = {0,1,0,-1};
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int starti = Integer.parseInt(st.nextToken());
		int startj = Integer.parseInt(st.nextToken());
		int endi = Integer.parseInt(st.nextToken());
		int endj = Integer.parseInt(st.nextToken());
		int sizei = Math.abs(starti) + Math.abs(endi) + 1 ;
		int sizej = Math.abs(startj) + Math.abs(endj) + 1;
		int arr[][] = new int [sizei][sizej];
		//int max = (int)Math.pow(Math.max(sizei, sizej),2);
		int i = 2; 
		int tx = 3, ty = 3;
		int togle  = 0;
		int jump = 0, dir = 0;

		arr[3][3] = 1;
		aa : while(true)
		{
			if(togle == 2)
			{
				togle = 0;
				jump ++;
			}
			if(dir == 4)
			{
				dir = 0;
			}
			for(int j = 0; j < jump; j ++, i++)
			{
				tx = tx + dx[dir];
				ty = ty + dy[dir];
				if(tx == sizej && ty == sizei) break aa;
				if(tx < 0 || ty < 0 || tx >= sizej || ty >=sizei)
				{
					continue;
				}
				arr[ty][tx] = i;
			}
			
			togle ++;
			dir ++;
			
		}
		String max = Integer.toString(i);
		for(int j = 0; j < sizei ; j ++)
		{
			for(int k = 0; k < sizej; k ++)
			{
				String temp = Integer.toString(arr[j][k]);
				if(temp.length() < max.length())
				{
					for(int l = 0; l < max.length() - temp.length(); l++)
					{
						System.out.print(" ");
					}
				}
				System.out.print(arr[j][k] + " ");
			}System.out.println();
		}
	}


}