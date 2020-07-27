import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class º£½ºÆ®¾Ù¹ü {

	public static void main(String[] args) throws Exception {
		String genres[] = {"classic", "pop", "classic", "classic", "pop"};
		int plays[] = {500, 600, 150, 800, 2500};
		
		int res[] = solution(genres, plays);
		System.out.println(Arrays.toString(res));
	}


	public static int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        HashMap<String, info> map = new HashMap<>();
        
        for(int i = 0; i < genres.length;  i ++ )
        {
        	if(map.containsKey(genres[i])) {
        		info temp = map.get(genres[i]);
        		temp.plays = temp.plays + plays[i];
        		temp.list.add(new play(i, plays[i]));
        	}
        	else
        	{
        		info temp = new info(plays[i]);
        		temp.list.add(new play(i, plays[i]));
        		map.put(genres[i], temp);        		
        	}
        }
        
        List<String> keySetList = new ArrayList<>(map.keySet());
        Collections.sort(keySetList, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return map.get(o2).plays - map.get(o1).plays;
			
			}
		});
        List<Integer> numlist = new ArrayList<>();
        for(String key :  keySetList)
        {
        	PriorityQueue<play> temp = map.get(key).list;
        	if(temp.size() > 1)
        	{
        		for(int i = 0; i < 2; i ++)
        		{
        			numlist.add( temp.poll().num);
         
        		}

        	}else
        	{
    			numlist.add( temp.poll().num);

        	}
      
        }
        answer = new int[numlist.size()];
        for(int i = 0; i < numlist.size(); i ++)
        {
        	answer[i] = numlist.get(i);
        }
        return answer;
    }
	public static class info{
		int plays;
		PriorityQueue<play> list;
		info(int plays)
		{
			this.plays = plays;
			this.list = new PriorityQueue<>();
		}
		
	}
	public static class play implements Comparable<play>{
		int num;
		int plays;
		play(int num, int plyas)
		{
			this.num = num;
			this.plays = plyas;
		}
		@Override
		public int compareTo(play o) {
			if(this.plays == o.plays)
			{
				return this.plays - o.plays;
			}
			return o.plays - this.plays;
		}
	}
}
