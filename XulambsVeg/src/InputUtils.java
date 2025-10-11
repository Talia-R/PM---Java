import java.util.Scanner;

public class InputUtils {
    private static final Scanner entrada = new Scanner(System.in);

    /**
     * Lê um inteiro, exibindo uma mensagem antes da leitura.
     * @param msg mensagem a ser exibida antes da leitura.
     * @return inteiro lido
     * @throws NumberFormatException se a entrada não puder ser convertida em número
     */
    public static int lerInt(String msg){
        System.out.print(msg);
        return Integer.parseInt(entrada.nextLine());
    }

    public static double lerDouble(String msg){
        System.out.print(msg);
        return Double.parseDouble(entrada.nextLine());
    }

    /**
     * Lê uma string, mostrando uma mensagem antes da leitura.
     * @param msg mensagem a ser exibida antes da leitura.
     * @return String lida.
     */
    public static String lerString(String msg){
        if(msg.isEmpty()){
        return entrada.nextLine();
        }
        System.out.print(msg);
        return entrada.nextLine();
    }

    /**
     * Fecha o Scanner
     */
    public static void fechar(){
        entrada.close();
    }
}
