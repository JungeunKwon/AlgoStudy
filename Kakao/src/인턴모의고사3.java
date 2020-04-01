import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 인턴모의고사3 {
	public static int ans;
	public static List<String> finallist = new ArrayList<>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String user_id[] = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
		String bannd_id[] = {"fr*d*", "*rodo", "******", "******"};
		int ans = solution(user_id, bannd_id);
		System.out.println(ans);
	}

	public static int solution(String[] user_id, String[] banned_id) {
		int answer = 0;
		int N = user_id.length;
		int C = banned_id.length;
		List<String> possiblelist[] = new ArrayList[C];
		for(int i = 0; i < C; i ++)
		{
			possiblelist[i] = new ArrayList<>();
			String templine = banned_id[i];
			aa: for(int j = 0; j < N ; j++)
			{
				if(templine.length() != user_id[j].length()) continue;
				for(int k = 0 ; k < templine.length() ; k ++)
				{
					if(templine.charAt(k) == '*') continue;
					if(templine.charAt(k) != user_id[j].charAt(k)) continue aa;
				}
				possiblelist[i].add(user_id[j]);
			}
		}
		
		ans = 0;
		List<String> newlist = new ArrayList<>();
		dfs(newlist, possiblelist, 0, C);
		return ans;
	}

	private static void dfs(List<String> newlist, List<String>[] possiblelist, int idx, int c) {
		if(idx == c)
		{
			Collections.sort(newlist);
			String line = "";
			for(int i = 0; i < newlist.size(); i ++)
			{
				line += newlist.get(i);
			}
			if(!finallist.contains(line))
			{
				finallist.add(line);
				ans++;
			}
			return;
		}
		for(int i = 0; i < possiblelist[idx].size(); i ++)
		{
			String line = possiblelist[idx].get(i);
			if(newlist.contains(line)) continue;
			newlist.add(line);
			dfs(newlist, possiblelist, idx+1, c);
			newlist.remove(line);
		}
		
	}

	
}
