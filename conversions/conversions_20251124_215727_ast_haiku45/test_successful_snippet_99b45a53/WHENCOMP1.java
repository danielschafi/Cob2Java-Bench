import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WHENCOMP1 {
    private static int sOn1 = 1;
    
    private static class V0P190 {
        private char[] p190Prog = new char[11];
        private char[] p190Text = new char[120];
        private char p190Kenn;
        
        V0P190() {
            for (int i = 0; i < p190Prog.length; i++) {
                p190Prog[i] = ' ';
            }
            for (int i = 0; i < p190Text.length; i++) {
                p190Text[i] = ' ';
            }
        }
        
        void moveToP190Prog(String value) {
            char[] src = value.toCharArray();
            for (int i = 0; i < Math.min(src.length, p190Prog.length); i++) {
                p190Prog[i] = src[i];
            }
        }
        
        void moveToP190Text(String value) {
            char[] src = value.toCharArray();
            for (int i = 0; i < Math.min(src.length, p190Text.length); i++) {
                p190Text[i] = src[i];
            }
        }
        
        void moveToP190TextAt(String value, int startPos, int length) {
            char[] src = value.toCharArray();
            int endPos = Math.min(startPos + length, p190Text.length);
            int srcIdx = 0;
            for (int i = startPos; i < endPos && srcIdx < src.length; i++) {
                p190Text[i] = src[srcIdx++];
            }
        }
        
        String getP190Text() {
            return new String(p190Text).trim();
        }
    }
    
    public static void main(String[] args) {
        V0P190 v0p190 = new V0P190();
        
        if (sOn1 == 1) {
            v0p190.moveToP190Prog("Y2600118 -");
            v0p190.moveToP190Text("Version 003");
            
            String whenCompiled = getWhenCompiled();
            
            v0p190.moveToP190TextAt("Compile-Datum: ", 29, 15);
            v0p190.moveToP190TextAt(whenCompiled.substring(6, 8), 44, 2);
            v0p190.moveToP190TextAt(".", 46, 1);
            v0p190.moveToP190TextAt(whenCompiled.substring(4, 6), 47, 2);
            v0p190.moveToP190TextAt(".", 49, 1);
            v0p190.moveToP190TextAt(whenCompiled.substring(0, 4), 50, 4);
            v0p190.moveToP190TextAt(whenCompiled.substring(8, 10), 56, 2);
            v0p190.moveToP190TextAt(".", 58, 1);
            v0p190.moveToP190TextAt(whenCompiled.substring(10, 12), 59, 2);
            v0p190.moveToP190TextAt(".", 61, 1);
            v0p190.moveToP190TextAt(whenCompiled.substring(12, 14), 62, 2);
            
            System.out.println(v0p190.getP190Text());
            sOn1 = 0;
        }
    }
    
    private static String getWhenCompiled() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return now.format(formatter);
    }
}