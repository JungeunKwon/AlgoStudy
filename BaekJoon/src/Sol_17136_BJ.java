import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Sol_17136_BJ {
	public static int arr[][];
	public static int paper[] = {1,2,3,5};
	public static boolean visited[][];
	public static int N;
	public static int min;
	static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visited = new boolean[N][N];
		min = Integer.MAX_VALUE;
		dfs(0,0,0,0);
	}
	private static void dfs(int posi, int posj, int papercount, int num) {
		if(papercount > min) return;
		
		for(int i = posi ; i <N ; i ++)
		{
			for(int j = 0; j < N; j ++)
			{
				
			}
		}
	}

}
