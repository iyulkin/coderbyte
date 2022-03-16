package com.khakimova.medium;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Bracket Matcher
 * Have the function BracketMatcher(str) take the str parameter being passed and
 * return 1 if the brackets are correctly matched and each one is accounted for.
 * Otherwise return 0.
 * <p>
 * For example: if str is "(hello (world))", then the output should be 1,
 * but if str is "((hello (world))" the the output should be 0 because the brackets do not correctly match up.
 * Only "(" and ")" will be used as brackets. If str contains no brackets return 1.
 * <p>
 * Examples
 * Input: "(coder)(byte))"
 * Output: 0
 * <p>
 * Input: "(c(oder)) b(yte)"
 * Output: 1
 */
public class BracketMatcher {

    private int bracketMatcher(String str) {
        Deque<String> bracketsStack = new ArrayDeque<>();
        String[] chars = str.split("");

        for (int i = 0; i < str.length(); i++) {
            if (Objects.equals(chars[i], "(")) {
                bracketsStack.push(chars[i]);
            } else if (Objects.equals(chars[i], ")")) {
                if (bracketsStack.isEmpty()) {
                    return 0;
                } else {
                    bracketsStack.pop();
                }
            }
        }
        if (bracketsStack.isEmpty()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Test
    public void brackerMatcherTest() {
        assertEquals(0, bracketMatcher("(coder)(byte))"));
        assertEquals(1, bracketMatcher("(c(oder)) b(yte)"));
        assertEquals(0, bracketMatcher("(c(oder)) b)yte("));
    }
}
