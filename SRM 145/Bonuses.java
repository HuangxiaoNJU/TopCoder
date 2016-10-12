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

}
