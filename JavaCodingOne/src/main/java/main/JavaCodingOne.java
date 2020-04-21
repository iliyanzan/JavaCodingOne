package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JavaCodingOne {

	static Set<String> fewerThanNineLetterWordsSet;
	static int count33 = 0;

	public static void main(String[] args) {
		List<String> nineLetterWords = new ArrayList<>();
		List<String> fewerThanNineLetterWords = new ArrayList<>();
		try (FileReader fr = new FileReader("scrabble-words.txt"); BufferedReader br = new BufferedReader(fr)) {
			br.readLine();
			br.readLine();
			String line;
			while ((line = br.readLine()) != null) {
				if (line.length() == 9) {
					nineLetterWords.add(line);
				} else if (line.length() < 9) {
					fewerThanNineLetterWords.add(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(nineLetterWords.size());
		System.out.println(fewerThanNineLetterWords.size());

		fewerThanNineLetterWordsSet = new HashSet<>(fewerThanNineLetterWords);

		long t1 = System.currentTimeMillis();
		int count1 = 0;
		int count11 = 0;
		for (String nineLetterWord : nineLetterWords) {
			boolean allGood = false;
			List<String> currentWords = new ArrayList<>();
			currentWords.add(nineLetterWord);
			for (int c = 0; c < currentWords.size() && !allGood; c++) {
				for (int k = 0; k < currentWords.get(c).length() && !allGood; k++) {
					count11++;
					String testWord = currentWords.get(c).substring(0, k) + currentWords.get(c).substring(k + 1);
					if (testWord.length() == 1) {
						char ch = testWord.charAt(0);
						if (ch == 'A' || ch == 'I') {
							allGood = true;
						}
					} else if (fewerThanNineLetterWordsSet.contains(testWord)) {
						if (!currentWords.contains(testWord)) {
							currentWords.add(testWord);
						}
					}
				}
			}
			if (allGood) {
				count1++;
				//System.out.println(nineLetterWord);
			}
		}
		long t2 = System.currentTimeMillis();
		
		System.out.println();
		System.out.println("queue:");
		System.out.println(count1);
		System.out.println("operations: " + count11);
		System.out.println("time: " + (t2 - t1));

		t1 = System.currentTimeMillis();
		
		int count2 = 0;
		int count22 = 0;
		for (String nineLetterWord : nineLetterWords) {
			boolean allGood = false;
			Deque<String> currentWords = new ArrayDeque<>();
			currentWords.addLast(nineLetterWord);
			while (!currentWords.isEmpty()) {
				String testWordLast = currentWords.pollLast();
				for (int k = 0; k < testWordLast.length() && !allGood; k++) {
					count22++;
					String testWord = testWordLast.substring(0, k) + testWordLast.substring(k + 1);
					if (testWord.length() == 1) {
						char ch = testWord.charAt(0);
						if (ch == 'A' || ch == 'I') {
							allGood = true;
						}
					} else if (fewerThanNineLetterWordsSet.contains(testWord)) {
						if (!currentWords.contains(testWord)) {
							currentWords.add(testWord);
						}
					}
				}
			}

			if (allGood) {
				count2++;
				//System.out.println(nineLetterWord);
			}
		}
		t2 = System.currentTimeMillis();
		
		System.out.println();
		System.out.println("stack:");
		System.out.println(count2);
		System.out.println("operations: " + count22);
		System.out.println("time: " + (t2 - t1));
		
		t1 = System.currentTimeMillis();

		int count3 = 0;
		
		for (String nineLetterWord : nineLetterWords) {
			if (backtrack(nineLetterWord)) {
				count3++;
				//System.out.println(nineLetterWord);
			}
		}
		t2 = System.currentTimeMillis();
		
		System.out.println();
		System.out.println("recursion:");
		System.out.println(count3);
		System.out.println("operations: " + count33);
		System.out.println("time: " + (t2 - t1));
	}

	public static boolean backtrack(String word) {
		if (word.length() == 2) {
			char ch0 = word.charAt(0);
			char ch1 = word.charAt(1);
			if (ch0 == 'A' || ch0 == 'I' || ch1 == 'A' || ch1 == 'I') {
				return true;
			}
		} else {
			boolean allGood = false;
			for (int k = 0; k < word.length(); k++) {
				count33++;
				String testWord = word.substring(0, k) + word.substring(k + 1);
				if (fewerThanNineLetterWordsSet.contains(testWord)) {
					if (backtrack(testWord)) {
						allGood = true;
					}
				}
			}
			return allGood;
		}

		return false;
	}

}
