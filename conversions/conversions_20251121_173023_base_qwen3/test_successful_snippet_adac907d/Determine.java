import java.util.ArrayList;
import java.util.List;

public class Determine {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    
    public static void main(String[] args) {
        List<Character> lowerCaseLetters = new ArrayList<>();
        List<Character> upperCaseLetters = new ArrayList<>();
        
        for (int i = 0; i < CHARACTERS.length(); i++) {
            char ch = CHARACTERS.charAt(i);
            if (Character.isLowerCase(ch)) {
                lowerCaseLetters.add(ch);
            } else if (Character.isUpperCase(ch)) {
                upperCaseLetters.add(ch);
            }
        }
        
        if (lowerCaseLetters.isEmpty()) {
            System.err.println("no lower case letters detected");
        } else {
            StringBuilder lowerStr = new StringBuilder();
            for (char c : lowerCaseLetters) {
                lowerStr.append(c);
            }
            System.out.println(lowerStr.toString());
        }
        
        if (upperCaseLetters.isEmpty()) {
            System.err.println("no upper case letters detected");
        } else {
            StringBuilder upperStr = new StringBuilder();
            for (char c : upperCaseLetters) {
                upperStr.append(c);
            }
            System.out.println(upperStr.toString());
        }
    }
}