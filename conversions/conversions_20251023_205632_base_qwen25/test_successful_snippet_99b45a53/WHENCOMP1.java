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
            String programName = "Y2600118 -";
            String version = "Version 003";
            String compileDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy.HH.mm.ss"));

            System.arraycopy(programName.toCharArray(), 0, p190PROG, 0, programName.length());
            System.arraycopy(version.toCharArray(), 0, p190TEXT, 0, version.length());

            String compileDateFormatted = String.format("Compile-Datum: %s.%s.%s %s.%s.%s",
                    compileDate.substring(6, 8),
                    compileDate.substring(4, 6),
                    compileDate.substring(0, 4),
                    compileDate.substring(9, 11),
                    compileDate.substring(11, 13),
                    compileDate.substring(13, 15));

            System.arraycopy(compileDateFormatted.toCharArray(), 0, p190TEXT, 29, compileDateFormatted.length());

            System.out.println(new String(p190TEXT).trim());
            sON1 = 0;
        }
    }
}