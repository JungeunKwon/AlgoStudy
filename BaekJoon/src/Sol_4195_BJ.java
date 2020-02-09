import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Sol_4195_BJ {
	public static int parent[];
	public static int count[];
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int a = 0; a < T ; a++)
		{
			int F = Integer.parseInt(br.readLine());
			int num = 1;
			parent = new int[F*2+1];
			 count= new int[F*2 + 1];
			for(int i = 0; i < parent.length ; i++)
			{
				parent[i] = i;
				count[i] = 1;
			}
			HashMap<String, Integer> map = new HashMap<String, Integer>();
			StringTokenizer st;
			for(int i = 0; i < F; i ++)
			{
				
				st = new StringTokenizer(br.readLine());
				String fi = st.nextToken();
				String se = st.nextToken();
				int ifi = 0;
				int ise = 0;
				if(map.containsKey(fi))
				{
					ifi = map.get(fi);
				}else
				{
					map.put(fi, num);
					ifi = num;
					num++;
				}
				if(map.containsKey(se))
				{
					ise = map.get(se);
				}else
				{
					map.put(se, num);
					ise = num;
					num++;
				}
				UnionSet(ifi, ise);
				
				
			}
			
			
		}
	}

	private static void UnionSet(int ifi, int ise) {
		ifi = findSet(ifi);
		ise = findSet(ise);
		if(ifi!=ise)
		{
			parent[ise] = ifi;
			count[ifi] = count[ifi] + count[ise];
			
		}
		System.out.println(count[ifi]);
		
	}

	private static int findSet(int x) {
		if(x!= parent[x])
		{
			int p = findSet(parent[x]);
			parent[x] = p;
			return p;
		}else
			return x;
		
	}

}
