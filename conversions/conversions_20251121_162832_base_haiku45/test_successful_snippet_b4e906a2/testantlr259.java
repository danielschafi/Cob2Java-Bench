public class testantlr259 {
    private static class WorkAreas {
        private long COUNTER = 0;
    }
    
    public static void main(String[] args) {
        WorkAreas workAreas = new WorkAreas();
        openFiles(workAreas);
    }
    
    private static void openFiles(WorkAreas workAreas) {
        workAreas.COUNTER += 1;
        workAreas.COUNTER += 10000;
        System.out.println(workAreas.COUNTER);
    }
}