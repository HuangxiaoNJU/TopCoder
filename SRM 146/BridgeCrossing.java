package srm146div2;

import java.io.*;
import java.util.*;

public class BridgeCrossing {
	
	private static int res;
	private static int[] times;
	private static int[] position;
	
	private static void go(int total, int light, int left) {
		if(left == 0) {
			if(total < res)
				res = total;
			return;
		}
		
		if(left == 1 && light == 0) {
			for (int i = 0; i < position.length; i++) {
				if(position[i] == 0)
					go(total + times[i], 1, 0);
			}
			return;
		}
		
		if(light == 0) {
			for (int i = 0; i < position.length; i++)
				for (int j = i + 1; j < position.length; j++) {
					if(position[i] == 0 && position[j] == 0) {
						position[i] = position[j] = 1;
						go(total + Math.max(times[i], times[j]), 1, left - 2);
						position[i] = position[j] = 0;
					}
				}
		}
		else {
			for (int i = 0; i < position.length; i++) {
				if(position[i] == 1) {
					position[i] = 0;
					go(total + times[i], 0, left + 1);
					position[i] = 1;
				}
			}
		}
		
	}
	
	public int minTime(int[] times) {
		int num = times.length;
		BridgeCrossing.res = 100000000;
		BridgeCrossing.times = times;
		position = new int[num];
		go(0, 0, num);
		return res;
	}

// CUT begin
	public static void main(String[] args){
//		System.err.println("BridgeCrossing (1000 Points)");
//		System.err.println();
//		HashSet<Integer> cases = new HashSet<Integer>();
//        for (int i = 0; i < args.length; ++i) cases.add(Integer.parseInt(args[i]));
//        runTest(cases);
		System.out.println(new BridgeCrossing().minTime(new int[]{1, 2, 5, 10}));
	}

	static void runTest(HashSet<Integer> caseSet) {
	    int cases = 0, passed = 0;
	    while (true) {
	    	String label = Reader.nextLine();
	    	if (label == null || !label.startsWith("--"))
	    		break;

            int[] times = new int[Integer.parseInt(Reader.nextLine())];
            for (int i = 0; i < times.length; ++i)
                times[i] = Integer.parseInt(Reader.nextLine());
            Reader.nextLine();
            int __answer = Integer.parseInt(Reader.nextLine());

            cases++;
            if (caseSet.size() > 0 && !caseSet.contains(cases - 1))
                continue;
    		System.err.print(String.format("  Testcase #%d ... ", cases - 1));

            if (doTest(times, __answer))
                passed++;
	    }
	    if (caseSet.size() > 0) cases = caseSet.size();
        System.err.println(String.format("%nPassed : %d/%d cases", passed, cases));

        int T = (int)(System.currentTimeMillis() / 1000) - 1474296918;
        double PT = T / 60.0, TT = 75.0;
        System.err.println(String.format("Time   : %d minutes %d secs%nScore  : %.2f points", T / 60, T % 60, 1000 * (0.3 + (0.7 * TT * TT) / (10.0 * PT * PT + TT * TT))));
	}

	static boolean doTest(int[] times, int __expected) {
		long startTime = System.currentTimeMillis();
		Throwable exception = null;
		BridgeCrossing instance = new BridgeCrossing();
		int __result = 0;
		try {
			__result = instance.minTime(times);
		}
		catch (Throwable e) { exception = e; }
		double elapsed = (System.currentTimeMillis() - startTime) / 1000.0;

		if (exception != null) {
			System.err.println("RUNTIME ERROR!");
			exception.printStackTrace();
			return false;
		}
		else if (__result == __expected) {
			System.err.println("PASSED! " + String.format("(%.2f seconds)", elapsed));
			return true;
		}
		else {
			System.err.println("FAILED! " + String.format("(%.2f seconds)", elapsed));
			System.err.println("           Expected: " + __expected);
			System.err.println("           Received: " + __result);
			return false;
		}
	}

	static class Reader {
        private static final String dataFileName = "BridgeCrossing.sample";
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
