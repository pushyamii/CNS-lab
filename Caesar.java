CAESAR CIPHER

import java.util.*;

public class Caesar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the plain text:");
        String str = sc.nextLine();
        
        char[] ch = str.toCharArray(); // Store characters in an array
        int n = ch.length;
        char[] res = new char[n];

        System.out.println("Plain text= " + str);
        System.out.print("Cipher text= ");

        for (int i = 0; i < n; i++) {
            ch[i] += 3; // Shift character by 3 positions
            
            if (ch[i] > 'z') { // Handle wrap-around for lowercase
                ch[i] -= 26;
            } else if (ch[i] > 'Z' && ch[i] < 'a') { // Handle wrap-around for uppercase
                ch[i] -= 26;
            }
            
            res[i] = ch[i]; // Store encrypted character
            System.out.print(res[i]); // Print encrypted character
        }
        
        System.out.println(); // Move to next line
        sc.close();
    }
}