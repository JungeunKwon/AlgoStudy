
public class 라인코테2 {

	public static void main(String[] args) {
		String answer_sheet = "24551";
		String[] sheets = {"24553", "24553", "24553", "24553"};
		int ans = solution(answer_sheet, sheets);
		System.out.println(ans);
	}
	public static int solution(String answer_sheet, String[] sheets) {
        int answer = 0;
        for(int i = 0 ; i< sheets.length; i ++)
        {
        	String sheet = sheets[i];
        	for(int j = i+1; j < sheets.length; j ++)
        	{
        		int lengthcount = 0, innermax = 0, count = 0;
        		for(int k  = 0; k < answer_sheet.length(); k++)
        		{
        			
        			if(sheet.charAt(k) == sheets[j].charAt(k))
        			{
        				if(sheet.charAt(k) != answer_sheet.charAt(k))
        				{
        					lengthcount ++;
        					count++;
        					if(lengthcount > innermax) innermax = lengthcount;

        				}else
        				{
        					lengthcount = 0;
        					
        				}
        			}
        			else
    				{
    					lengthcount = 0;
    					
    				}
        			
        		}
        		int semiresult = count + (innermax * innermax);
        		if(answer < semiresult) answer = semiresult;
        	}
        }
       

        return answer;
    }
}
