import java.util.Scanner;

public class CaesarCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a message to encrypt: ");
        String plaintext = scanner.nextLine();
        
        System.out.print("Enter the amount to shift by: ");
        int offset = scanner.nextInt();
        
        String encryptedStr = encrypt(offset, plaintext);
        System.out.println("Encrypted: " + encryptedStr);
        System.out.println("Decrypted: " + decrypt(offset, encryptedStr));
    }
    
    public static String encrypt(int offset, String str) {
        char[] encryptedStr = str.toCharArray();
        
        for (int i = 0; i < encryptedStr.length; i++) {
            char c = encryptedStr[i];
            
            if (!Character.isLetter(c) || c == ' ') {
                continue;
            }
            
            char base = Character.isUpperCase(c) ? 'A' : 'a';
            int a = (int) base;
            int shifted = (c - a + offset) % 26;
            encryptedStr[i] = (char) (shifted + a);
        }
        
        return new String(encryptedStr);
    }
    
    public static String decrypt(int offset, String str) {
        int decryptOffset = 26 - offset;
        return encrypt(decryptOffset, str);
    }
}