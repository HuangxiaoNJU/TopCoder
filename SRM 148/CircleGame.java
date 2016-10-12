import java.io.*;
import java.util.*;

public class CircleGame {
	public int cardsLeft(String deck) {
		Vector<Integer> cards = new Vector<Integer>();
		for (char c : deck.toCharArray()) {
			int value;
			switch(c) {
			case 'A': value = 1; break;
			case 'T': value = 10; break;
			case 'J': value = 11; break;
			case 'Q': value = 12; break;
			case 'K': value = 13; break;
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

}
