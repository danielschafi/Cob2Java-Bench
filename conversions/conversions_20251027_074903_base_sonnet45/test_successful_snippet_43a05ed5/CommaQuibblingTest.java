import java.util.Arrays;

public class CommaQuibblingTest {
    public static void main(String[] args) {
        String[] strs = new String[9];
        strs[0] = "ABC";
        strs[1] = "DEF";
        strs[2] = "G";
        strs[3] = "H";
        
        for (int strsLen = 0; strsLen <= 4; strsLen++) {
            System.out.println(commaQuibbling(strs, strsLen));
        }
    }
    
    public static String commaQuibbling(String[] strs, int strsLen) {
        if (strsLen == 0) {
            return "{}";
        }
        
        if (strsLen == 1) {
            return "{" + strs[0].trim() + "}";
        }
        
        String str = strs[strsLen - 2].trim() + " and " + strs[strsLen - 1].trim() + "}";
        
        if (strsLen > 2) {
            int numExtraWords = strsLen - 2;
            for (int i = numExtraWords - 1; i >= 0; i--) {
                str = strs[i].trim() + ", " + str;
            }
        }
        
        str = "{" + str;
        
        return str;
    }
}