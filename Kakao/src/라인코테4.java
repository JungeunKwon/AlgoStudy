import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;


public class 라인코테4 {

	public static void main(String[] args) throws Exception {
		String snapshots[][] = {{"ACCOUNT1", "100"}, 
				  {"aCCOUNT1", "150"}};
		String tran[][] = {{"1", "SAVE", "ACCOUNT1", "100"},
				  {"2", "WITHDRAW", "ACCOUNT1", "50"}, 
				  {"1", "SAVE", "aCCOUNT1", "100"}, 
				  {"4", "SAVE", "ACCOUNT3", "500"}, 
				  {"3", "WITHDRAW", "aCCOUNT1", "30"}};
		String[][] answer = solution(snapshots, tran);
		for(int i = 0; i < answer.length; i++)
		{
			System.out.println(Arrays.toString(answer[i]));
		}

	}
	public static String[][] solution(String[][] snapshots, String[][] transactions) {
        String[][] answer = {};
        
        TreeMap<String, String> account = new TreeMap<>();
        List<String> translist = new ArrayList<>();
        for(int i = 0; i < snapshots.length; i ++)
        {
        	String acclist = snapshots[i][0];
        	String money = snapshots[i][1];
        	account.put(acclist, money);
        }
        for(int i = 0; i < transactions.length; i ++)
        {
        	String id = transactions[i][0];
        	String command = transactions[i][1];
        	String acc = transactions[i][2];
        	String money = transactions[i][3];
        	if(translist.contains(id)) continue;
        	translist.add(id);
        	if(command.equals("SAVE"))
        	{
        		if(account.containsKey(acc))
        		{
        			String before = account.get(acc);
            		int result = Integer.parseInt(before) + Integer.parseInt(money);
            		account.put(acc, Integer.toString(result));
        		}else
        		{
        			account.put(acc, money);
        		}
        	
        	}else if(command.equals("WITHDRAW"))
        	{
        		String before = account.get(acc);
        		int result = Integer.parseInt(before) - Integer.parseInt(money);
        		account.put(acc, Integer.toString(result));
        	}
        }
        answer = new String[account.size()][2];
        Iterator it = account.keySet().iterator();
        int i = 0;
        Object obj;
        while(it.hasNext())
        {
        	obj = it.next();
        	String key = (String)obj;
        	String value = account.get(obj);
        	answer[i][0] = key;
        	answer[i][1] = value;
        	i++;
        }
  
        return answer;
    }
	
}
