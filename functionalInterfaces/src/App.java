import java.util.Scanner;
import java.util.function.Predicate;

public class App {
    //#region Utilitarios
        static Scanner entrada = new Scanner(System.in);

        //#region Ler dados
            static int lerInteiro(String mensagem){
                System.out.print(mensagem);
                int v = Integer.parseInt(entrada.nextLine());
                return v;
            }

            static double lerDouble(String mensagem){
                System.out.print(mensagem);
                double v = Double.parseDouble(entrada.nextLine());
                return v;
            }

            static String lerString(String mensagem){
                System.out.print(mensagem);
                String v = entrada.nextLine();
                return v;
            }
        //#endregion

        //#region Menu
        static String menu(){
            StringBuilder s = new StringBuilder();
            s.append("\n---------");
            s.append("\n0) Sair\n");
            s.append("\n--- Predicate ---");
            s.append("\n1) Par ou Impar");
            s.append("\n2) String não vazia");
            s.append("\n3) Maior de idade");
            s.append("\n4) Número positivo");

            return s.toString();
        }

        //#endregion

    //#endregion

    //#region Predicate
        /*
            Predicate<T> nome = condicao // retorna um boolean
            Uso: filtros
        */

        static String parOuImpar(Predicate<Integer> condicao, int numero){
            return condicao.test(numero) ? numero + " é par" : numero + " é impar";
        }

        static String stringNaoNula(Predicate<String> condicao, String s){
            return condicao.test(s) ? "Vazia ou nula" : "Preenchida";
        }

        static String maiorDeIdade(Predicate<Integer> condicao, int numero){
            return condicao.test(numero) ? "É maior de idade" : "É menor de idade";
        }

    //#endregion
    public static void main(String[] args) throws Exception {
        Predicate<Integer> filtraNumeroPar = i -> i % 2 == 0;
        Predicate<String> filtraStringVazia = s -> s == null || s.isBlank();
        Predicate<Integer> filtrarMaiorIdade = i -> i >= 18;

        int escolha;

        do{
            System.out.print(menu());
            escolha = lerInteiro("\n\nDigite o exercício: ");
            switch(escolha){
                case 1 -> {
                    int n = lerInteiro("\nDigite o valor: ");
                    System.out.print(parOuImpar(filtraNumeroPar, n));
                    
                }
                case 2 ->{
                    String s = lerString("\nInsira a frase a ser verificada: ");
                    System.out.println(stringNaoNula(filtraStringVazia, s));
                }
                case 3 -> {
                    int idade = lerInteiro("\nDigite a idade: ");
                    System.out.println(maiorDeIdade(filtrarMaiorIdade, idade));
                }
            }

        } while(escolha != 0);

    }

}
