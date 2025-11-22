public class testantlr059 {
    private static class WorkAreas {
        private int COUNTER = 0;
    }

    public static void main(String[] args) {
        WorkAreas workAreas = new WorkAreas();
        
        workAreas.COUNTER += 1;
        workAreas.COUNTER += 10;
        
        System.out.println(workAreas.COUNTER);
    }
}