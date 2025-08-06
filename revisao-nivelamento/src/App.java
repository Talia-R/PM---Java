import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner escolha = new Scanner(System.in);

        System.out.println(
            "Menu\n" +
              "1) Divisora\n" +
              "2) Maior Número"
            );
        System.out.println("Escolha: ");
        int numero = escolha.nextInt();


        switch (numero) {
            case 1:
            try{
                double primeiroDigito = 0;
                double segundoDigito = 0;
                System.out.println("Digite o primeiro valor: ");
                primeiroDigito = escolha.nextDouble();
                System.out.println("Digite o segundo valor: ");
                segundoDigito = escolha.nextDouble();
                System.out.println(primeiroDigito + "/" + segundoDigito + " = " + divisora(primeiroDigito, segundoDigito));
            } catch (InputMismatchException ime){
                System.out.println("Erro: ambos os digitos devem ser numerais");
                escolha.nextLine();

            } catch(ArithmeticException ae){
                System.out.println(ae.getMessage());
            }
            break;
            case 2:
            try{
                double primeiroDigito = 0;
                double segundoDigito = 0;
                double terceiroDigito = 0;
                System.out.println("Digite o primeiro valor: ");
                primeiroDigito = escolha.nextDouble();
                System.out.println("Digite o segundo valor: ");
                segundoDigito = escolha.nextDouble();
                System.out.println("Digite o terceiro valor: ");
                terceiroDigito = escolha.nextDouble();
                System.out.println("O maior valor é: " + maiorNumero(primeiroDigito, segundoDigito, terceiroDigito));
                } catch (InputMismatchException ime){
                    System.out.println("Erro: ambos os digitos devem ser numerais");
                    escolha.nextLine();
                }
            break;
        }

       escolha.close();
        
    }

    /**
     * Divide dois números, caso o divisor seja zero a função lança uma ArithmeticException
     * @param x dividendo
     * @param y divisor não nulo
     * @return
     */
    public static double divisora(double  x, double  y){
        if( y == 0) {
            throw new ArithmeticException("Erro: divisão por zero");
        }
        return x/y;
    }

    /**
     * Calcula qual o maior número entre três números inseridos
     * @param x primeiro número
     * @param y segundo número
     * @param z terceiro número
     * @return retorna o maior número
     */
    public static double maiorNumero(double x, double y, double z){
        double maiorNumero = x > y && x > z ? x : y > x && y > z ? y : z;
        return maiorNumero;
    }


}


