import java.util.*;

public class Main {
    static double[] xs = new double[101];
    static double[] ys = new double[101];
    static double xmin;
    static double ymin;
    static double tmp;
    static double x;
    static double y;
    static double z;
    static int d;
    static double a;
    static int n;
    static double ans;
    static String show;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str_val = scanner.nextLine();
        
        String[] parts = str_val.split("\\s+");
        x = Double.parseDouble(parts[0]);
        y = Double.parseDouble(parts[1]);
        z = Double.parseDouble(parts[2]);
        n = Integer.parseInt(parts[3]);
        
        xmin = x;
        ymin = y;
        
        while (n > 0) {
            n--;
            str_val = scanner.nextLine();
            parts = str_val.split("\\s+");
            d = Integer.parseInt(parts[0]);
            a = Double.parseDouble(parts[1]);
            
            if (d == 0) {
                tmp = x - a;
                xmin = Math.min(xmin, Math.min(a, Math.abs(tmp)));
                for (int j = 1; j <= xs.length - 1; j++) {
                    if (j > xs.length - 1) break;
                    tmp = xs[j] - a;
                    xmin = Math.min(xmin, Math.abs(tmp));
                }
                xs[++xs.length - 1] = a;
            } else {
                tmp = y - a;
                ymin = Math.min(ymin, Math.min(a, Math.abs(tmp)));
                for (int j = 1; j <= ys.length - 1; j++) {
                    if (j > ys.length - 1) break;
                    tmp = ys[j] - a;
                    ymin = Math.min(ymin, Math.abs(tmp));
                }
                ys[++ys.length - 1] = a;
            }
        }
        
        ans = xmin * ymin * z;
        System.out.println(String.format("%,.15f", ans).replaceAll("0*$", "").replaceAll("\\.$", ""));
    }
}