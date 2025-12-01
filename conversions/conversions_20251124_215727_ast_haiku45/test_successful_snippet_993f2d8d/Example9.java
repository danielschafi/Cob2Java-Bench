```java
public class Example9 {
    public static void main(String[] args) {
        String sampStr = "errie beef sneezd";
        int numChars = 0;
        int numEs = 0;
        String fname = "Martin";
        String mName = "Luther King";
        String lName = "King";
        String fLName = "";
        String fMLName = "";
        String sStr1 = "The egg";
        String sStr2 = "is #1 and";
        String dest = "is the big chicken";
        int ptr = 1;
        String sStr3 = "";
        String sStr4 = "";

        numChars = sampStr.length();
        System.out.println("Number of Characters : " + numChars);

        numEs = 0;
        for (char c : sampStr.toCharArray()) {
            if (c == 'e') {
                numEs++;
            }
        }
        System.out.println("Number of Es : " + numEs);

        System.out.println(sampStr.toUpperCase());
        System.out.println(sampStr.toLowerCase());

        fLName = fname + " " + lName;
        System.out.println(fLName);

        String fLNameTrimmed = fLName.trim();
        fMLName = fLNameTrimmed + " " + mName + " " + lName;
        if (fMLName.length() > 18) {
            System.out.println("Overflowed");
        }
        System.out.println(fMLName);

        StringBuilder destBuilder = new StringBuilder();
        destBuilder.append(sStr1);
        destBuilder.append(" ");
        int delimiterIndex = sStr2.indexOf("#");
        if (delimiterIndex >= 0) {
            destBuilder.append(sStr2.substring(0, delimiterIndex));
        } else {
            destBuilder.append(sStr2);
        }
        dest = destBuilder.toString();
        if (dest.length() > 33) {
            System.out.println("Overflowed");
        }
        System.out.println(dest);

        String[] parts = sStr1.split(" ");
        if (parts.length > 0) {
            sStr3 = parts[0];
        }
        if (parts.length > 1) {
            sStr4 = parts[1];
        }
        System.out.println(sStr4);
    }
}