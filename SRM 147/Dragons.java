package srm147;

import java.io.*;
import java.util.*;

public class Dragons {
	
	private static long gcd(long a, long b) {
		long r = a % b;
		while(r != 0) {
			a = b;
			b = r;
			r = a % b;
		}
		return b;
	}
	
	private static long[] minus(long[] a, long[] b) {
		long gcd;
		if(a[1] == b[1]) {
			gcd = gcd(a[0] - b[0], a[1]);
			return new long[]{(a[0] - b[0]) / gcd, a[1] / gcd};
		}
		else {
			gcd = gcd(a[0] * b[1] - a[1] * b[0], a[1] * b[1]);
			return new long[]{a[0] * b[1] - a[1] * b[0] / gcd, a[1] * b[1] / gcd};
		}
	}
	
	private static long[] mul(long[] a, long[] b) {
		long gcd = gcd(a[0] * b[0], a[1] * b[1]);
		return new long[]{a[0] * b[0] / gcd, a[1] * b[1] / gcd};
	}
	
	// 0 front 1 back 2 up 3 down 4 left 5 right
	public String snaug(int[] initialFood, int rounds) {
		long[] total = new long[]{0, 1};
		long[][] lastFood = new long[6][2];
		long[][] food = new long[6][2];
		long[] quarter = new long[]{1, 4};
		for (int i = 0; i < 6; i++) {
			lastFood[i][0] = food[i][0] = initialFood[i];
			lastFood[i][1] = food[i][1] = 1;
			total[0] += initialFood[i];
		}
		for (int i = 0; i < rounds; i++) {
			food[0] = food[1] = mul(quarter, minus(minus(total, lastFood[0]), lastFood[1]));
			food[2] = food[3] = mul(quarter, minus(minus(total, lastFood[2]), lastFood[3]));
			food[4] = food[5] = mul(quarter, minus(minus(total, lastFood[4]), lastFood[5]));
			for (int j = 0; j < 6; j++)
				for (int k = 0; k < 2; k++)
					lastFood[j][k] = food[j][k];
		}
		return "" + food[2][0] + (food[2][1] == 1 ? "" : "/" + food[2][1]);
	}

// CUT begin
	public static void main(String[] args){
		System.err.println("Dragons (500 Points)");
		System.err.println();
		HashSet<Integer> cases = new HashSet<Integer>();
        for (int i = 0; i < args.length; ++i) cases.add(Integer.parseInt(args[i]));
        runTest(cases);
//		System.out.println(new Dragons().snaug(new int[]{1,2,3,4,5,6}, 45));
		
	}

	static void runTest(HashSet<Integer> caseSet) {
	    int cases = 0, passed = 0;
	    while (true) {
	    	String label = Reader.nextLine();
	    	if (label == null || !label.startsWith("--"))
	    		break;

            int[] initialFood = new int[Integer.parseInt(Reader.nextLine())];
            for (int i = 0; i < initialFood.length; ++i)
                initialFood[i] = Integer.parseInt(Reader.nextLine());
            int rounds = Integer.parseInt(Reader.nextLine());
            Reader.nextLine();
            String __answer = Reader.nextLine();

            cases++;
            if (caseSet.size() > 0 && !caseSet.contains(cases - 1))
                continue;
    		System.err.print(String.format("  Testcase #%d ... ", cases - 1));

            if (doTest(initialFood, rounds, __answer))
                passed++;
	    }
	    if (caseSet.size() > 0) cases = caseSet.size();
        System.err.println(String.format("%nPassed : %d/%d cases", passed, cases));

        int T = (int)(System.currentTimeMillis() / 1000) - 1475073114;
        double PT = T / 60.0, TT = 75.0;
        System.err.println(String.format("Time   : %d minutes %d secs%nScore  : %.2f points", T / 60, T % 60, 500 * (0.3 + (0.7 * TT * TT) / (10.0 * PT * PT + TT * TT))));
	}

	static boolean doTest(int[] initialFood, int rounds, String __expected) {
		long startTime = System.currentTimeMillis();
		Throwable exception = null;
		Dragons instance = new Dragons();
		String __result = "";
		try {
			__result = instance.snaug(initialFood, rounds);
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
        private static final String dataFileName = "Dragons.sample";
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
