import java.util.LinkedList;
import java.util.Queue;

public class 라인코테3 {
	public static int max = 0;
	public static void main(String[] args) throws Exception {

		String road = "001100";
		int n = 5;
		int ans = solution(road, n);
		System.out.println(ans);

	}

	public static int solution(String road, int n) {
        int answer = -1, size, now, count=0;
        Queue<Integer> q = new LinkedList<Integer>();

        size = road.length();
        for(int i=0; i<size; i++) {
            now = road.charAt(i)-'0';
            q.offer(now);
            if(now==0) count++;

            while(count>n) {
                if(q.poll()==0) count--;
            }
            if(count<=n) answer = Math.max(answer, q.size());
        }


        return answer;
	}

	private static int Countroad(int[] newarr) {
		int count = 0;
		int maxlength= 0;
		for(int i = 0; i < newarr.length ; i ++)
		{
			if(newarr[i] == 1)
			{
				count++;
			}else
			{
				if(count >maxlength)maxlength = count;
				count = 0;
			}
		}
		if(count > maxlength)maxlength = count;
		return maxlength;
	}
}
