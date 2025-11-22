import java.text.SimpleDateFormat;
import java.util.Date;

public class ProgCobDesafioDante {
    private double ws77Aumento;
    private int ws77TempoCasa;
    private String ws77Cad = "LUCAS DA ROSA MAGALHAES       201300500000";
    private String ws77Cad1 = "DANTE ROBERTO DE VIT LUNARDI  198500200000";
    private String ws05Nome;
    private int ws05AnoEntrada;
    private int ws05Salario;
    private int ws03Ano;
    private int ws03Mes;
    private int ws03Dia;

    public static void main(String[] args) {
        ProgCobDesafioDante program = new ProgCobDesafioDante();
        program.iniciar();
        program.processar();
        program.finalizar();
    }

    private void iniciar() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String currentDate = dateFormat.format(new Date());
        ws03Ano = Integer.parseInt(currentDate.substring(0, 4));
        ws03Mes = Integer.parseInt(currentDate.substring(4, 6));
        ws03Dia = Integer.parseInt(currentDate.substring(6, 8));

        System.out.println("======================================================");
        System.out.println("DATA ATUAL: " + ws03Dia + "/" + ws03Mes + "/" + ws03Ano);
        System.out.println("======================================================");

        move(ws77Cad, ws05Nome, ws05AnoEntrada, ws05Salario);
        System.out.println("CAD: " + ws05Nome.trim() + " " + ws05AnoEntrada + " " + ws05Salario);

        move(ws77Cad1, ws05Nome, ws05AnoEntrada, ws05Salario);
        System.out.println("CAD1: " + ws05Nome.trim() + " " + ws05AnoEntrada + " " + ws05Salario);
        System.out.println("======================================================");
    }

    private void processar() {
        move(ws77Cad, ws05Nome, ws05AnoEntrada, ws05Salario);
        ws77TempoCasa = ws03Ano - ws05AnoEntrada;

        if (ws77TempoCasa >= 0 && ws77TempoCasa <= 1) {
            ws77Aumento = 0;
        } else if (ws77TempoCasa >= 2 && ws77TempoCasa <= 5) {
            ws77Aumento = (ws05Salario / 100.0) * 0.05;
        } else if (ws77TempoCasa >= 6 && ws77TempoCasa <= 15) {
            ws77Aumento = (ws05Salario / 100.0) * 0.10;
        } else {
            ws77Aumento = (ws05Salario / 100.0) * 0.15;
        }

        System.out.println("TEMPO DE CASA LUCAS: " + ws77TempoCasa + " ANO(S)");
        System.out.println("AUMENTO LUCAS: R$ " + ws77Aumento);
        System.out.println("======================================================");
    }

    private void finalizar() {
        move(ws77Cad1, ws05Nome, ws05AnoEntrada, ws05Salario);
        ws77TempoCasa = ws03Ano - ws05AnoEntrada;

        if (ws77TempoCasa >= 0 && ws77TempoCasa <= 1) {
            ws77Aumento = 0;
        } else if (ws77TempoCasa >= 2 && ws77TempoCasa <= 5) {
            ws77Aumento = (ws05Salario / 100.0) * 0.05;
        } else if (ws77TempoCasa >= 6 && ws77TempoCasa <= 15) {
            ws77Aumento = (ws05Salario / 100.0) * 0.10;
        } else {
            ws77Aumento = (ws05Salario / 100.0) * 0.15;
        }

        System.out.println("TEMPO DE CASA DANTE: " + ws77TempoCasa + " ANO(S)");
        System.out.println("AUMENTO DANTE: R$ " + ws77Aumento);
        System.out.println("======================================================");
    }

    private void move(String source, String[] target) {
        target[0] = source.substring(0, 30).trim();
        target[1] = source.substring(30, 34).trim();
        target[2] = source.substring(34, 42).trim();
    }

    private void move(String source, String nome, int anoEntrada, int salario) {
        nome = source.substring(0, 30).trim();
        anoEntrada = Integer.parseInt(source.substring(30, 34).trim());
        salario = Integer.parseInt(source.substring(34, 42).trim());
    }
}