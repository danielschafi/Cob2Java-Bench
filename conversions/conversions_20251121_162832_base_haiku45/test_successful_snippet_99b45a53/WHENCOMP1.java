public class WHENCOMP1 {
    private static int S_ON1 = 1;
    private static String P190_PROG = "";
    private static String P190_TEXT = "";

    public static void main(String[] args) {
        if (S_ON1 == 1) {
            P190_PROG = "Y2600118 -";
            P190_TEXT = "Version 003";
            
            String whenCompiled = getWhenCompiled();
            
            P190_TEXT = "Compile-Datum: " + whenCompiled;
            
            System.out.println(P190_TEXT);
            S_ON1 = 0;
        }
    }

    private static String getWhenCompiled() {
        String timestamp = String.format("%1$tddMMyyyy%1$tHH%1$tmss", System.currentTimeMillis());
        
        StringBuilder result = new StringBuilder("Version 003");
        result.setLength(29);
        result.append("Compile-Datum: ");
        
        String day = timestamp.substring(0, 2);
        String month = timestamp.substring(2, 4);
        String year = timestamp.substring(4, 8);
        String hour = timestamp.substring(8, 10);
        String minute = timestamp.substring(10, 12);
        String second = timestamp.substring(12, 14);
        
        result.append(day).append(".").append(month).append(".").append(year)
              .append(hour).append(".").append(minute).append(".").append(second);
        
        return result.toString();
    }
}