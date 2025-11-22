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

        for (int i = 0; i < SampStr.length(); i++) {
            if (SampStr.charAt(i) == 'e') {
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

        StringBuilder destBuilder = new StringBuilder(Dest);
        int ptrIndex = Ptr - 1;
        destBuilder.insert(ptrIndex, SStr1 + " ");
        ptrIndex += SStr1.length() + 1;
        int hashIndex = SStr2.indexOf('#');
        if (hashIndex != -1) {
            destBuilder.insert(ptrIndex, SStr2.substring(0, hashIndex));
        } else {
            destBuilder.insert(ptrIndex, SStr2);
        }
        System.out.println(destBuilder.toString());

        String[] unstringResult = SStr1.split(" ", 2);
        if (unstringResult.length >= 2) {
            SStr3 = unstringResult[0];
            SStr4 = unstringResult[1];
        } else {
            SStr3 = unstringResult[0];
            SStr4 = "";
        }
        System.out.println(SStr4);
    }
}