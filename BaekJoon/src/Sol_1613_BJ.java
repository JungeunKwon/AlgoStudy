import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sol_1613_BJ {
	public static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int arr[][] = new int[N+1][N+1];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int fi = Integer.parseInt(st.nextToken());
			int se = Integer.parseInt(st.nextToken());
			
			arr[fi][se] = 1; // 1이면 나보다 앞에 있는거
		}
		int S = Integer.parseInt(br.readLine());
		  
	    for(int k = 1; k <=N ; k ++)
        {
            for(int i = 1; i <= N ; i ++)
            {
                if(arr[i][k] == 1)
                {
                    for(int j = 1; j <= N ;j ++)
                    {
                        if(arr[k][j] == 1 &&arr[i][j] == 0)
                        {
                            arr[i][j] = 1;
                        }
                    }
                }
            }
        }
		for (int i = 0; i < S; i++) {
			st = new StringTokenizer(br.readLine());
			int fi = Integer.parseInt(st.nextToken());
			int se = Integer.parseInt(st.nextToken());
			if(arr[fi][se] == 1)
			{
				System.out.println(-1);

			}else if(arr[se][fi] == 1)
			{
				System.out.println(1);

			}else
			{
				System.out.println(0);
			}

		}
	}


}
