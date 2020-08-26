import java.util.*;

public class 실패율_2019_BLIND {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 8;
		int stages[] = {1,2,3,4,5,6,7};
	
		int result[] = solution(n, stages);
		System.out.println(Arrays.toString(result));
	}

	public static int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int[] count = new int[N+2];
        int[] pass = new int[N+2];
        for(int i = 0; i<stages.length; i++) {
        	int num = stages[i];
        	count[num]++;
        	for(int j = 1 ; j <= num; j++) {
        		pass[j]++;
        	}
        }
        List<Info> list = new ArrayList<>();
        for(int i = 1; i <= N; i ++) {
        	if(pass[i] == 0 && count[i] == 0) {
            	list.add(new Info(i, -1));
            	continue;
        	}
        	double fail = (double)count[i]/(double)pass[i];
        	list.add(new Info(i, fail));
        }
        Collections.sort(list);
        for(int i = 0; i< list.size();i++) {
        	answer[i] = list.get(i).num;
        }
        return answer;
    }
	public static class Info implements Comparable<Info>{
		int num;
		double fail;
		Info(int num, double fail){
			this.num = num;
			this.fail = fail;
		}
		@Override
		public int compareTo(Info o) {
			if(this.fail == o.fail) {
				return this.num - o.num;
			}
			if(this.fail < o.fail) {
				return 1;
			}else return -1;
		}
	}
}
