public class CommaQuibblingTest {
    public static void main(String[] args) {
        String[] strs = new String[10];
        strs[1] = "ABC";
        strs[2] = "DEF";
        strs[3] = "G";
        strs[4] = "H";

        for (int strsLen = 0; strsLen <= 4; strsLen++) {
            System.out.println(commaQuibbling(strs, strsLen));
        }
    }

    public static String commaQuibbling(String[] strs, int strsLen) {
        if (strsLen == 0) {
            return "{}";
        } else if (strsLen == 1) {
            return "{" + strs[1].trim() + "}";
        }

        StringBuilder str = new StringBuilder();
        str.append(strs[strsLen - 1].trim())
          .append(" and ")
          .append(strs[strsLen].trim())
          .append("}");

        if (strsLen > 2) {
            int numExtraWords = strsLen - 2;
            for (int i = numExtraWords; i >= 1; i--) {
                str.insert(0, strs[i].trim() + ", ");
            }
        }

        str.insert(0, "{");
        return str.toString();
    }
}