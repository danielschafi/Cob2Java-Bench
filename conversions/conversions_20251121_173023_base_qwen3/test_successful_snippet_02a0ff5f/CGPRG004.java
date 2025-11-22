import java.io.*;
import java.text.*;
import java.util.*;

public class CGPRG004 {
    static String wsFim = "N";
    static int wsCtxib = 0;
    static int asCep = 9000400;
    static double asFrente = 22.50;
    static double asFundo = 80.00;
    static double asValMetro = 2315.00;
    static double asValVenda = 0.0;
    static double asComissao = 0.0;
    static String asData = "";
    static String asHora = "";
    static int wsCep = 0;
    static double wsFrente = 0.0;
    static double wsFundo = 0.0;
    static double wsValMetro = 0.0;
    static double wsValVenda = 0.0;
    static double wsComissao = 0.0;
    static String wsMensagem = "";

    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH.mm.ss");
        asData = sdfDate.format(cal.getTime());
        asHora = sdfTime.format(cal.getTime());
        iniciar();
        do {
            processar();
        } while (!wsFim.equals("S"));
        terminar();
    }

    static void iniciar() {
        System.out.println("** ATIVIDADE 4 **");
        System.out.println("** ANA CAROLINA GOMES DA SILVA **");
        System.out.println("** CALCULO DO PRECO DE VENDA DE UM TERRENO **");
        System.out.println("** RETANGULAR **");
        System.out.println("DATA ATUAL: " + asData);
        System.out.println("HORA ATUAL: " + asHora);
        System.out.println("---------------------");
        wsCtxib = 0;
    }

    static void processar() {
        System.out.println("** PROCESSAMENTO **");
        asValVenda = asFrente * asFundo * asValMetro;
        if (asValVenda > 1500000) {
            asComissao = asValVenda * 0.04;
            wsMensagem = "ALTO PADRAO";
        } else {
            asComissao = asValVenda * 0.06;
            wsMensagem = "MEDIO PADRAO";
        }
        wsCep = asCep;
        wsFrente = asFrente;
        wsFundo = asFundo;
        wsValMetro = asValMetro;
        wsValVenda = asValVenda;
        wsComissao = asComissao;
        DecimalFormat df1 = new DecimalFormat("00000000");
        DecimalFormat df2 = new DecimalFormat("000.00");
        DecimalFormat df3 = new DecimalFormat("00000.00");
        DecimalFormat df4 = new DecimalFormat("000000.00");
        DecimalFormat df5 = new DecimalFormat("0000000.00");
        System.out.printf("%s %s %s M %s M %s M2 %s %s %s%n", 
            df1.format(wsCep), 
            df2.format(wsFrente).replace('.', ','), 
            df2.format(wsFundo).replace('.', ','), 
            df3.format(wsValMetro).replace('.', ','), 
            df4.format(wsValVenda).replace('.', ','), 
            df5.format(wsComissao).replace('.', ','), 
            wsMensagem);
        wsCtxib += 1;
        wsFim = "S";
    }

    static void terminar() {
        System.out.println("---------------------------");
        System.out.println("** ENCERRANDO A EXECUCAO **");
        System.out.println("REGISTROS EXIBIDOS = " + wsCtxib);
        System.out.println("TERMINO NORMAL DO PROGRAMA CGPRG004");
    }
}