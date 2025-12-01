import java.io.*;
import java.util.*;

public class RunStddev {
    static class TbData {
        int size;
        double[] table;
        
        TbData() {
            this.size = 0;
            this.table = new double[100];
        }
    }
    
    public static void main(String[] args) {
        TbData tbData = new TbData();
        double[] stddev = new double[1];
        
        tbData.size = 0;
        
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            boolean noMoreInput = false;
            
            line = reader.readLine();
            if (line == null) {
                noMoreInput = true;
            }
            
            while (!noMoreInput) {
                tbData.size++;
                int inpFld = Integer.parseInt(line.trim());
                tbData.table[tbData.size - 1] = inpFld;
                
                stddev(tbData, stddev);
                
                System.out.println("inp=" + inpFld + " stddev=" + stddev[0]);
                
                line = reader.readLine();
                if (line == null) {
                    noMoreInput = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static void stddev(TbData tbData, double[] wsStddev) {
        double wsSum = 0;
        double wsSumsq = 0;
        double wsAvg = 0;
        
        wsSum = 0;
        for (int wsTbx = 1; wsTbx <= tbData.size; wsTbx++) {
            wsSum = wsSum + tbData.table[wsTbx - 1];
        }
        
        wsAvg = Math.round((wsSum / tbData.size) * 10000.0) / 10000.0;
        
        wsSumsq = 0;
        for (int wsTbx = 1; wsTbx <= tbData.size; wsTbx++) {
            wsSumsq = wsSumsq + Math.pow(tbData.table[wsTbx - 1] - wsAvg, 2.0);
        }
        
        wsStddev[0] = Math.pow(wsSumsq / tbData.size, 0.5);
    }
}