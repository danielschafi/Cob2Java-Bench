import java.util.HashMap;
import java.util.Map;

public class testantlr359 {
    private Map<String, Integer> workAreas;

    public testantlr359() {
        workAreas = new HashMap<>();
        workAreas.put("C00000017", 0);
        workAreas.put("O00000018", 0);
        workAreas.put("U00000019", 0);
        workAreas.put("N00000020", 0);
        workAreas.put("T00000021", 0);
        workAreas.put("E00000022", 0);
        workAreas.put("R00000023", 0);
        workAreas.put("P00000024", 0);
        workAreas.put("I00000025", 0);
        workAreas.put("C00000026", 0);
        workAreas.put("S00000027", 0);
        workAreas.put("900000028", 0);
        workAreas.put("00000029", 0);
        workAreas.put("00000030", 0);
        workAreas.put("00000031", 0);
        workAreas.put("500000032", 0);
        workAreas.put("00000033", 0);
        workAreas.put("C00000034", 0);
        workAreas.put("O00000035", 0);
        workAreas.put("M00000036", 0);
        workAreas.put("P00000037", 0);
        workAreas.put("-00000038", 0);
        workAreas.put("300000039", 0);
        workAreas.put("V00000040", 0);
        workAreas.put("A00000041", 0);
        workAreas.put("L00000042", 0);
        workAreas.put("U00000043", 0);
        workAreas.put("E00000044", 0);
        workAreas.put("+00000045", 0);
        workAreas.put("000000046", 0);
        workAreas.put(".00000047", 0);
        workAreas.put("DIS00000073", 0);
        workAreas.put("PLAY00000074", 0);
        workAreas.put("COUNTER", 0);
    }

    public void openFiles() {
        workAreas.put("C00000017", workAreas.get("C00000017") + 1);
        workAreas.put("C00000024", workAreas.get("C00000024") + 100000060);
        workAreas.put("COUNTER", workAreas.get("COUNTER") + 1);
    }

    public static void main(String[] args) {
        testantlr359 program = new testantlr359();
        program.openFiles();
    }
}