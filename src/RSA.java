/*
    Name: Amr Mahmoud
    ID: 2142598
    Course: CPIT-425
    Lab: 4
*/

import java.math.BigInteger;
import java.util.Scanner;

public class RSA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a prime number for p q: ");
        int p = scanner.nextInt();
        int q = scanner.nextInt();
        
        System.out.print("Enter the value of the message: ");
        long m = scanner.nextInt();
        
        int n = getN(p, q);
        int phiN = getPhiN(p, q);
        
        int publicKey = getE(phiN);
        int privateKey = getD(phiN, publicKey);
        
        long ciphertext = encryptDecrypt(m, publicKey, n);
        long plaintext = encryptDecrypt(ciphertext, privateKey, n);
        
        System.out.println("\nN = " + n);
        System.out.println("Phi(N) = " + phiN);
        System.out.println("Public Key: {" + publicKey + ", " + n + "}");
        System.out.println("Private Key: {" + privateKey + ", " + n + "}");

        System.out.println("\nCiphertext: " + ciphertext);
        System.out.println("Plaintext: " + plaintext);
    }
    
    public static int getN(int p, int q){
        return p * q;
    }
    
    public static int getPhiN(int p, int q){
        return (p - 1) * (q - 1);
    }
    
    public static int getE(int phiN){
        int e = 0;
        for (int i = 2; i < phiN; i++) {
            if(gcd(i, phiN) == 1){
                e = i;
                break;
            }
        }
        return e;
    }
    
    public static int gcd(int num1, int num2) {
        if (num2 == 0) {
            return num1;
        }
        return gcd(num2, num1 % num2);
    }
    
    public static int getD(int phiN, int e){
        int d = 0;
        for (int i = 0; i <= phiN; i++) {
            if(i * e % phiN == 1){
                d = i;
                break;
            }
        }
        return d;
    }
    
    public static long encryptDecrypt(long message, int key, int n){
        BigInteger m = BigInteger.valueOf(message);
        BigInteger k = BigInteger.valueOf(key);
        BigInteger mod = BigInteger.valueOf(n);
        BigInteger result = m.modPow(k, mod);
        return result.longValue();
    }
}
