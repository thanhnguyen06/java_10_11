package Assigment3;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Assigment3 {
    /*
    List A contains 1,2,3,4,10(integer) .
    Map B contains ("a","1") ("b","2") ("c","10")
    get a list which contains all the elements in list A, but not in map B.
     */
    public static List<Integer> problem15(List<Integer> arr, Map<String, Integer> map) {
        Set<Integer> values = map.values().stream().collect(Collectors.toSet());

        List<Integer> results = new ArrayList<>();
        for (int ele: arr) {
            if (!values.contains(ele)) {
                results.add(ele);
            }
        }
        return results;
    }
    public static void main(String[] args) {
        List<Integer> A = Arrays.asList(1,2,3,4,10);
        Map<String, Integer> B = Stream.of(new Object[][] {
                { "a", 1 },
                { "b", 2 },
                { "c", 10 }
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));
        problem15(A, B);
    }
}
