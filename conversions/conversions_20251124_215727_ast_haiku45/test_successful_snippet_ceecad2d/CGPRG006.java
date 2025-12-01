import java.util.Scanner;

public class CGPRG006 {
    private String wsFim = " ";
    private int wsCtlido = 0;
    private int mediaSp = 0;
    private int totalSp = 0;
    private int qtdCidSp = 0;
    private int pctAcid = 0;
    private int cidMaior = 0;
    private int acidMaiorTotal = 0;
    private int qtdCidMaior = 0;
    private int cidMenor = 0;
    private int pctMenor = 0;
    private int cidMenorVeics = 0;
    private int cidMenorObitos = 0;

    private int wsCidade = 0;
    private String wsEstado = "";
    private int wsQtdVeiculos = 0;
    private String wsBafometro = "";
    private int wsQtdAcidentes = 0;
    private int wsQtdObitos = 0;

    private int cid = 0;
    private String uf = "";
    private int veics = 0;
    private String bafo = "";
    private int acids = 0;
    private int obitos = 0;
    private double porcAcids = 0.0;

    private Scanner scanner;

    public CGPRG006() {
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        CGPRG006 program = new CGPRG006();
        program.executar();
    }

    private void executar() {
        iniciar();
        while (!wsFim.equals("S")) {
            processar();
        }
        processarSp();
        processarMaior();
        processarMenor();
        terminar();
    }

    private void iniciar() {
        System.out.println("ANA CAROLINA GOMES DA SILVA");
        System.out.println("ATIVIDADE 6");
        System.out.println("ESTATISTICAS - DATA DO CALCULO: 11/04/2021");
        lerSysin();
    }

    private void lerSysin() {
        String linha = scanner.nextLine();
        String[] partes = linha.split("\\s+");

        if (linha.trim().equals("99999999999999999999")) {
            wsFim = "S";
        } else {
            try {
                if (partes.length >= 6) {
                    wsCidade = Integer.parseInt(partes[0]);
                    wsEstado = partes[1];
                    wsQtdVeiculos = Integer.parseInt(partes[2]);
                    wsBafometro = partes[3];
                    wsQtdAcidentes = Integer.parseInt(partes[4]);
                    wsQtdObitos = Integer.parseInt(partes[5]);
                }
                wsCtlido++;
            } catch (NumberFormatException e) {
                wsFim = "S";
            }
        }
    }

    private void processar() {
        pctAcid = (wsQtdAcidentes * wsQtdObitos) / 100;
        uf = wsEstado;
        veics = wsQtdVeiculos;
        bafo = wsBafometro;
        acids = wsQtdObitos;
        obitos = wsQtdObitos;
        porcAcids = pctAcid;

        lerSysin();
    }

    private void processarSp() {
        if (wsCidade == 23571) {
            qtdCidSp++;
        }

        totalSp = qtdCidSp * wsQtdAcidentes;
        if (pctAcid != 0) {
            mediaSp = totalSp / pctAcid;
        }

        System.out.println("------------------------");
        System.out.println("Media de porcentagens de SP..............: " + mediaSp);
        System.out.println("Qtde. de acidentes totais em SP.........: " + totalSp);
        System.out.println("Qtde. de cidades de SP pesquisadas......: " + qtdCidSp);
    }

    private void processarMaior() {
        if (wsQtdAcidentes > 15000 || wsQtdObitos > 5000) {
            cidMaior++;
            cid = wsCidade;
        }

        if (wsQtdAcidentes != 0) {
            acidMaiorTotal = cidMaior / wsQtdAcidentes;
        }
        qtdCidMaior = wsQtdAcidentes * cidMaior;

        System.out.println("------------------------");
        System.out.println("Cidade com MAIOR quantidade de acidentes: " + cid);
        System.out.println("Qtde. de acidentes desta cidade..: " + acidMaiorTotal);
        System.out.println("Qtde. TOTAL de cidades pesquisadas..: " + qtdCidMaior);
    }

    private void processarMenor() {
        if (wsQtdAcidentes < 5000 || wsQtdObitos < 500) {
            cidMenor++;
            cid = wsCidade;
            cidMenorVeics = wsQtdVeiculos;
            cidMenorObitos = wsQtdObitos;
        }

        pctMenor = (cidMenorVeics * cidMenorObitos) / 100;

        System.out.println("------------------------");
        System.out.println("Cidade com MENOR porcentagem de obitos..: " + cid);
        System.out.println("Porcentagem obitos/acidente desta cidade: " + pctMenor);
    }

    private void terminar() {
        System.out.println(" *========================================*");
        System.out.println(" *   TOTAIS DE CONTROLE - CGPRG006        *");
        System.out.println(" *----------------------------------------*");
        System.out.println(" * REGISTROS LIDOS    - SYSIN  = " + wsCtlido);
        System.out.println(" *========================================*");
        System.out.println(" *----------------------------------------*");
        System.out.println(" *      TERMINO NORMAL DO CGPRG006        *");
        System.out.println(" *----------------------------------------*");
    }
}