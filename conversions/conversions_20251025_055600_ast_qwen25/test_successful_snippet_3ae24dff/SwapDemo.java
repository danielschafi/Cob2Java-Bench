import java.util.Scanner;

public class SwapDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String val1 = new String(new char[72]).replace('\0', ' ');
        String val2 = new String(new char[72]).replace('\0', ' ');

        System.out.print("Enter a Value: ");
        val1 = scanner.nextLine().trim();
        System.out.print("Enter another Value: ");
        val2 = scanner.nextLine().trim();
        System.out.println();

        System.out.println("First value: " + val1.trim());
        System.out.println("Second value: " + val2.trim());

        swap(val1, val2);

        System.out.println();
        System.out.println("After SWAP");
        System.out.println();
        System.out.println("First value: " + val1.trim());
        System.out.println("Second value: " + val2.trim());

        scanner.close();
    }

    public static void swap(String[] field1, String[] field2) {
        String temp = field1[0];
        field1[0] = field2[0];
        field2[0] = temp;
    }

    public static void swap(String field1, String field2) {
        String[] field1Array = {field1};
        String[] field2Array = {field2};
        swap(field1Array, field2Array);
    }
}