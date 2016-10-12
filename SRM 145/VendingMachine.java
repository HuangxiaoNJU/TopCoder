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

}
