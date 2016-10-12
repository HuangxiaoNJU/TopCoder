
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

}
