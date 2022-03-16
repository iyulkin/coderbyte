package com.khakimova.easy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Have the function CodelandUsernameValidation(str) take the str parameter being passed and determine if the string is a valid username according to the following rules:
 * <p>
 * 1. The username is between 4 and 25 characters.
 * 2. It must start with a letter.
 * 3. It can only contain letters, numbers, and the underscore character.
 * 4. It cannot end with an underscore character.
 * <p>
 * If the username is valid then your program should return the string true, otherwise return the string false.
 * <p>
 * Input: "aa_"
 * Output: false
 * <p>
 * Input: "u__hello_world123"
 * Output: true
 * <p>
 * searching | string manipulation | regular expression
 */
public class UsernameValidation {

    public static String codelandUsernameValidation(String str) {
        Matcher matcher = Pattern.compile("[a-zA-Z][a-zA-Z0-9_]{3,23}[a-zA-Z0-9]").matcher(str);
        if (matcher.matches()) {
            return Boolean.TRUE.toString();
        }
        return Boolean.FALSE.toString();
    }

    @Test
    void codelandUsernameValidationTest() {
        assertEquals("false", codelandUsernameValidation("aa_"));
        assertEquals("false", codelandUsernameValidation("1aa"));
        assertEquals("false", codelandUsernameValidation("sdf-"));
        assertEquals("true", codelandUsernameValidation("asJ_ssdf121"));
    }
}
