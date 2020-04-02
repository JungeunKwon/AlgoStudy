import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Sol_12100_BJ {
	public static int N;
	public static int arr[][];
	public static int move[];
	public static int dx[] = {-1,0,1,0};
	public static int dy[] = {0,-1,0,1};
	public static int max;
	public static LinkedList<Integer> list ;
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr  = new int[N][N];
		move = new int[5];
		StringTokenizer st;
		 list = new LinkedList<>();
		 max= 0;
		for(int i = 0; i < N; i ++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j ++)
			{
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] > max) max = arr[i][j];
			}
		}
		
		findmove(0);
		System.out.println(max);
	}
	private static void findmove(int idx) {
		if(idx >= 5)
		{
			int temp[][] = new int[N][N];
			for(int i = 0; i < N; i ++)
			{
				temp[i] = Arrays.copyOf(arr[i], N);
			}
			play(temp, move);
		
			return;
		}
		for(int i = 0; i < 4; i ++)
		{
			if(idx > 0)
			{
				if(move[idx-1] == i) continue;
			}
			move[idx]=i;
			findmove(idx+1);
		}
		
	}
	private static void play(int[][] temp, int[] move2) {
		for(int i = 0; i < 5; i ++)
		{
			if(move2[i] == 0)
			{
				left(temp);
			}else if(move2[i] == 1)
			{
				up(temp);
			}else if(move2[i] == 2)
			{
				right(temp);
			}else if(move2[i] == 3)
			{
				down(temp);
			}
			
		}
		

	}
	private static void down(int[][] temp) {
		  for(int j = 0; j < N ; j++)
          {
              list.clear();
              for(int i = N - 1; i >= 0; i--)
              {   
                  if(temp[i][j] == 0)continue;
                  if(i != 0 && temp[i - 1][j] == 0)
                  {
                	  temp[i-1][j] = temp[i][j];
                      continue;
                  }
                  if(i != 0 && temp[i][j] == temp[i-1][j])
                  {
                      list.addLast(temp[i][j] * 2);
                      if(max < temp[i][j] * 2)max = temp[i][j] * 2;
                      i--;
                  }else
                  {
                      list.addLast(temp[i][j]);
                  }
              }
              for(int i = N - 1; i >= 0 ; i --)
              {
                  if(!list.isEmpty())
                  {
                	  temp[i][j] = list.getFirst();
                      list.removeFirst();
                  }else
                	  temp[i][j] = 0;
              }
          }
	}
	private static void right(int[][] temp) {
		 for(int i = 0; i< N ; i++)
         {
             list.clear();
             for(int j = N -1 ; j >= 0; j --)
             {
                 if(temp[i][j] == 0)continue;
                 if(j != 0 && temp[i][j - 1] == 0)
                 {
                	 temp[i][j-1] = temp[i][j];
                     continue;
                 }
                 if(j != 0 && temp[i][j] == temp[i][j - 1])
                 {
                     list.addLast(temp[i][j] * 2);
                     if(max < temp[i][j] * 2)max = temp[i][j] * 2;

                     j--;
                 }else
                     list.addLast(temp[i][j]);
             }
             for(int j = N -1 ; j >= 0; j --)
             {
                 if(!list.isEmpty())
                 {
                	 temp[i][j] = list.getFirst();
                     list.removeFirst();
                 }else
                	 temp[i][j] = 0;
             }
         }
          
	}
	private static void up(int[][] temp) {
		  for(int j = 0; j < N ; j++)
          {
              list.clear();
              for(int i = 0; i < N; i++)
              {   
                  if(temp[i][j] == 0)continue;
                  if(i != N -1 &&temp[i + 1][j] == 0)
                  {
                	  temp[i+1][j] = temp[i][j];
                      continue;
                  }
                  if(i != N -1 && temp[i][j] == temp[i+1][j])
                  {
                      list.addLast(temp[i][j] * 2);
                      if(max < temp[i][j] * 2)max = temp[i][j] * 2;

                      i++;
                  }else
                  {
                      list.addLast(temp[i][j]);
                  }
              }
              for(int i = 0; i < N ; i ++)
              {
                  if(!list.isEmpty())
                  {
                	  temp[i][j] = list.getFirst();
                      list.removeFirst();
                  }else
                	  temp[i][j] = 0;
              }
          }
	}
	private static void left(int[][] temp) {
		 for(int i = 0; i< N ; i++)
         {
             list.clear();
             for(int j = 0 ; j < N ; j++)
             {
                 if(temp[i][j] == 0)continue;
                 if(j != N - 1 &&temp[i][j + 1] == 0)
                 {
                	 temp[i][j+1] = temp[i][j];
                     continue;
                 }
                 if(j != N - 1 && temp[i][j] == temp[i][j +1])
                 {
                     list.addLast(temp[i][j]*2);
                     if(max < temp[i][j] * 2)max = temp[i][j] * 2;

                     j++;
                 }else
                     list.addLast(temp[i][j]);
             }
             for(int j = 0 ; j < N ; j++)
             {
                 if(!list.isEmpty())
                 {
                	 temp[i][j] = list.getFirst();
                     list.removeFirst();
                 }else
                	 temp[i][j] = 0;
             }
         }
	}
	public static int opposite(int pos)
	{
		if(pos == 0)
		{
			return 2;
		}else if(pos == 1)
		{
			return 3;
		}else if(pos == 2)
		{
			return 0;
		}else {
			return 1;
		}
	}
}
