package com.github.mzule.androidweekly.util;

import org.tartarus.snowball.ext.EnglishStem;

/**
 * Created by CaoDongping on 4/2/16.
 */
public class StemUtil {
    public static String stem(String input) {
        String[] words = input.replaceAll("[^a-zA-Z ]", " ").toLowerCase().split("\\s+");
        StringBuilder sb = new StringBuilder();
        EnglishStem stem = new EnglishStem();
        for (String w : words) {
            stem.setCurrent(w);
            stem.stem();
            sb.append(stem.getCurrent()).append(" ");
        }
        return sb.toString();
    }
}
