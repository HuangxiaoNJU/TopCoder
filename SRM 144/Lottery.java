
import java.io.*;
import java.util.*;

public class Lottery {
    
    public static class Rule implements Comparable<Rule> {
        String name;
        int choices, blanks;
        boolean sorted, unique;
        long types;
        
        public Rule(String r) {
            String[] str = r.split(":");
            name = str[0];
            str = str[1].split(" ");
            choices = Integer.parseInt(str[1]);
            blanks = Integer.parseInt(str[2]);
            sorted = "T".equals(str[3]);
            unique = "T".equals(str[4]);
            if(sorted && unique) {
                types = combine(choices, blanks);
            } else if(sorted && !unique) {
                types = combine(choices + blanks - 1, blanks);
            } else if(!sorted && unique) {
                types = arrange(choices, blanks);
            } else {
                types = (long)Math.pow(choices, blanks);
            }
        }
        
        public int compareTo(Rule o) {
            return types == o.types ? name.compareTo(o.name)
            : types < o.types ? -1 : 1;
        }
    }
    
	public String[] sortByOdds(String[] rules) {
        Rule[] rl = new Rule[rules.length];
        for(int i = 0; i < rl.length; i++) {
            rl[i] = new Rule(rules[i]);
        }
        Arrays.sort(rl);
        for(int i = 0; i < rl.length; i++) {
            rules[i] = rl[i].name;
        }
		return rules;
	}
    
    public static long combine(long n, long k) {
        long result = arrange(n, k);
        for(int i = 2; i <= k; i++) {
            result /= i;
        }
        return result;
    }
    
    public static long arrange(long n, long k) {
        long result = 1;
        for(int i = 0; i < k; i++) {
            result *= n - i;
        }
        return result;
    }

// CUT begin
	public static void main(String[] args){
		System.err.println("Lottery (550 Points)");
		System.err.println();
		HashSet<Integer> cases = new HashSet<Integer>();
        for (int i = 0; i < args.length; ++i) cases.add(Integer.parseInt(args[i]));
        runTest(cases);
//		String[] rules = new String[]{"INDIGO: 93 8 T F",
//				"ORANGE: 29 8 F T",
//				"VIOLET: 76 6 F F",
//				"BLUE: 100 8 T T",
//				"RED: 99 8 T T",
//				"GREEN: 78 6 F T",
//				"YELLOW: 75 6 F F"};
//		new Lottery().sortByOdds(rules);
//		System.out.println(combine(100, 8));
	}

	static void runTest(HashSet<Integer> caseSet) {
	    int cases = 0, passed = 0;
	    while (true) {
	    	String label = Reader.nextLine();
	    	if (label == null || !label.startsWith("--"))
	    		break;

            String[] rules = new String[Integer.parseInt(Reader.nextLine())];
            for (int i = 0; i < rules.length; ++i)
                rules[i] = Reader.nextLine();
            Reader.nextLine();
            String[] __answer = new String[Integer.parseInt(Reader.nextLine())];
            for (int i = 0; i < __answer.length; ++i)
                __answer[i] = Reader.nextLine();

            cases++;
            if (caseSet.size() > 0 && !caseSet.contains(cases - 1))
                continue;
    		System.err.print(String.format("  Testcase #%d ... ", cases - 1));

            if (doTest(rules, __answer))
                passed++;
	    }
	    if (caseSet.size() > 0) cases = caseSet.size();
        System.err.println(String.format("%nPassed : %d/%d cases", passed, cases));

        int T = (int)(System.currentTimeMillis() / 1000) - 1473947297;
        double PT = T / 60.0, TT = 75.0;
        System.err.println(String.format("Time   : %d minutes %d secs%nScore  : %.2f points", T / 60, T % 60, 550 * (0.3 + (0.7 * TT * TT) / (10.0 * PT * PT + TT * TT))));
	}

	static boolean doTest(String[] rules, String[] __expected) {
		for (int i = 0; i < rules.length; i++) {
			rules[i] = new String(rules[i]);
		}
		long startTime = System.currentTimeMillis();
		Throwable exception = null;
		Lottery instance = new Lottery();
		String[] __result = new String[0];
		try {
			__result = instance.sortByOdds(rules);
		}
		catch (Throwable e) { exception = e; }
		double elapsed = (System.currentTimeMillis() - startTime) / 1000.0;

		if (exception != null) {
			System.err.println("RUNTIME ERROR!");
			exception.printStackTrace();
			return false;
		}
		else if (equals(__result, __expected)) {
			System.err.println("PASSED! " + String.format("(%.2f seconds)", elapsed));
			return true;
		}
		else {
			System.err.println("FAILED! " + String.format("(%.2f seconds)", elapsed));
			System.err.println("           Expected: " + toString(__expected));
			System.err.println("           Received: " + toString(__result));
			return false;
		}
	}

	static boolean equals(String[] a, String[] b) {
		if (a.length != b.length) return false;
		for (int i = 0; i < a.length; ++i) if (a[i] == null || b[i] == null || !a[i].equals(b[i])) return false;
		return true;
	}

	static String toString(String[] arr) {
		StringBuffer sb = new StringBuffer();
		sb.append("[ ");
		for (int i = 0; i < arr.length; ++i) {
			if (i > 0) sb.append(", ");
			sb.append(arr[i]);
		}
		return sb.toString() + " ]";
	}

	static class Reader {
        private static final String dataFileName = "Lottery.sample";
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
