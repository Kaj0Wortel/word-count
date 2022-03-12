package com.ordina.word_count;

/**
 * Interface for classes storing the frequency of a word in a text.
 */
public interface WordFrequency {
	/**
	 * @return The word represented by this instance.
	 */
	String getWord();

	/**
	 * @return The frequency of the word.
	 */
	int getFrequency();
	
}
