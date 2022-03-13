package com.ordina.word_count.word_frequency;

import com.ordina.word_count.WordFrequency;
import com.ordina.word_count.WordFrequencyImpl;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.*;

/**
 * Naive implementation of the {@link WordFrequencyAnalyzer} interface.
 */
@ApplicationScoped
public class NaiveWordFrequencyAnalyzerImpl
		implements WordFrequencyAnalyzer {

	/**
	 * Prepares the string to analyze.
	 * 
	 * @param text The text to prepare.
	 * @return Converts the text to lowercase and split it into words.
	 */
	private static String[] prep(String text) {
		return text.toLowerCase().split("[^a-zA-Z]");
	}

	/**
	 * Computes the frequency map for the given words.
	 * 
	 * @param data The words to analyze.
	 * @return A map/bag containing the frequencies for each word.
	 */
	private Map<String, Integer> computeFreqMap(String[] data) {
		Map<String, Integer> freqMap = new HashMap<>();
		for (String word : data) {
			freqMap.compute(word, (word_, count) -> (count == null ? 1 : count + 1));
		}
		return freqMap;
	}
	
	@Override
	public int calculateHighestFrequency(String text) {
		return computeFreqMap(prep(text))
				.values()
				.stream()
				.mapToInt(v -> v)
				.max()
				.orElse(0);
	}

	@Override
	public int calculateFrequencyForWord(String text, String word) {
		word = word.toLowerCase();
		int count = 0;
		for (String wordInText : prep(text)) {
			if (word.equals(wordInText)) {
				count++;
			}
		}
		return count;
	}

	@Override
	public List<WordFrequency> calculateMostFrequentNWords(String text, int n) {
		Map<String, Integer> freqMap = computeFreqMap(prep(text));
		Comparator<WordFrequency> cmp = Comparator
				.comparingInt(WordFrequency::getFrequency)
				.thenComparing(WordFrequency::getWord, Comparator.reverseOrder());
		var queue = new PriorityQueue<>(cmp);
		
		for (var entry : freqMap.entrySet()) {
			queue.add(new WordFrequencyImpl(entry.getKey(), entry.getValue()));
			if (queue.size() > n) {
				queue.poll();
			}
		}
		
		List<WordFrequency> rtn = new ArrayList<>(queue);
		rtn.sort(cmp.reversed());
		return rtn;
	}
	
}
