public class testantlr359 {
    
    private static class WorkAreas {
        int COUNTER = 0;
    }
    
    public static void main(String[] args) {
        WorkAreas workAreas = new WorkAreas();
        
        // CCVS1 SECTION - OPEN-FILES
        workAreas.COUNTER += 1;
        workAreas.COUNTER += 10;
        
        System.out.println(workAreas.COUNTER);
    }
}