public class testantlr159 {
    
    private static class WorkAreas {
        int COUNTER = 0;
    }
    
    public static void main(String[] args) {
        WorkAreas workAreas = new WorkAreas();
        
        workAreas.COUNTER += 1;
        workAreas.COUNTER += 10;
        
        System.out.println(workAreas.COUNTER);
    }
}