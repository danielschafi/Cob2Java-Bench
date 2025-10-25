import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WHENCOMP1 {
    private int sON1 = 1;
    private char[] p190PROG = new char[11];
    private char[] p190TEXT = new char[120];

    public static void main(String[] args) {
        WHENCOMP1 program = new WHENCOMP1();
        program.execute();
    }

    public void execute() {
        if (sON1 == 1) {
            String prog = "Y2600118 -";
            String text = "Version 003";
            System.arraycopy(prog.toCharArray(), 0, p190PROG, 0, prog.length());
            System.arraycopy(text.toCharArray(), 0, p190TEXT, 0, text.length());

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.HH.mm.ss");
            String compileDate = now.format(formatter);

            p190TEXT[30] = 'C';
            p190TEXT[31] = 'o';
            p190TEXT[32] = 'm';
            p190TEXT[33] = 'p';
            p190TEXT[34] = 'i';
            p190TEXT[35] = 'l';
            p190TEXT[36] = 'e';
            p190TEXT[37] = '-';
            p190TEXT[38] = 'D';
            p190TEXT[39] = 'a';
            p190TEXT[40] = 't';
            p190TEXT[41] = 'u';
            p190TEXT[42] = 'm';
            p190TEXT[43] = ':';
            p190TEXT[44] = ' ';

            p190TEXT[45] = compileDate.charAt(0);
            p190TEXT[46] = compileDate.charAt(1);
            p190TEXT[47] = '.';
            p190TEXT[48] = compileDate.charAt(3);
            p190TEXT[49] = compileDate.charAt(4);
            p190TEXT[50] = '.';
            p190TEXT[51] = compileDate.charAt(6);
            p190TEXT[52] = compileDate.charAt(7);
            p190TEXT[53] = compileDate.charAt(8);
            p190TEXT[54] = compileDate.charAt(9);
            p190TEXT[55] = '.';
            p190TEXT[56] = compileDate.charAt(11);
            p190TEXT[57] = compileDate.charAt(12);
            p190TEXT[58] = '.';
            p190TEXT[59] = compileDate.charAt(14);
            p190TEXT[60] = compileDate.charAt(15);

            System.out.println(new String(p190TEXT).trim());
            sON1 = 0;
        }
    }
}