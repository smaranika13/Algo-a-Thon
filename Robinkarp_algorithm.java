//robin karp algorithm in java

import java.util.*;

public class RabinKarp {
    public final static int d = 256;  // Number of characters in the input alphabet
    public final static int q = 101;  // A prime number for modulus operation (helps avoid overflow)

    // Function to search for the pattern in the given text using Rabin-Karp Algorithm
    public static void search(String pattern, String text) {
        int m = pattern.length();
        int n = text.length();
        int p = 0;  // Hash value for pattern
        int t = 0;  // Hash value for text
        int h = 1;

        // Calculate the value of h = d^(m-1) % q (used for hash recalculation)
        for (int i = 0; i < m - 1; i++) {
            h = (h * d) % q;
        }

        // Calculate the initial hash values of the pattern and the first window of text
        for (int i = 0; i < m; i++) {
            p = (d * p + pattern.charAt(i)) % q;
            t = (d * t + text.charAt(i)) % q;
        }

        // Slide the pattern over the text one character at a time
        for (int i = 0; i <= n - m; i++) {
            // If the hash values match, check for characters one by one
            if (p == t) {
                boolean match = true;
                for (int j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    System.out.println("Pattern found at index " + i);
                }
            }

            // Recalculate the hash value for the next window of text
            if (i < n - m) {
                t = (d * (t - text.charAt(i) * h) + text.charAt(i + m)) % q;
                // We might get a negative value of t, convert it to positive
                if (t < 0) {
                    t = t + q;
                }
            }
        }
    }

    public static void main(String[] args) {
        String text = "HACKTOBERFEST";
        String pattern = "BER";

        search(pattern, text);
    }
}
