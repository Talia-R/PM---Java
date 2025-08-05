import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner escolha = new Scanner(System.in);
        System.out.println(
            "Menu\n" +
              "1) Dividir"
            );
        System.out.println("Escolha: ");
        int numero = escolha.nextInt();

        switch (numero) {
            case 1:
            try{
                System.out.println("Digite o primeiro valor: ");
                double primeiroDigito = escolha.nextDouble();
                System.out.println("Digite o segundo valor: ");
                double segundoDigito = escolha.nextDouble();
                System.out.println(divisora(primeiroDigito, segundoDigito));
            } catch (InputMismatchException ime){
                System.out.println("Erro: ambos os digitos devem ser numerais");
            } catch(ArithmeticException ae){
                System.out.println(ae.getMessage());
            }
            break;
        }

       escolha.close();
        
    }

    public static double divisora(double  x, double  y){
        if( y == 0) {
            throw new ArithmeticException("Erro: divisão por zero");
        }

        return x/y;
    }
}


