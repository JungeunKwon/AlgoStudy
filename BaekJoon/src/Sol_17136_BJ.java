import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_17136_BJ {
	public static int arr[][];
	public static boolean visited[][];
	public static int N;
	public static int min;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//N = Integer.parseInt(br.readLine());
		arr = new int[10][10];
		visited = new boolean[10][10];
		StringTokenizer st ;
		int onecount = 0;
		for(int i = 0 ; i < 10; i ++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 10; j ++)
			{
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 1) onecount++;
			}
		}
		min = Integer.MAX_VALUE;
		System.out.println(onecount);
		dfs(0,0,0,5,5,5,5,5, onecount);
		System.out.println(min);
	}
	private static void dfs(int posi, int posj,int papercount, int one, int two, int three, int four, int five,int onecount) throws InterruptedException {
		if(papercount > min) return;
		
		if(one < 0 || two < 0 || three < 0 || four < 0 || five < 0)return;
		if(onecount == 0)
		{
			if(papercount < min) min =papercount;
			return;
		}
		for(int i = posi ; i < 10 ; i ++)
		{
			for(int j = 0; j < 10; j ++)
			{
				if(arr[i][j] == 1)
				{
					arr[i][j] = 0;
					dfs(i,j,papercount+1,one-1,two,three,four,five, onecount-1);
					arr[i][j] = 1;
					for(int m = 2; m <6; m ++)
					{
						int k = 0 , l = 0;
						int stopi = 0, stopj = 0;
						boolean flag = false;
						for(k = i; k < i+ m; k ++)
						{
							stopi ++;
							for(l = j; l <j + m; l ++)
							{
							
								if(k >= 10)
								{
									stopi = 9;
									break;
								}if(l >= 10)
								{
									stopj = 9;
									break;
								}
								if(arr[k][l] == 0)
								{
									flag = true;
									stopi = k;
									stopj = l;
									break;
								}else
								{
									arr[k][l] = 0;
									onecount--;
									if(onecount <=0)
									{
										flag =true;
										stopi = k;
										stopj = l;
										break;
									}
								}	stopj++;
							}
						
						}
						if(flag == false)
						{
							if(m == 2)
							{
								dfs(k,l,papercount + 1,one,two-1,three,four,five,onecount );
							}else if(m ==3)
							{
								dfs(k,l,papercount + 1,one,two,three-1,four,five,onecount );

							}
							else if(m ==4)
							{
								dfs(k,l,papercount + 1,one,two,three,four-1,five,onecount );

							}
							else if(m ==5)
							{
								dfs(k,l,papercount + 1,one,two,three,four,five-1,onecount );

							}								
						}
						System.out.println(k + " " + l + " " + i + " " + j);
						for(int q = stopi ; q >= i ; q--)
						{
							for(int w = stopj; w >=j; w --)
							{
								System.out.println(q + " " + w + " " + j);
								arr[q][w] = 1;
								onecount++;
							}
						}
					}
					System.out.println();
					Thread.sleep(500);
					for(int e = 0; e < 10; e++)
					{
						for(int r = 0; r < 10; r++)
						{
							System.out.print(arr[e][r] + " ");
						}System.out.println();
					}
				}
			}
		}
	
	}

}
