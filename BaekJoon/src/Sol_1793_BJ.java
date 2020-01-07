import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Scanner;

public class Sol_1793_BJ {

	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		StringBuilder sb= new StringBuilder();
		BigInteger dp[] = new BigInteger[251];
		dp[0] = new BigInteger("1");
		dp[1] = new BigInteger("1");
		for(int i = 2; i <= 250; i ++)
		{
			dp[i] = dp[i-1].add(dp[i-2].multiply(BigInteger.valueOf(2)));
		}
		while(sc.hasNext())
		{
			
			int n = sc.nextInt();
			System.out.println(dp[n]);
		}
		sc.close();
	}

}
