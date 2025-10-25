import java.text.SimpleDateFormat;
import java.util.Date;

public class PGM {
    private double ws77Aumento = 0.0;
    private int ws77TempoCasa = 0;
    private int ws77Ind = 0;
    private String[][] ws02Dados = {
        {"2013", "LUCAS DA ROSA MAGALHAES       ", "00500000"},
        {"1985", "DANTE ROBERTO DE VIT LUNARDI  ", "00200000"}
    };
    private int ws03Ano = 0;
    private int ws03Mes = 0;
    private int ws03Dia = 0;

    public static void main(String[] args) {
        PGM program = new PGM();
        program.start();
    }

    public void start() {
        init();
        process();
        finalizeProgram();
    }

    public void init() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(new Date());
        ws03Ano = Integer.parseInt(date.substring(0, 4));
        ws03Mes = Integer.parseInt(date.substring(4, 6));
        ws03Dia = Integer.parseInt(date.substring(6, 8));

        System.out.println("========================================================");
        System.out.println("DATA ATUAL: " + ws03Dia + "/" + ws03Mes + "/" + ws03Ano);
        System.out.println("========================================================");
    }

    public void process() {
        ws77Ind = 1;
        processRecord();

        ws77Ind = 2;
        processRecord();
    }

    public void processRecord() {
        System.out.println("NOME: " + ws02Dados[ws77Ind - 1][1].trim() +
                           " ANO DE ENTRADA: " + ws02Dados[ws77Ind - 1][0] +
                           " SALARIO: " + ws02Dados[ws77Ind - 1][2]);

        int anoEntrada = Integer.parseInt(ws02Dados[ws77Ind - 1][0]);
        int salario = Integer.parseInt(ws02Dados[ws77Ind - 1][2]);
        ws77TempoCasa = ws03Ano - anoEntrada;

        if (ws77TempoCasa >= 0 && ws77TempoCasa <= 1) {
            ws77Aumento = 0;
        } else if (ws77TempoCasa >= 2 && ws77TempoCasa <= 5) {
            ws77Aumento = (salario / 100.0) * 0.05;
        } else if (ws77TempoCasa >= 6 && ws77TempoCasa <= 15) {
            ws77Aumento = (salario / 100.0) * 0.10;
        } else {
            ws77Aumento = (salario / 100.0) * 0.15;
        }

        System.out.println("TEMPO DE CASA: " + ws77TempoCasa + " ANO(S) AUMENTO: R$ " + ws77Aumento);
        System.out.println("========================================================");
    }

    public void finalizeProgram() {
        System.out.println("FIM");
    }
}