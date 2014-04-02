package DesignExcercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MarkovChain {
	Map<String, Map<String, Integer>> markov;
	Map<String, Integer> wordCount;
	
	
	public MarkovChain() {
		super();
		markov = new HashMap<String, Map<String, Integer>>();
		wordCount = new HashMap<String, Integer>();
	}

	void add(String w1, String w2) {
		
		if (!wordCount.containsKey(w1)) {
			wordCount.put(w1, 0);
		}
		wordCount.put(w1, wordCount.get(w1) + 1);
		if (w2 != null) {
			if (!markov.containsKey(w1)) {
				markov.put(w1, new HashMap<String, Integer>());
			}
			if (!markov.get(w1).containsKey(w2)) {
				markov.get(w1).put(w2, 0);
			}
			markov.get(w1).put(w2, markov.get(w1).get(w2) + 1);
		}
	}

	String getNextWord(String word) {
		Random r = new Random();
		double t = r.nextDouble();
		for (String w2 : markov.get(word).keySet()) {
			int count = markov.get(word).get(w2);
			double prob = count / (double) wordCount.get(word);
			if (prob > t) {
				return w2;
			}
			t -= prob;
		}
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] wordList = {"a", "b", "a", "c", "a", "d", "a", "d"};
		MarkovChain mc = new MarkovChain();
		for (int i = 0; i < wordList.length - 1; ++i) {
			mc.add(wordList[i], wordList[i + 1]);
		}
		mc.add(wordList[wordList.length - 1], null);
		
		for (int i = 0; i < 10; ++i) {
			System.out.print(mc.getNextWord("d"));
		}
	}
	
	
}
