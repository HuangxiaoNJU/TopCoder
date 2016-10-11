package sram144DIV2;

import java.io.*;
import java.util.*;

public class Bonuses {
	
	public static class Employee implements Comparable<Employee> {
		public int position;
		public int score;
		public int bonus;
		
		public Employee(int position, int points) {
			this.position = position;
			this.score = points;
		}

		@Override
		public int compareTo(Employee o) {
			return score == o.score ? (position < o.position ? -1 : 1) : (score < o.score ? 1 : -1);
		}
	}
	
	public int[] getDivision(int[] points) {
		int pool = 0;
		Employee[] employees = new Employee[points.length];
		
		for (int i = 0; i < points.length; i++) {
			pool += points[i];
			employees[i] = new Employee(i, points[i]);
		}
		
		int left = 100;
		for (int i = 0; i < employees.length; i++) {
			employees[i].bonus = (int)(100 * (employees[i].score * 1.0 / pool));
			left -= employees[i].bonus;
		}
		
		Employee[] sortedEmployees = new Employee[points.length];
		for (int i = 0; i < sortedEmployees.length; i++) {
			sortedEmployees[i] = employees[i];
		}
		
		Arrays.sort(sortedEmployees);
		for (int i = 0; i < left; i++) {
			sortedEmployees[i].bonus ++;
		}
		
		int[] result = new int[points.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = employees[i].bonus;
		}
		return result;
	}

// CUT begin
	public static void main(String[] args){
		System.err.println("Bonuses (250 Points)");
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

            int[] points = new int[Integer.parseInt(Reader.nextLine())];
            for (int i = 0; i < points.length; ++i)
                points[i] = Integer.parseInt(Reader.nextLine());
            Reader.nextLine();
            int[] __answer = new int[Integer.parseInt(Reader.nextLine())];
            for (int i = 0; i < __answer.length; ++i)
                __answer[i] = Integer.parseInt(Reader.nextLine());

            cases++;
            if (caseSet.size() > 0 && !caseSet.contains(cases - 1))
                continue;
    		System.err.print(String.format("  Testcase #%d ... ", cases - 1));

            if (doTest(points, __answer))
                passed++;
	    }
	    if (caseSet.size() > 0) cases = caseSet.size();
        System.err.println(String.format("%nPassed : %d/%d cases", passed, cases));

        int T = (int)(System.currentTimeMillis() / 1000) - 1473998371;
        double PT = T / 60.0, TT = 75.0;
        System.err.println(String.format("Time   : %d minutes %d secs%nScore  : %.2f points", T / 60, T % 60, 250 * (0.3 + (0.7 * TT * TT) / (10.0 * PT * PT + TT * TT))));
	}

	static boolean doTest(int[] points, int[] __expected) {
		long startTime = System.currentTimeMillis();
		Throwable exception = null;
		Bonuses instance = new Bonuses();
		int[] __result = new int[0];
		try {
			__result = instance.getDivision(points);
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

	static boolean equals(int[] a, int[] b) {
		if (a.length != b.length) return false;
		for (int i = 0; i < a.length; ++i) if (a[i] != b[i]) return false;
		return true;
	}

	static String toString(int[] arr) {
		StringBuffer sb = new StringBuffer();
		sb.append("[ ");
		for (int i = 0; i < arr.length; ++i) {
			if (i > 0) sb.append(", ");
			sb.append(arr[i]);
		}
		return sb.toString() + " ]";
	}

	static class Reader {
        private static final String dataFileName = "Bonuses.sample";
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
