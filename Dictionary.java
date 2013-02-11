import java.util.ArrayList;
import java.util.List;

public class Dictionary {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] word = {"abcd", "aca", "acab", "aba", "ada", "axyz", "zthf", "cdefgg"};
		Dict dict = new Dict();
		for(String w: word)
			dict.insert(w);
		dict.printWords("");
		System.out.println(dict.matchWord("axyz"));
		System.out.println(dict.matchWord("aaaa"));
		System.out.println(dict.matchWord("cdefgg"));
		System.out.println(dict.matchPattern("a.a", ""));
		System.out.println(dict.matchPattern("a*cab*", ""));
		System.out.println(dict.matchPattern("a.*", ""));
	}
}

class Dict{
	Dict[] children;
	boolean isWord;
	public Dict(){
		children = new Dict[26];
		isWord = false;
	}
	
	public List<String> matchPattern(String w, String s){
		List<String> result = new ArrayList<String>();
		if(w.length() == 0){
			if(isWord)
				result.add(s);
		}
		else if(w.length() > 0){
			if(w.length() > 1){
				if(w.charAt(1) == '*'){
					char c = w.charAt(0);
					result.addAll(matchPattern(w.substring(2), 
							s));
					if(c != '.'){
						if(children[getCode(c)] != null)
							result.addAll(children[getCode(c)].matchPattern(w, 
									s + String.valueOf(c)));
					}
					else{
						for(int i = 0; i < children.length; ++i)
							if(children[i] != null)
								result.addAll(children[i].matchPattern(w, 
										s + String.valueOf(getChar(i))));
					}
				}
				else{
					char c = w.charAt(0);
					if(c != '.'){
						if(children[getCode(c)] != null)
							result.addAll(children[getCode(c)].matchPattern(w.substring(1), 
									s + String.valueOf(c)));
					}
					else{
						for(int i = 0; i < children.length; ++i)
							if(children[i] != null)
								result.addAll(children[i].matchPattern(w.substring(1), 
										s + String.valueOf(getChar(i))));
					}
				}
			}
			else{
				char c = w.charAt(0);
				if(c != '.'){
					if(children[getCode(c)] != null)
						result.addAll(children[getCode(c)].matchPattern(w.substring(1), 
								s + String.valueOf(c)));
				}
				else{
					for(int i = 0; i < children.length; ++i)
						if(children[i] != null)
							result.addAll(children[i].matchPattern(w.substring(1), 
									s + String.valueOf(getChar(i))));
				}
			}
		}
		return result;
	}
	
	public boolean matchWord(String w){
		if(w.length() == 0)
			return isWord;
		else{
			char c = w.charAt(0);
			if(children[getCode(c)] != null)
				return children[getCode(c)].matchWord(w.substring(1));
			else
				return false;
		}
	}
	
	public void printWords(String w){
		if(isWord)
			System.out.println(w);
		//else{
			for(int i = 0; i < children.length; ++i){
				if(children[i] != null){
					children[i].printWords(w + String.valueOf(getChar(i)));
				}
			}
		//}
	}
	
	public void insert(String word){
		if(word.length() > 0){
			if(children[getCode(word.charAt(0))] == null)
				children[getCode(word.charAt(0))] = new Dict();
			children[getCode(word.charAt(0))].insert(word.substring(1));
		}
		else{
			isWord = true;
		}
	}
	
	private char getChar(int a){
		switch(a){
		case 0:
			return 'a';
		case 1:
			return 'b';
		case 2:
			return 'c';
		case 3:
			return 'd';
		case 4:
			return 'e';
		case 5:
			return 'f';
		case 6:
			return 'g';
		case 7:
			return 'h';
		case 8:
			return 'i';
		case 9:
			return 'j';
		case 10:
			return 'k';
		case 11:
			return 'l';
		case 12:
			return 'm';
		case 13:
			return 'n';
		case 14:
			return 'o';
		case 15:
			return 'p';
		case 16:
			return 'q';
		case 17:
			return 'r';
		case 18:
			return 's';
		case 19:
			return 't';
		case 20:
			return 'u';
		case 21:
			return 'v';
		case 22:
			return 'w';
		case 23:
			return 'x';
		case 24:
			return 'y';
		case 25:
			return 'z';
		default:
			return ' ';
		}
	}
	private int getCode(char a){
		switch(a){
		case 'a':
			return 0;
		case 'b':
			return 1;
		case 'c':
			return 2;
		case 'd':
			return 3;
		case 'e':
			return 4;
		case 'f':
			return 5;
		case 'g':
			return 6;
		case 'h':
			return 7;
		case 'i':
			return 8;
		case 'j':
			return 9;
		case 'k':
			return 10;
		case 'l':
			return 11;
		case 'm':
			return 12;
		case 'n':
			return 13;
		case 'o':
			return 14;
		case 'p':
			return 15;
		case 'q':
			return 16;
		case 'r':
			return 17;
		case 's':
			return 18;
		case 't':
			return 19;
		case 'u':
			return 20;
		case 'v':
			return 21;
		case 'w':
			return 22;
		case 'x':
			return 23;
		case 'y':
			return 24;
		case 'z':
			return 25;
		default:
			return -1;
		}
	}
}