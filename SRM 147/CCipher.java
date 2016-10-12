import java.io.*;
import java.util.*;

public class CCipher {
	public String decode(String cipherText, int shift) {
        char[] str = cipherText.toCharArray();
        for(int i = 0; i < str.length; i++) {
            if(str[i] - shift < 'A') {
                str[i] = (char)((int)str[i] - shift + 26);
            }
            else {
                str[i] = (char)((int)str[i] - shift);
            }
        }
		return new String(str);
	}
}
