/*
    Name: Amr Mahmoud
    ID: 2142598
    Course: CPIT-425
    **Project**
*/

import java.math.BigInteger;
import java.util.Scanner;

public class CustomRSA {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a prime number for p q: ");
        int p = scanner.nextInt();
        int q = scanner.nextInt();
        
        System.out.print("Enter your message: ");
        scanner.nextLine();
        String m = scanner.nextLine();
        int[] plaintextIndexs = fromCharToInt(m.toUpperCase());
        
        int n = getN(p, q);
        int phiN = getPhiN(p, q);
        
        int publicKey = getE(phiN);
        int privateKey = getD(phiN, publicKey);
        
        String ciphertext = encrypt(plaintextIndexs, publicKey, n);
        String plaintext = decrypt(ciphertext, privateKey, n);
        
        System.out.println("\nN = " + n);
        System.out.println("Phi(N) = " + phiN);
        System.out.println("Public Key: {" + publicKey + ", " + n + "}");
        System.out.println("Private Key: {" + privateKey + ", " + n + "}");

        System.out.println("\nCiphertext: " + ciphertext.replaceAll(" ", ""));
        System.out.println("Plaintext: " + plaintext);
    }
    
    public static int[] fromCharToInt(String m){
        char[] upperCaseAlphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M', ' ', 'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        int[] indexs = new int[m.length()];
        
        for (int i = 0; i < m.length(); i++) {
            for (int j = 0; j < upperCaseAlphabet.length; j++) {
                if(m.charAt(i) == upperCaseAlphabet[j]){
                    indexs[i] = j;
                    break;
                }
            }
        }
        
        return indexs;
    }
    
    public static String fromIntToChar(int[] plaintextIndexs){
        char[] upperCaseAlphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M', ' ', 'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        String plaintext = "";
        
        for (int i = 0; i < plaintextIndexs.length; i++) {
            for (int j = 0; j < upperCaseAlphabet.length; j++) {
                if(plaintextIndexs[i] == j){
                    plaintext += upperCaseAlphabet[j];
                    break;
                }
            }
        }
        
        return plaintext;
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
    
    public static String encrypt(int[] plaintextIndexs, int key, int n){
        String ciphertext = "";
        BigInteger m, k, mod, result;
        
        for (int i = 0; i < plaintextIndexs.length; i++) {
            m = BigInteger.valueOf(plaintextIndexs[i]);
            k = BigInteger.valueOf(key);
            mod = BigInteger.valueOf(n);
            result = m.modPow(k, mod);
            
            ciphertext += result.intValue() + " ";
        }
        
        return ciphertext;
    }
    
    public static String decrypt(String ciphertext, int key, int n){
        String[] ciphertextArray = ciphertext.split(" ");
        int[] plaintextIndexs = new int[ciphertextArray.length];
        String plaintext;
        BigInteger m, k, mod, result;
        
        for (int i = 0; i < ciphertextArray.length; i++) {
            m = BigInteger.valueOf(Integer.parseInt(ciphertextArray[i]));
            k = BigInteger.valueOf(key);
            mod = BigInteger.valueOf(n);
            result = m.modPow(k, mod);
            
            plaintextIndexs[i] = result.intValue();
        }
        
        plaintext = fromIntToChar(plaintextIndexs);
        
        return plaintext;
    }
}
