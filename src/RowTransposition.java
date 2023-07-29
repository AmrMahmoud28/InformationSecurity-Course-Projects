/*
    Name: Amr Mahmoud
    ID: 2142598
    Course: CPIT-425
    Lab: 3
*/

public class RowTransposition {
    public static void main(String[] args) {
        String plaintext = "WE ARE DISCOVERED SAVE YOURSELF ABC";
        String key = "165234";
        
        System.out.println("Encryption:");
        System.out.println("1 6 5 2 3 4\n-----------");
        String encryption = encrypt(plaintext, key);
        System.out.println("\nThe Ciphertext is:\n" + encryption);
        
        System.out.println("---------------------------------------------------------------");
        
        System.out.println("Decryption:");
        System.out.println("1 2 3 4 5 6\n-----------");
        System.out.println("\nThe Plaintext after decryption is:\n" + decrypt(encryption.replaceAll(" ", ""), key));
    }
    
    public static String encrypt(String plaintext, String key){
        char[][] plaintextArray = toCharArray(plaintext.replaceAll(" ", ""), key);
        String ciphertext = "";
        
        for (int i = 0; i < plaintextArray[0].length; i++) {
            for (int j = 0; j < plaintextArray.length; j++) {
                ciphertext += plaintextArray[j][i] + " ";
            }
        }
        
        return ciphertext;
    }
    
    public static String decrypt(String ciphertext, String key){
        char[][] ciphertextArray = new char[ciphertext.length() / key.length()][key.length()];
        String plaintext = "";
        
        int letterCount = 0;
        
        for (int i = 0; i < key.length(); i++) {
            for (int j = 0; j < ciphertextArray.length; j++) {
                ciphertextArray[j][i] = ciphertext.charAt(letterCount);
                letterCount++;
            }
        }
        
        
        for (int i = 0; i < ciphertextArray.length; i++) {
            for (int j = 0; j < ciphertextArray[i].length; j++) {
                plaintext += ciphertextArray[i][Integer.parseInt("" + key.charAt(j)) - 1] + " ";
                System.out.print(ciphertextArray[i][j] + " ");
            }
            System.out.println("");
        }
        
        return plaintext;
    }
    
    public static char[][] toCharArray(String message, String key){
        char[][] messageArray = new char[message.length() / key.length()][key.length()];
        
        int letterCount = 0;
        
        for (int i = 0; i < messageArray.length; i++) {
            for (int j = 0; j < key.length(); j++) {
                messageArray[i][(Integer.parseInt("" + key.charAt(j)) - 1)] = message.charAt(letterCount);
                System.out.print(messageArray[i][(Integer.parseInt("" + key.charAt(j)) - 1)] + " ");
                letterCount++;
            }
            System.out.println("");
        }
        
        return messageArray;
    }
}
