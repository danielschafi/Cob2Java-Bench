```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        double[] xs = new double[101];
        double[] ys = new double[101];
        int xc = 0;
        int yc = 0;
        
        String str_val = scanner.nextLine();
        String[] parts = str_val.trim().split("\\s+");
        double x = Double.parseDouble(parts[0]);
        double y = Double.parseDouble(parts[1]);
        double z = Double.parseDouble(parts[2]);
        int n = Integer.parseInt(parts[3]);
        
        double xmin = x;
        double ymin = y;
        
        while (n > 0) {
            n--;
            str_val = scanner.nextLine();
            parts = str_val.trim().split("\\s+");
            int d = Integer.parseInt(parts[0]);
            double a = Double.parseDouble(parts[1]);
            
            if (d == 0) {
                double tmp = x - a;
                xmin = Math.min(xmin, a);
                xmin = Math.min(xmin, Math.abs(tmp));
                
                for (int j = 0; j < xc; j++) {
                    tmp = xs[j] - a;
                    xmin = Math.min(xmin, Math.abs(tmp));
                }
                
                xs[xc] = a;
                xc++;
            } else {
                double tmp = y - a;
                ymin = Math.min(ymin, a);
                ymin = Math.min(ymin, Math.abs(tmp));
                
                for (int j = 0; j < yc; j++) {
                    tmp = ys[j] - a;
                    ymin = Math.min(ymin, Math.abs(tmp));
                }
                
                ys[yc] = a;
                yc++;
            }
        }
        
        double ans = xmin * ymin * z;
        
        String show = String.format("%.0f", ans);
        System.out.println(show.trim());
        
        scanner.close();
    }
}