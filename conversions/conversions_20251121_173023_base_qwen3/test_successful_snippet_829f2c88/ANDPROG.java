public class ANDPROG {
    public static void main(String[] args) {
        char STRING1 = 'A';
        
        System.out.println(STRING1);
        
        if (("A".equals(String.valueOf(STRING1)) && "A".equals("A")) || "B".equals("B")) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }
        
        if (("A".equals(String.valueOf(STRING1)) && "A".equals("A")) || "B".equals("C")) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }
        
        if (("B".equals(String.valueOf(STRING1)) && "A".equals("A")) || "B".equals("B")) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }
        
        if (("B".equals(String.valueOf(STRING1)) && "A".equals("A")) || "B".equals("C")) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }
        
        if (("B".equals(String.valueOf(STRING1)) && "A".equals("A")) || ("B".equals("B") && "C".equals("C"))) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }
        
        if (("B".equals(String.valueOf(STRING1)) && "A".equals("A")) || "B".equals("C")) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }
        
        if (("B".equals(String.valueOf(STRING1)) && "A".equals("A")) || ("B".equals("B") && "C".equals("B"))) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }
    }
}