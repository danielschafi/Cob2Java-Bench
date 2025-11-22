import java.util.Arrays;

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

        NumChars = SampStr.length();
        System.out.println("Number of Characters : " + NumChars);

        for (char c : SampStr.toCharArray()) {
            if (c == 'e') {
                NumEs++;
            }
        }
        System.out.println("Number of Es : " + NumEs);

        System.out.println(SampStr.toUpperCase());
        System.out.println(SampStr.toLowerCase());

        FLName = Fname + " " + LName;
        System.out.println(FLName);

        FMLName = FLName + " " + MName + " " + LName;
        if (FMLName.length() > 18) {
            System.out.println("Overflowed");
        }
        System.out.println(FMLName);

        Dest = SStr1 + " " + SStr2.substring(0, SStr2.indexOf('#'));
        if (Dest.length() > 33) {
            System.out.println("Overflowed");
        }
        System.out.println(Dest);

        String[] parts = SStr1.split(" ");
        if (parts.length > 1) {
            SStr3 = parts[0];
            SStr4 = parts[1];
        }
        System.out.println(SStr4);
    }
}