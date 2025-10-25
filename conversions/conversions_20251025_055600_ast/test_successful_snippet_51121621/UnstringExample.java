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
        int start = ptr - 1;
        int end = 0;
        int delimiterIndex = 0;

        while (start < randomString.length() && delimiterIndex < delimiters.length) {
            end = randomString.indexOf(delimiters[delimiterIndex], start);
            if (end == -1) {
                end = randomString.length();
            }
            if (countsplit == 0) {
                ch1 = randomString.substring(start, end).trim();
                delich1 = (end < randomString.length()) ? delimiters[delimiterIndex] : "   ";
                countch1 = ch1.length();
            } else if (countsplit == 1) {
                ch2 = randomString.substring(start, end).trim();
                delich2 = (end < randomString.length()) ? delimiters[delimiterIndex] : "   ";
                countch2 = ch2.length();
            } else if (countsplit == 2) {
                ch3 = randomString.substring(start, end).trim();
                delich3 = (end < randomString.length()) ? delimiters[delimiterIndex] : "   ";
                countch3 = ch3.length();
            }
            countsplit++;
            start = end + delimiters[delimiterIndex].length();
            delimiterIndex = (delimiterIndex + 1) % delimiters.length;
        }

        if (countsplit > 3) {
            System.out.println("Pointer too low/high or not enough variables :(");
        }

        System.out.println("Content 1 : " + ch1 + " (delimiter : " + delich1 + ", size : " + countch1 + ")");
        System.out.println("Content 2 : " + ch2 + " (delimiter : " + delich2 + ", size : " + countch2 + ")");
        System.out.println("Content 3 : " + ch3 + " (delimiter : " + delich3 + ", size : " + countch3 + ")");
        System.out.println("Split number : " + countsplit);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String todayDate = dateFormat.format(new Date());
        String todayYear = todayDate.substring(0, 4);
        String todayMonth = todayDate.substring(4, 6);
        String todayDay = todayDate.substring(6, 8);

        System.out.println("Today: " + todayDay + "/" + todayMonth + "/" + todayYear);
    }
}