import java.util.*;

public class CommaQuibblingTest {
    
    public static void main(String[] args) {
        String[] strs = {"ABC", "DEF", "G", "H"};
        
        for (int len = 0; len <= 4; len++) {
            System.out.println(commaQuibbling(strs, len));
        }
    }
    
    public static String commaQuibbling(String[] strs, int len) {
        if (len == 0) {
            return "{}";
        } else if (len == 1) {
            return "{" + strs[0].trim() + "}";
        }
        
        StringBuilder result = new StringBuilder();
        result.append(strs[len - 2].trim())
              .append(" and ")
              .append(strs[len - 1].trim())
              .append("}");
        
        if (len > 2) {
            for (int i = len - 3; i >= 0; i--) {
                result.insert(0, strs[i].trim() + ", ");
            }
        }
        
        result.insert(0, "{");
        return result.toString();
    }
}