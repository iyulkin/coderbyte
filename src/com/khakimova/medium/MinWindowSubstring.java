package com.khakimova.medium;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Have the function MinWindowSubstring(strArr) take the array of strings stored in strArr,
 * which will contain only two strings, the first parameter being the string N
 * and the second parameter being a string K of some characters,
 * and your goal is to determine the smallest substring of N that contains all the characters in K.
 * <p>
 * For example: if strArr is ["aaabaaddae", "aed"] then the smallest substring of N
 * that contains the characters a, e, and d is "dae" located at the end of the string.
 * So for this example your program should return the string dae.
 * <p>
 * Another example: if strArr is ["aabdccdbcacd", "aad"] then the smallest substring of N
 * that contains all of the characters in K is "aabd" which is located at the beginning of the string.
 * Both parameters will be strings ranging in length from 1 to 50 characters and all of K's characters
 * will exist somewhere in the string N. Both strings will only contains lowercase alphabetic characters.
 * <p>
 * Input: new String[] {"ahffaksfajeeubsne", "jefaa"}
 * Output: aksfaje
 * <p>
 * Input: new String[] {"aaffhkksemckelloe", "fhea"}
 * Output: affhkkse
 */
public class MinWindowSubstring {

    public String minWindowSubstring(String[] strArr) {
        String givenString = strArr[0];
        String[] array = givenString.split("");
        String[] chars = strArr[1].split("");

        int windowEndPointer;
        int windowStartPointer = 0;
        String smallestSubstring = givenString;

        Map<String, Integer> nChars = new HashMap<>();
        for (String aChar : chars) {
            nChars.merge(aChar, 1, Integer::sum);
        }

        for (windowEndPointer = 0; windowEndPointer < array.length; windowEndPointer++) {
            nChars.computeIfPresent(array[windowEndPointer], (k, v) -> v - 1);
            while (nChars.values().stream().allMatch(c -> c <= 0)) {
                int windowLength = windowEndPointer - windowStartPointer + 1;
                if (windowLength < smallestSubstring.length()) {
                    smallestSubstring = givenString.substring(windowStartPointer, windowEndPointer + 1);
                }
                nChars.computeIfPresent(array[windowStartPointer], (k, v) -> v + 1);
                windowStartPointer++;
            }
        }

        return smallestSubstring;
    }

    @Test
    public void minWindowSubstringTest() {
        assertEquals("aksfaje", minWindowSubstring(new String[] {"ahffaksfajeeubsne", "jefaa"}));
        assertEquals("affhkkse", minWindowSubstring(new String[] {"aaffhkksemckelloe", "fhea"}));
    }
}
