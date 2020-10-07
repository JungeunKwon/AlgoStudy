import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Sol_2435_BJ {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int arr[] = new int[N];
		for(int i = 0; i < N ; i ++)
		{
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int max = Integer.MIN_VALUE;
	
		for(int i = 0; i <= N - K ; i ++)
		{
			int sum = 0;
			for(int j = i ; j < i + K ; j ++)
			{
				sum += arr[j];
			}
			
			
			if(max < sum)max = sum;
		}
		
		System.out.println(max);
	}

}
