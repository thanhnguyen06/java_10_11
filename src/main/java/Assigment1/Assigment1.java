package Assigment1;
import java.io.*;
import java.util.Arrays;
import java.util.stream.Stream;

public class Assigment1 {
    public static void SumToX(int x) {
        int sum = ( 1 + x) * x / 2;
        System.out.println(" Some of all integer from 1 to "+ Integer.toString(x) + ": " + Integer.toString(sum));
    }
    public static void problem13() throws IOException {
        BufferedReader cin = null;
        String existString = "q";
        try {
            cin = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please enter an integer x");
            String x;
            do {
                x = cin.readLine().trim();
                if (!x.equals(existString)) {
                    int value = Integer.parseInt(x);
                    if (value > 0) {
                        for (int i = 0; i < value; i++) {
                            SumToX(value);
                        }
                    } else {
                        System.out.println("Please enter positive integer");
                    }
                }
            } while (!x.equals(existString));
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
             if (cin != null) {
                 cin.close();
             }
        }
    }

    /*
    problem 14: A program merge 2 array int
    */
    public static Integer[] mergeArray(Integer[] arr1, Integer[] arr2)
    {
        return (Integer[]) Stream.of(arr1, arr2).flatMap(Stream::of).toArray();
    }

    /*
    find second largest number inside an array of int
     */
    public static void problem15(Integer[] arr) {
        int n = arr.length;
        if (n < 2) {
            System.out.println("There is no second largest number inside the array");
        }
        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        for (int i= 0; i < n ; i ++) {
            if (arr[i] > largest) {
                largest = arr[i];
                secondLargest = largest;
            }
        }
        if (secondLargest == Integer.MIN_VALUE) {
            System.out.println("There is no second largest number inside the array");
        } else {
            System.out.println("There is no second largest number inside the array");
        }
    }
    public static void main(String[] args) throws IOException {
        problem13();
    }
}
