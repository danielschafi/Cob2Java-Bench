import java.util.Arrays;

public class InspectExample {
    public static void main(String[] args) {
        String randomString = "123ABC";

        System.out.println(randomString);

        inspect1(randomString);
        System.out.println("inspect 1 : " + randomString);

        randomString = "123123123";

        inspect2(randomString);
        System.out.println("inspect 2 : " + randomString);

        inspect3(randomString);
        System.out.println("inspect 3 : " + randomString);

        inspect4(randomString);
        System.out.println("inspect 4 : " + randomString);

        inspect5(randomString);
        System.out.println("inspect 5 : " + randomString);

        inspect6(randomString);
        System.out.println("inspect 6 : " + randomString);
    }

    public static void inspect1(StringBuilder randomString) {
        for (int i = 0; i < randomString.length(); i++) {
            randomString.setCharAt(i, '*');
        }
    }

    public static void inspect2(StringBuilder randomString) {
        int index = randomString.indexOf("3");
        if (index != -1) {
            for (int i = 0; i < index; i++) {
                randomString.setCharAt(i, '3');
            }
        }
    }

    public static void inspect3(StringBuilder randomString) {
        int index = randomString.indexOf("33");
        if (index != -1) {
            for (int i = index + 2; i < randomString.length(); i++) {
                randomString.setCharAt(i, '4');
            }
        }
    }

    public static void inspect4(StringBuilder randomString) {
        int initialIndex = randomString.indexOf("4");
        if (initialIndex != -1) {
            int firstIndex = randomString.indexOf("4", initialIndex + 1);
            if (firstIndex != -1) {
                randomString.setCharAt(firstIndex, '3');
            }
        }
    }

    public static void inspect5(StringBuilder randomString) {
        while (randomString.length() > 0 && randomString.charAt(0) == '3') {
            randomString.setCharAt(0, '4');
        }
    }

    public static void inspect6(StringBuilder randomString) {
        for (int i = 0; i < randomString.length(); i++) {
            if (randomString.charAt(i) == '4') {
                randomString.setCharAt(i, '2');
            }
        }
        for (int i = 0; i < randomString.length(); i++) {
            if (randomString.charAt(i) == '2') {
                randomString.setCharAt(i, '3');
            }
        }
    }
}