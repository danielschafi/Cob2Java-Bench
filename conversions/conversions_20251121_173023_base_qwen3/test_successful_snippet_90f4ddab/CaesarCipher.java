import java.lang.Math;

public class CaesarCipher {
    
    public static String encrypt(int offset, String str) {
        char[] encryptedStr = str.toCharArray();
        int i;
        int a;
        
        for (i = 0; i < encryptedStr.length; i++) {
            if (!Character.isLetter(encryptedStr[i]) || encryptedStr[i] == ' ') {
                continue;
            }
            
            if (Character.isUpperCase(encryptedStr[i])) {
                a = (int)'A';
            } else {
                a = (int)'a';
            }
            
            int charValue = (int)encryptedStr[i];
            int newValue = (charValue - a + offset) % 26 + a;
            encryptedStr[i] = (char)newValue;
        }
        
        return new String(encryptedStr);
    }
    
    public static String decrypt(int offset, String str) {
        int decryptOffset = 26 - offset;
        return encrypt(decryptOffset, str);
    }
    
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        
        System.out.print("Enter a message to encrypt: ");
        String plaintext = scanner.nextLine();
        
        System.out.print("Enter the amount to shift by: ");
        int offset = scanner.nextInt();
        
        String encryptedStr = encrypt(offset, plaintext);
        System.out.println("Encrypted: " + encryptedStr);
        System.out.println("Decrypted: " + decrypt(offset, encryptedStr));
    }
}