package com.ordina.word_count;

import lombok.*;

/**
 * Container class for a word and its frequency in a text.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class WordFrequencyImpl
		implements WordFrequency {
	private String word;
	private int frequency;
	
}
