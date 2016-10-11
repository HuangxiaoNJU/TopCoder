package srm145div1;

import java.io.*;
import java.util.*;

public class VendingMachine {
	
	public static class Machine {
		int shelves;
		int colomns;
		int display;
		int[][] money;
		
		// 初始化数据
		public Machine(String[] prices) {
			shelves = prices.length;
			colomns = prices[0].split(" ").length;
			display = 0;
			money = new int[shelves][colomns];
			for (int i = 0; i < shelves; i++) {
				for (int j = 0; j < colomns; j++) {
					String[] str = prices[i].split(" ");
					money[i][j] = Integer.parseInt(str[j]);
				}
			}
		}
		
		// 找出总价格最多的一列
		public int expensive() {
			int res = 0;
			int maxMoney = 0;
			for (int i = 0; i < colomns; i++) {
				int sum = 0;
				for (int j = 0; j < shelves; j++)
					sum += money[j][i];
				if(sum > maxMoney) {
					maxMoney = sum;
					res = i;
				}
			}
			return res;
		}
		
		// 计算旋转到某一列需要的时间
		public int timeToRotate(int toColomn) {
			int distance = Math.abs(display - toColomn);
			display = toColomn;
			return Math.min(distance, colomns - distance);
		}
		
		// 购买
		public int purchase(String item) {
			String[] strs = item.split(",");
			int shelf = Integer.parseInt(strs[0]);
			int colomn = Integer.parseInt(strs[1]);
			if(money[shelf][colomn] == 0)
				return -1;
			money[shelf][colomn] = 0;
			return timeToRotate(colomn);
		}
	}
	
	public static int buy(Machine machine, String[] purchases) {
		int res = 0;
		int n = purchases.length;
		String[] things = new String[n];
		int[] time = new int[n];
		for (int i = 0; i < n; i++) {
			String[] strs = purchases[i].split(":");
			things[i] = strs[0];
			time[i] = Integer.parseInt(strs[1]);
		}
		
		res += machine.timeToRotate(machine.expensive());
		for (int i = 0; i < n; i++) {
			int second = machine.purchase(things[i]);
			// 错误模拟，已经被购买
			if(second == -1)
				return -1;
			else
				res += second;
			
			if(i != n - 1 && time[i + 1] - time[i] >= 5)
				res += machine.timeToRotate(machine.expensive());
		}
		res += machine.timeToRotate(machine.expensive());
		
		return res;
	}
	
	public int motorUse(String[] prices, String[] purchases) {
		Machine machine = new Machine(prices);
		return buy(machine, purchases);
	}

// CUT begin
	public static void main(String[] args){
//		System.err.println("VendingMachine (600 Points)");
//		System.err.println();
//		HashSet<Integer> cases = new HashSet<Integer>();
//        for (int i = 0; i < args.length; ++i) cases.add(Integer.parseInt(args[i]));
//        runTest(cases);
		int res = new VendingMachine().motorUse(
				new String[]{
						"368 7960 8282 593 303 95 13 1230", 
						"74 370 134 6316 8 535 61 2435", 
						"9240 43 215 8853 37 42 92 6403", 
						"482 687 508 9602 4345 5152 5624 3113", 
						"70 5 6193 8 94 1323 5171 4776", 
						"4237 7 7 1 636 736 1 8441", 
						"516 2773 33 4772 1740 57 156 2800", 
						"4739 35 99 5050 459 2 9104 2", 
						"12 9029 5 91 95 7039 5 2", 
						"6 1 83 3062 864 665 8500 1268", 
						"7922 8347 58 1352 3753 133 4548 6", 
						"2051 1 583 1399 8354 17 347 3115", 
						"1094 5178 774 1 63 49 2 90", 
						"966 8090 1 8467 356 148 7906 27", 
						"9832 4 4 892 9128 34 5034 429", 
						"5 5257 8949 35 276 1996 93 1", 
						"6657 334 84 33 9908 2388 9332 85", 
						"943 70 153 9488 998 6274 1 9180", 
						"238 133 42 5 889 7576 9446 4254", 
						"1424 9142 6685 58 57 6974 71 8258", 
						"8 2236 63 4039 1 194 788 565", 
						"6953 3 99 487 5 5952 7468 7639", 
						"9846 8012 7705 8 7149 3 94 8608", 
						"7853 5 4935 1296 66 9 4346 2277", 
						"253 6836 89 7534 7304 8 1 70"}, 
				
				new String[]{"1,7:7", "2,4:8", "11,4:10", "8,7:18"});
		System.out.println(res);
	}

	static void runTest(HashSet<Integer> caseSet) {
	    int cases = 0, passed = 0;
	    while (true) {
	    	String label = Reader.nextLine();
	    	if (label == null || !label.startsWith("--"))
	    		break;

            String[] prices = new String[Integer.parseInt(Reader.nextLine())];
            for (int i = 0; i < prices.length; ++i)
                prices[i] = Reader.nextLine();
            String[] purchases = new String[Integer.parseInt(Reader.nextLine())];
            for (int i = 0; i < purchases.length; ++i)
                purchases[i] = Reader.nextLine();
            Reader.nextLine();
            int __answer = Integer.parseInt(Reader.nextLine());

            cases++;
            if (caseSet.size() > 0 && !caseSet.contains(cases - 1))
                continue;
    		System.err.print(String.format("  Testcase #%d ... ", cases - 1));

            if (doTest(prices, purchases, __answer))
                passed++;
	    }
	    if (caseSet.size() > 0) cases = caseSet.size();
        System.err.println(String.format("%nPassed : %d/%d cases", passed, cases));

        int T = (int)(System.currentTimeMillis() / 1000) - 1474001232;
        double PT = T / 60.0, TT = 75.0;
        System.err.println(String.format("Time   : %d minutes %d secs%nScore  : %.2f points", T / 60, T % 60, 600 * (0.3 + (0.7 * TT * TT) / (10.0 * PT * PT + TT * TT))));
	}

	static boolean doTest(String[] prices, String[] purchases, int __expected) {
		for (int i = 0; i < prices.length; i++) {
			prices[i] = new String(prices[i]);
		}
		for (int i = 0; i < purchases.length; i++) {
			purchases[i] = new String(purchases[i]);
		}
		long startTime = System.currentTimeMillis();
		Throwable exception = null;
		VendingMachine instance = new VendingMachine();
		int __result = 0;
		try {
			__result = instance.motorUse(prices, purchases);
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
        private static final String dataFileName = "VendingMachine.sample";
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
