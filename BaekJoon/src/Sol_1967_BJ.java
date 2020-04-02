import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_1967_BJ {

	public static void main(String[] args)throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		Queue<Info> q[] = new LinkedList[N]; 
		int dp[] = new int[N+1];
		for(int i  = 0; i  < N - 1; i ++)
		{
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			if(q[num] == null)
			{
				q[num] = new LinkedList<>();
			}
			q[num].offer(new Info(child, weight));
		}
		for(int i = 0; i < N ; i ++)	
		{
			System.out.print(dp[i] + " ");
		}
		System.out.println();
		int max = 0;
		for(int i = N-1; i > 0; i--)
		{
			while(q[i] != null && !q[i].isEmpty())
			{
				int size = q[i].size();
				int inmax = 0;
				int inmaxse = 0;
				for(int j = 0; j < size; j ++)
				{
					Info temp = q[i].poll();
					int cal = temp.weight + dp[temp.num];
					if(inmax < cal)
					{
						inmax = cal;
					}else
					{
						if(inmaxse < cal)
						{
							inmaxse = cal;
						}
					}
					
				}
				dp[i] = inmax;
				if(max < inmaxse + inmax)
				{
					max = inmaxse+inmax;
				}
			}
		
			
		}
		
		for(int i = 0; i < N ; i ++)	
		{
			System.out.print(dp[i] + " ");
		}
		System.out.println(max);
	}
	public static class Node
	{
		int num;
		Node(int num)
		{
			this.num = num;
		}
	}
	public static class Info
	{
		int num;
		int weight;
		Info(int num, int weight)
		{
			this.num = num;
			this.weight = weight;
		}
	}
}
