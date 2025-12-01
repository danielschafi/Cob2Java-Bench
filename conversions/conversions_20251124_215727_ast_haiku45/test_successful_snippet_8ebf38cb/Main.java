import java.util.Scanner;

public class Main {
    static class Variables {
        String str_val = "";
        short j = 0;
        double[] xs = new double[101];
        double[] ys = new double[101];
        short xc = 0;
        short yc = 0;
        double xmin = 0;
        double ymin = 0;
        double tmp = 0;
        double x = 0;
        double y = 0;
        double z = 0;
        short d = 0;
        double a = 0;
        short n = 0;
        double ans = 0;
        String show = "";
    }

    static Variables vars = new Variables();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        mainRoutine();
    }

    static void mainRoutine() {
        zeroMain();
    }

    static void zeroMain() {
        vars.str_val = scanner.nextLine();
        String[] parts = vars.str_val.split(" ");
        vars.x = Double.parseDouble(parts[0]);
        vars.y = Double.parseDouble(parts[1]);
        vars.z = Double.parseDouble(parts[2]);
        vars.n = Short.parseShort(parts[3]);

        vars.xmin = vars.x;
        vars.ymin = vars.y;

        checkNext();
    }

    static void checkNext() {
        if (vars.n > 0) {
            vars.n--;
            vars.str_val = scanner.nextLine();
            String[] parts = vars.str_val.split(" ");
            vars.d = Short.parseShort(parts[0]);
            vars.a = Double.parseDouble(parts[1]);

            if (vars.d == 0) {
                calcXmin();
            } else {
                calcYmin();
            }
        } else {
            answer();
        }
    }

    static void calcXmin() {
        vars.tmp = vars.x;
        vars.tmp -= vars.a;
        vars.xmin = Math.min(vars.xmin, Math.min(vars.a, vars.tmp));

        for (vars.j = 1; vars.j <= vars.xc; vars.j++) {
            vars.tmp = vars.xs[vars.j - 1];
            vars.tmp -= vars.a;
            vars.xmin = Math.min(vars.xmin, Math.abs(vars.tmp));
        }

        vars.xc++;
        vars.xs[vars.xc - 1] = vars.a;
        checkNext();
    }

    static void calcYmin() {
        vars.tmp = vars.y;
        vars.tmp -= vars.a;
        vars.ymin = Math.min(vars.ymin, Math.min(vars.a, vars.tmp));

        for (vars.j = 1; vars.j <= vars.yc; vars.j++) {
            vars.tmp = vars.ys[vars.j - 1];
            vars.tmp -= vars.a;
            vars.ymin = Math.min(vars.ymin, Math.abs(vars.tmp));
        }

        vars.yc++;
        vars.ys[vars.yc - 1] = vars.a;
        checkNext();
    }

    static void answer() {
        vars.ans = vars.xmin;
        vars.ans *= vars.ymin;
        vars.ans *= vars.z;

        String result = String.format("%.0f", vars.ans);
        System.out.println(result.trim());
    }
}