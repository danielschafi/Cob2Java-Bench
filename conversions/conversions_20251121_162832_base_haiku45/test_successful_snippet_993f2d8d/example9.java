```java
public class example9 {
    public static void main(String[] args) {
        String SampStr = "errie beef sneezd";
        int NumChars = 0;
        int NumEs = 0;
        String Fname = "Martin";
        String MName = "Luther King";
        String LName = "King";
        String FLName = "";
        String FMLName = "";
        String SStr1 = "The egg";
        String SStr2 = "is #1 and";
        String Dest = "is the big chicken";
        int Ptr = 1;
        String SStr3 = "";
        String SStr4 = "";

        // INSPECT SampStr TALLYING NumChars FOR CHARACTERS
        NumChars = SampStr.length();
        System.out.println("Number of Characters : " + NumChars);

        // INSPECT SampStr TALLYING NumEs FOR ALL 'e'
        NumEs = 0;
        for (char c : SampStr.toCharArray()) {
            if (c == 'e') {
                NumEs++;
            }
        }
        System.out.println("Number of Es : " + NumEs);

        // DISPLAY FUNCTION UPPER-CASE(SampStr)
        System.out.println(SampStr.toUpperCase());

        // DISPLAY FUNCTION LOWER-CASE(SampStr)
        System.out.println(SampStr.toLowerCase());

        // STRING FName DELIMITED BY SIZE SPACE LName DELIMITED BY SIZE INTO FLName
        FLName = Fname + " " + LName;
        System.out.println(FLName);

        // STRING FLName DELIMITED BY SPACES SPACE MName DELIMITED BY SIZE SPACE LName DELIMITED BY SIZE INTO FMLName
        String FLNameTrimmed = FLName.trim();
        FMLName = FLNameTrimmed + " " + MName + " " + LName;
        if (FMLName.length() > 18) {
            System.out.println("Overflowed");
        }
        System.out.println(FMLName);

        // STRING SStr1 DELIMITED BY SIZE SPACE SStr2 DELIMITED BY "#" INTO Dest WITH POINTER ptr
        StringBuilder destBuilder = new StringBuilder();
        destBuilder.append(SStr1);
        destBuilder.append(" ");
        int delimiterIndex = SStr2.indexOf("#");
        if (delimiterIndex >= 0) {
            destBuilder.append(SStr2.substring(0, delimiterIndex));
        } else {
            destBuilder.append(SStr2);
        }
        Dest = destBuilder.toString();
        if (Dest.length() > 33) {
            System.out.println("Overflowed");
        }
        System.out.println(Dest);

        // UNSTRING SStr1 DELIMITED BY SPACE INTO SStr3, SStr4
        String[] parts = SStr1.split(" ");
        if (parts.length > 0) {
            SStr3 = parts[0];
        }
        if (parts.length > 1) {
            SStr4 = parts[1];
        }
        System.out.println(SStr4);
    }
}