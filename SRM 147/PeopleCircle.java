import java.io.*;
import java.util.*;

public class PeopleCircle {
		
	public String order(int numMales, int numFemales, int K) {
		int total = numMales + numFemales;
		int start = 0;
		char[] circle = new char[total];
		if(numMales == 0) {
			for (int i = 0; i < total; i++)
				circle[i] = 'F';
			return new String(circle);
		}
		for (int i = 0; i < total; i++)
			circle[i] = 'M';
		for (int i = 0; i < numFemales; i++) {
			int count = 1, j;
			for (j = start; count <= K; j = (j + 1) % total) {
				if(circle[j] == 'M')
					count ++;
			}
			circle[j == 0 ? total - 1 : j - 1] = 'F';
			for (int k = j; k < total; k = (k + 1) % total)
				if(circle[k] == 'M') {
					start = k;
					break;
				}
		}
		return new String(circle);
	}
    
}
