import java.io.*;
import java.util.*;

public class GoldenChain {
	public int minCuts(int[] sections) {
		int res = 0;
		int min = 0, max = sections.length - 1;
		if(min == max)
			return 1;
		Arrays.sort(sections);
		while(min <= max) {
			// 只剩一条链
			if(min == max) {
				res ++;
				break;
			}
			// 只剩两条链
			if(max - min == 1) {
				if(sections[min] == 1)
					res ++;
				else
					res += 2;
				break;
			}
			// 还剩两条以上链
			if(sections[min] == 1) {
				res ++;
				min ++; max--;
				sections[max] = sections[max] + sections[max + 1] + 1;
			}
			else {
				res ++;
				max --;
				sections[min] --;
				sections[max] = sections[max] + sections[max + 1] + 1;
			}
		}
		return res;
	}
}
