import java.util.Scanner;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class X1001PRG {
    private String wsFim = "";
    private int wsCtlido = 0;
    private double asPAcids = 0.0;
    private int asMaior = 0;
    private int asCidMaior = 0;
    private int asQtdeMaior = 0;
    
    private int wsCidade = 0;
    private String wsEstado = "";
    private int wsQtdVeiculos = 0;
    private String wsBafometro = "";
    private int wsQtdAcidentes = 0;
    private int wsQtdObitos = 0;
    
    private int cid = 0;
    private int veics = 0;
    private String bafo = "";
    private int acids = 0;
    private int obitos = 0;
    private double porcAcids = 0.0;
    
    private Scanner scanner;
    
    public X1001PRG() {
        scanner = new Scanner(System.in);
    }
    
    public static void main(String[] args) {
        X1001PRG program = new X1001PRG();
        program.executar();
    }
    
    public void executar() {
        iniciar();
        while (!wsFim.equals("S")) {
            processar();
        }
        terminar();
    }
    
    private void iniciar() {
        System.out.println("P1-2019-2");
        System.out.println("MIGUEL COSTA DE MORAIS");
        System.out.println("1680481721001");
        System.out.println("----------------------");
        asMaior = 0;
        lerSysin();
    }
    
    private void lerSysin() {
        String linha = "";
        if (scanner.hasNextLine()) {
            linha = scanner.nextLine();
        }
        
        if (linha.equals("9999999999999999999")) {
            wsFim = "S";
        } else {
            try {
                wsCidade = Integer.parseInt(linha.substring(0, 4));
                wsEstado = linha.substring(4, 6);
                wsQtdVeiculos = Integer.parseInt(linha.substring(6, 13));
                wsBafometro = linha.substring(13, 14);
                wsQtdAcidentes = Integer.parseInt(linha.substring(14, 18));
                wsQtdObitos = Integer.parseInt(linha.substring(18, 22));
                wsCtlido++;
            } catch (Exception e) {
                wsFim = "S";
            }
        }
    }
    
    private void processar() {
        if (wsQtdVeiculos > 0) {
            asPAcids = ((double) wsQtdObitos / wsQtdVeiculos) * 100;
        } else {
            asPAcids = 0;
        }
        porcAcids = asPAcids;
        
        if (wsQtdAcidentes > asMaior) {
            asMaior = wsQtdAcidentes;
            asCidMaior = wsCidade;
            asQtdeMaior = wsQtdAcidentes;
        }
        
        cid = wsCidade;
        veics = wsQtdVeiculos;
        bafo = wsBafometro;
        acids = wsQtdAcidentes;
        obitos = wsQtdObitos;
        
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        DecimalFormat df = new DecimalFormat("###,##0.00", symbols);
        
        String output = String.format("%04d %s %,d %s %d %d %s%% ", 
            cid, wsEstado, veics, bafo, acids, obitos, df.format(porcAcids));
        System.out.println(output);
        
        lerSysin();
    }
    
    private void terminar() {
        System.out.println("------------------------------------------*");
        System.out.println("CIDADE COM MAIOR NUMERO DE ACIDENTES: " + asCidMaior);
        System.out.println("QTDE DE ACIDENTES DA CIDADE ACIMA: " + asQtdeMaior);
        System.out.println("QTDE DE CIDADES PESQUISADAS: " + wsCtlido);
        System.out.println("------------------------------------------*");
    }
}