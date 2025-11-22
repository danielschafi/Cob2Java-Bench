public class Cipher {
    private static String str = "   ";
    private static String og = "   ";
    private static String encrypted = "   ";
    private static int offset = 0;
    private static int counter = 0;
    private static int i = 0;
    private static String c = " ";

    public static void main(String[] args) {
        // Assign the original values and default key
        str = "HAL";
        og = str;
        offset = 3;

        // For comparison     
        System.out.println("Original ------> " + str);
        
        // Run Encrypt on the default
        encrypt();
        encrypted = str;
        
        // Using the encrypted version, decrypt
        decrypt();

        // To solve run through the subprogram 26 times    
        counter = 0;
        offset = 0;
        System.out.println("Solve:");
        while (counter < 26) {
            solve();
        }
    }

    public static void encrypt() {
        og = og.toUpperCase();
        
        // If the offset is 26, cycle back to 0    
        if (offset >= 26) {
            offset = offset % 26;
        }
        
        // This is basically a For loop
        for (i = 1; i <= og.length(); i++) {
            // Get rid of extra spaces
            if (og.charAt(i - 1) != ' ') {
                // For each char in OG(original) Add the offset
                c = String.valueOf(og.charAt(i - 1));
                if ((c.charAt(0) + offset) <= 'Z') {
                    str = str.substring(0, i - 1) + (char)(c.charAt(0) + offset) + str.substring(i);
                } else {
                    str = str.substring(0, i - 1) + (char)('A' + ((c.charAt(0) + offset) - 1) - 'Z') + str.substring(i);
                }
            }
        }
        
        // Show the User the diff
        System.out.println("Encrypted " + og + " -> " + str + " with Key:" + offset);
    }

    public static void decrypt() {
        str = str.toUpperCase();
        
        if (offset >= 26) {
            offset = offset % 26;
        }
        
        for (i = 1; i <= str.length(); i++) {
            if (str.charAt(i - 1) != ' ') {
                c = String.valueOf(str.charAt(i - 1));
                if ((c.charAt(0) - offset) >= 'A') {
                    str = str.substring(0, i - 1) + (char)(c.charAt(0) - offset) + str.substring(i);
                } else {
                    str = str.substring(0, i - 1) + (char)('Z' - ((offset - 1) - (c.charAt(0) - 'A'))) + str.substring(i);
                }
            }
        }
        
        System.out.println("Decrypted " + encrypted + " -> " + str + " with Key:" + offset);
    }

    public static void solve() {
        counter++;
        offset++;
        encrypt();
    }
}