import java.io.*;
import java.util.*;

public class Time {
	public String whatTime(int seconds) {
        int hour, minute, second;
        hour = seconds / 3600;
        seconds %= 3600;
        minute = seconds / 60;
        second = seconds % 60;
        return "" + hour + ":" + minute + ":" + second;
	}
}
