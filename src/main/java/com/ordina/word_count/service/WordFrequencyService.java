package com.ordina.word_count.service;

import com.ordina.word_count.word_frequency.WordFrequencyAnalyzer;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Service for analyzing the word frequency in a text.
 */
@Path("analyze/{text}")
public class WordFrequencyService {
	
	/** The analyzer used to delegate the requests to. */
	@Inject
	private WordFrequencyAnalyzer analyzer;

	/**
	 * Entrypoint for {@link WordFrequencyAnalyzer#calculateHighestFrequency(String)}.
	 * 
	 * @param text The text to search in.
	 * @return The frequency of the highest frequency word in the text.
	 */
	@GET
	@Path("max-freq")
	@Produces(MediaType.TEXT_PLAIN)
	public Response maxFreq(@PathParam("text") String text) {
		return Response.ok(analyzer.calculateHighestFrequency(text)).build();
	}

	/**
	 * Entrypoint for {@link WordFrequencyAnalyzer#calculateFrequencyForWord(String, String)}.
	 *
	 * @param text The text to search in.
	 * @param word The word to count.
	 * @return The frequency of {@code word} in {@code text}.
	 */
	@GET
	@Path("freq-of")
	@Produces(MediaType.TEXT_PLAIN)
	public Response freqOf(@PathParam("text") String text,
						   @QueryParam("word") String word) {
		return Response.ok(analyzer.calculateFrequencyForWord(text, word)).build();
	}

	/**
	 * Entrypoint for {@link WordFrequencyAnalyzer#calculateMostFrequentNWords(String, int)}.
	 *
	 * @param text The text to search in.
	 * @param n    The maximum amount of most frequent words to return.
	 * @return The {@code n} most frequent words with their frequencies in {@code text}.
	 */
	@GET
	@Path("n-most-freq")
	@Produces(MediaType.APPLICATION_JSON)
	public Response freqOf(@PathParam("text") String text,
						   @QueryParam("n") Integer n) {
		return Response.ok(analyzer.calculateMostFrequentNWords(text, n)).build();
	}

}
