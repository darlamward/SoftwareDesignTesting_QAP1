package com.keyin;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) {

        Stream<String> inputWords= Stream.of("code", "bite");
        Stream<String> filteredWords = known(inputWords);

        // Print the first 10 variations
        inputWords.forEach(s -> System.out.println(s));

    }

    private static Stream<String> known(Stream<String> words) {
        Map<String,Integer> wordMap = new HashMap<>();
        //System.out.println(words.filter( (word) -> wordMap.containsKey(word) ));
        return words.filter( (word) -> wordMap.containsKey(word) );
    }
    /*
    private static Stream<String> wordEdits(String word) {
        Stream<String> deletes = IntStream.range(0, word.length())
                .mapToObj(i -> word.substring(0, i) + word.substring(i + 1));

        Stream<String> replaces = IntStream.range(0, word.length())
                .mapToObj(i -> i)
                .flatMap(i -> "abcdefghijklmnopqrstuvwxyz".chars()
                        .mapToObj(c -> word.substring(0, i) + (char) c + word.substring(i + 1)));

        Stream<String> inserts = IntStream.range(0, word.length() + 1)
                .mapToObj(i -> i)
                .flatMap(i -> "abcdefghijklmnopqrstuvwxyz".chars()
                        .mapToObj(c -> word.substring(0, i) + (char) c + word.substring(i)));

        Stream<String> transposes = IntStream.range(0, word.length() - 1)
                .mapToObj(i -> word.substring(0, i) + word.substring(i + 1, i + 2) + word.charAt(i) + word.substring(i + 2));

        return Stream.of(deletes, replaces, inserts, transposes).flatMap(x -> x);
    }*/
}
