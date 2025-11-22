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
        StringBuilder encryptedStr = new StringBuilder(str);
        for (int i = 0; i < encryptedStr.length(); i++) {
            char ch = encryptedStr.charAt(i);
            if (!Character.isAlphabetic(ch) || ch == ' ') {
                continue;
            }

            int a = Character.isUpperCase(ch) ? 'A' : 'a';
            encryptedStr.setCharAt(i, (char) ((ch - a + offset) % 26 + a));
        }
        return encryptedStr.toString();
    }

    public static String decrypt(int offset, String str) {
        int decryptOffset = (26 - offset) % 26;
        return encrypt(decryptOffset, str);
    }
}