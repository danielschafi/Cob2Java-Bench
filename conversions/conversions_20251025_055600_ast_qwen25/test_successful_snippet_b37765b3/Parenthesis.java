import java.io.*;

public class Parenthesis {
    public static void main(String[] args) {
        char string1 = 'A';

        System.out.println(string1);
        if ("A".equals(String.valueOf(string1))) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }

        if ("B".equals(String.valueOf(string1))) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }

        if ("A".equals(String.valueOf(string1)) && "A".equals("A")) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }

        if ("A".equals(String.valueOf(string1)) || "B".equals("A")) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }

        if ("A".equals(String.valueOf(string1)) || "B".equals("A") && "A".equals("B")) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }

        if (("A".equals("A") || "B".equals("A")) && "A".equals("B")) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }
    }
}