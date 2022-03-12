package com.ordina.word_count;

import java.util.List;

/**
 * Naive implementation of the {@link WordFrequencyAnalyzer} interface.
 */
public class NaiveWordFrequencyAnalyzerImpl
		implements WordFrequencyAnalyzer {
	
	private String[] prep(String text) {
		return text.toLowerCase().split(" ");
	}
	
	@Override
	public int calculateHighestFrequency(String text) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int calculateFrequencyForWord(String text, String word) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<WordFrequency> calculateMostFrequentNWords(String text, int n) {
		throw new UnsupportedOperationException();
	}
	
}
