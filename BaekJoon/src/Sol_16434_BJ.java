import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_16434_BJ {
	public static int N;
	public static long Hatk;
	public static long calhp;
	public static long potion;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Hatk = Integer.parseInt(st.nextToken());
		calhp = 1;
		potion = 0;
		for(int i  = 0; i < N ; i ++)
		{
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			if(t == 1)
			{
				while(h > 0)
				{
					h -= Hatk;
					if(h <=0)break;
					if(potion != 0)
					{
						if(potion - a >= 0)
						{
							potion -= a;
						}else
						{
							calhp += a;
							calhp -= potion;
							potion = 0;
						}
						
					}else
						calhp += a;
				
				}
				
			}else
			{
				if(potion+h >= calhp)
				{
					
					potion = calhp;
				}else
				{
					
					potion += h;
				}
				Hatk += a;
			}
		}
		System.out.println(calhp);

	}

}
