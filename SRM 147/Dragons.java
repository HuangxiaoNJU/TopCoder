import java.io.*;
import java.util.*;

public class Dragons {
	
	private static long gcd(long a, long b) {
		long r = a % b;
		while(r != 0) {
			a = b;
			b = r;
			r = a % b;
		}
		return b;
	}
	
	private static long[] minus(long[] a, long[] b) {
		long gcd;
		if(a[1] == b[1]) {
			gcd = gcd(a[0] - b[0], a[1]);
			return new long[]{(a[0] - b[0]) / gcd, a[1] / gcd};
		}
		else {
			gcd = gcd(a[0] * b[1] - a[1] * b[0], a[1] * b[1]);
			return new long[]{a[0] * b[1] - a[1] * b[0] / gcd, a[1] * b[1] / gcd};
		}
	}
	
	private static long[] mul(long[] a, long[] b) {
		long gcd = gcd(a[0] * b[0], a[1] * b[1]);
		return new long[]{a[0] * b[0] / gcd, a[1] * b[1] / gcd};
	}
	
	// 0 front 1 back 2 up 3 down 4 left 5 right
	public String snaug(int[] initialFood, int rounds) {
		long[] total = new long[]{0, 1};
		long[][] lastFood = new long[6][2];
		long[][] food = new long[6][2];
		long[] quarter = new long[]{1, 4};
		for (int i = 0; i < 6; i++) {
			lastFood[i][0] = food[i][0] = initialFood[i];
			lastFood[i][1] = food[i][1] = 1;
			total[0] += initialFood[i];
		}
		for (int i = 0; i < rounds; i++) {
			food[0] = food[1] = mul(quarter, minus(minus(total, lastFood[0]), lastFood[1]));
			food[2] = food[3] = mul(quarter, minus(minus(total, lastFood[2]), lastFood[3]));
			food[4] = food[5] = mul(quarter, minus(minus(total, lastFood[4]), lastFood[5]));
			for (int j = 0; j < 6; j++)
				for (int k = 0; k < 2; k++)
					lastFood[j][k] = food[j][k];
		}
		return "" + food[2][0] + (food[2][1] == 1 ? "" : "/" + food[2][1]);
	}

}
