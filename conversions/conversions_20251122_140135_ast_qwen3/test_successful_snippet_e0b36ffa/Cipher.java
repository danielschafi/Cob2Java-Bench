public class Cipher {
    private static String str = "   ";
    private static String og = "   ";
    private static String encrypted = "   ";
    private static int offset = 0;
    private static int Counter = 0;
    private static int i = 0;
    private static String c = " ";

    public static void main(String[] args) {
        // Assign the original values and default key
        str = "HAL";
        og = str;
        offset = 3;

        // For comparison     
        System.out.println("Original ------> " + str);
        // Run Encrypt on the defualt
        Encrypt();
        encrypted = str;
        // Using the encrypted version, decrypt
        Decrypt();

        // To solve run through the subprogram 26 times    
        Counter = 0;
        offset = 0;
        System.out.println("Solve:");
        Solve();
        Solve();
        Solve();
        Solve();
        Solve();
        Solve();
        Solve();
        Solve();
        Solve();
        Solve();
        Solve();
        Solve();
        Solve();
        Solve();
        Solve();
        Solve();
        Solve();
        Solve();
        Solve();
        Solve();
        Solve();
        Solve();
        Solve();
        Solve();
        Solve();
        Solve();
        Solve();
    }

    public static void Encrypt() {
        og = og.toUpperCase();
        // If the offset is 26, cycle back to 0    
        if (offset >= 26) {
            offset = offset % 26;
        }
        // This is basically a For loop I had to read the manual to find this....    
        for (i = 1; i <= og.length(); i++) {
            //    Get rid of extra spaces
            if (og.charAt(i - 1) != ' ') {
                //  For each char in OG(original) Add the offset
                c = String.valueOf(og.charAt(i - 1));
                if ((int) c.charAt(0) + offset <= (int) 'Z') {
                    str = str.substring(0, i - 1) + (char) ((int) c.charAt(0) + offset) + str.substring(i);
                } else {
                    str = str.substring(0, i - 1) + (char) ((int) 'A' + ((int) c.charAt(0) + offset) - 1 - (int) 'Z') + str.substring(i);
                }
            }
        }
        // Show the User the diff
        System.out.println("Encrypted " + og + " -> " + str + " with Key:" + offset);
    }

    public static void Decrypt() {
        str = str.toUpperCase();

        if (offset >= 26) {
            offset = offset % 26;
        }

        for (i = 1; i <= str.length(); i++) {
            if (str.charAt(i - 1) != ' ') {
                c = String.valueOf(str.charAt(i - 1));
                if ((int) c.charAt(0) - offset >= (int) 'A') {
                    str = str.substring(0, i - 1) + (char) ((int) c.charAt(0) - offset) + str.substring(i);
                } else {
                    str = str.substring(0, i - 1) + (char) ((int) 'Z' - ((offset - 1) - ((int) c.charAt(0) - (int) 'A'))) + str.substring(i);
                }
            }
        }
        System.out.println("Decrypted " + encrypted + " -> " + str + " with Key:" + offset);
    }

    public static void Solve() {
        Counter++;
        offset++;
        Encrypt();
    }
}