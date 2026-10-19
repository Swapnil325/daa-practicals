/*
 * DAA Mini Project: String Matching Using Naive and Rabin-Karp Algorithms
 * File: StringMatching.java
 * 
 * Simple console program that reads text and pattern from stdin and
 * prints all match starting indices using both naive and Rabin-Karp.
 *
 * Compile: javac StringMatching.java
 * Run:     java StringMatching
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringMatching {
    static final int D = 256;   // size of the input alphabet
    static final int Q = 101;   // prime number used as hash modulus

    // Character-by-character comparison of pattern with text starting at index s
    static boolean isMatch(String text, String pattern, int s) {
        for (int j = 0; j < pattern.length(); j++) {
            if (text.charAt(s + j) != pattern.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    // Naive algorithm: check the pattern at every shift of the text
    static List<Integer> naiveSearch(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        int n = text.length(), m = pattern.length();
        if (m == 0 || m > n) return result;
        for (int s = 0; s <= n - m; s++) {
            if (isMatch(text, pattern, s)) {
                result.add(s);
            }
        }
        return result;
    }

    // Rabin-Karp algorithm: compare hash values first, then verify characters
    static List<Integer> rabinKarp(String text, String pattern) {
        List<Integer> result = new ArrayList<>();
        int n = text.length(), m = pattern.length();
        if (m == 0 || m > n) return result;
        long h = 1;   // h = D^(m-1) mod Q
        long p = 0;   // hash value of the pattern
        long t = 0;   // hash value of the current text window

        for (int i = 0; i < m - 1; i++) {
            h = (h * D) % Q;
        }
        for (int i = 0; i < m; i++) {
            p = (D * p + pattern.charAt(i)) % Q;
            t = (D * t + text.charAt(i)) % Q;
        }
        for (int s = 0; s <= n - m; s++) {
            if (p == t && isMatch(text, pattern, s)) {   // verify to avoid spurious hit
                result.add(s);
            }
            if (s < n - m) {   // rolling hash: remove text[s], add text[s + m]
                t = (D * (t - text.charAt(s) * h) + text.charAt(s + m)) % Q;
                if (t < 0) t += Q;
            }
        }
        return result;
    }

    static void printResult(List<Integer> result) {
        if (result.isEmpty()) {
            System.out.println("Pattern not found");
            return;
        }
        for (int index : result) {
            System.out.println("Pattern found at index " + index);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text: ");
        String text = sc.hasNextLine() ? sc.nextLine() : "";
        System.out.print("Enter pattern: ");
        String pattern = sc.hasNextLine() ? sc.nextLine() : "";
        if (pattern.isEmpty()) {
            System.out.println("\nError: Pattern is empty.");
            sc.close();
            return;
        }
        if (pattern.length() > text.length()) {
            System.out.println("\nError: Pattern is longer than the text.");
            sc.close();
            return;
        }

        System.out.println("\nNaive String Matching:");
        printResult(naiveSearch(text, pattern));
        System.out.println("\nRabin-Karp Algorithm:");
        printResult(rabinKarp(text, pattern));
        sc.close();
    }
}
