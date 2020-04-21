package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JavaCodingOne {

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

		Set<String> fewerThanNineLetterWordsSet = new HashSet<>(fewerThanNineLetterWords);

		int count = 0;
		for (String nineLetterWord : nineLetterWords) {
			boolean allGood = false;
			List<String> currentWords = new ArrayList<>();
			currentWords.add(nineLetterWord);
			for (int c = 0; c < currentWords.size() && !allGood; c++) {
				for (int k = 0; k < currentWords.get(c).length() && !allGood; k++) {
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
				count++;
				System.out.println(nineLetterWord);
			}
		}
		System.out.println(count);
	}

}
