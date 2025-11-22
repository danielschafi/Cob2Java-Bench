```java
public class HappyBirthday {
    public static void main(String[] args) {
        displayWithDelay("***************************************************");
        displayWithDelay("*                                                 *");
        displayWithDelay("*                                                 *");
        displayWithDelay("*                 Happy Birthday!                 *");
        displayWithDelay("*                                                 *");
        displayWithDelay("*                                                 *");
        displayWithDelay("***************************************************");
        displayWithDelay("CCCLLfffffffLLLLLfffffffftttttttfLLLCCCCLLLCLLLLLCC");
        displayWithDelay("tt11111111ttffffffLCCCCCLLLfft111tfLLLLLLLfftfLLLLL");
        displayWithDelay("1111111tfffffttfCG0GGCCLLLCCLfLLfffLfffLLfft11fLLLf");
        displayWithDelay("tttftttfffftttCGGGLffffffffLfLLLLLLLffffttffftfffff");
        displayWithDelay("ffffffftttttLGCCCfttttt1tffffffLfffLftt111tffffffff");
        displayWithDelay("tttttttttttfCffft11111tfLCCGGCLfLffLLLfttttttttttff");
        displayWithDelay("LLLffffttttCLfLftttfffLCGGGG00GLLCLLCLLffffffffffff");
        displayWithDelay("LLLLLLfftttCLGCCCCCGGGGGGGGGG00GLCCCCLLLLLLLLLLLLLL");
        displayWithDelay("LLLLLLLLLLLCLGCCCCCCGGGGGGGGGG0GGCLCCCCLLLLLLfffLLL");
        displayWithDelay("GCCLLLLCLLfLfLCCGGGGCCCCGGGGGG0000CCCGCLLLLLLLLLLLL");
        displayWithDelay("GGCCCLLLLfffLLGCCCLLLLLLftt1tffCG00CCLCLLCCCLCCGGGC");
        displayWithDelay("CLLLLLLLLLLLLCCt11111fLLt11tfLLCG00GGCCLLLCLfLLLLLL");
        displayWithDelay("LLLLfffLffLLfCCfttttffGGCCCCCGGGG00GG0CLLLftttfffft");
        displayWithDelay("LfLLLCCCCCLLCCGGGCCCLLG00GCLCCCGGGGGCLLLLLLLLLLCLLL");
        displayWithDelay("ffLLCCCCCCCCLLCCCCCLfLCGGGCLLLLLCCGGG0GLLLfffffffLL");
        displayWithDelay("LLLLLLfLLLLLLLLCLLffLLtttffLLffLCLCGGG@80000GG0GLLL");
        displayWithDelay("LLLLLLfLLLLLCCCLLffffffttffLCLffLLCGGL88888@8@88@88");
        displayWithDelay("LLLLLLLLLLffLLLfffftfLLLffLCCCLffLLGCL88880808808@@");
        displayWithDelay("LLLLLLLLLLLLLLLC08GftfLCCCCCLLffffLCLC888888000G088");
        displayWithDelay("fffLLLLLLCCLCG888880CttttttttttffLLLLG088880800G088");
        displayWithDelay("ffffffLLLLCG888800800LfftttttttffLffCLfLCG0G000G000");
        displayWithDelay("LLLffLLLC08@8000008000CLttffffffffLCCLLLLC00000GG00");
        displayWithDelay("fffLLLC08@880000008880GGCffffffffCGCCGCCGG00000GG00");
        displayWithDelay("LLLLC08@88880000000GCLfLGGCCCCLfCGGGGGCGGG000000GGG");
        displayWithDelay("CCC0888888880000000CCCCLLCCCCCGGCG000GGGGGG00000GGG");
        displayWithDelay("***************************************************");
        displayWithDelay("*                                                 *");
        displayWithDelay("*                                                 *");
        displayWithDelay("*                 Happy Birthday!                 *");
        displayWithDelay("*                                                 *");
        displayWithDelay("*                                                 *");
        displayWithDelay("***************************************************");
    }

    private static void displayWithDelay(String text) {
        System.out.println(text);
        try {
            Thread.sleep(80);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}