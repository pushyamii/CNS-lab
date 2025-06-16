//HILL CIPHER

import java.util.*;

class HillCipher {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter plain text (uppercase letters only):");
        String str = sc.nextLine();
        
        int n = str.length();

        char[] ch = str.toCharArray();
        int[] a = new int[n];
        int[][] b = new int[n][n]; // Hill cipher key matrix
        int[] c = new int[n]; // Resultant matrix
        char[] d = new char[n]; // Cipher text array
        
        // Convert plaintext to numbers
        System.out.println("Plain text matrix:");
        for (int i = 0; i < n; i++) {
            a[i] = ch[i] - 'A'; // Convert letters to numbers
            System.out.println(a[i]); 
        }

        // Get the key matrix
        System.out.println("Enter " + (n * n) + " key elements:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                b[i][j] = sc.nextInt();
            }
        }

        // Matrix multiplication
        for (int i = 0; i < n; i++) {
            c[i] = 0; // Initialize
            for (int j = 0; j < n; j++) {
                c[i] += b[i][j] * a[j]; // Correct matrix multiplication
            }
        }

        // Modulo 26 operation
        System.out.println("Matrix after mod 26:");
        for (int i = 0; i < n; i++) {
            c[i] %= 26;
            System.out.println(c[i]);
        }

        // Convert back to characters
        System.out.println("Cipher text:");
        for (int i = 0; i < n; i++) {
            d[i] = (char) ('A' + c[i]);
            System.out.print(d[i] + " ");
        }
        
        sc.close();
    }
}