package srm147;

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

// CUT begin
	public static void main(String[] args){
		System.err.println("PeopleCircle (600 Points)");
		System.err.println();
		HashSet<Integer> cases = new HashSet<Integer>();
        for (int i = 0; i < args.length; ++i) cases.add(Integer.parseInt(args[i]));
        runTest(cases);
//		System.out.println(new PeopleCircle().order(25, 25, 1000));
	}

	static void runTest(HashSet<Integer> caseSet) {
	    int cases = 0, passed = 0;
	    while (true) {
	    	String label = Reader.nextLine();
	    	if (label == null || !label.startsWith("--"))
	    		break;

            int numMales = Integer.parseInt(Reader.nextLine());
            int numFemales = Integer.parseInt(Reader.nextLine());
            int K = Integer.parseInt(Reader.nextLine());
            Reader.nextLine();
            String __answer = Reader.nextLine();

            cases++;
            if (caseSet.size() > 0 && !caseSet.contains(cases - 1))
                continue;
    		System.err.print(String.format("  Testcase #%d ... ", cases - 1));

            if (doTest(numMales, numFemales, K, __answer))
                passed++;
	    }
	    if (caseSet.size() > 0) cases = caseSet.size();
        System.err.println(String.format("%nPassed : %d/%d cases", passed, cases));

        int T = (int)(System.currentTimeMillis() / 1000) - 1474698901;
        double PT = T / 60.0, TT = 75.0;
        System.err.println(String.format("Time   : %d minutes %d secs%nScore  : %.2f points", T / 60, T % 60, 600 * (0.3 + (0.7 * TT * TT) / (10.0 * PT * PT + TT * TT))));
	}

	static boolean doTest(int numMales, int numFemales, int K, String __expected) {
		long startTime = System.currentTimeMillis();
		Throwable exception = null;
		PeopleCircle instance = new PeopleCircle();
		String __result = "";
		try {
			__result = instance.order(numMales, numFemales, K);
		}
		catch (Throwable e) { exception = e; }
		double elapsed = (System.currentTimeMillis() - startTime) / 1000.0;

		if (exception != null) {
			System.err.println("RUNTIME ERROR!");
			exception.printStackTrace();
			return false;
		}
		else if (__expected.equals(__result)) {
			System.err.println("PASSED! " + String.format("(%.2f seconds)", elapsed));
			return true;
		}
		else {
			System.err.println("FAILED! " + String.format("(%.2f seconds)", elapsed));
			System.err.println("           Expected: " + "\"" + __expected + "\"");
			System.err.println("           Received: " + "\"" + __result + "\"");
			return false;
		}
	}

	static class Reader {
        private static final String dataFileName = "PeopleCircle.sample";
	    private static BufferedReader reader;

	    public static String nextLine() {
	        try {
                if (reader == null) {
                    reader = new BufferedReader(new InputStreamReader(new FileInputStream(dataFileName)));
                }
                return reader.readLine();
	        }
	        catch (IOException e) {
	            System.err.println("FATAL!! IOException");
	            e.printStackTrace();
	            System.exit(1);
	        }
	        return "";
	    }
	}
// CUT end
}
