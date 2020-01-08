import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.TreeSet;

public class Sol_1181_BJ {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		TreeSet<Info> tree = new TreeSet<>();
		for(int i = 0;i < N ; i ++)
		{
			tree.add(new Info(br.readLine()));
		}
		Iterator<Info> it = tree.iterator();
		while(it.hasNext())
		{
			System.out.println(it.next().line);
		}
	}
	public static class Info implements Comparable<Info>{
		String line;
		Info(String line)
		{
			this.line = line;
		}
		@Override
		public int compareTo(Info o) {
			if(this.line.length() == o.line.length()) 
			{
				return this.line.compareTo(o.line);
			}
			else
			{
				return this.line.length() - o.line.length();
			}
		}
	}

	
}
