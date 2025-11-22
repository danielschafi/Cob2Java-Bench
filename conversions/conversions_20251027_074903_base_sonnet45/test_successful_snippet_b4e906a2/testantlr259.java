public class testantlr259 {
    
    private static class WorkAreas {
        int COUNTER_PICS_9_0005_COMP_3_VALUE_0 = 0;
    }
    
    private static WorkAreas workAreas = new WorkAreas();
    
    public static void main(String[] args) {
        openFiles();
    }
    
    private static void openFiles() {
        workAreas.COUNTER_PICS_9_0005_COMP_3_VALUE_0 += 1;
        workAreas.COUNTER_PICS_9_0005_COMP_3_VALUE_0 += 100;
        System.out.println(workAreas.COUNTER_PICS_9_0005_COMP_3_VALUE_0);
    }
}