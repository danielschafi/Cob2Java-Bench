public class InspectExample {
    
    private static String randomString = "123ABC";
    
    public static void main(String[] args) {
        System.out.println(randomString);
        
        inspect1();
        System.out.println("inspect 1 : " + randomString);
        
        randomString = "123123123";
        
        inspect2();
        System.out.println("inspect 2 : " + randomString);
        
        inspect3();
        System.out.println("inspect 3 : " + randomString);
        
        inspect4();
        System.out.println("inspect 4 : " + randomString);
        
        inspect5();
        System.out.println("inspect 5 : " + randomString);
        
        inspect6();
        System.out.println("inspect 6 : " + randomString);
    }
    
    private static void inspect1() {
        randomString = randomString.replaceAll(".", "*");
    }
    
    private static void inspect2() {
        int firstThree = randomString.indexOf("3");
        if (firstThree == -1) {
            randomString = randomString.replaceAll(".", "3");
        } else {
            String before = randomString.substring(0, firstThree);
            String after = randomString.substring(firstThree);
            before = before.replaceAll(".", "3");
            randomString = before + after;
        }
    }
    
    private static void inspect3() {
        int pos = randomString.indexOf("33");
        if (pos != -1 && pos + 2 < randomString.length()) {
            String before = randomString.substring(0, pos + 2);
            String after = randomString.substring(pos + 2);
            after = after.replaceAll(".", "4");
            randomString = before + after;
        }
    }
    
    private static void inspect4() {
        int firstFour = randomString.indexOf("4");
        if (firstFour != -1 && firstFour + 1 < randomString.length()) {
            String before = randomString.substring(0, firstFour + 1);
            String after = randomString.substring(firstFour + 1);
            int nextFour = after.indexOf("4");
            if (nextFour != -1) {
                after = after.substring(0, nextFour) + "3" + after.substring(nextFour + 1);
            }
            randomString = before + after;
        }
    }
    
    private static void inspect5() {
        int i = 0;
        while (i < randomString.length() && randomString.charAt(i) == '3') {
            i++;
        }
        if (i > 0) {
            String leading = randomString.substring(0, i).replace('3', '4');
            String rest = randomString.substring(i);
            randomString = leading + rest;
        }
    }
    
    private static void inspect6() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < randomString.length(); i++) {
            char c = randomString.charAt(i);
            if (c == '4') {
                result.append('2');
            } else if (c == '2') {
                result.append('3');
            } else {
                result.append(c);
            }
        }
        randomString = result.toString();
    }
}