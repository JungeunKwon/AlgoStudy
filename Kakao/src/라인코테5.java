import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class 라인코테5 {

	public static void main(String[] args) throws Exception {
		String dataSource[][] = { {"doc1", "t1", "t2", "t3"},
				{"Doc1", "t0", "t2", "t3"},
			    {"doc3", "t1", "t6", "t7"},
			    {"doc4", "t1", "t2", "t4"},
			    {"doc5", "t6", "t100", "t8"}};
		String tags[] = {"t1", "t2", "t3"};
		String[] answer = solution(dataSource, tags);
		System.out.println(Arrays.toString(answer));
		

	}
	 public static String[] solution(String[][] dataSource, String[] tags) {
	        String[] answer = {};
	        List<info> docs = new ArrayList<>();
	        List<String>taglist = new ArrayList<>();
	        for(int i = 0; i < tags.length; i ++)
	        {
	        	taglist.add(tags[i]);
	        }
	        for(int i = 0; i < dataSource.length; i ++)
	        {
	        	String doc = dataSource[i][0];
	        	int count = 0;
	        	for(int j = 1; j < dataSource[i].length; j ++)
	        	{
	        		String tag = dataSource[i][j];
	        		if(taglist.contains(tag))
	        		{
	        			count++;
	        		}
	        	}
	        	if(count > 0 )
	        	{
	        		docs.add(new info(doc, count));
	        	}
	        	
	        }
	        Collections.sort(docs, new Comparator<info>() {

				@Override
				public int compare(info o1, info o2) {
					if(o1.count == o2.count)
					{
						return o1.doc.compareTo(o2.doc);
					}else
					{
						return o2.count - o1.count;
					}
				
				}
			});
	        int size = docs.size();
	        if(size > 10) size = 10;
	        answer = new String[size];
	        
	        for(int i = 0; i < size ; i ++)
	        {
	        	answer[i] = docs.get(i).doc;
	        }
	        return answer;
	    }
	public static class info{
		public String doc;
		public int count;
		info(String doc, int count)
		{
			this.doc = doc;
			this.count = count;
		}
	}
}
