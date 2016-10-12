import java.io.*;
import java.util.*;

public class CeyKaps {
	public String decipher(String typed, String[] switches) {
		String res = "";
        char[] key = new char[26];
        int[] position = new int[26];
        for (char i = 'A'; i <= 'Z'; i++) {
			key[i - 'A'] = i;
			position[i - 'A'] = i - 'A';
        }
        char temp;
        for (int i = 0; i < switches.length; i++) {
        	char letter1 = switches[i].charAt(0);
        	char letter2 = switches[i].charAt(2);
			int p1 = position[letter1 - 'A'];
			int p2 = position[letter2 - 'A'];
			position[letter1 - 'A'] = p2;
			position[letter2 - 'A'] = p1;
			temp = key[p1];
			key[p1] = key[p2];
			key[p2] = temp;
		}
        for (int i = 0; i < typed.length(); i++)
			res += key[typed.charAt(i) - 'A'];
		return res;
	}
}
