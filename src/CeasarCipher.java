/*
    Name: Amr Mahmoud
    ID: 2142598
    Course: CPIT-425
    Lab: 2
*/

public class CeasarCipher {
    public static void main(String[] args) {
        int key = 3;
        String plaintext = "Ahmed";
        
        System.out.println("Encryption: " + encrypt(plaintext.toUpperCase(), key));
        System.out.println("Decryption: " + decrypt(encrypt(plaintext.toUpperCase(), key), key));
    }
    
    public static String encrypt(String plaintext, int k){
        int[] plaintextIndexs = getIndexs(plaintext);
        int [] ciphertextIndexs = new int[plaintextIndexs.length];
        
        for (int i = 0; i < plaintextIndexs.length; i++) {
            ciphertextIndexs[i] = (plaintextIndexs[i] + k) % 26;
        }
        
        String ciphertext = toChar(ciphertextIndexs);
        return ciphertext;
    }
    
    public static String decrypt(String ciphertext, int k){
        int[] ciphertextIndexs = getIndexs(ciphertext);
        int [] plaintextIndexs = new int[ciphertextIndexs.length];
        
        for (int i = 0; i < ciphertextIndexs.length; i++) {
            plaintextIndexs[i] = (ciphertextIndexs[i] - k) % 26;
        }
        
        String plaintext = toChar(plaintextIndexs);
        return plaintext;
    }
    
    public static int[] getIndexs(String message){
        char[] upperCaseAlphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        int[] indexs = new int[message.length()];
        
        for (int i = 0; i < message.length(); i++) {
            for (int j = 0; j < upperCaseAlphabet.length; j++) {
                if(message.charAt(i) == upperCaseAlphabet[j]){
                    indexs[i] = j;
                    break;
                }
            }
        }
        
        return indexs;
    }
    
    public static String toChar(int[] indexs){
        char[] upperCaseAlphabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        String message = "";
        
        for (int i = 0; i < indexs.length; i++) {
            for (int j = 0; j < upperCaseAlphabet.length; j++) {
                if(indexs[i] == j){
                    message += upperCaseAlphabet[j];
                    break;
                }
            }
        }
        
        return message;
    }
}
