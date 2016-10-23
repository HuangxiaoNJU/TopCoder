import java.io.*;
import java.util.*;

public class FormatAmt {
	public String amount(int dollars, int cents) {
		String str = "";
		if(dollars == 0)
			return "$0." + cents / 10 + cents % 10;
		str += "" + cents % 10 + cents / 10 + '.';
		for (int i = 1; dollars != 0; i++) {
			str += "" + dollars % 10;
			dollars /= 10;
			if(i % 3 == 0 && dollars != 0)
				str += ',';
		}
		String res = "$";
		for (int i = str.length() - 1; i >= 0; i--)
			res += str.charAt(i);
		return res;
	}
}
