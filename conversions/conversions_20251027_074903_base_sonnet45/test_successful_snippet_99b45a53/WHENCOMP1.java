import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WHENCOMP1 {
    
    private static class WorkingStorage {
        int S_ON1 = 1;
        
        String P190_PROG = "";
        String P190_TEXT = "";
    }
    
    public static void main(String[] args) {
        WHENCOMP1 program = new WHENCOMP1();
        program.execute();
    }
    
    public void execute() {
        WorkingStorage ws = new WorkingStorage();
        
        if (ws.S_ON1 == 1) {
            ws.P190_PROG = padRight("Y2600118 -", 11);
            ws.P190_TEXT = padRight("Version 003", 120);
            
            String whenCompiled = getWhenCompiled();
            
            String day = whenCompiled.substring(6, 8);
            String month = whenCompiled.substring(4, 6);
            String year = whenCompiled.substring(0, 4);
            String hour = whenCompiled.substring(8, 10);
            String minute = whenCompiled.substring(10, 12);
            String second = whenCompiled.substring(12, 14);
            
            StringBuilder text = new StringBuilder(padRight(ws.P190_TEXT, 120));
            
            replaceInString(text, 29, "Compile-Datum: ");
            replaceInString(text, 44, day);
            replaceInString(text, 46, ".");
            replaceInString(text, 47, month);
            replaceInString(text, 49, ".");
            replaceInString(text, 50, year);
            replaceInString(text, 56, hour);
            replaceInString(text, 58, ".");
            replaceInString(text, 59, minute);
            replaceInString(text, 61, ".");
            replaceInString(text, 62, second);
            
            ws.P190_TEXT = text.toString();
            
            System.out.println(ws.P190_TEXT);
            
            ws.S_ON1 = 0;
        }
    }
    
    private String getWhenCompiled() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return now.format(formatter);
    }
    
    private void replaceInString(StringBuilder sb, int position, String value) {
        for (int i = 0; i < value.length() && (position + i) < sb.length(); i++) {
            sb.setCharAt(position + i, value.charAt(i));
        }
    }
    
    private String padRight(String s, int length) {
        if (s.length() >= length) {
            return s.substring(0, length);
        }
        StringBuilder sb = new StringBuilder(s);
        while (sb.length() < length) {
            sb.append(' ');
        }
        return sb.toString();
    }
}