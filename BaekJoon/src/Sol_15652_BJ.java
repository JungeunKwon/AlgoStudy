import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Sol_15652_BJ {
	public static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		boolean flag[] = new boolean[N];
		int arr[] = new int[M];
		Combi(N, M, 0, flag, arr);
		System.out.println(sb.toString());
	}

	private static void Combi(int n, int m, int idx, boolean[] flag, int[] arr) {
		if(idx >= m )
		{
			for(int i = 0; i  < m; i ++)
			{
				sb.append(arr[i]+1 + " ");
			}
			sb.append("\n");
			return;
		}
		for(int j = 0; j < n ;j ++)
		{
			
			arr[idx] = j;
			if(idx>0)
			{
				if(arr[idx - 1] > arr[idx])continue; 
			}
			Combi(n, m, idx + 1, flag, arr);

		}
	}


	

}
