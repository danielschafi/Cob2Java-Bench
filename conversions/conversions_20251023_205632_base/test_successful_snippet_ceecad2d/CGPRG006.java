import java.util.Scanner;

public class CGPRG006 {
    public static void main(String[] args) {
        String wsFim = "";
        int wsCtlido = 0;
        int mediaSP = 0;
        int totalSP = 0;
        int qtdCidSP = 0;
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
        String veics = "";
        String bafo = "";
        int acids = 0;
        int obitos = 0;
        double porcAcids = 0.0;

        System.out.println("ANA CAROLINA GOMES DA SILVA");
        System.out.println("ATIVIDADE 6");
        System.out.println("ESTATISTICAS - DATA DO CALCULO: 11/04/2021");

        Scanner scanner = new Scanner(System.in);

        while (!wsFim.equals("S")) {
            wsCidade = scanner.nextInt();
            wsEstado = scanner.next();
            wsQtdVeiculos = scanner.nextInt();
            wsBafometro = scanner.next();
            wsQtdAcidentes = scanner.nextInt();
            wsQtdObitos = scanner.nextInt();

            if (wsCidade == 99999 && wsQtdVeiculos == 9999999 && wsQtdAcidentes == 9999 && wsQtdObitos == 9999) {
                wsFim = "S";
            } else {
                wsCtlido++;

                pctAcid = wsQtdAcidentes * wsQtdObitos / 100;
                uf = wsEstado;
                veics = String.format("%,d", wsQtdVeiculos);
                bafo = wsBafometro;
                acids = wsQtdAcidentes;
                obitos = wsQtdObitos;
                porcAcids = pctAcid;

                if (wsCidade == 229) { // Assuming "SAO PAULO" is represented by 229
                    qtdCidSP++;
                }

                totalSP = qtdCidSP * wsQtdAcidentes;
                if (pctAcid != 0) {
                    mediaSP = totalSP / pctAcid;
                }

                System.out.println("------------------------");
                System.out.println("Media de porcentagens de SP..............:" + mediaSP);
                System.out.println("Qtde. de acidentes totais em SP.........:" + totalSP);
                System.out.println("Qtde. de cidades de SP pesquisadas......:" + qtdCidSP);

                if (wsQtdAcidentes > 15000 || wsQtdObitos > 5000) {
                    cidMaior++;
                    cid = wsCidade;
                }

                acidMaiorTotal = cidMaior / wsQtdAcidentes;
                qtdCidMaior = wsQtdAcidentes * cidMaior;

                System.out.println("------------------------");
                System.out.println("Cidade com MAIOR quantidade de acidentes:" + cid);
                System.out.println("Qtde. de acidentes desta cidade..:" + acidMaiorTotal);
                System.out.println("Qtde. TOTAL de cidades pesquisadas..:" + qtdCidMaior);

                if (wsQtdAcidentes < 5000 || wsQtdObitos < 500) {
                    cidMenor++;
                    cid = wsCidade;
                    cidMenorVeics = wsQtdVeiculos;
                    cidMenorObitos = wsQtdObitos;
                }

                pctMenor = cidMenorVeics * cidMenorObitos / 100;

                System.out.println("------------------------");
                System.out.println("Cidade com MENOR porcentagem de obitos..:" + cid);
                System.out.println("Porcentagem obitos/acidente desta cidade:" + pctMenor);
            }
        }

        System.out.println(" *========================================*");
        System.out.println(" *   TOTAIS DE CONTROLE - CGPRG006        *");
        System.out.println(" *----------------------------------------*");
        System.out.println(" * REGISTROS LIDOS    - SYSIN  = " + wsCtlido);
        System.out.println(" *========================================*");
        System.out.println(" *----------------------------------------*");
        System.out.println(" *      TERMINO NORMAL DO CGPRG006        *");
        System.out.println(" *----------------------------------------*");

        scanner.close();
    }
}