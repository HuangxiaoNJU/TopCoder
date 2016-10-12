import java.io.*;
import java.util.*;

public class MNS {
	public static int res;
	public static int[] nums;
	public static boolean[] isUsed;
	public static int[][] square;
	public static int sum;
	public static int[] times;
	
	public static boolean isMagic() {
		for (int i = 0; i < 3; i++)
			if(square[i][0] + square[i][1] + square[i][2] != sum || square[0][i] + square[1][i] + square[2][i] != sum)
				return false;	
		return true;
	}
	
	public static void dfs(int n) {
		if(n == 9) {
			if(isMagic())
				res ++;
			return;
		}
		for (int i = 0; i < isUsed.length; i++)
			if(!isUsed[i]) {
				isUsed[i] = true;
				square[n / 3][n % 3] = nums[i];
				dfs(n + 1);
				isUsed[i] = false;
				square[n / 3][n % 3] = 0;
			}
	}
	
	public static boolean init(int[] numbers) {
		res = 0;
		nums = numbers;
		isUsed = new boolean[9];
		square = new int[3][3];
		times = new int[10];
		for (int i = 0; i < numbers.length; i++) {
			sum += numbers[i];
			times[numbers[i]] ++;
		}
		if(sum % 3 != 0)
			return false;
		else {
			sum /= 3;
			return true;
		}
	}
	
	public int combos(int[] numbers) {
		if(init(numbers)) {
			dfs(0);
			for (int i = 0; i < 10; i++)
				if(times[i] > 1) {
					for (int j = 2; j <= times[i]; j++) {
						res /= j;
					}
				}
			return res;
		}
		else {
			return 0;
		}
	}

}
