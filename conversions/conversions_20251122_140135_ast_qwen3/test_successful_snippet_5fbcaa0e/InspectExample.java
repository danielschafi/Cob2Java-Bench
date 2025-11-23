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
        StringBuilder sb = new StringBuilder(randomString);
        for (int i = 0; i < sb.length(); i++) {
            sb.setCharAt(i, '*');
        }
        randomString = sb.toString();
    }
    
    private static void inspect2() {
        int index = randomString.indexOf("3");
        if (index != -1) {
            StringBuilder sb = new StringBuilder(randomString);
            for (int i = 0; i < index; i++) {
                sb.setCharAt(i, '3');
            }
            randomString = sb.toString();
        }
    }
    
    private static void inspect3() {
        int index = randomString.indexOf("33");
        if (index != -1 && index + 2 < randomString.length()) {
            StringBuilder sb = new StringBuilder(randomString);
            for (int i = index + 2; i < sb.length(); i++) {
                sb.setCharAt(i, '4');
            }
            randomString = sb.toString();
        }
    }
    
    private static void inspect4() {
        int firstIndex = randomString.indexOf("4");
        if (firstIndex != -1) {
            StringBuilder sb = new StringBuilder(randomString);
            sb.setCharAt(firstIndex, '3');
            
            int afterIndex = randomString.indexOf("4", firstIndex + 1);
            if (afterIndex != -1) {
                for (int i = afterIndex; i < sb.length(); i++) {
                    if (sb.charAt(i) == '4') {
                        sb.setCharAt(i, '3');
                    }
                }
            }
            randomString = sb.toString();
        }
    }
    
    private static void inspect5() {
        StringBuilder sb = new StringBuilder(randomString);
        boolean found = false;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '3' && !found) {
                sb.setCharAt(i, '4');
            } else if (sb.charAt(i) != '3') {
                found = true;
            }
        }
        randomString = sb.toString();
    }
    
    private static void inspect6() {
        StringBuilder sb = new StringBuilder(randomString);
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '4') {
                sb.setCharAt(i, '2');
            }
        }
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '2') {
                sb.setCharAt(i, '3');
            }
        }
        randomString = sb.toString();
    }
}