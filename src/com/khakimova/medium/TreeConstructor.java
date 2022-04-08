package com.khakimova.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tree Constructor
 * Have the function TreeConstructor(strArr) take the array of strings stored in strArr,
 * which will contain pairs of integers in the following format: (i1,i2),
 * where i1 represents a child node in a tree
 * and the second integer i2 signifies that it is the parent of i1.
 * <p>
 * For example: if strArr is ["(1,2)", "(2,4)", "(7,2)"], then this forms a proper binary tree.
 * Your program should, in this case, return the string true because a valid binary tree can be formed.
 * <p>
 * If a proper binary tree cannot be formed with the integer pairs, then return the string false.
 * All of the integers within the tree will be unique, which means there can only be one node in the tree with the given integer value.
 * <p>
 * Examples
 * Input: new String[] {"(1,2)", "(2,4)", "(5,7)", "(7,2)", "(9,5)"}
 * Output: true
 * <p>
 * Input: new String[] {"(1,2)", "(3,2)", "(2,12)", "(5,2)"}
 * Output: false
 */
public class TreeConstructor {

    private boolean treeConstructor(String[] strArr) {
        List<String[]> nodes = Arrays.stream(strArr)
            .map(str -> str.replaceAll("[()]", "").split(","))
            .collect(Collectors.toList());

        Map<String, String> children = new HashMap<>();
        Map<String, List<String>> parents = new HashMap<>();

        for (String[] node : nodes) {
            String child = node[0];
            String parent = node[1];

            parents.computeIfAbsent(parent, k -> new ArrayList<>()).add(child);
            parents.computeIfAbsent(child, k -> new ArrayList<>());

            // if parent has more than 2 children, that's not a binary tree
            if (parents.get(parent).size() > 2) {
                return false;
            }

            // if child already has parent, that's not a tree
            if (children.containsKey(child)) {
                return false;
            } else {
                children.put(child, parent);
            }
        }

        String root = null;
        List<String> leafs = parents.keySet().stream()
            .filter(k -> parents.get(k).isEmpty()).collect(Collectors.toList());

        // check that tree has only one root node
        for (String leaf : leafs) {
            String node = leaf;
            while (children.containsKey(node)) {
                node = children.get(node);
            }
            if(root == null) {
                root = node;
            }
            if(!Objects.equals(root, node)) {
                return false;
            }
        }

        // todo: check that all nodes are connected

        return true;
    }

    @Test
    public void treeConstructorTest() {
        assertTrue(treeConstructor(new String[] {"(1,2)", "(2,4)", "(5,7)", "(7,2)", "(9,5)"}));
        assertTrue(treeConstructor(new String[] {"(2,3)", "(1,2)", "(4,9)", "(9,3)", "(12,9)", "(6,4)"}));
        assertFalse(treeConstructor(new String[] {"(1,2)", "(3,2)", "(2,12)", "(5,2)"}));
        assertFalse(treeConstructor(new String[] {"(2,3)", "(1,2)", "(4,9)", "(9,3)", "(12,9)", "(6,4)", "(1,9)"}));
    }
}
