package com.epam.mjc;

import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source     source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        for (String delimiter : delimiters) {
            source = source.replaceAll(delimiter, " ");
        }
        return List.of(source.trim().split("\\s+"));
//        throw new UnsupportedOperationException("You should implement this method.");
    }
}
