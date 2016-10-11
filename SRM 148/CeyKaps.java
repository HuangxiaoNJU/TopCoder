package srm148;

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

// CUT begin
	public static void main(String[] args){
		System.err.println("CeyKaps (600 Points)");
		System.err.println();
		HashSet<Integer> cases = new HashSet<Integer>();
        for (int i = 0; i < args.length; ++i) cases.add(Integer.parseInt(args[i]));
        runTest(cases);
	}

	static void runTest(HashSet<Integer> caseSet) {
	    int cases = 0, passed = 0;
	    while (true) {
	    	String label = Reader.nextLine();
	    	if (label == null || !label.startsWith("--"))
	    		break;

            String typed = Reader.nextLine();
            String[] switches = new String[Integer.parseInt(Reader.nextLine())];
            for (int i = 0; i < switches.length; ++i)
                switches[i] = Reader.nextLine();
            Reader.nextLine();
            String __answer = Reader.nextLine();

            cases++;
            if (caseSet.size() > 0 && !caseSet.contains(cases - 1))
                continue;
    		System.err.print(String.format("  Testcase #%d ... ", cases - 1));

            if (doTest(typed, switches, __answer))
                passed++;
	    }
	    if (caseSet.size() > 0) cases = caseSet.size();
        System.err.println(String.format("%nPassed : %d/%d cases", passed, cases));

        int T = (int)(System.currentTimeMillis() / 1000) - 1476196191;
        double PT = T / 60.0, TT = 75.0;
        System.err.println(String.format("Time   : %d minutes %d secs%nScore  : %.2f points", T / 60, T % 60, 600 * (0.3 + (0.7 * TT * TT) / (10.0 * PT * PT + TT * TT))));
	}

	static boolean doTest(String typed, String[] switches, String __expected) {
        typed = new String(typed);
		for (int i = 0; i < switches.length; i++) {
			switches[i] = new String(switches[i]);
		}
		long startTime = System.currentTimeMillis();
		Throwable exception = null;
		CeyKaps instance = new CeyKaps();
		String __result = "";
		try {
			__result = instance.decipher(typed, switches);
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
        private static final String dataFileName = "CeyKaps.sample";
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
