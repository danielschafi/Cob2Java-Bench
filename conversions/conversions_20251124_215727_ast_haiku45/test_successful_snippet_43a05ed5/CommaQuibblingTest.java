public class CommaQuibblingTest {
    static class StrsArea {
        int strsLen;
        String[] strs;
        
        StrsArea() {
            this.strsLen = 0;
            this.strs = new String[9];
            for (int i = 0; i < 9; i++) {
                this.strs[i] = "";
            }
        }
    }
    
    public static void main(String[] args) {
        StrsArea strsArea = new StrsArea();
        strsArea.strs[0] = "ABC";
        strsArea.strs[1] = "DEF";
        strsArea.strs[2] = "G";
        strsArea.strs[3] = "H";
        
        for (int strsLen = 0; strsLen <= 4; strsLen++) {
            strsArea.strsLen = strsLen;
            System.out.println(commaQuibbling(strsArea));
        }
    }
    
    public static String commaQuibbling(StrsArea strsArea) {
        int strsLen = strsArea.strsLen;
        String[] strs = strsArea.strs;
        String str;
        
        if (strsLen == 0) {
            return "{}";
        }
        
        if (strsLen == 1) {
            return "{" + trim(strs[0]) + "}";
        }
        
        str = trim(strs[strsLen - 2]) + " and " + trim(strs[strsLen - 1]) + "}";
        
        if (strsLen > 2) {
            int numExtraWords = strsLen - 2;
            for (int i = numExtraWords - 1; i >= 0; i--) {
                str = trim(strs[i]) + ", " + str;
            }
        }
        
        str = "{" + str;
        return str;
    }
    
    private static String trim(String s) {
        return s.trim();
    }
}