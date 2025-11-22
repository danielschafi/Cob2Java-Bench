public class example9 {
    public static void main(String[] args) {
        String SampStr = "errie beef sneezd";
        int NumChars = 0;
        int NumEs = 0;
        String Fname = "Martin";
        String MName = "Luther King";
        String LName = "King";
        String FLName = "           ";
        String FMLName = "                  ";
        String SStr1 = "The egg";
        String SStr2 = "is #1 and";
        String Dest = "is the big chicken";
        int Ptr = 1;
        String SStr3 = "   ";
        String SStr4 = "   ";

        NumChars = SampStr.length();
        System.out.println("Number of Characters : " + String.format("%02d", NumChars));

        for (int i = 0; i < SampStr.length(); i++) {
            if (SampStr.charAt(i) == 'e') {
                NumEs++;
            }
        }
        System.out.println("Number of Es : " + String.format("%02d", NumEs));

        System.out.println(SampStr.toUpperCase());
        System.out.println(SampStr.toLowerCase());

        StringBuilder FLNameBuilder = new StringBuilder();
        FLNameBuilder.append(Fname);
        FLNameBuilder.append(" ");
        FLNameBuilder.append(LName);
        FLName = String.format("%-11s", FLNameBuilder.toString().substring(0, Math.min(FLNameBuilder.length(), 11)));
        System.out.println(FLName);

        StringBuilder FMLNameBuilder = new StringBuilder();
        String FLNameTrimmed = FLName.trim();
        FMLNameBuilder.append(FLNameTrimmed);
        FMLNameBuilder.append(" ");
        FMLNameBuilder.append(MName);
        FMLNameBuilder.append(" ");
        FMLNameBuilder.append(LName);
        if (FMLNameBuilder.length() > 18) {
            System.out.println("Overflowed");
        }
        FMLName = String.format("%-18s", FMLNameBuilder.toString().substring(0, Math.min(FMLNameBuilder.length(), 18)));
        System.out.println(FMLName);

        StringBuilder DestBuilder = new StringBuilder(Dest);
        int currentPtr = Ptr - 1;
        DestBuilder.replace(currentPtr, currentPtr + SStr1.length(), SStr1);
        currentPtr += SStr1.length();
        DestBuilder.replace(currentPtr, currentPtr + 1, " ");
        currentPtr += 1;
        String SStr2Part = SStr2.split("#")[0];
        DestBuilder.replace(currentPtr, currentPtr + SStr2Part.length(), SStr2Part);
        currentPtr += SStr2Part.length();
        if (currentPtr > 33) {
            System.out.println("Overflowed");
        }
        Dest = DestBuilder.toString();
        System.out.println(Dest);

        String[] parts = SStr1.split(" ", 3);
        if (parts.length > 0) {
            SStr3 = String.format("%-3s", parts[0].substring(0, Math.min(parts[0].length(), 3)));
        }
        if (parts.length > 1) {
            SStr4 = String.format("%-3s", parts[1].substring(0, Math.min(parts[1].length(), 3)));
        }
        System.out.println(SStr4);
    }
}