import java.math.BigInteger;
import java.security.*;

public class ElGamalSignature {

    private static final int BIT_LENGTH = 512;
    private static final SecureRandom random = new SecureRandom();

    private BigInteger q;         // prime number
    private BigInteger alpha;     // primitive root modulo q
    private BigInteger Xa;        // private key
    private BigInteger Ya;        // public key component (alpha^Xa mod q)

    // Generate keys
    public void generateKeys() {
        q = BigInteger.probablePrime(BIT_LENGTH, random);
        alpha = new BigInteger(BIT_LENGTH - 1, random).mod(q);
        Xa = new BigInteger(BIT_LENGTH - 2, random).mod(q.subtract(BigInteger.ONE));
        Ya = alpha.modPow(Xa, q); // Ya = alpha^Xa mod q
    }

    // Sign a message
    public BigInteger[] sign(String message) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        BigInteger hash = new BigInteger(1, md.digest(message.getBytes()));

        BigInteger k;
        do {
            k = new BigInteger(BIT_LENGTH - 2, random).mod(q.subtract(BigInteger.ONE));
        } while (!k.gcd(q.subtract(BigInteger.ONE)).equals(BigInteger.ONE)); // ensure k is coprime to (q-1)

        BigInteger S1 = alpha.modPow(k, q); // S1 = alpha^k mod q
        BigInteger kInv = k.modInverse(q.subtract(BigInteger.ONE)); // k^(-1) mod (q-1)
        BigInteger S2 = (hash.subtract(Xa.multiply(S1))).multiply(kInv).mod(q.subtract(BigInteger.ONE)); // S2 = k^(-1)*(hash - Xa*S1) mod (q-1)

        return new BigInteger[]{S1, S2}; // Signature (S1, S2)
    }

    // Verify the signature
    public boolean verify(String message, BigInteger S1, BigInteger S2) throws NoSuchAlgorithmException {
        if (S1.compareTo(BigInteger.ZERO) <= 0 || S1.compareTo(q) >= 0) return false;
        if (S2.compareTo(BigInteger.ZERO) <= 0 || S2.compareTo(q.subtract(BigInteger.ONE)) >= 0) return false;

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        BigInteger hash = new BigInteger(1, md.digest(message.getBytes()));

        BigInteger V1 = alpha.modPow(hash, q); // V1 = alpha^hash mod q
        BigInteger V2 = (Ya.modPow(S1, q).multiply(S1.modPow(S2, q))).mod(q); // V2 = Ya^S1 * S1^S2 mod q

        return V1.equals(V2);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        ElGamalSignature elgamal = new ElGamalSignature();
        elgamal.generateKeys();

        String message = "Hello, ElGamal!";
        BigInteger[] signature = elgamal.sign(message);

        System.out.println("Message: " + message);
        System.out.println("Signature: S1 = " + signature[0] + ", S2 = " + signature[1]);
        boolean isValid = elgamal.verify(message, signature[0], signature[1]);

        System.out.println("Signature Valid: " + isValid);
    }
}
