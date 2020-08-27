import java.util.*;

public class 오픈채팅방_2019_BLIND {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String record[] = { "Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo",
				"Change uid4567 Ryan" };

		String result[] = solution(record);
		System.out.println(Arrays.toString(result));
	}

	public static String[] solution(String[] record) {
		String[] answer = {};
		int alertcnt = 0;
		HashMap<String, String> nickname = new HashMap<>();
		StringTokenizer st;
		for (int i = 0; i < record.length; i++) {
			st = new StringTokenizer(record[i], " ");
			String alert = st.nextToken();
			String uid = st.nextToken();
			
			if(alert.equals("Leave")) {
				alertcnt++;
				continue;
			}
			String nick = st.nextToken();
			if (alert.equals("Enter")) {
				alertcnt++;
				if(!nickname.containsKey(uid)) {
					nickname.put(uid, nick);
				}
			} 
			nickname.put(uid, nick);
		}
		answer = new String[alertcnt];
		int j = 0;
		for (int i = 0; i < record.length; i++) {
			st = new StringTokenizer(record[i], " ");
			String alert = st.nextToken();
			String uid = st.nextToken();
			if (alert.equals("Change"))
				continue;
			if (alert.equals("Enter")) {
				answer[j] = nickname.get(uid) + "님이 들어왔습니다.";
				j++;
			}
			if (alert.equals("Leave")) {
				answer[j] = nickname.get(uid) + "님이 나갔습니다.";
				j++;
			}
		}
		return answer;
	}
}