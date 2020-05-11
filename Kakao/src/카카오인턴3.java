
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class 카카오인턴3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String gems[] = {"AA", "AB", "AC", "AA", "AC"};
		int[] ans = solution(gems);
		System.out.println(Arrays.toString(ans));
	}

	public static int[] solution(String[] gems) {
		  int[] answer = new int[2];
	        HashMap<String, Integer> map = new HashMap<>();
	        int j = 0;
	        for(int i = 0; i < gems.length; i ++)
	        {
	            if(!map.containsValue(gems[i]))
	            {
	                map.put(gems[i],j);
	                j++;
	            }
	        }
	        int min = Integer.MAX_VALUE;
	        int count[] = new int[map.size()];
	       aa: for(int i = 0; i < gems.length; i ++)
	        {
	            HashSet<String> set = new HashSet();
	            for(j = i ; j < gems.length; j ++)
	            {
	                if(min < j-i) continue aa;
	                set.add(gems[j]);                
	                if(set.size() == map.size())
	                {
	                    if(min > (j-i))
	                    {                       
	                        min = j - i;
	                        answer[0] = i + 1;
	                        answer[1] = j + 1;
	                    }
	                    continue aa;
	                }
	            } 
	        }
	        return answer;
	    }
    
	
}
