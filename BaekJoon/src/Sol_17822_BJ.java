import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sol_17822_BJ {
   public static int N;
   public static int M;
   public static int T;
   public static int arr[][];
   public static int dx[] = {-1,1,0,0};
   public static int dy[] = {0,0,-1,1};
   public static boolean visited[][];
   public static boolean flag;
   public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());
      T = Integer.parseInt(st.nextToken());
      arr = new int[N+1][M];
      visited = new boolean[N+1][M];
      for(int i = 1; i <= N ; i ++)
      {
         st = new StringTokenizer(br.readLine());
         for(int j = 0; j < M ; j ++)
         {
            arr[i][j] = Integer.parseInt(st.nextToken());
         }
      }
      for(int i = 0 ; i < T ; i ++)
      {
         st = new StringTokenizer(br.readLine());
         int num = Integer.parseInt(st.nextToken());
         int d = Integer.parseInt(st.nextToken());
         int k = Integer.parseInt(st.nextToken());
         for(int j = 0; j  < k ; j ++)
         {
            for(int l = 1; l <= N ; l++)
            {
               if(num * l > N) continue;
               if(d == 0)
               {
                  rightrotate(arr[num*l]);
               }else
               {
                  leftrotate(arr[num*l]);
               }
            }
            
         }
         flag = false;
         for(int j = 1; j <= N; j ++)
         {
            for(int l = 0; l < M ; l++)
            {
               //if(visited[j][l])continue;
               if(arr[j][l] ==0) continue;
               visited = new boolean[N+1][M];
               visited[j][l] = true;
               find(arr[j][l], j , l);
               
               
            }
         }
        /* for(int m = 1; m <= N; m ++)
         {
            System.out.println(Arrays.toString(arr[m]));
         }
         System.out.println();*/
         
         if(!flag)
         {
            double count = 0;
            int total  = 0;
            for(int j = 1; j <= N;  j++)
            {
               for(int l =  0; l < M ; l ++)
               {
                     
                  if(arr[j][l]!=0)
                  {
                     count += arr[j][l];   
                     total ++;
                  }
               }
            }
            double avg = (double)(count/total);
          //  System.out.println(avg < 5);
            for(int j = 1; j <= N;  j++)
            {
               for(int l =  0; l < M ; l ++)
               {
                     
                  if(arr[j][l]!=0)
                  {
                     if(arr[j][l] < avg)
                     {
                        arr[j][l]++;   
                     }else if(arr[j][l] > avg)
                     {
                        arr[j][l]--;   
                     }
                  
                  }
               }
            }
            /*
            for(int m = 1; m <= N; m ++)
            {
               System.out.println(Arrays.toString(arr[m]));
            }
            System.out.println();*/
         }
       /*  for(int j = 1; j <= N; j ++)
         {
            System.out.println(Arrays.toString(arr[j]));
         }*/
      }
      int count = 0;
      for(int i = 1; i <= N;  i++)
      {
         for(int j =  0; j < M ; j ++)
         {
            count += arr[i][j];      
            
         }
      }
      System.out.println(count);
   }
   private static void find(int num, int tempi, int tempj) {
      int tx = 0 , ty = 0;
      for(int i = 0; i < 4; i ++)
      {
         tx = tempj + dx[i];
         ty = tempi + dy[i];
         if(tx < 0)
         {
            tx = M-1;
         }else if(tx >= M)
         {
            tx  = 0;            
         }else if(ty <= 0 || ty>N)continue;
         if(visited[ty][tx]) continue;
         if(arr[ty][tx] == num )
         {
            arr[ty][tx] = 0;
            arr[tempi][tempj] = 0;
            visited[ty][tx] = true;
            flag = true;
            find(num, ty, tx);
            
            
         }
      }
      
   }
   public static void rightrotate(int tr[]) {
      int temp = tr[tr.length - 1];
      for(int i = tr.length - 1; i > 0; i --)
      {
         tr[i] = tr[i-1];
      }
      tr[0] = temp;
   }
   public static void leftrotate(int tr[]) {
      int temp = tr[0];
      for(int i = 0; i < tr.length - 1; i++)
      {
         tr[i] = tr[i+1];
      }
      tr[tr.length -1] = temp;
   }
}