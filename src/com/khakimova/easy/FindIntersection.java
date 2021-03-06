package com.khakimova.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Have the function FindIntersection(strArr) read the array of strings stored in strArr
 * which will contain 2 elements:
 * the first element will represent a list of comma-separated numbers sorted in ascending order,
 * the second element will represent a second list of comma-separated numbers (also sorted).
 *
 * Your goal is to return a comma-separated string containing the numbers that occur in elements of strArr in sorted order.
 * If there is no intersection, return the string false.
 *
 * Examples
 * Input: new String[] {"1, 3, 4, 7, 13", "1, 2, 4, 13, 15"}
 * Output: 1,4,13
 *
 * Input: new String[] {"1, 3, 9, 10, 17, 18", "1, 4, 9, 10"}
 * Output: 1,9,10
 */
public class FindIntersection {

    private String findIntersection(String[] strArr) {
        Set<String> set = new HashSet(Arrays.asList(strArr[0].split(", ")));
        List<String> list = Arrays.asList(strArr[1].split(", "));
        List<String> intersection = new ArrayList<>();

        for(String s : list) {
            if(set.contains(s)) {
                intersection.add(s);
            }
        }

        if(intersection.isEmpty()) {
            return "false";
        }

        return String.join(",", intersection);
    }

    private String findIntersection2(String[] strArr) {
        List<String> intersection = new ArrayList<>();
        List<List<String>> arrays = Arrays.stream(strArr).map(str -> Arrays.asList(str.split(", "))).collect(Collectors.toList());

        arrays.get(0).forEach( element -> {
            if(arrays.get(1).contains(element)) {
                intersection.add(element);
            }
        });

        if(intersection.isEmpty()) {
            return "false";
        }
        return String.join(",", intersection);
    }

//    private Integer[] convertToArrayOfInteger(String str) {
//        String[] strings = str.split(", ");
//        Integer[] numbers = new Integer[strings.length];
//
//        for(int i=0; i < strings.length; i++) {
//            numbers[i] = Integer.parseInt(strings[i]);
//        }
//
//        return numbers;
//    }

    @Test
    public void findIntersectionTest() {
        assertEquals("1,4,13", findIntersection(new String[] {"1, 3, 4, 7, 13", "1, 2, 4, 13, 15"}));
        assertEquals("1,9,10", findIntersection(new String[] {"1, 3, 9, 10, 17, 18", "1, 4, 9, 10"}));
    }

    @Test
    public void findIntersection2Test() {
        assertEquals("1,4,13", findIntersection2(new String[] {"1, 3, 4, 7, 13", "1, 2, 4, 13, 15"}));
        assertEquals("1,9,10", findIntersection2(new String[] {"1, 3, 9, 10, 17, 18", "1, 4, 9, 10"}));
    }
}
