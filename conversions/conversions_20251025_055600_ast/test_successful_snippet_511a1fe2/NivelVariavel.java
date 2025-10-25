import java.util.Arrays;

public class NivelVariavel {
    public static void main(String[] args) {
        String NOME = "Lucas Kurata";
        String RG = "559810386";
        String CPF = "50885168860";
        String ENDERECO = "Av Sousa Bandeira";
        String DIA = "23/";
        String MES = "06/";
        String ANO = "2000";
        String DIA_MES = DIA + MES;
        String CONSTANTES = "CONST";

        char BOOLEANO = 'F';
        boolean FEMININO = BOOLEANO == 'F';

        System.out.println(NOME);
        System.out.println(RG);
        System.out.println(CPF);
        System.out.println(ENDERECO);
        System.out.println(DIA + MES + ANO);
        System.out.println(DIA_MES);
        System.out.println(CONSTANTES);

        if (FEMININO) {
            System.out.println(BOOLEANO);
        }
    }
}