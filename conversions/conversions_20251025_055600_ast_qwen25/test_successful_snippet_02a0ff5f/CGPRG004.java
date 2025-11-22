import java.text.SimpleDateFormat;
import java.util.Date;

public class CGPRG004 {
    private String wsFim = "N";
    private int wsCtexib = 0;
    private int asCep;
    private double asFrente;
    private double asFundo;
    private double asValMetro;
    private double asValVenda;
    private double asComissao;
    private String asData;
    private String asHora;
    private String wsCep;
    private String wsFrente;
    private String wsFundo;
    private String wsValMetro;
    private String wsValVenda;
    private String wsComissao;
    private String wsMensagem;

    public static void main(String[] args) {
        CGPRG004 program = new CGPRG004();
        program.execute();
    }

    public void execute() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH.mm.ssSS");
        asData = dateFormat.format(new Date());
        asHora = timeFormat.format(new Date());
        iniciar();
        while (!wsFim.equals("S")) {
            processar();
        }
        terminar();
    }

    public void iniciar() {
        System.out.println("** ATIVIDADE 4 **");
        System.out.println("** ANA CAROLINA GOMES DA SILVA **");
        System.out.println("** CALCULO DO PRECO DE VENDA DE UM TERRENO **");
        System.out.println("** RETANGULAR **");
        System.out.println("DATA ATUAL: " + asData);
        System.out.println("HORA ATUAL: " + asHora);
        System.out.println("---------------------");
        wsCtexib = 0;
    }

    public void processar() {
        System.out.println("** PROCESSAMENTO **");
        asCep = 9000400;
        asFrente = 22.50;
        asFundo = 80.00;
        asValMetro = 2315.00;
        asValVenda = asFrente * asFundo * asValMetro;
        if (asValVenda > 1500000) {
            asComissao = asValVenda * 0.04;
            wsMensagem = "ALTO PADRAO";
        } else {
            asComissao = asValVenda * 0.06;
            wsMensagem = "MEDIO PADRAO";
        }
        wsCep = String.format("%08d", asCep);
        wsFrente = String.format("%05.2f", asFrente).replace(',', '.');
        wsFundo = String.format("%05.2f", asFundo).replace(',', '.');
        wsValMetro = String.format("%07.2f", asValMetro).replace(',', '.');
        wsValVenda = String.format("%011.2f", asValVenda).replace(',', '.');
        wsComissao = String.format("%08.2f", asComissao).replace(',', '.');
        System.out.printf("%s %sM %sM %sM2 R$ %s R$ %s %s%n", wsCep, wsFrente, wsFundo, wsValMetro, wsValVenda, wsComissao, wsMensagem);
        wsCtexib++;
        wsFim = "S";
    }

    public void terminar() {
        System.out.println("---------------------------");
        System.out.println("** ENCERRANDO A EXECUCAO **");
        System.out.println("REGISTROS EXIBIDOS = " + wsCtexib);
        System.out.println("TERMINO NORMAL DO PROGRAMA CGPRG004");
    }
}