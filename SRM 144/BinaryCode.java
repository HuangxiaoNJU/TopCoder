import java.io.*;
import java.util.*;

public class BinaryCode {
	
	public String[] decode(String message) {
		String str1 = parse(message, 0);
		String str2 = parse(message, 1);
		return new String[]{str1, str2};
	}
	
	private static String parse(String message, int P0) {
		int length = message.length();
		char[] Q = message.toCharArray();
		char[] P = new char[length];
		
		P[0] = (char)(P0 + '0');
		
		if(length == 1) {
			if(P[0] != Q[0])
				return "NONE";

			return "" + P[0];
		}
		
		P[1] = (char)(Q[0] - P[0] + '0');

		if(length == 2) {
			if(P[1] != '0' && P[1] != '1' || (P[0] - '0' + P[1] - '0') != Q[1] - '0')
				return "NONE";
			return "" + P[0] + P[1]；
		}
		
		for (int i = 2; i < length; i++) {
			P[i] = (char)((Q[i - 1] - '0') - (P[i - 1] - '0') - (P[i - 2] - '0') + '0');
			if(P[i] != '0' && P[i] != '1') {
				return "NONE";
			}
		}
		
		if(Q[length - 1] - '0' != P[length - 1] - '0' + P[length - 2] - '0') {
			return "NONE";
		}
		
		return new String(P);
	}

}
