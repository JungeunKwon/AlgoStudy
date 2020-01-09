import java.util.LinkedList;
import java.util.Queue;
public class 컬러링북_2017예선 {
	static class Solution {
	  public static boolean visited[][];
	  public static int max;
	  public static Queue<Info> q;
	  public static int dx[] = {0,0,-1,1};
	  public static int dy[] = {1,-1,0,0};
	  public int[] solution(int m, int n, int[][] picture) {
	      int numberOfArea = 0;
	      int maxSizeOfOneArea = 0;
	      max = 0;
	      q = new LinkedList<>();
	      visited = new boolean[m][n];
	        for(int i = 0; i < m; i ++)
		      {
		          for(int j = 0; j < n ; j ++)
		          {
		              if(picture[i][j] != 0 && !visited[i][j])
		              {
		                  visited[i][j] = true;
		                  q.offer(new Info(i, j));
		                  bfs(picture, m,n, picture[i][j]);
		                  numberOfArea ++;
		              }
		          }
		      }
	      int[] answer = new int[2];
	      answer[0] = numberOfArea;
	      answer[1] = max;
	      return answer;
	  }
	  public static class Info{
	      int i;
	      int j;
	      Info(int i, int j)
	      {
	          this.i = i;
	          this.j = j;
	      }
	  }
	 public static void bfs(int[][] arr, int m, int n, int num)
		  {
		      int tx = 0, ty =0;
		      int count =1;
		      while(!q.isEmpty())
		      {
		    	  Info temp = q.poll();
		    	  for(int i = 0; i < 4; i ++)
		    	  {
		    		  tx = temp.j + dx[i];
		    		  ty = temp.i + dy[i];
		    		  if(tx < 0 || ty <0 || tx>= n || ty >=m)continue;
		    		  if(visited[ty][tx]) continue;
		    		  if(arr[ty][tx] == num)
		    		  {
		    			count ++;
		    			visited[ty][tx] = true;
		    			q.offer(new Info(ty, tx));
		    		  }
		    	  }
		      }
		      if(count > max) max =count;
		  }
	  
	}

}
