import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class 튜플_2019_인턴 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[][] = { { 0, 0, 0, 0, 0 }, { 0, 0, 1, 0, 3 }, { 0, 2, 5, 0, 1 }, { 4, 2, 4, 4, 2 }, { 3, 5, 1, 3, 1 } };
		String line = "{{4,2,3},{3},{2,3,4,1},{2,3}}";
		int[] ans = solution(line);
		System.out.println(Arrays.toString(ans));
	}

	public static int[] solution(String s) {
		int[] answer = {};
		List<String> list = new ArrayList<>();
		List<Integer>templist = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		for(int a = 0; a < s.length(); a++)
		{
			char c = s.charAt(a);
			if(c == '{') {
				sb = new StringBuilder();
			}else if (c == '}')
				list.add(sb.toString());
			else
				sb.append(c);
		}
		Collections.sort(list, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				
				return o1.length() - o2.length();
			}
		});
	
		for(int i = 0; i < list.size(); i ++)
		{
			StringTokenizer st = new StringTokenizer(list.get(i), ",");
			while(st.hasMoreTokens()) {
				int tempS = Integer.parseInt(st.nextToken());
				if(templist.contains(tempS))continue;
				else
					templist.add(tempS);
			}
		}
		answer = new int[templist.size()];
		for(int i = 0; i < templist.size(); i ++)
		{
			answer[i] = templist.get(i);
		}
		return answer;
	}

}
