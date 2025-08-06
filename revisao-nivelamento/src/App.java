import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class App {

    static ArrayList<Double> todosNumeros = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        Scanner escolha = new Scanner(System.in);
        int numero = 1;

        
        do {
            System.out.println("-".repeat(10));
            System.out.println(
                    "Menu\n" +
                            "1) Divisora\n" +
                            "2) Maior Número\n" +
                            "3) Armazenar Número\n" +
                            "4) Somar Todos Números Armazenados\n" +
                            "0) Sair");
            System.out.println("-".repeat(10));
            System.out.println("Digite uma opção: ");
            numero = escolha.nextInt();

            switch (numero) {
                case 1:
                    try {
                        double primeiroDigito = 0;
                        double segundoDigito = 0;
                        System.out.println("Digite o primeiro valor: ");
                        primeiroDigito = escolha.nextDouble();
                        System.out.println("Digite o segundo valor: ");
                        segundoDigito = escolha.nextDouble();
                        System.out.println(
                                primeiroDigito + "/" + segundoDigito + " = " + divisora(primeiroDigito, segundoDigito));
                    } catch (InputMismatchException ime) {
                        System.out.println("Erro: ambos os digitos devem ser numerais");
                        escolha.nextLine();

                    } catch (ArithmeticException ae) {
                        System.out.println(ae.getMessage());
                    }
                    break;
                case 2:
                    try {
                        double primeiroDigito = 0;
                        double segundoDigito = 0;
                        double terceiroDigito = 0;
                        System.out.println("Digite o primeiro valor: ");
                        primeiroDigito = escolha.nextDouble();
                        System.out.println("Digite o segundo valor: ");
                        segundoDigito = escolha.nextDouble();
                        System.out.println("Digite o terceiro valor: ");
                        terceiroDigito = escolha.nextDouble();
                        System.out.println(
                                "O maior valor é: " + maiorNumero(primeiroDigito, segundoDigito, terceiroDigito));
                    } catch (InputMismatchException ime) {
                        System.out.println("Erro: ambos os digitos devem ser numerais");
                        escolha.nextLine();
                    }
                    break;
                case 3:
                    try {
                        int encerrar = 1;
                        do {
                            System.out.println("Digite apenas um número para armazenamento: ");
                            double digito = escolha.nextDouble();
                            System.out.println("Números armazenados: " + armazenaNumeros(digito, todosNumeros));
                            System.out.println("-".repeat(20));
                            System.out.println("\nDeseja: 1 - Armazenar outro número | 0 - Sair");
                            encerrar = escolha.nextInt();
                        } while (encerrar != 0);
                    } catch (InputMismatchException ime) {
                        System.out.println("Erro: Os digitos devem ser numerais");
                        escolha.nextLine();
                    }
                    break;
                case 4:
                    System.out.println("A soma dos números armazenados é: " + (somaNumerosArmazenados(todosNumeros)));
                    break;
                default:
                if(numero == 0) {
                    System.out.println("Saindo...");
                    continue;
                }
                System.out.println("Entrada inválida");
                System.out.println("-".repeat(20));
                break;
            }
        } while (numero != 0);

        escolha.close();

    }

    /**
     * Divide dois números, caso o divisor seja zero a função lança uma
     * ArithmeticException
     * 
     * @param x dividendo
     * @param y divisor não nulo
     * @return
     */
    public static double divisora(double x, double y) {
        if (y == 0) {
            throw new ArithmeticException("Erro: divisão por zero");
        }
        return x / y;
    }

    /**
     * Calcula qual o maior número entre três números inseridos
     * 
     * @param x primeiro número
     * @param y segundo número
     * @param z terceiro número
     * @return retorna o maior número
     */
    public static double maiorNumero(double x, double y, double z) {
        double maiorNumero = x > y && x > z ? x : y > x && y > z ? y : z;
        return maiorNumero;
    }

    /**
     * Armazena um número por vez a lista
     * 
     * @param numeroAtual entrada do usuário
     * @return todos os números salvos na array
     */
    // public static ArrayList<Double> armazenaNumeros(double numeroAtual,
    // ArrayList<Double> lista){
    // // ArrayList<Double> todosNumeros = new ArrayList<>();
    // todosNumeros.add(numeroAtual);
    // return todosNumeros;
    // }
    public static ArrayList<Double> armazenaNumeros(double numeroAtual, ArrayList<Double> lista) {
        // ArrayList<Double> todosNumeros = new ArrayList<>();
        lista.add(numeroAtual);
        return lista;
    }

    /**
     * Chama uma lista, itera por cada um de seus elementos fazendo a soma deles.
     * @param lista uma ArrayList contendo valores que podem ter sido salvos previamente
     * @return a soma dos elementos da ArrayList
     */
    public static double somaNumerosArmazenados(ArrayList<Double> lista) {
        double soma = 0;
        for (double elemento : lista) {
            soma += elemento;
        }

        return soma;
    }

}
