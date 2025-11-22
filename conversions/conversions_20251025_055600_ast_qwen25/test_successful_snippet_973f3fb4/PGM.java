import java.text.SimpleDateFormat;
import java.util.Date;

public class PGM {
    private double ws77Aumento;
    private int ws77TempoCasa;
    private int ws77Ind;
    private String[][] ws03Informacoes = {
        {"2013", "LUCAS DA ROSA MAGALHAES       ", "00500000"},
        {"1985", "DANTE ROBERTO DE VIT LUNARDI  ", "00200000"}
    };
    private int ws03Ano;
    private int ws03Mes;
    private int ws03Dia;

    public static void main(String[] args) {
        PGM program = new PGM();
        program.principal();
    }

    public void principal() {
        iniciar();
        processar();
        finalizar();
    }

    public void iniciar() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String currentDate = dateFormat.format(new Date());
        ws03Ano = Integer.parseInt(currentDate.substring(0, 4));
        ws03Mes = Integer.parseInt(currentDate.substring(4, 6));
        ws03Dia = Integer.parseInt(currentDate.substring(6, 8));

        System.out.println("=========================================================");
        System.out.println("DATA ATUAL: " + ws03Dia + "/" + ws03Mes + "/" + ws03Ano);
        System.out.println("=========================================================");
    }

    public void processar() {
        ws77Ind = 1;
        processarIndice();

        ws77Ind = 2;
        processarIndice();
    }

    public void processarIndice() {
        System.out.println("NOME: " + ws03Informacoes[ws77Ind - 1][1].trim() +
                           " ANO DE ENTRADA: " + ws03Informacoes[ws77Ind - 1][0] +
                           " SALARIO: " + ws03Informacoes[ws77Ind - 1][2]);

        ws77TempoCasa = ws03Ano - Integer.parseInt(ws03Informacoes[ws77Ind - 1][0]);

        if (ws77TempoCasa >= 0 && ws77TempoCasa <= 1) {
            ws77Aumento = 0;
        } else if (ws77TempoCasa >= 2 && ws77TempoCasa <= 5) {
            ws77Aumento = (Double.parseDouble(ws03Informacoes[ws77Ind - 1][2]) / 100) * 0.05;
        } else if (ws77TempoCasa >= 6 && ws77TempoCasa <= 15) {
            ws77Aumento = (Double.parseDouble(ws03Informacoes[ws77Ind - 1][2]) / 100) * 0.10;
        } else {
            ws77Aumento = (Double.parseDouble(ws03Informacoes[ws77Ind - 1][2]) / 100) * 0.15;
        }

        System.out.println("TEMPO DE CASA: " + ws77TempoCasa + " ANO(S) AUMENTO: R$ " + ws77Aumento);
        System.out.println("=========================================================");
    }

    public void finalizar() {
        System.out.println("FIM");
    }
}