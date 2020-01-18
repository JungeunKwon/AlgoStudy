import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_1022_BJ {
	public static int dx [] = {1,0,-1,0};
	public static int dy [] = {0,-1,0,1};
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int starti = Integer.parseInt(st.nextToken());
		int startj = Integer.parseInt(st.nextToken());
		int endi = Integer.parseInt(st.nextToken());
		int endj = Integer.parseInt(st.nextToken());
		int sizei = endi - starti + 1 ;
		int sizej = endj - startj + 1;
		int count = sizei * sizej;
		int arr[][] = new int [sizei][sizej];
		//int max = (int)Math.pow(Math.max(sizei, sizej),2);
		int i = 1; 
		int tx = 0, ty = 0;
		int togle  = 0;
		int imax = 0;
		int jump = 1, dir = 0;
		int gapi = - starti;
		int gapj = - startj;
		
		//System.out.println(gapi  + " " + gapj);
		
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
				
				if(tx >= startj && tx <= endj && ty <=endi && ty>=starti)
				{
					int tempi = ty + gapi;
					int tempj = tx + gapj;
					arr[tempi][tempj] = i;
					if(imax < i )
					{
						imax = i;
					}
					count --;
					if(count ==0) break aa;
				}
				tx = tx + dx[dir];
				ty = ty + dy[dir];
				
			}
			
			togle ++;
			dir ++;
			
		}
		int max = String.valueOf(imax).length();

		for(int j = 0; j < sizei ; j ++)
		{
			for(int k = 0; k < sizej; k ++) 
			{
				System.out.format("%" + max + "d ", arr[j][k]);
			}
			System.out.println();
		}
		
//		for(int j = 0; j < sizei ; j ++)
//		{
//			for(int k = 0; k < sizej; k ++)
//			{
//				String temp = Integer.toString(arr[j][k]);
//				if(temp.length() < max.length())
//				{
//					for(int l = 0; l < max.length() - temp.length(); l++)
//					{
//						System.out.print(" ");
//					}
//				}
//				System.out.print(arr[j][k] + " ");
//			}System.out.println();
//		}
	}


}