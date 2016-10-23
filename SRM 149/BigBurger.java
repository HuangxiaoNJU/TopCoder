import java.io.*;
import java.util.*;

public class BigBurger {
	public int maxWait(int[] arrival, int[] service) {
		int time = arrival[0];
		int wait = 0;
		for (int i = 1; i < arrival.length; i++) {
			time = Math.max(time + service[i - 1], arrival[i]);
			if(time - arrival[i] > wait)
				wait = time - arrival[i];
		}
		return wait;
	}
}
