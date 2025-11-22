import java.util.ArrayList;
import java.util.List;

public class CommaQuibblingTest {
    
    static class StrsArea {
        int strsLen;
        List<String> strs;
        
        StrsArea() {
            this.strsLen = 0;
            this.strs = new ArrayList<>();
        }
    }
    
    public static void main(String[] args) {
        StrsArea strsArea = new StrsArea();
        
        strsArea.strs.add("ABC");
        strsArea.strs.add("DEF");
        strsArea.strs.add("G");
        strsArea.strs.add("H");
        
        for (int strsLen = 0; strsLen <= 4; strsLen++) {
            strsArea.strsLen = strsLen;
            System.out.println(commaQuibbling(strsArea));
        }
    }
    
    static String commaQuibbling(StrsArea strsArea) {
        int strsLen = strsArea.strsLen;
        List<String> strs = strsArea.strs;
        String str;
        
        if (strsLen == 0) {
            return "{}";
        }
        
        if (strsLen == 1) {
            return "{" + strs.get(0).trim() + "}";
        }
        
        str = strs.get(strsLen - 2).trim() + " and " + strs.get(strsLen - 1).trim() + "}";
        
        if (strsLen > 2) {
            int numExtraWords = strsLen - 2;
            for (int i = numExtraWords - 1; i >= 0; i--) {
                str = strs.get(i).trim() + ", " + str;
            }
        }
        
        str = "{" + str;
        return str;
    }
}