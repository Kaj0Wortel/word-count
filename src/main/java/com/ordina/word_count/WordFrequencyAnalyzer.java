package com.ordina.word_count;

import java.util.List;

/**
 * Interface for analyzing the word frequency of a text.
 */
public interface WordFrequencyAnalyzer {
	/**
	 * @param text The text to parse.
	 * @return The frequency of the highest frequency word in the text.
	 */
	int calculateHighestFrequency(String text);

	/**
	 * @param text The text to parse.
	 * @param word The word to look for.
	 * @return The frequency of the word in the given text.
	 */
	int calculateFrequencyForWord(String text, String word);

	/**
	 * Determines the {@code n} most frequent words in the given {@code text}. <br>
	 * All words will be returned in <b>lower case</b>. If several words have
	 * the same frequency, then the words are returned in alphabetical order,
	 * i.e. using {@link CharSequence#compare(CharSequence, CharSequence)}.
	 *
	 * @param text The text to parse.
	 * @param n    The amount of words to find.
	 * @return A list of the most frequent {@code n} words.
	 */
	List<WordFrequency> calculateMostFrequentNWords(String text, int n);

}
