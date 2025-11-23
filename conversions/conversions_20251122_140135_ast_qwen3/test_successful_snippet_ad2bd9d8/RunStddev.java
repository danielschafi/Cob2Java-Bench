import java.io.*;
import java.util.*;

public class RunStddev {
    static class WsTbData {
        int wsTbSize;
        List<Double> wsTbTable = new ArrayList<>();
    }
    
    static class WsTbWork {
        double wsSum = 0.0;
        double wsSumsq = 0.0;
        double wsAvg = 0.0;
    }
    
    static boolean noMoreInput = false;
    static WsTbData wsTbData = new WsTbData();
    static double wsStddev;
    
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            String line;
            
            wsTbData.wsTbSize = 0;
            
            while ((line = reader.readLine()) != null) {
                int inpFld = Integer.parseInt(line.trim());
                
                wsTbData.wsTbSize++;
                wsTbData.wsTbTable.add((double) inpFld);
                
                stddev(wsTbData, wsStddev);
                
                System.out.println("inp=" + inpFld + " stddev=" + String.format("%.4f", wsStddev));
            }
            
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static void stddev(WsTbData wsTbData, double wsStddev) {
        WsTbWork wsTbWork = new WsTbWork();
        
        wsTbWork.wsSum = 0.0;
        for (int i = 0; i < wsTbData.wsTbSize; i++) {
            wsTbWork.wsSum += wsTbData.wsTbTable.get(i);
        }
        
        wsTbWork.wsAvg = wsTbWork.wsSum / wsTbData.wsTbSize;
        
        wsTbWork.wsSumsq = 0.0;
        for (int i = 0; i < wsTbData.wsTbSize; i++) {
            double diff = wsTbData.wsTbTable.get(i) - wsTbWork.wsAvg;
            wsTbWork.wsSumsq += diff * diff;
        }
        
        wsStddev = Math.sqrt(wsTbWork.wsSumsq / wsTbData.wsTbSize);
    }
}