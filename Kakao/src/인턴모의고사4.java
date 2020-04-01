import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 인턴모의고사4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long k = 10;
		long[] room_number = { 1, 3, 4, 1, 3, 1 };
		long[] ans = solution(k, room_number);
		System.out.println(Arrays.toString(ans));
	}

	public static long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        List<Long> roomlist = new ArrayList<Long>();
        for(int i = 0; i < room_number.length; i ++)
        {
        	long num = room_number[i];
        	if(roomlist.contains(num))
        	{
        		 long result = binarySearch(num+1, (long)Math.pow(10, 12), roomlist);
        		 roomlist.add(result);
        	}else
        	{
        		roomlist.add(num);
        	}
        }
        
        for(int i = 0; i < roomlist.size(); i ++)
        {
        	answer[i] = roomlist.get(i);
        }

       
        return answer;
      
    }

	private static long binarySearch(long start, long end, List<Long> roomlist) {
		if(start > end)
			return -1;
		else if(!roomlist.contains(start))
		{
			return start;
		}else
		{
			long mid = (start+end)/2;
			long returnval = binarySearch(start+1, mid, roomlist);
			if(returnval != -1)
			{
				return returnval;
			}else
			{
				return binarySearch(mid, end, roomlist);
			}
			
		}
	
	
	}
}
