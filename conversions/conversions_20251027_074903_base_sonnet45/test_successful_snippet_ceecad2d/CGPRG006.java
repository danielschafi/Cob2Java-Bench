import java.util.Scanner;

public class CGPRG006 {
    
    private static class WorkingStorage {
        String wsFim = "N";
        int wsCtlido = 0;
        int mediaSp = 0;
        int totalSp = 0;
        int qtdCidSp = 0;
        int pctAcid = 0;
        int cidMaior = 0;
        int acidMaiorTotal = 0;
        int qtdCidMaior = 0;
        int cidMenor = 0;
        int pctMenor = 0;
        int cidMenorVeics = 0;
        int cidMenorObitos = 0;
        
        int wsCidade = 0;
        String wsEstado = "";
        int wsQtdVeiculos = 0;
        String wsBafometro = "";
        int wsQtdAcidentes = 0;
        int wsQtdObitos = 0;
        
        int cid = 0;
        String uf = "";
        int veics = 0;
        String bafo = "";
        int acids = 0;
        int obitos = 0;
        int porcAcids = 0;
    }
    
    private static WorkingStorage ws = new WorkingStorage();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        cgprg006();
    }
    
    private static void cgprg006() {
        iniciar010();
        while (!ws.wsFim.equals("S")) {
            processar030();
        }
        processarSp040();
        processarMaior045();
        processarMenor047();
        terminar090();
    }
    
    private static void iniciar010() {
        System.out.println("ANA CAROLINA GOMES DA SILVA");
        System.out.println("ATIVIDADE 6");
        System.out.println("ESTATISTICAS - DATA DO CALCULO: 11/04/2021");
        lerSysin025();
    }
    
    private static void lerSysin025() {
        if (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            
            if (linha.trim().isEmpty() || linha.matches("9+")) {
                ws.wsFim = "S";
            } else {
                try {
                    ws.wsCidade = Integer.parseInt(linha.substring(0, 5).trim());
                    ws.wsEstado = linha.substring(5, 7).trim();
                    ws.wsQtdVeiculos = Integer.parseInt(linha.substring(7, 14).trim());
                    ws.wsBafometro = linha.substring(14, 15).trim();
                    ws.wsQtdAcidentes = Integer.parseInt(linha.substring(15, 19).trim());
                    ws.wsQtdObitos = Integer.parseInt(linha.substring(19, 23).trim());
                    ws.wsCtlido++;
                } catch (Exception e) {
                    ws.wsFim = "S";
                }
            }
        } else {
            ws.wsFim = "S";
        }
    }
    
    private static void processar030() {
        ws.pctAcid = ws.wsQtdAcidentes * ws.wsQtdObitos / 100;
        ws.uf = ws.wsEstado;
        ws.veics = ws.wsQtdVeiculos;
        ws.bafo = ws.wsBafometro;
        ws.acids = ws.wsQtdObitos;
        ws.obitos = ws.wsQtdObitos;
        ws.porcAcids = ws.pctAcid;
        
        lerSysin025();
    }
    
    private static void processarSp040() {
        if (ws.wsCidade == 0 && ws.wsEstado.equals("SP")) {
            ws.qtdCidSp++;
        }
        
        ws.totalSp = ws.qtdCidSp * ws.wsQtdAcidentes;
        if (ws.pctAcid != 0) {
            ws.mediaSp = ws.totalSp / ws.pctAcid;
        }
        
        System.out.println("------------------------");
        System.out.println("Media de porcentagens de SP..............:" + ws.mediaSp);
        System.out.println("Qtde. de acidentes totais em SP.........:" + ws.totalSp);
        System.out.println("Qtde. de cidades de SP pesquisadas......:" + ws.qtdCidSp);
    }
    
    private static void processarMaior045() {
        if (ws.wsQtdAcidentes > 15000 || ws.wsQtdObitos > 5000) {
            ws.cidMaior++;
            ws.cid = ws.wsCidade;
        }
        
        if (ws.wsQtdAcidentes != 0) {
            ws.acidMaiorTotal = ws.cidMaior / ws.wsQtdAcidentes;
        }
        ws.qtdCidMaior = ws.wsQtdAcidentes * ws.cidMaior;
        
        System.out.println("------------------------");
        System.out.println("Cidade com MAIOR quantidade de acidentes:" + ws.cid);
        System.out.println("Qtde. de acidentes desta cidade..:" + ws.acidMaiorTotal);
        System.out.println("Qtde. TOTAL de cidades pesquisadas..:" + ws.qtdCidMaior);
    }
    
    private static void processarMenor047() {
        if (ws.wsQtdAcidentes < 5000 || ws.wsQtdObitos < 500) {
            ws.cidMenor++;
            ws.cid = ws.wsCidade;
            ws.cidMenorVeics = ws.wsQtdVeiculos;
            ws.cidMenorObitos = ws.wsQtdObitos;
        }
        
        ws.pctMenor = ws.cidMenorVeics * ws.cidMenorObitos / 100;
        
        System.out.println("------------------------");
        System.out.println("Cidade com MENOR porcentagem de obitos..:" + ws.cid);
        System.out.println("Porcentagem obitos/acidente desta cidade:" + ws.pctMenor);
    }
    
    private static void terminar090() {
        System.out.println(" *========================================*");
        System.out.println(" *   TOTAIS DE CONTROLE - CGPRG006        *");
        System.out.println(" *----------------------------------------*");
        System.out.println(" * REGISTROS LIDOS    - SYSIN  = " + ws.wsCtlido);
        System.out.println(" *========================================*");
        System.out.println(" *----------------------------------------*");
        System.out.println(" *      TERMINO NORMAL DO CGPRG006        *");
        System.out.println(" *----------------------------------------*");
        
        scanner.close();
    }
}