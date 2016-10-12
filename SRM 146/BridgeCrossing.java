import java.io.*;
import java.util.*;

public class BridgeCrossing {
	
	private static int res;
	private static int[] times;
	private static int[] position;
	
	private static void go(int total, int light, int left) {
		if(left == 0) {
			if(total < res)
				res = total;
			return;
		}
		
		if(left == 1 && light == 0) {
			for (int i = 0; i < position.length; i++) {
				if(position[i] == 0)
					go(total + times[i], 1, 0);
			}
			return;
		}
		
		if(light == 0) {
			for (int i = 0; i < position.length; i++)
				for (int j = i + 1; j < position.length; j++) {
					if(position[i] == 0 && position[j] == 0) {
						position[i] = position[j] = 1;
						go(total + Math.max(times[i], times[j]), 1, left - 2);
						position[i] = position[j] = 0;
					}
				}
		}
		else {
			for (int i = 0; i < position.length; i++) {
				if(position[i] == 1) {
					position[i] = 0;
					go(total + times[i], 0, left + 1);
					position[i] = 1;
				}
			}
		}
		
	}
	
	public int minTime(int[] times) {
		int num = times.length;
		BridgeCrossing.res = 100000000;
		BridgeCrossing.times = times;
		position = new int[num];
		go(0, 0, num);
		return res;
	}

}
