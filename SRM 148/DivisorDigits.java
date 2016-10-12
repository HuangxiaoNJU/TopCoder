import java.io.*;
import java.util.*;

public class DivisorDigits {
	public int howMany(int number) {
        int res = 0;
        int temp = number;
        while(temp != 0) {
            int digit = temp % 10;
            temp /= 10;
            if(digit == 0) continue;
            if(number % digit == 0)
                res ++;
        }
		return res;
	}
}
