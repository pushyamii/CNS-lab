//DES data encryption standard
import java.util.*;

public class des {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the key (10-bit, space-separated):");
        int[] key = new int[10];

        for (int i = 0; i < 10; i++)
            key[i] = sc.nextInt();

        // Step 1: Apply P10
        int[] p10 = new int[10];
        p10[0] = key[4];
        p10[1] = key[2];
        p10[2] = key[9];
        p10[3] = key[5];
        p10[4] = key[8];
        p10[5] = key[1];
        p10[6] = key[0];
        p10[7] = key[3];
        p10[8] = key[6];
        p10[9] = key[7];

        System.out.println("P10 permutation:");
        printArray(p10);

        // Step 2: Split into left and right 5 bits
        int[] left = Arrays.copyOfRange(p10, 0, 5);
        int[] right = Arrays.copyOfRange(p10, 5, 10);

        // Step 3: Perform LS-1 (1-bit left shift)
        int[] left1 = leftShift(left, 1);
        int[] right1 = leftShift(right, 1);

        System.out.println("After LS-1:");
        System.out.print("Left: ");
        printArray(left1);
        System.out.print("Right: ");
        printArray(right1);

        // Step 4: Combine and apply P8 to get Key 1
        int[] combined1 = combineHalves(left1, right1);
        int[] key1 = perm8(combined1);

        System.out.println("Key 1:");
        printArray(key1);

        // Step 5: Perform LS-2 (2-bit left shift)
        int[] left2 = leftShift(left1, 2);
        int[] right2 = leftShift(right1, 2);

        System.out.println("After LS-2:");
        System.out.print("Left: ");
        printArray(left2);
        System.out.print("Right: ");
        printArray(right2);

        // Step 6: Combine and apply P8 to get Key 2
        int[] combined2 = combineHalves(left2, right2);
        int[] key2 = perm8(combined2);

        System.out.println("Key 2:");
        printArray(key2);

        sc.close();
    }

    // Left shift by n positions
    public static int[] leftShift(int[] bits, int shift) {
        int[] result = new int[bits.length];
        for (int i = 0; i < bits.length; i++) {
            result[i] = bits[(i + shift) % bits.length];
        }
        return result;
    }

    // Combine two 5-bit arrays into one 10-bit array
    public static int[] combineHalves(int[] left, int[] right) {
        int[] combined = new int[10];
        for (int i = 0; i < 5; i++) {
            combined[i] = left[i];
            combined[i + 5] = right[i];
        }
        return combined;
    }

    // P8 permutation
    public static int[] perm8(int[] bits) {
        int[] p8 = new int[8];
        p8[0] = bits[4];
        p8[1] = bits[6];
        p8[2] = bits[7];
        p8[3] = bits[5];
        p8[4] = bits[1];
        p8[5] = bits[2];
        p8[6] = bits[3];
        p8[7] = bits[0];
        return p8;
    }

    // Print array
    public static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}