import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sol_1759_BJ {
	public static boolean flag[];
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());	
		int L = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[] arr = new char[C];
		flag = new boolean[C];
		String line = br.readLine().replace(" ", "");
		for(int i= 0; i <  C ; i ++)
		{
			arr[i] = line.charAt(i);
		}
	

		Arrays.sort(arr);
		
		char[] newchar = new char[L];
		
		Combi2(newchar,arr,0, L, C);
	}

	private static void Combi2(char[] newchar, char[] arr, int idx, int l, int c) {
		if(idx == l)
		{
			String line = "";
			boolean flag = false;
			int count = 0;
			for(int i = 0; i < l ; i ++)
			{
				line += newchar[i];
				if(newchar[i] == 'a' ||newchar[i] == 'e'|| 
						newchar[i] == 'i' || newchar[i] == 'o' ||newchar[i] == 'u')
				{
					flag = true;
					count ++;
				}
			}
			if(!flag) return;
			if(l - count < 2) return;
			System.out.println(line);
			return;
		}
		for(int i = 0; i < c; i ++) {
			if(flag[i]) continue;
			
			if(idx!=0)
			{
				if(newchar[idx-1] > arr[i]) continue;
			}
			flag[i] = true;

			newchar[idx] = arr[i];
			Combi2(newchar, arr, idx+1, l,c);
			flag[i] = false;
		}
		
	}

	private static void Combi(char[] newchar, char[] arr, int r, int n) {
		if(r <= 0)
		{
			System.out.println(Arrays.toString(newchar));
			return;
		}
		if(n < r) return;
		newchar[r-1] = arr[n-1];
		Combi(newchar, arr, r-1, n-1);
		Combi(newchar, arr, r, n-1);
		
	}

}
