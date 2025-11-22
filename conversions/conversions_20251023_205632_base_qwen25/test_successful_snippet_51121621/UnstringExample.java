import java.text.SimpleDateFormat;
import java.util.Date;

public class UnstringExample {
    public static void main(String[] args) {
        String randomString = "123ABCDEFGHIJKL";
        String ch1 = new String(new char[10]).replace('\0', ' ');
        String ch2 = new String(new char[10]).replace('\0', ' ');
        String ch3 = new String(new char[10]).replace('\0', ' ');
        String delich1 = new String(new char[3]).replace('\0', ' ');
        String delich2 = new String(new char[3]).replace('\0', ' ');
        String delich3 = new String(new char[3]).replace('\0', ' ');
        int countch1 = 0;
        int countch2 = 0;
        int countch3 = 0;
        int ptr = 4;
        int countsplit = 0;

        String[] delimiters = {"ABC", "GHI"};
        int[] counts = {countch1, countch2, countch3};
        String[] results = {ch1, ch2, ch3};
        String[] delims = {delich1, delich2, delich3};

        int start = ptr - 1;
        int end = 0;
        int index = 0;

        while (start < randomString.length() && index < delimiters.length) {
            for (String delimiter : delimiters) {
                end = randomString.indexOf(delimiter, start);
                if (end != -1) {
                    results[index] = randomString.substring(start, end).trim();
                    delims[index] = delimiter;
                    counts[index] = results[index].length();
                    start = end + delimiter.length();
                    countsplit++;
                    index++;
                    break;
                }
            }
            if (end == -1) {
                results[index] = randomString.substring(start).trim();
                counts[index] = results[index].length();
                countsplit++;
                break;
            }
        }

        System.out.println("Content 1 : " + results[0] + " (delimiter : " + delims[0] + ", size : " + counts[0] + ")");
        System.out.println("Content 2 : " + results[1] + " (delimiter : " + delims[1] + ", size : " + counts[1] + ")");
        System.out.println("Content 3 : " + results[2] + " (delimiter : " + delims[2] + ", size : " + counts[2] + ")");
        System.out.println("Split number : " + countsplit);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String todayDate = dateFormat.format(new Date());
        String todayYear = todayDate.substring(0, 4);
        String todayMonth = todayDate.substring(4, 6);
        String todayDay = todayDate.substring(6, 8);

        System.out.println("Today: " + todayDay + "/" + todayMonth + "/" + todayYear);
    }
}