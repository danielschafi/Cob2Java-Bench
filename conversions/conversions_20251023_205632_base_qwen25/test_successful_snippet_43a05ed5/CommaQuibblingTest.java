import java.util.ArrayList;
import java.util.List;

public class CommaQuibblingTest {
    public static void main(String[] args) {
        List<String> strs = new ArrayList<>();
        strs.add("ABC");
        strs.add("DEF");
        strs.add("G");
        strs.add("H");

        for (int strsLen = 0; strsLen <= 4; strsLen++) {
            System.out.println(commaQuibbling(strs, strsLen));
        }
    }

    public static String commaQuibbling(List<String> strs, int strsLen) {
        StringBuilder str = new StringBuilder();

        if (strsLen == 0) {
            return "{}";
        } else if (strsLen == 1) {
            return "{" + strs.get(0).trim() + "}";
        }

        str.append(strs.get(strsLen - 2).trim()).append(" and ").append(strs.get(strsLen - 1).trim()).append("}");

        if (strsLen > 2) {
            for (int i = strsLen - 3; i >= 0; i--) {
                str.insert(0, strs.get(i).trim() + ", ");
            }
        }

        return "{" + str.toString();
    }
}