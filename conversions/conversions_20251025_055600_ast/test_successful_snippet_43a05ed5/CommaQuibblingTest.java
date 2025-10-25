import java.util.Arrays;

public class CommaQuibblingTest {
    public static void main(String[] args) {
        String[] strs = new String[10];
        strs[0] = "ABC";
        strs[1] = "DEF";
        strs[2] = "G";
        strs[3] = "H";

        for (int strsLen = 0; strsLen <= 4; strsLen++) {
            System.out.println(commaQuibbling(strs, strsLen));
        }
    }

    public static String commaQuibbling(String[] strs, int strsLen) {
        StringBuilder str = new StringBuilder();

        switch (strsLen) {
            case 0:
                return "{}";
            case 1:
                return "{" + strs[0].trim() + "}";
        }

        str.append(strs[strsLen - 1].trim()).append(" and ").append(strs[strsLen - 2].trim()).append("}");

        if (strsLen > 2) {
            for (int i = strsLen - 3; i >= 0; i--) {
                str.insert(0, strs[i].trim() + ", ");
            }
        }

        return "{" + str.toString() + "}";
    }
}