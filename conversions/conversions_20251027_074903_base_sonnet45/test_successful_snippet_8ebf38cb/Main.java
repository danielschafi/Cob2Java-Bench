import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        double[] xs = new double[101];
        double[] ys = new double[101];
        int xc = 0;
        int yc = 0;
        double xmin;
        double ymin;
        double tmp;
        double x;
        double y;
        double z;
        int d;
        double a;
        int n;
        double ans;
        
        String[] firstLine = scanner.nextLine().trim().split("\\s+");
        x = Double.parseDouble(firstLine[0]);
        y = Double.parseDouble(firstLine[1]);
        z = Double.parseDouble(firstLine[2]);
        n = Integer.parseInt(firstLine[3]);
        
        xmin = x;
        ymin = y;
        
        while (n > 0) {
            n--;
            String[] line = scanner.nextLine().trim().split("\\s+");
            d = Integer.parseInt(line[0]);
            a = Double.parseDouble(line[1]);
            
            if (d == 0) {
                tmp = x - a;
                xmin = Math.min(xmin, Math.min(a, tmp));
                
                for (int j = 0; j < xc; j++) {
                    tmp = xs[j] - a;
                    xmin = Math.min(xmin, Math.abs(tmp));
                }
                
                xs[xc] = a;
                xc++;
            } else {
                tmp = y - a;
                ymin = Math.min(ymin, Math.min(a, tmp));
                
                for (int j = 0; j < yc; j++) {
                    tmp = ys[j] - a;
                    ymin = Math.min(ymin, Math.abs(tmp));
                }
                
                ys[yc] = a;
                yc++;
            }
        }
        
        ans = xmin * ymin * z;
        
        if (ans == (long) ans) {
            System.out.println((long) ans);
        } else {
            System.out.println(ans);
        }
        
        scanner.close();
    }
}