import java.io.*;
import java.util.*;

public class YahtzeeScore {
	public int maxPoints(int[] toss) {
        int res = 0;
        int[] times = new int[7];
        for(int i = 1; i <= 6; i++)
            times[i] = 0;
        for(int i = 0; i < toss.length; i++)
            times[toss[i]]++;
        for(int i = 1; i <= 6; i++) {
            if(times[i] * i > res)
                res = times[i] * i;
        }
		return res;
	}
}
