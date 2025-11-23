public class Whencomp1 {
    private static int sOn1 = 1;
    private static String p190Prog = "";
    private static String p190Text = "";
    private static String whenCompiled = "20060509152233";
    
    public static void main(String[] args) {
        if (sOn1 == 1) {
            p190Prog = "Y2600118 -";
            p190Text = "Version 003";
            
            // Compile-Datum aufbereiten
            p190Text = p190Text + "Compile-Datum: ";
            
            // Extract date components from WHEN-COMPILED
            String day = whenCompiled.substring(6, 8);
            String month = whenCompiled.substring(4, 6);
            String year = whenCompiled.substring(0, 4);
            String hour = whenCompiled.substring(8, 10);
            String minute = whenCompiled.substring(10, 12);
            String second = whenCompiled.substring(12, 14);
            
            p190Text = p190Text + day + "." + month + "." + year + " " + hour + ":" + minute + ":" + second;
            
            System.out.println(p190Text);
            sOn1 = 0;
        }
    }
}