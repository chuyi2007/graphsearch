package DesignExcercise;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PartialOrderToTotalOrder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getTotalOrder(new String[]{"aaa", "abc", "bb", "bc", "cdkxyz", "cdka"}));
	}
	public static String getTotalOrder(String[] wordList) {
    	Map<Character, Set<Character>> partialOrder 
    		= new HashMap<Character, Set<Character>>();
    	Map<Character, Set<Character>> orderDepends
    		= new HashMap<Character, Set<Character>>();
    	for (String word : wordList) {
    		for (char c : word.toCharArray()) {
    			if (!partialOrder.containsKey(c)) {
    				partialOrder.put(c, new HashSet<Character>());
    				orderDepends.put(c, new HashSet<Character>());
    			}
    		}
    	}
    	
    	for (int i = 0; i < wordList.length - 1; ++i) {
    		char[] order = getFirstDiffChar(wordList[i], wordList[i + 1]);
    		if (order != null) {
    			partialOrder.get(order[0]).add(order[1]);
    			orderDepends.get(order[1]).add(order[0]);
    		}
    	}
    	
    	String result = "";
    	Set<Character> validCharSet = new HashSet<Character>();
    	for (char c : orderDepends.keySet()) {
    		if (orderDepends.get(c).isEmpty())
    			validCharSet.add(c);
    	}
    	while (!validCharSet.isEmpty()) {
    		char c = validCharSet.iterator().next();
    		result = result + String.valueOf(c);
    		for (char d : partialOrder.get(c)) {
    			orderDepends.get(d).remove(c);
    		}
    		for (char d : orderDepends.keySet()) {
    			if (orderDepends.get(d).isEmpty())
    				validCharSet.add(d);
    		}
    		validCharSet.remove(c);
    		orderDepends.remove(c);
    	}
    	
    	if (!orderDepends.isEmpty()) {
    		return "error with input";
    	}
    	return result;
    }
	
	public static char[] getFirstDiffChar(String word1, String word2) {
    	for (int i = 0; i < Math.min(word1.length(), word2.length()); ++i) {
    		if (word1.charAt(i) != word2.charAt(i)) {
    			char[] order = new char[2];
    			order[0] = word1.charAt(i);
    			order[1] = word2.charAt(i);
    			return order;
    		}
    	}
    	return null;
    }
}
