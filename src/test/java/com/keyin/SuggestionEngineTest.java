// Test for SuggestionEngine Code. Software Design, Architecture and Testing - Semester 4
// Completed by Darla Ward
// Completed on June 6, 2023

//Testing

package com.keyin;

import java.io.IOException;
import java.nio.file.Paths;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SuggestionEngineTest {

    @Test
    @DisplayName("a correct word returns empty string")
    public void testGenerateSuggestionIsValid() throws Exception {
        SuggestionEngine engine = new SuggestionEngine();

        engine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").getPath()));

        String word = "abate";
        String wordSuggestions = engine.generateSuggestions(word);

        assertEquals("", wordSuggestions, "A valid word should not generate any suggestions.");
    }


    @Test
    @DisplayName("misspelled word returns a suggestion")
    public void testGenerateSuggestionsForIncorrectSpelling() throws IOException {
        SuggestionEngine engine = new SuggestionEngine();

        engine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").getPath()));

        String word = "abans";
        String expectedWord = "aband";
        String result = engine.generateSuggestions(word);
        boolean containsWord = false;
        if (result.contains(expectedWord)) {
            containsWord = true;
        }

        assertTrue(containsWord, "A misspelled word should generate a suggestion.");
    }

    @Test
    @DisplayName("empty input returns empty string")
    public void testGenerateSuggestionsForEmptyInput() throws Exception {
        SuggestionEngine engine = new SuggestionEngine();

        engine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").getPath()));

        String word = "";
        String wordSuggestions = engine.generateSuggestions(word);

        assertEquals("", wordSuggestions, "An empty input should generate an empty string.");
    }

    @Test
    @DisplayName("nonexistent word returns an empty string")
    void testGenerateSuggestionsWordNotInDictionary() throws Exception {
        SuggestionEngine engine = new SuggestionEngine();

        engine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").getPath()));

        String word = "dis8920";
        String suggestions = engine.generateSuggestions(word);

        assertEquals("", suggestions, "Nonexistent words should not generate suggestions");
    }

    @Test
    @DisplayName("only special characters returns an empty string")
    void testGenerateSuggestionsWithOnlySpecialCharacters() throws Exception {
        SuggestionEngine engine = new SuggestionEngine();

        engine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").getPath()));

        String word = "boot+";
        String suggestions = engine.generateSuggestions(word);

        assertEquals("", suggestions, "Using only special characters will generate an empty string");
    }
    @Test
    @DisplayName("special characters with word returns suggestions")
    void testGenerateSuggestionsWithSpecialCharacters() throws Exception {
        SuggestionEngine engine = new SuggestionEngine();

        engine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").getPath()));

        String word = "bo+ot+";
        String expected = "boot";
        String suggestions = engine.generateSuggestions(word);

        assertTrue(suggestions.contains(expected), "Special characters in a word will generate suggestions");
    }
}