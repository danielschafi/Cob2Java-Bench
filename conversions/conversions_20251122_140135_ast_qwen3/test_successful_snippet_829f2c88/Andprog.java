public class Andprog {
    private static String STRING1 = "A";

    public static void main(String[] args) {
        System.out.println(STRING1);
        
        if ("A".equals(STRING1) && "A".equals("A") || "B".equals("B")) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }

        if ("A".equals(STRING1) && "A".equals("A") || "B".equals("C")) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }

        if ("B".equals(STRING1) && "A".equals("A") || "B".equals("B")) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }

        if ("B".equals(STRING1) && "A".equals("A") || "B".equals("C")) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }

        if ("B".equals(STRING1) && "A".equals("A") || "B".equals("B") && "C".equals("C")) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }

        if ("B".equals(STRING1) && "A".equals("A") || "B".equals("C")) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }

        if ("B".equals(STRING1) && "A".equals("A") || "B".equals("B") && "C".equals("B")) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }
    }
}