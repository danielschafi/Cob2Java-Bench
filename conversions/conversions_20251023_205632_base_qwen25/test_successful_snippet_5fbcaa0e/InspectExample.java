import java.util.Arrays;

public class InspectExample {
    public static void main(String[] args) {
        char[] randomString = "123ABC".toCharArray();

        System.out.println(new String(randomString));

        inspect1(randomString);
        System.out.println("inspect 1 : " + new String(randomString));

        randomString = "123123123".toCharArray();

        inspect2(randomString);
        System.out.println("inspect 2 : " + new String(randomString));

        inspect3(randomString);
        System.out.println("inspect 3 : " + new String(randomString));

        inspect4(randomString);
        System.out.println("inspect 4 : " + new String(randomString));

        inspect5(randomString);
        System.out.println("inspect 5 : " + new String(randomString));

        inspect6(randomString);
        System.out.println("inspect 6 : " + new String(randomString));
    }

    public static void inspect1(char[] str) {
        Arrays.fill(str, '*');
    }

    public static void inspect2(char[] str) {
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '3') {
                for (int j = 0; j < i; j++) {
                    str[j] = '3';
                }
                break;
            }
        }
    }

    public static void inspect3(char[] str) {
        boolean found = false;
        for (int i = 0; i < str.length - 1; i++) {
            if (str[i] == '3' && str[i + 1] == '3') {
                found = true;
            }
            if (found) {
                str[i] = '4';
            }
        }
        if (found) {
            str[str.length - 1] = '4';
        }
    }

    public static void inspect4(char[] str) {
        boolean foundFirst4 = false;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '4') {
                if (foundFirst4) {
                    str[i] = '3';
                    break;
                }
                foundFirst4 = true;
            }
        }
    }

    public static void inspect5(char[] str) {
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '3') {
                str[i] = '4';
            } else {
                break;
            }
        }
    }

    public static void inspect6(char[] str) {
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '4') {
                str[i] = '2';
            }
        }
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '2') {
                str[i] = '3';
            }
        }
    }
}