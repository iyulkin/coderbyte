package com.khakimova.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Вернуть все дубликаты которые встречаются среди первого или второго массива.
 * Дубликатами считаются числа, которые находятся либо в одинаковых, либо в разных массивах.
 * Дубликаты должны быть возвращены в том же порядке в котором они встречаются сначала в первом массиве, затем во втором
 * <p>
 * Например:
 * a = [4, 4, 3, 9, 8]
 * b = [2, 1, 2, 9]
 * <p>
 * Ожидаемый результат:
 * [4, 4, 9, 2, 2, 9]
 */
public class Duplicates {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {

            final var a = makeArray();
            final var b = makeArray();

            System.out.println(Arrays.toString(a));
            System.out.println(Arrays.toString(b));

            System.out.println("Duplicates: " + Arrays.toString(findDuplicates(a, b)));
        }
    }

    public static int[] findDuplicates(int[] a, int[] b) {

        Map<Integer, Integer> numbersOccurenceMap = new HashMap();
        List<Integer> duplicates = new ArrayList<>();

        List<Integer> list =
            IntStream.concat(Arrays.stream(a), Arrays.stream(b)).boxed().collect(Collectors.toList());

        list.forEach(number -> {
            numbersOccurenceMap.merge(number, 1, (k, v) -> v + 1);
        });
        list.forEach(number -> {
            if (numbersOccurenceMap.get(number) > 1) {
                duplicates.add(number);
            }
        });

        return duplicates.stream().mapToInt(v -> v).toArray();
    }

    private static int[] makeArray() {
        final var random = new Random();
        final var len = random.nextInt(8) + 3;
        return IntStream.range(0, len).map(i -> random.nextInt(10)).toArray();
    }
}
