public class Whencomp1 {
    private static int sOn1 = 1;
    private static String p190Prog = "           ";
    private static String p190Text = "                           ";
    private static String whenCompiled = "20060509153000";
    
    public static void main(String[] args) {
        if (sOn1 == 1) {
            p190Prog = "Y2600118 -";
            p190Text = "Version 003               ";
            
            StringBuilder sb = new StringBuilder();
            sb.append("Compile-Datum: ");
            sb.append(whenCompiled.substring(6, 8));
            sb.append(".");
            sb.append(whenCompiled.substring(4, 6));
            sb.append(".");
            sb.append(whenCompiled.substring(0, 4));
            sb.append(whenCompiled.substring(8, 10));
            sb.append(".");
            sb.append(whenCompiled.substring(10, 12));
            sb.append(".");
            sb.append(whenCompiled.substring(12, 14));
            
            p190Text = p190Text.substring(0, 29) + sb.toString() + p190Text.substring(sb.toString().length() + 29);
            
            System.out.println(p190Text);
            sOn1 = 0;
        }
    }
}