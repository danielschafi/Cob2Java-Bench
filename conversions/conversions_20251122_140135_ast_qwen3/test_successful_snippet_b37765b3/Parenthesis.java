public class Parenthesis {
    private static String STRING1 = "A";

    public static void main(String[] args) {
        System.out.println(STRING1);
        
        if ("A".equals(STRING1)) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }

        if ("B".equals(STRING1)) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }

        if (("A".equals(STRING1)) && ("A".equals("A"))) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }

        if (("A".equals(STRING1)) || ("B".equals("A"))) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }

        if (("A".equals(STRING1)) || ("B".equals("A")) && ("A".equals("B"))) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }

        if (("A".equals("A")) || ("B".equals("A")) && ("A".equals("B"))) {
            System.out.println("TRUE");
        } else {
            System.out.println("FALSE");
        }
    }
}