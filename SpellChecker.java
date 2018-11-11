
import java.io.*;
import java.util.*;

public class SpellCheck {

	private HashSet<String> dictionary = new HashSet<String>();
	private TreeSet<String> miss_spelled_words = new TreeSet<String>();
	int lineNum = 0;

	public SpellCheck() throws FileNotFoundException {
		Scanner words = null;
		words = new Scanner(new File("/Users/jess/eclipse-workspace/JH6_JDuell/dictionary.txt"));

		while (words.hasNextLine()) {
			dictionary.add(words.next());
		}

		words.close();
	}

	public void checkSpelling(String fileName) throws FileNotFoundException {
		System.out.println("************* Spell Checking " + fileName + "*************");

		miss_spelled_words.clear();

		Scanner input = new Scanner(new File(fileName));
		Scanner keyboard = new Scanner (System.in);

		while (input.hasNextLine()) {
			lineNum++;
			String line = input.nextLine();
			StringTokenizer st = new StringTokenizer(line, " \t,.;:-()%'\"");
			

			while (st.hasMoreTokens()) {
				String word = st.nextToken();
				word = word.toLowerCase();
				if (Character.isDigit(word.charAt(0))) {
					continue;
				} else {
					if (!dictionary.contains(word) && !miss_spelled_words.contains(word)) {
						String newWord = word.substring(0, word.length() - 1);
						if (!dictionary.contains(newWord) && !miss_spelled_words.contains(newWord)) {
							System.out.println(lineNum + " : " + line);
							System.out.println(word + " not in dictionary. Add to dictionary? (y/n)");
							String answer = keyboard.nextLine();
							answer.toLowerCase();
							char c = answer.charAt(0);
							if (c == 'y') {
								dictionary.add(word);
							} else if (c == 'n') {
								miss_spelled_words.add(word);
							} else {
								System.out.println("Please enter y or n");
							}
						}
					}

				}
			}
		}

	}

	public void dump_miss_spelled_words() {
		Iterator<String> iter = miss_spelled_words.iterator();
		System.out.println("****** Miss Spelled Words ******");
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}

	public static void main(String[] args) {
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
