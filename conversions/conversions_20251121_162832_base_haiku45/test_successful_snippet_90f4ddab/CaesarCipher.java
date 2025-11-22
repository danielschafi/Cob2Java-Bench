import java.util.Scanner;

public class CaesarCipher {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter a message to encrypt: ");
        String plaintext = scanner.nextLine();
        
        System.out.print("Enter the amount to shift by: ");
        int offset = Integer.parseInt(scanner.nextLine());
        
        String encryptedStr = encrypt(offset, plaintext);
        System.out.println("Encrypted: " + encryptedStr);
        System.out.println("Decrypted: " + decrypt(offset, encryptedStr));
        
        scanner.close();
    }
    
    public static String encrypt(int offset, String str) {
        StringBuilder encryptedStr = new StringBuilder(str);
        
        for (int i = 0; i < str.length(); i++) {
            char c = encryptedStr.charAt(i);
            
            if (!Character.isLetter(c) || c == ' ') {
                continue;
            }
            
            int a;
            if (Character.isUpperCase(c)) {
                a = (int) 'A';
            } else {
                a = (int) 'a';
            }
            
            int charCode = (int) c;
            int newCharCode = ((charCode - a + offset) % 26) + a;
            encryptedStr.setCharAt(i, (char) newCharCode);
        }
        
        return encryptedStr.toString();
    }
    
    public static String decrypt(int offset, String str) {
        int decryptOffset = offset - 26;
        return encrypt(decryptOffset, str);
    }
}