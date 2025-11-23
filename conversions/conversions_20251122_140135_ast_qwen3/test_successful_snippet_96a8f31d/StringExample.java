public class StringExample {
    private static String nom = new String(new char[25]);
    private static String prenom = new String(new char[25]);
    private static String tYear = new String(new char[4]);
    private static String tMonth = new String(new char[2]);
    private static String tDay = new String(new char[2]);
    private static double nonInteger = 3.14159;
    private static String piEdit = new String(new char[50]);
    private static int signedInteger = 42;
    private static int beginString = 13;
    private static String entete = new String(new char[132]);
    private static boolean accepted = false;

    public static void main(String[] args) {
        nom = "Doe";
        prenom = "John";
        
        accepted = true;
        
        if (accepted) {
            // Simulate accept date from system
            java.time.LocalDate currentDate = java.time.LocalDate.now();
            tYear = String.format("%04d", currentDate.getYear());
            tMonth = String.format("%02d", currentDate.getMonthValue());
            tDay = String.format("%02d", currentDate.getDayOfMonth());
        } else {
            // Use function current-date equivalent
            java.time.LocalDateTime now = java.time.LocalDateTime.now();
            tYear = String.valueOf(now.getYear());
            tMonth = String.format("%02d", now.getMonthValue());
            tDay = String.format("%02d", now.getDayOfMonth());
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(prenom);
        sb.append(" ");
        sb.append(nom);
        sb.append(" on ");
        sb.append(tDay);
        sb.append("/");
        sb.append(tMonth);
        sb.append("/");
        sb.append(tYear);
        
        entete = sb.toString();
        
        System.out.println();
        System.out.println(entete);
        
        // Test non-integer string operation
        piEdit = "PI is : " + String.format("%09d", (int)(nonInteger * 100000));
        System.out.println(piEdit);
        
        // Test signed integer string operation
        piEdit = "Answer to life is : " + signedInteger;
        System.out.println(piEdit);
    }
}