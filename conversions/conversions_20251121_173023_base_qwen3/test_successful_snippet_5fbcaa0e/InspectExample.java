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
        int index = randomString.indexOf("3");
        if (index != -1) {
            randomString = randomString.substring(0, index) + 
                          randomString.substring(index).replaceFirst("1", "3")
                                       .replaceFirst("2", "3");
        }
    }
    
    private static void inspect3() {
        int index = randomString.indexOf("33");
        if (index != -1 && index + 2 < randomString.length()) {
            randomString = randomString.substring(0, index + 2) + 
                          randomString.substring(index + 2).replaceAll(".", "4");
        }
    }
    
    private static void inspect4() {
        int firstIndex = randomString.indexOf("4");
        if (firstIndex != -1) {
            randomString = randomString.substring(0, firstIndex) + 
                          "3" + 
                          randomString.substring(firstIndex + 1);
        }
        int lastIndex = randomString.lastIndexOf("4");
        if (lastIndex != -1) {
            randomString = randomString.substring(0, lastIndex) + 
                          randomString.substring(lastIndex).replaceFirst("4", "3");
        }
    }
    
    private static void inspect5() {
        randomString = randomString.replaceFirst("^3+", "4");
    }
    
    private static void inspect6() {
        randomString = randomString.replaceAll("4", "2").replaceAll("2", "3");
    }
}