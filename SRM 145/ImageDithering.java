import java.io.*;
import java.util.*;

public class ImageDithering {
	public int count(String dithered, String[] screen) {
		int res = 0;
		for (int i = 0; i < screen.length; i++)
			for (char c : screen[i].toCharArray())
				for (int k = 0; k < dithered.length(); k++)
					if(c == dithered.charAt(k))
						res ++;
		return res;
	}
}
