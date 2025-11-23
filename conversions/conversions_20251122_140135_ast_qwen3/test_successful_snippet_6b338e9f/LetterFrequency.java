import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LetterFrequency {
    private static int A_cnt = 0;
    private static int B_cnt = 0;
    private static int C_cnt = 0;
    private static int D_cnt = 0;
    private static int E_cnt = 0;
    private static int F_cnt = 0;
    private static int G_cnt = 0;
    private static int H_cnt = 0;
    private static int I_cnt = 0;
    private static int J_cnt = 0;
    private static int K_cnt = 0;
    private static int L_cnt = 0;
    private static int M_cnt = 0;
    private static int N_cnt = 0;
    private static int O_cnt = 0;
    private static int P_cnt = 0;
    private static int Q_cnt = 0;
    private static int R_cnt = 0;
    private static int S_cnt = 0;
    private static int T_cnt = 0;
    private static int U_cnt = 0;
    private static int V_cnt = 0;
    private static int W_cnt = 0;
    private static int X_cnt = 0;
    private static int Y_cnt = 0;
    private static int Z_cnt = 0;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new FileReader("File.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String upperLine = line.toUpperCase();
                A_cnt += countChar(upperLine, 'A');
                B_cnt += countChar(upperLine, 'B');
                C_cnt += countChar(upperLine, 'C');
                D_cnt += countChar(upperLine, 'D');
                E_cnt += countChar(upperLine, 'E');
                F_cnt += countChar(upperLine, 'F');
                G_cnt += countChar(upperLine, 'G');
                H_cnt += countChar(upperLine, 'H');
                I_cnt += countChar(upperLine, 'I');
                J_cnt += countChar(upperLine, 'J');
                K_cnt += countChar(upperLine, 'K');
                L_cnt += countChar(upperLine, 'L');
                M_cnt += countChar(upperLine, 'M');
                N_cnt += countChar(upperLine, 'N');
                O_cnt += countChar(upperLine, 'O');
                P_cnt += countChar(upperLine, 'P');
                Q_cnt += countChar(upperLine, 'Q');
                R_cnt += countChar(upperLine, 'R');
                S_cnt += countChar(upperLine, 'S');
                T_cnt += countChar(upperLine, 'T');
                U_cnt += countChar(upperLine, 'U');
                V_cnt += countChar(upperLine, 'V');
                W_cnt += countChar(upperLine, 'W');
                X_cnt += countChar(upperLine, 'X');
                Y_cnt += countChar(upperLine, 'Y');
                Z_cnt += countChar(upperLine, 'Z');
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        System.out.println("Letter Frequency Distribution");
        System.out.println("-----------------------------");
        System.out.printf("A : %5d          N : %5d%n", A_cnt, N_cnt);
        System.out.printf("B : %5d          O : %5d%n", B_cnt, O_cnt);
        System.out.printf("C : %5d          P : %5d%n", C_cnt, P_cnt);
        System.out.printf("D : %5d          Q : %5d%n", D_cnt, Q_cnt);
        System.out.printf("E : %5d          R : %5d%n", E_cnt, R_cnt);
        System.out.printf("F : %5d          S : %5d%n", F_cnt, S_cnt);
        System.out.printf("G : %5d          T : %5d%n", G_cnt, T_cnt);
        System.out.printf("H : %5d          U : %5d%n", H_cnt, U_cnt);
        System.out.printf("I : %5d          V : %5d%n", I_cnt, V_cnt);
        System.out.printf("J : %5d          W : %5d%n", J_cnt, W_cnt);
        System.out.printf("K : %5d          X : %5d%n", K_cnt, X_cnt);
        System.out.printf("L : %5d          Y : %5d%n", L_cnt, Y_cnt);
        System.out.printf("M : %5d          Z : %5d%n", M_cnt, Z_cnt);
    }

    private static int countChar(String str, char ch) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                count++;
            }
        }
        return count;
    }
}