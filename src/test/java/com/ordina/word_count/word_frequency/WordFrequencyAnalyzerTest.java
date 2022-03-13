package com.ordina.word_count.word_frequency;

import com.ordina.word_count.AbstractTest;
import com.ordina.word_count.WordFrequencyImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test case for the implementations of the {@link WordFrequencyAnalyzer} interface.
 */
public class WordFrequencyAnalyzerTest
		extends AbstractTest {
	
	private List<WordFrequencyAnalyzer> impls;
	
	@BeforeEach
	public void init() {
		impls = List.of(
				new NaiveWordFrequencyAnalyzerImpl()
		);
	}

	@Test
	public void highestFreqTest() {
		for (var impl : impls) {
			String text = "word word notword word WORD WoRd Word otherword wordn word";
			assertEquals(7, impl.calculateHighestFrequency(text));
			
			text = "c c c a a b b b";
			assertEquals(3, impl.calculateHighestFrequency(text));
		}
	}

	@Test
	public void wordFreqTest() {
		for (var impl : impls) {
			String text = "word word notword word WORD WoRd Word otherword wordn word";
			assertEquals(7, impl.calculateFrequencyForWord(text, "word"));
			assertEquals(7, impl.calculateFrequencyForWord(text, "WoRd"));
			assertEquals(7, impl.calculateFrequencyForWord(text, "WORD"));
			assertEquals(1, impl.calculateFrequencyForWord(text, "notword"));
			assertEquals(1, impl.calculateFrequencyForWord(text, "otherWord"));
			assertEquals(1, impl.calculateFrequencyForWord(text, "wordn"));
			assertEquals(0, impl.calculateFrequencyForWord(text, "other"));
			
			text = "c c c a a b b b";
			assertEquals(2, impl.calculateFrequencyForWord(text, "a"));
			assertEquals(3, impl.calculateFrequencyForWord(text, "b"));
			assertEquals(3, impl.calculateFrequencyForWord(text, "c"));
		}
	}
	
	@Test
	public void mostFreqWordTest() {
		for (var impl : impls) {
			String text = "word word notword word WORD WoRd Word otherword wordn word";
			List<WordFrequencyImpl> exp = new ArrayList<>();
			
			assertEquals(exp, impl.calculateMostFrequentNWords(text, 0));
			
			exp.add(new WordFrequencyImpl("word", 7));
			assertEquals(exp, impl.calculateMostFrequentNWords(text, 1));
			
			exp.add(new WordFrequencyImpl("notword", 1));
			assertEquals(exp, impl.calculateMostFrequentNWords(text, 2));

			exp.add(new WordFrequencyImpl("otherword", 1));
			assertEquals(exp, impl.calculateMostFrequentNWords(text, 3));

			exp.add(new WordFrequencyImpl("wordn", 1));
			assertEquals(exp, impl.calculateMostFrequentNWords(text, 4));

			assertEquals(exp, impl.calculateMostFrequentNWords(text, 5));
		}
	}
	
}
