public class testantlr159 {
    static class WorkAreas {
        int COUNTER = 0;
        String COMP_3 = "+0";
    }
    
    public static void main(String[] args) {
        WorkAreas workAreas = new WorkAreas();
        
        workAreas.COUNTER = workAreas.COUNTER + 1;
        workAreas.COUNTER = workAreas.COUNTER + 10;
        
        System.out.println(workAreas.COUNTER);
    }
}