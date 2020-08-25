import java.util.Stack;

public class 크레인인형뽑기_2019_인턴 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int arr[][] = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
		int moves[] = {1,5,3,5,1,2,1,4};
		int ans = solution(arr, moves);
		System.out.println(ans);
	}

	public static int solution(int[][] board, int[] moves) {
		int answer = 0;
		Stack<Integer> gotdolls = new Stack<>();
		for(int a = 0; a < moves.length; a++) {
			int pos = moves[a] - 1;
			aa: for(int t = 0; t < board.length; t++)
			{
				if(board[t][pos] == 0)continue;
				else
				{
					int dollnum = board[t][pos];
					board[t][pos] = 0;
					if(gotdolls.isEmpty()) gotdolls.add(dollnum);
					else
					{
						boolean flag = false;
						while(!gotdolls.isEmpty())
						{
							if(gotdolls.peek() == dollnum)
							{
								gotdolls.pop();
								answer ++;
								flag = true;
							}else
								break;
						}
						if(!flag) gotdolls.add(dollnum);
						else answer++;
					}
					break aa;
				}
			}
		
		}
		return answer;
	}
}
