import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sol_2668_BJ {
	public static int N;
	public static int max;
	public static List<Integer> total = new ArrayList<>();
	public static boolean visited[];
	public static boolean flag;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int arr[][] = new int[N+1][2];
		flag = false;
		visited = new boolean[N+1];
		ArrayList<Integer> origin = new ArrayList<>();
		ArrayList<int[]> list = new ArrayList<>();
		max = 0;
		for(int i = 1; i <=N ; i ++)
		{
			arr[i][0] = i;
			arr[i][1] = Integer.parseInt(br.readLine());
			if(arr[i][0] == arr[i][1])
			{
				origin.add(arr[i][0]);
				visited[i] = true;
			}
		}
		
		for(int i = 1 ; i <= N ; i ++)
		{
			if(visited[i])continue;
			flag = false;
			list = new ArrayList<>();
			list.add(arr[i]);
			dfs(arr, list, arr[i][1], 0, arr[i][0]);
			if(flag) {
				for(int j = 0; j < list.size(); j ++)
				{
					origin.add(list.get(j)[0]);
					visited[list.get(j)[0]] = true;
				}
			}
		}
		Collections.sort(origin);
		System.out.println(origin.size());
		for(int i = 0;  i < origin.size(); i ++)
		{
			System.out.println(origin.get(i));
		}
	}
	private static void dfs(int[][] arr, ArrayList<int[]> list, int pos, int idx, int originnum) {
		 if(idx >= N)
		 {
			 if(arr[pos][1] == originnum) {
				 list.add(arr[pos]);
				 flag =true;
			 } 
			 return;
			 
		 }
		 if(visited[pos]) return;
		 if(arr[pos][1] == originnum) {
			 list.add(arr[pos]);
			 flag = true;
		 }else
		 {
			 list.add(arr[pos]);
			 dfs(arr, list, arr[pos][1], idx+1, originnum);
		 }
		 return;
	}

}
