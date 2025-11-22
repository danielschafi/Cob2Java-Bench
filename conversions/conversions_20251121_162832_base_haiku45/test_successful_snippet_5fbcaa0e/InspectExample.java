public class InspectExample {
    public static void main(String[] args) {
        String randomString = "123ABC";
        
        System.out.println(randomString);
        
        randomString = inspect1(randomString);
        System.out.println("inspect 1 : " + randomString);
        
        randomString = "123123123";
        
        randomString = inspect2(randomString);
        System.out.println("inspect 2 : " + randomString);
        
        randomString = inspect3(randomString);
        System.out.println("inspect 3 : " + randomString);
        
        randomString = inspect4(randomString);
        System.out.println("inspect 4 : " + randomString);
        
        randomString = inspect5(randomString);
        System.out.println("inspect 5 : " + randomString);
        
        randomString = inspect6(randomString);
        System.out.println("inspect 6 : " + randomString);
    }
    
    static String inspect1(String str) {
        return str.replaceAll(".", "*");
    }
    
    static String inspect2(String str) {
        int index = str.indexOf("3");
        if (index >= 0) {
            StringBuilder sb = new StringBuilder(str);
            for (int i = 0; i < index; i++) {
                sb.setCharAt(i, '3');
            }
            return sb.toString();
        }
        return str;
    }
    
    static String inspect3(String str) {
        int index = str.indexOf("33");
        if (index >= 0) {
            StringBuilder sb = new StringBuilder(str);
            for (int i = index + 2; i < sb.length(); i++) {
                sb.setCharAt(i, '4');
            }
            return sb.toString();
        }
        return str;
    }
    
    static String inspect4(String str) {
        int index = str.indexOf("4");
        if (index >= 0) {
            int replaceIndex = str.indexOf("4", index + 1);
            if (replaceIndex >= 0) {
                StringBuilder sb = new StringBuilder(str);
                sb.setCharAt(replaceIndex, '3');
                return sb.toString();
            }
        }
        return str;
    }
    
    static String inspect5(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '3') {
                sb.setCharAt(i, '4');
            } else {
                break;
            }
        }
        return sb.toString();
    }
    
    static String inspect6(String str) {
        String result = str.replaceAll("4", "\u0000");
        result = result.replaceAll("2", "3");
        result = result.replaceAll("\u0000", "2");
        return result;
    }
}