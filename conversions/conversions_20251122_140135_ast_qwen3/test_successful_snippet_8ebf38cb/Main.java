import java.util.Scanner;
import java.lang.Math;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        double[] xs = new double[101];
        double[] ys = new double[101];
        
        String str_val = scanner.nextLine();
        String[] parts = str_val.split("\\s+");
        double x = Double.parseDouble(parts[0]);
        double y = Double.parseDouble(parts[1]);
        double z = Double.parseDouble(parts[2]);
        int n = Integer.parseInt(parts[3]);
        
        double xmin = x;
        double ymin = y;
        int xc = 0;
        int yc = 0;
        
        while (n > 0) {
            n--;
            str_val = scanner.nextLine();
            parts = str_val.split("\\s+");
            int d = Integer.parseInt(parts[0]);
            double a = Double.parseDouble(parts[1]);
            
            if (d == 0) {
                double tmp = x - a;
                xmin = Math.min(xmin, Math.min(a, Math.abs(tmp)));
                
                for (int j = 1; j <= xc; j++) {
                    tmp = xs[j] - a;
                    xmin = Math.min(xmin, Math.abs(tmp));
                }
                
                xc++;
                xs[xc] = a;
            } else {
                double tmp = y - a;
                ymin = Math.min(ymin, Math.min(a, Math.abs(tmp)));
                
                for (int j = 1; j <= yc; j++) {
                    tmp = ys[j] - a;
                    ymin = Math.min(ymin, Math.abs(tmp));
                }
                
                yc++;
                ys[yc] = a;
            }
        }
        
        double ans = xmin * ymin * z;
        System.out.println(String.format("%15.0f", ans).trim());
    }
}