import java.util.Scanner;

public class CGPRG006 {
    private char wsFim;
    private int wsCtlido;
    private int mediaSP;
    private int totalSP;
    private int qtdCidSP;
    private int pctAcid;
    private int cidMaior;
    private int acidMaiorTotal;
    private int qtdCidMaior;
    private int cidMenor;
    private int pctMenor;
    private int cidMenorVeics;
    private int cidMenorObitos;
    private int wsCidade;
    private String wsEstado;
    private int wsQtdVeiculos;
    private char wsBafometro;
    private int wsQtdAcidentes;
    private int wsQtdObitos;
    private int cid;
    private String uf;
    private int veics;
    private char bafo;
    private int acids;
    private int obitos;
    private double porcAcids;

    public static void main(String[] args) {
        CGPRG006 program = new CGPRG006();
        program.execute();
    }

    public void execute() {
        iniciar();
        while (wsFim != 'S') {
            processar();
        }
        processarSP();
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
        Scanner scanner = new Scanner(System.in);
        wsCidade = scanner.nextInt();
        wsEstado = scanner.next();
        wsQtdVeiculos = scanner.nextInt();
        wsBafometro = scanner.next().charAt(0);
        wsQtdAcidentes = scanner.nextInt();
        wsQtdObitos = scanner.nextInt();

        if (wsCidade == 99999 && wsEstado.equals("99") && wsQtdVeiculos == 9999999 && wsBafometro == '9' && wsQtdAcidentes == 9999 && wsQtdObitos == 9999) {
            wsFim = 'S';
        } else {
            wsCtlido++;
        }
    }

    private void processar() {
        pctAcid = wsQtdAcidentes * wsQtdObitos / 100;
        uf = wsEstado;
        veics = wsQtdVeiculos;
        bafo = wsBafometro;
        acids = wsQtdAcidentes;
        obitos = wsQtdObitos;
        porcAcids = pctAcid;

        lerSysin();
    }

    private void processarSP() {
        if (wsCidade == 34777) { // Assuming "SAO PAULO" is represented as 34777 in integer
            qtdCidSP++;
        }

        totalSP = qtdCidSP * wsQtdAcidentes;
        mediaSP = totalSP / pctAcid;

        System.out.println("------------------------");
        System.out.println("Media de porcentagens de SP..............:" + mediaSP);
        System.out.println("Qtde. de acidentes totais em SP.........:" + totalSP);
        System.out.println("Qtde. de cidades de SP pesquisadas......:" + qtdCidSP);
    }

    private void processarMaior() {
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
    }

    private void processarMenor() {
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