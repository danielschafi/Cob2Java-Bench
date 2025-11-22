public class ConverteMetrosParaCentimetros {
    public static void main(String[] args) throws java.io.IOException {
        int WRK_NUM = 0;
        int WRK_REPETICAO = 0;
        int WRK_RESULTADO = 0;
        int WRK_RESTO = 0;
        int WRK_CENTENA = 0;
        int WRK_DEZENA = 0;
        int WRK_UNIDADE = 0;

        java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
        WRK_NUM = Integer.parseInt(reader.readLine().trim());

        while (WRK_REPETICAO != 1) {
            if (WRK_NUM > 0 && WRK_NUM < 1000) {
                WRK_RESULTADO = WRK_NUM / 100;
                WRK_RESTO = WRK_NUM % 100;
                WRK_CENTENA += WRK_RESULTADO;

                if (WRK_RESTO > 0) {
                    WRK_RESULTADO = 0;
                    WRK_RESULTADO = WRK_RESTO / 10;
                    WRK_RESTO = WRK_RESTO % 10;
                    WRK_DEZENA += WRK_RESULTADO;
                }

                if (WRK_RESTO > 0) {
                    WRK_RESULTADO = 0;
                    WRK_RESULTADO = WRK_RESTO / 1;
                    WRK_RESTO = WRK_RESTO % 1;
                    WRK_UNIDADE += WRK_RESULTADO;
                }

                System.out.println("CENTENA: " + WRK_CENTENA);
                System.out.println("DEZENA: " + WRK_DEZENA);
                System.out.println("UNIDADE: " + WRK_UNIDADE);

                WRK_REPETICAO += 1;
            }
        }
    }
}