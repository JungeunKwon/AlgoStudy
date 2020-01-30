import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;

public class Sol_17281_BJ {
	public static boolean flag[];
	public static int order[];
	public static int play;
	public static int player[][];
	public static int max;
	public static int ground[];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		play = Integer.parseInt(br.readLine());
		player = new int[play][9];
		flag = new boolean[9];
		order = new int[9];
		order[3] = 0;
		flag[0] = true;
		max = 0;
		
		int pos = 0;
		for(int i = 0; i <play; i ++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j <9 ; j ++)
			{
				player[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		Perm(order,0);
		
		
		System.out.println(max);
			
		
	}

	private static void Perm(int[] order, int idx) {
		if(idx >= 9)
		{
			int pos = 0;
			int point = 0;
			for(int t = 0; t < play; t++)
			{	
				ground = new int[4]; 
				point += baseball(t,pos);
			}
			if(max < point) max = point;
			return;
		}
		for(int i = 1 ; i < 9 ; i ++)
		{
			if(flag[i])continue;
			
			if(idx != 3)
			{
				order[idx] = i;
				flag[i] = true;
			}
			Perm(order, idx+1);
			flag[i] = false;
		}
	}

	private static int baseball(int t, int pos) {
		int point = 0;
		int out =0;
		while(out < 3)
		{
			if(pos == 9)
			{
				pos = 0;
			}
			int i =0;
			switch(player[t][order[pos]])
			{
			case 0:
				out ++;
				break;
			case 1:
				i = 1;
				break;
			case 2:
				i = 2;
				break;
			case 3:
				i = 3;
				break;
			case 4:
				i = 4;
				break;
			}
			if(i != 0)
			{
				for(int j = 0; j < i ; j ++)
				{
					if(ground[3] != 0) point ++;
					for(int k = 3; k >0 ; k --)
					{
						ground[k] = ground[k-1];
					}
					if(j == 0) ground[0]++;
				}
			}
			pos ++;
		}
		return point;
	}

}
