import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Sol_15654_BJ {
	public static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		boolean flag[] = new boolean[N];
		int arr[] = new int[N];
		int newarr[] = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N ; i ++)
		{
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		Combi(N, M, 0, flag, newarr, arr);
		System.out.println(sb.toString());
	}

	private static void Combi(int n, int m, int idx, boolean[] flag, int[] newarr,  int[] arr) {
		if(idx >= m )
		{
			for(int i = 0; i  < m; i ++)
			{
				sb.append(newarr[i] + " ");
			}
			sb.append("\n");
			return;
		}
		for(int j = 0; j < n ;j ++)
		{
			if(flag[j])continue;
			flag[j] = true;
			newarr[idx] = arr[j];
			Combi(n, m, idx + 1, flag, newarr, arr);
			flag[j] = false;
		}
	}


	

}
