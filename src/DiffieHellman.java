/*
    Name: Amr Mahmoud
    ID: 2142598
    Course: CPIT-425
    Lab: 5
*/

import java.util.Scanner;

public class DiffieHellman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the values of P G: ");
        int p = scanner.nextInt();
        int g = scanner.nextInt();
        
        int privateA = generatePrivateKey();
        int privateB = generatePrivateKey();
        
        int publicA = calculatePublicKeyAndSharedSecret(g, privateA, p);
        int publicB = calculatePublicKeyAndSharedSecret(g, privateB, p);
        
        int sharedSecretA = calculatePublicKeyAndSharedSecret(publicB, privateA, p);
        int sharedSecretB = calculatePublicKeyAndSharedSecret(publicA, privateB, p);
        
        System.out.println("\nPrivate key of A: " + privateA);
        System.out.println("Public key of A: " + publicA);
        
        System.out.println("\nPrivate key of B: " + privateB);
        System.out.println("Public key of B: " + publicB);
        
        System.out.println("\nShared Secret of A: " + sharedSecretA);
        System.out.println("Shared Secret of B: " + sharedSecretB);
    }
    
    public static int generatePrivateKey(){
        return (int) (Math.random() * 10) + 1;
    }
    
    public static int calculatePublicKeyAndSharedSecret(int num1, int num2, int num3){
        return (int) (Math.pow(num1, num2) % num3);
    }
}
