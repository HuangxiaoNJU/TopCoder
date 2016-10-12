package srm148;

import java.io.*;
import java.util.*;

public class CircleGame {
	public int cardsLeft(String deck) {
		Vector<Integer> cards = new Vector<Integer>();
		for (char c : deck.toCharArray()) {
			int value;
			switch(c) {
			case 'A': value = 1;  break;
			case 'T': value = 10; break;
			case 'J': value = 11; break;
			case 'Q': value = 12; break;
			case 'K': value = 13;  break;
			default : value = c - '0'; break;
			}
			if(value != 13)
				cards.add(value);
		}
		boolean done = false;
		while(!done) {
			done = true;
			for (int i = 0; i < cards.size(); i++) {
				if(cards.get(i) + cards.get((i + 1) % cards.size()) == 13) {
					done = false;
					if(i == cards.size() - 1) {
						cards.remove(i);
						cards.remove(0);
					}
					else {
						cards.remove(i);
						cards.remove(i);
					}
					i--;
				}
			}
		}
		return cards.size();
	}

// CUT begin
	public static void main(String[] args){
//		System.err.println("CircleGame (250 Points)");
//		System.err.println();
//		HashSet<Integer> cases = new HashSet<Integer>();
//        for (int i = 0; i < args.length; ++i) cases.add(Integer.parseInt(args[i]));
//        runTest(cases);
		System.out.println(new CircleGame().cardsLeft("7879J4JQK24Q46K2A3TQ7T4256632TQ738JA6KA8K959J5T895"));
	}

	static void runTest(HashSet<Integer> caseSet) {
	    int cases = 0, passed = 0;
	    while (true) {
	    	String label = Reader.nextLine();
	    	if (label == null || !label.startsWith("--"))
	    		break;

            String deck = Reader.nextLine();
            Reader.nextLine();
            int __answer = Integer.parseInt(Reader.nextLine());

            cases++;
            if (caseSet.size() > 0 && !caseSet.contains(cases - 1))
                continue;
    		System.err.print(String.format("  Testcase #%d ... ", cases - 1));

            if (doTest(deck, __answer))
                passed++;
	    }
	    if (caseSet.size() > 0) cases = caseSet.size();
        System.err.println(String.format("%nPassed : %d/%d cases", passed, cases));

        int T = (int)(System.currentTimeMillis() / 1000) - 1476198447;
        double PT = T / 60.0, TT = 75.0;
        System.err.println(String.format("Time   : %d minutes %d secs%nScore  : %.2f points", T / 60, T % 60, 250 * (0.3 + (0.7 * TT * TT) / (10.0 * PT * PT + TT * TT))));
	}

	static boolean doTest(String deck, int __expected) {
        deck = new String(deck);
		long startTime = System.currentTimeMillis();
		Throwable exception = null;
		CircleGame instance = new CircleGame();
		int __result = 0;
		try {
			__result = instance.cardsLeft(deck);
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
        private static final String dataFileName = "CircleGame.sample";
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
