
import java.io.*;
import java.util.*;

public class SpellCheck {
	
	private HashSet <String> dictionary = new HashSet <String>();
	private TreeSet <String> miss_spelled_words = new TreeSet <String>();
	
	public SpellCheck () throws FileNotFoundException{
	Scanner words = null; 
	words = new Scanner (new File ("/dictionary.txt"));
	
	while (words.hasNextLine()) {
		dictionary.add(words.next());
	}
	
	words.close();
	} 
	
	public void checkSpelling (String fileName) throws FileNotFoundException {
		Scanner input = null;
		input = new Scanner (new File (fileName));
		
	}
	
	public void dump_miss_spelled_words() {
		
	}

	public static void main (String [] args) {
		try {
			SpellCheck sc = new SpellCheck();
			for (int i = 0; i < args.length; i++) {
				sc.checkSpelling(args[i]);
				sc.dump_miss_spelled_words();
			}
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
}
