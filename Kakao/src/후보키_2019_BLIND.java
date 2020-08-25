import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class 후보키_2019_BLIND {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String[][] relation = { { "100", "ryan", "music", "2" }, { "200", "apeach", "math", "2" },
		//		{ "300", "tube", "computer", "3" }, { "400", "con", "computer", "4" }, { "500", "muzi", "music", "3" },
		//		{ "600", "apeach", "music", "2" } };

		// String[][] relation = { { "a", "b", "c" }, { "1", "b", "c" }, { "a", "b", "4"
		// }, { "a", "5", "c" } };
		// String[][] relation = { { "a", "1", "4" }, { "2", "1", "5" }, { "a", "2", "4"
		// }};
		String[][] relation = { {"b","2","a","a","b"},
				{"b","2","7","1","b"},
				{"1","0","a","a","8"},
				{"7","5","a","a","9"},
				{"3","0","a","f","9"}};

		int result = solution(relation);
		System.out.println(result);
	}

	public static boolean flag[];
	public static List<int[]> totalList;

	public static int solution(String[][] relation) {
		int answer = 0;
		
		int N = relation.length;
		int M = relation[0].length;
		int arr[] = new int[M];
		flag = new boolean[M];
		totalList = new ArrayList<>();
		for (int i = 0; i <= M; i++) {
			flag = new boolean[M];
			Combi(relation, M, N, i, 0);
		}
		answer = totalList.size();
  		return answer;
	}

	private static void Combi(String[][] relation, int M, int N, int m, int idx) {
		if (m == 0) {
			List<Integer> list = new ArrayList<>();
			for (int i = 0; i < M; i++) {
				if (flag[i]) {
					list.add(i);
				}
			}
			if(totalList != null)
			{
				for(int i = 0; i < totalList.size() ; i++)
				{
					int[] temp = totalList.get(i);
					int contain = 0; 
					for(int j = 0; j < temp.length; j ++)
					{
						if(list.contains(temp[j]))
						{
							contain++;
						}
					}
					if(contain == temp.length)
						return;
				}
			}
			HashSet<String> hash = new HashSet<>();
			for (int i = 0; i < N; i++) {
				String temp = "";
				for (int j = 0; j < list.size(); j++) {
					temp = temp + relation[i][list.get(j)];
				
				}
				if(hash.contains(temp))
					return;
				hash.add(temp);
			}
			
			
			int temp [] = new int[list.size()];
			for(int i = 0; i < list.size(); i++)
			{
				temp[i] = list.get(i);
			}
			totalList.add(temp);
			return;
		}
		for (int i = idx; i < M; i++) {
			if (flag[i])
				continue;
			flag[i] = true;
			Combi(relation, M, N, m - 1, idx + 1);
			flag[i] = false;
		}

	}

}
