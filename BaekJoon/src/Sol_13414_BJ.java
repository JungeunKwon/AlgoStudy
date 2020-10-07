import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.StringTokenizer;

public class Sol_13414_BJ {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		LinkedHashSet<String>set = new LinkedHashSet<>();
		for(int i = 0; i < L ; i ++)
		{
			String num = br.readLine();
			if(set.contains(num))
			{
				set.remove(num);
			}
			set.add(num);
		}
		Iterator<String> it = set.iterator();
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while(it.hasNext())
		{
			sb.append(it.next() + "\n");
			i++;
			if(i == K) break;
		}
		
		System.out.println(sb.toString());
	
	}

}
