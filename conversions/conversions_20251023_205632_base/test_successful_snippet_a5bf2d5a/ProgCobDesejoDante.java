import java.text.SimpleDateFormat;
import java.util.Date;

public class ProgCobDesejoDante {
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
        ProgCobDesejoDante program = new ProgCobDesejoDante();
        program.executar();
    }

    public void executar() {
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

        System.out.println("======================================================");
        System.out.println("DATA ATUAL: " + ws03Dia + "/" + ws03Mes + "/" + ws03Ano);
        System.out.println("======================================================");

        ws03Informacoes(ws77Cad);
        System.out.println("CAD: " + ws05Nome + " " + ws05AnoEntrada + " " + ws05Salario);

        ws03Informacoes(ws77Cad1);
        System.out.println("CAD1: " + ws05Nome + " " + ws05AnoEntrada + " " + ws05Salario);
        System.out.println("======================================================");
    }

    public void processar() {
        ws03Informacoes(ws77Cad);
        ws77TempoCasa = ws03Ano - ws05AnoEntrada;
        ws77Aumento = calcularAumento(ws77TempoCasa, ws05Salario);

        System.out.println("TEMPO DE CASA LUCAS: " + ws77TempoCasa + " ANO(S)");
        System.out.println("AUMENTO LUCAS: R$ " + ws77Aumento);
        System.out.println("======================================================");
    }

    public void finalizar() {
        ws03Informacoes(ws77Cad1);
        ws77TempoCasa = ws03Ano - ws05AnoEntrada;
        ws77Aumento = calcularAumento(ws77TempoCasa, ws05Salario);

        System.out.println("TEMPO DE CASA DANTE: " + ws77TempoCasa + " ANO(S)");
        System.out.println("AUMENTO DANTE: R$ " + ws77Aumento);
        System.out.println("======================================================");
    }

    public void ws03Informacoes(String cad) {
        ws05Nome = cad.substring(0, 30).trim();
        ws05AnoEntrada = Integer.parseInt(cad.substring(30, 34));
        ws05Salario = Integer.parseInt(cad.substring(34, 42));
    }

    public double calcularAumento(int tempoCasa, int salario) {
        if (tempoCasa >= 0 && tempoCasa <= 1) {
            return 0;
        } else if (tempoCasa >= 2 && tempoCasa <= 5) {
            return (salario / 100.0) * 0.05;
        } else if (tempoCasa >= 6 && tempoCasa <= 15) {
            return (salario / 100.0) * 0.10;
        } else {
            return (salario / 100.0) * 0.15;
        }
    }
}