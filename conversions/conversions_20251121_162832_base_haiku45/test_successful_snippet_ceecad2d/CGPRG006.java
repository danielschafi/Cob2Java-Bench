```java
import java.util.Scanner;

public class CGPRG006 {
    private static String WS_FIM = " ";
    private static int WS_CTLIDO = 0;
    private static int MEDIA_SP = 0;
    private static int TOTAL_SP = 0;
    private static int QTD_CID_SP = 0;
    private static int PCT_ACID = 0;
    private static int CID_MAIOR = 0;
    private static int ACID_MAIOR_TOTAL = 0;
    private static int QTD_CID_MAIOR = 0;
    private static int CID_MENOR = 0;
    private static int PCT_MENOR = 0;
    private static int CID_MENOR_VEICS = 0;
    private static int CID_MENOR_OBITOS = 0;

    private static int WS_CIDADE = 0;
    private static String WS_ESTADO = "";
    private static int WS_QTD_VEICULOS = 0;
    private static String WS_BAFOMETRO = "";
    private static int WS_QTD_ACIDENTES = 0;
    private static int WS_QTD_OBITOS = 0;

    private static int CID = 0;
    private static String UF = "";
    private static int VEICS = 0;
    private static String BAFO = "";
    private static int ACIDS = 0;
    private static int OBITOS = 0;
    private static double PORC_ACIDS = 0.0;

    public static void main(String[] args) {
        iniciar();
        while (!WS_FIM.equals("S")) {
            processar();
        }
        processarSP();
        processarMaior();
        processarMenor();
        terminar();
    }

    private static void iniciar() {
        System.out.println("ANA CAROLINA GOMES DA SILVA");
        System.out.println("ATIVIDADE 6");
        System.out.println("ESTATISTICAS - DATA DO CALCULO: 11/04/2021");
        lerSysin();
    }

    private static void lerSysin() {
        Scanner scanner = new Scanner(System.in);
        String linha = scanner.nextLine();

        if (linha.trim().equals("99999999999999999999")) {
            WS_FIM = "S";
        } else {
            String[] partes = linha.split("\\s+");
            if (partes.length >= 6) {
                WS_CIDADE = Integer.parseInt(partes[0]);
                WS_ESTADO = partes[1];
                WS_QTD_VEICULOS = Integer.parseInt(partes[2]);
                WS_BAFOMETRO = partes[3];
                WS_QTD_ACIDENTES = Integer.parseInt(partes[4]);
                WS_QTD_OBITOS = Integer.parseInt(partes[5]);
            }
            WS_CTLIDO++;
        }
    }

    private static void processar() {
        if (WS_QTD_ACIDENTES > 0) {
            PCT_ACID = (WS_QTD_ACIDENTES * WS_QTD_OBITOS) / 100;
        }
        UF = WS_ESTADO;
        VEICS = WS_QTD_VEICULOS;
        BAFO = WS_BAFOMETRO;
        ACIDS = WS_QTD_OBITOS;
        OBITOS = WS_QTD_OBITOS;
        PORC_ACIDS = PCT_ACID;

        lerSysin();
    }

    private static void processarSP() {
        if (WS_CIDADE == 35) {
            QTD_CID_SP++;
        }

        if (PCT_ACID > 0) {
            TOTAL_SP = QTD_CID_SP * WS_QTD_ACIDENTES;
            MEDIA_SP = TOTAL_SP / PCT_ACID;
        }

        System.out.println("------------------------");
        System.out.println("Media de porcentagens de SP..............: " + MEDIA_SP);
        System.out.println("Qtde. de acidentes totais em SP.........: " + TOTAL_SP);
        System.out.println("Qtde. de cidades de SP pesquisadas......: " + QTD_CID_SP);
    }

    private static void processarMaior() {
        if (WS_QTD_ACIDENTES > 15000 || WS_QTD_OBITOS > 5000) {
            CID_MAIOR++;
            CID = WS_CIDADE;
        }

        if (WS_QTD_ACIDENTES > 0) {
            ACID_MAIOR_TOTAL = CID_MAIOR / WS_QTD_ACIDENTES;
        }
        QTD_CID_MAIOR = WS_QTD_ACIDENTES * CID_MAIOR;

        System.out.println("------------------------");
        System.out.println("Cidade com MAIOR quantidade de acidentes: " + CID);
        System.out.println("Qtde. de acidentes desta cidade..: " + ACID_MAIOR_TOTAL);
        System.out.println("Qtde. TOTAL de cidades pesquisadas..: " + QTD_CID_MAIOR);
    }

    private static void processarMenor() {
        if (WS_QTD_ACIDENTES < 5000 || WS_QTD_OBITOS < 500) {
            CID_MENOR++;
            CID = WS_CIDADE;
            CID_MENOR_VEICS = WS_QTD_VEICULOS;
            CID_MENOR_OBITOS = WS_QTD_OBITOS;
        }

        if (CID_MENOR_VEICS > 0) {
            PCT_MENOR = (CID_MENOR_VEICS * CID_MENOR_OBITOS) / 100;
        }

        System.out.println("------------------------");
        System.out.println("Cidade com MENOR porcentagem de obitos..: " + CID);
        System.out.println("Porcentagem obitos/acidente desta cidade: " + PCT_MENOR);
    }

    private static void terminar() {
        System.out.println(" *========================================*");
        System.out.println(" *   TOTAIS DE CONTROLE - CGPRG006        *");
        System.out.println(" *----------------------------------------*");
        System.out.println(" * REGISTROS LIDOS    - SYSIN  = " + WS_CTLIDO);
        System.out.println(" *========================================*");
        System.out.println(" *----------------------------------------*");
        System.out.println(" *      TERMINO NORMAL DO CGPRG006        *");
        System.out.println(" *----------------------------------------*");
    }
}