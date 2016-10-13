import java.io.*;
import java.util.*;

public class ExerciseMachine {
	
	private static int gcd(int a, int b) {
		int r = a % b;
		while(r != 0) {
			a = b;
			b = r;
			r = a % b;
		}
		return b;
	}
	
	public int getPercentages(String time) {
		String[] strs = time.split(":");
		int seconds = Integer.parseInt(strs[0]) * 3600 + Integer.parseInt(strs[1]) * 60 + Integer.parseInt(strs[2]);
		int gcd = gcd(seconds, 100);
		
		if(gcd > 100 || 100 % gcd != 0)
			return 0;
		return gcd - 1;
	}

}
