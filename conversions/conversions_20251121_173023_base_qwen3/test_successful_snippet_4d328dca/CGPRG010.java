import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class CGPRG010 {
    private static final String CADALUJ = "CADALUJ";
    private static final String RELALUJ = "RELALUJ";
    
    // Working storage variables
    private static int ws_ctlido = 0;
    private static int ws_ctimpr = 0;
    private static int ws_ctlin = 65;
    private static String ws_dtedi = "";
    private static String ws_hredi = "";
    private static String ws_fs_alu = "";
    private static String ws_fs_rel = "";
    private static String ws_msg = "";
    private static String ws_fs_msg = "";
    private static String ws_curso_cab = "";
    private static String ws_data_cab = "";
    private static int ws_pag_cab = 0;
    private static String ws_avaliacao_r = "";
    private static int ws_numero_r = 0;
    private static String ws_nome_r = "";
    private static String ws_curso_r = "";
    private static double ws_nota1_r = 0.0;
    private static double ws_nota2_r = 0.0;
    private static double ws_media_r = 0.0;
    private static String ws_hifen = "--------------------------------------------------------------------------------";
    
    // Input record
    private static class RegCadalu {
        int numero_e;
        String nome_e;
        String sexo_e;
        int idade_e;
        String curso_e;
        double nota1_e;
        double nota2_e;
        double media_e;
    }
    
    // Output record
    private static class RegRelalu {
        String linha;
    }
    
    public static void main(String[] args) {
        try {
            perform_000_rspg004();
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
    
    private static void perform_000_rspg004() throws Exception {
        perform_010_iniciar();
        
        while (!ws_fs_alu.equals("10")) {
            perform_030_processar();
        }
        
        perform_090_terminar();
    }
    
    private static void perform_010_iniciar() throws Exception {
        perform_015_data_hora();
        
        System.out.println(" *----------------------------------------*");
        System.out.println(" * INICIO : " + ws_dtedi + " AS " + ws_hredi);
        System.out.println(" *----------------------------------------*");
        
        System.out.println(" *----------------------------------------*");
        System.out.println(" * PROGRAMA 10 - FATEC SCS                *");
        System.out.println(" * MIGUEL MORAIS                          *");
        System.out.println(" * JESSICA HOLANDA                        *");
        System.out.println(" *----------------------------------------*");
        
        perform_020_abrir_arq();
        perform_025_ler_cadalu();
        
        if (ws_fs_alu.equals("10")) {
            ws_msg = "ERRO - CADALU VAZIO";
            ws_fs_msg = ws_fs_alu;
            perform_999_erro();
            return;
        }
        
        Scanner scanner = new Scanner(System.in);
        System.out.print("Informe o curso: ");
        ws_curso_cab = scanner.nextLine().trim();
        ws_data_cab = ws_dtedi;
    }
    
    private static void perform_015_data_hora() {
        Date now = new Date();
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss:SS");
        
        ws_dtedi = sdfDate.format(now);
        ws_hredi = sdfTime.format(now);
    }
    
    private static void perform_020_abrir_arq() throws Exception {
        // Simulating file operations
        // In real implementation, you would open actual files here
        
        ws_fs_alu = "00"; // Success
        ws_fs_rel = "00"; // Success
        
        if (!ws_fs_alu.equals("00")) {
            ws_msg = "ERRO AO ABRIR O CADALU";
            ws_fs_msg = ws_fs_alu;
            perform_999_erro();
            return;
        }
        
        if (!ws_fs_rel.equals("00")) {
            ws_msg = "ERRO AO ABRIR O RELALU";
            ws_fs_msg = ws_fs_rel;
            perform_999_erro();
            return;
        }
    }
    
    private static void perform_025_ler_cadalu() throws Exception {
        // Simulating reading from file
        // In real implementation, you would read from actual file
        
        ws_fs_alu = "00"; // Success
        
        if (!(ws_fs_alu.equals("00") || ws_fs_alu.equals("10"))) {
            ws_msg = "ERRO NA LEITURA DO CADALU";
            ws_fs_msg = ws_fs_alu;
            perform_999_erro();
            return;
        } else if (ws_fs_alu.equals("00")) {
            ws_ctlido++;
        }
    }
    
    private static void perform_030_processar() throws Exception {
        // Simulating data processing
        // In real implementation, you would process the actual data
        
        if (ws_media_e < 7) {
            ws_avaliacao_r = "REPROVADO";
        } else {
            ws_avaliacao_r = "APROVADO ";
        }
        
        ws_numero_r = ws_numero_e;
        ws_nome_r = ws_nome_e;
        ws_curso_r = ws_curso_e;
        ws_nota1_r = ws_nota1_e;
        ws_nota2_r = ws_nota2_e;
        ws_media_r = ws_media_e;
        
        perform_035_imprel();
        perform_025_ler_cadalu();
    }
    
    private static void perform_035_imprel() throws Exception {
        if (ws_ctlin > 13) {
            perform_040_impcab();
        }
        
        // Simulate writing to output file
        ws_fs_rel = "00"; // Success
        
        if (!ws_fs_rel.equals("00")) {
            ws_msg = "ERRO NA GRAVACAO DO RELALU";
            ws_fs_msg = ws_fs_alu;
            perform_999_erro();
            return;
        }
        
        ws_ctimpr++;
        ws_ctlin++;
    }
    
    private static void perform_040_impcab() throws Exception {
        ws_pag_cab++;
        
        // Simulate writing header to file
        ws_fs_rel = "00"; // Success
        
        if (!ws_fs_rel.equals("00")) {
            ws_msg = "ERRO GRAVACAO CAB1";
            ws_fs_msg = ws_fs_rel;
            perform_999_erro();
            return;
        }
        
        // Write hifen line
        ws_fs_rel = "00"; // Success
        
        if (!ws_fs_rel.equals("00")) {
            ws_msg = "ERRO GRAVACAO HIFEN-1";
            ws_fs_msg = ws_fs_rel;
            perform_999_erro();
            return;
        }
        
        // Write second header
        ws_fs_rel = "00"; // Success
        
        if (!ws_fs_rel.equals("00")) {
            ws_msg = "ERRO GRAVACAO CAB2";
            ws_fs_msg = ws_fs_rel;
            perform_999_erro();
            return;
        }
        
        // Write hifen line again
        ws_fs_rel = "00"; // Success
        
        if (!ws_fs_rel.equals("00")) {
            ws_msg = "ERRO GRAVACAO HIFEN-2";
            ws_fs_msg = ws_fs_rel;
            perform_999_erro();
            return;
        }
        
        ws_ctlin = 4;
    }
    
    private static void perform_090_terminar() throws Exception {
        perform_015_data_hora();
        
        System.out.println(" *----------------------------------------*");
        System.out.println(" * TERMINO: " + ws_dtedi + " AS " + ws_hredi);
        System.out.println(" *----------------------------------------*");
        System.out.println(" *========================================*");
        System.out.println(" *   TOTAIS DE CONTROLE - CGPRG010        *");
        System.out.println(" *----------------------------------------*");
        System.out.println(" * REGISTROS LIDOS     - CADALU = " + ws_ctlido);
        System.out.println(" * REGISTROS IMPRESSOS - RELALU = " + ws_ctimpr);
        System.out.println(" *========================================*");
        
        perform_095_fechar_arq();
        
        System.out.println(" *----------------------------------------*");
        System.out.println(" *      TERMINO NORMAL DO CGPRG010        *");
        System.out.println(" *----------------------------------------*");
    }
    
    private static void perform_095_fechar_arq() throws Exception {
        // Simulate closing files
        ws_fs_alu = "00"; // Success
        ws_fs_rel = "00"; // Success
        
        if (!ws_fs_alu.equals("00")) {
            ws_msg = "ERRO AO FECHAR O CADALU";
            ws_fs_msg = ws_fs_alu