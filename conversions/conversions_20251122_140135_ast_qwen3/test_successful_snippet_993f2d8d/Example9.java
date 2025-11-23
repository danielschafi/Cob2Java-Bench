public class Example9 {
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
        for (int i = 0; i < SampStr.length(); i++) {
            if (SampStr.charAt(i) == 'e') {
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
        FMLName = FLName + " " + MName + " " + LName;
        System.out.println(FMLName);

        // STRING SStr1 DELIMITED BY SIZE SPACE SStr2 DELIMITED BY "#" INTO Dest WITH POINTER ptr
        StringBuilder destBuilder = new StringBuilder(Dest);
        destBuilder.insert(Ptr - 1, SStr1 + " " + SStr2);
        Dest = destBuilder.toString();
        System.out.println(Dest);

        // UNSTRING SStr1 DELIMITED BY SPACE INTO SStr3, SStr4
        String[] parts = SStr1.split(" ", 2);
        if (parts.length > 0) {
            SStr3 = parts[0];
        }
        if (parts.length > 1) {
            SStr4 = parts[1];
        }
        System.out.println(SStr4);
    }
}