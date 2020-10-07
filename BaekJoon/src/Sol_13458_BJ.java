import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Sol_13458_BJ {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int num = Integer.parseInt(st.nextToken());
		ArrayList<Integer> list = new ArrayList<Integer>();
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < num; i++) {
			list.add(Integer.parseInt(st.nextToken()));
		}
		long count = 0;
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		for (int i = 0; i < num; i++) {
			int size = list.get(i);
			count++;
			if (size <= B)
				continue;
			count += (size - B) / C;
			if ((size - B) % C != 0)
				count++;
		}
		System.out.println(count);
	}

}
