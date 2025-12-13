import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
class Pessoa{
    String nome;
    int idade;

    public Pessoa(String nome, int idade){
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome(){
        return nome;
    }

    public int getIdade(){
        return idade;
    }

    @Override
    public String toString(){
        return nome + " | " + idade; 
    }

}
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
            s.append("\n\n--- Function ---");
            s.append("\n4) Dobrar número");
            s.append("\n5) Verificar tamanho de uma frase");
            s.append("\n6) Extrair idade da pessoa");
            s.append("\n\n--- Consumer ---");
            s.append("\n7) Imprimir número no console");
            s.append("\n8) Imprimir string em maiúsculas");
            s.append("\n9) Fazer aniversário");
            s.append("\n\n--- Comparator ---");
            s.append("\n10) Ordenar por idade (crescente)");
            s.append("\n11) Ordenar por nome (ordem alfabética)");
            s.append("\n\n--- Streams ---");
            s.append("\n12) Maior de idade + ordem alfabética + somente nome");
            s.append("\n13) Contar pessoas menores de idade");
            s.append("\n14) Lista de idades únicas");
            s.append("\n15) Pessoa mais velha");
            s.append("\n16) Pessoa mais nova");
            s.append("\n17) Média de idade");
            s.append("\n18) Soma total das idades");
            s.append("\n19) Mensagem personalizada");
            s.append("\n20) Nome mais longo");


            return s.toString();
        }

        //#endregion

    //#endregion

    //#region Predicate<T>
        /*
            Predicate<T> nome = condicao // retorna um boolean
            metodo principal: test()
            recebe um valor e retorna boolean
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

        static String pessoaMaiorDeIdade(Predicate<Pessoa> condicao, Pessoa pessoa){
            return condicao.test(pessoa) ? "É maior de idade" : "É menor de idade";
        }

    //#endregion
    
    //#region Function<T,R>
        /*
            Function<T, R> = representa uma transformação de T(entrada) em R(retorno)
            metodo principal: apply
            recebe um valor e retorna qualquer coisa
        */
       static int dobrarNumero(Function<Integer, Integer> funcao, int numero){
            return funcao.apply(numero);    
       }

       static int verificarCaracters(Function<String,Integer> funcao, String frase){
        return funcao.apply(frase);
       }

       static int verificarIdade(Function<Pessoa,Integer> funcao, Pessoa pessoa){
        return funcao.apply(pessoa);
       }
    //#endregion

    //#region Consumer<T>
        /*
            Consumer<T> = representa uma ação,
            metodo principal: accept
            recebe um valor e não retorna nada
        */

    //#endregion

    //#region Comparator<T>
       /*
        Compara dois objetos 
        retorna: -1 -> this é menor | 0 -> são iguais | 1 -> this é maior
        metodos: Collections.sort(), List.sort(), Strem.sorted()
       */
    //#endregion
    public static void main(String[] args) throws Exception {

        List<Pessoa> list = new LinkedList<>(
        List.of(
            new Pessoa("Bryce", 80),
            new Pessoa("Laurinha", 2),
            new Pessoa("Nino", 4),
            new Pessoa("Feyre", 20),
            new Pessoa("Aelin", 20),
            new Pessoa("Clare", 18),
            new Pessoa("Saori", 18),
            new Pessoa("Vin", 21),
            new Pessoa("Siri", 18)
        )
        );

        Predicate<Integer> filtraNumeroPar = i -> i % 2 == 0;
        Predicate<String> filtraStringVazia = s -> s == null || s.isBlank();
        Predicate<Integer> filtrarMaiorIdade = i -> i >= 18;
        Predicate<Pessoa> filtrarPessoaIdade = p -> p.idade >= 18;

        Function<Integer, Integer> dobraNumero = i -> i*2;
        Function<String, Integer> verificaQntCaracters = s -> s == null ? -1 : s.trim().length();
        Function<Pessoa, Integer> extraiIdade = p -> p.idade;
        Function<Pessoa, String> fraseComIdade = p -> String.format("%s tem %d anos", p.nome, p.idade);

        Consumer<Integer> imprimirConsole = i -> System.out.println(i);
        Consumer<String> imprimirMaiusculo = s -> System.out.println(s.toUpperCase());
        Consumer<Pessoa> imprimirPessoa = p -> System.out.println(String.format("Nome: %s | Idade: %d", p.nome, p.idade));
        Consumer<Pessoa> aumentarIdade = p -> ++p.idade; 

        // Comparator<Pessoa> porIdade = (p1, p2) -> p1.idade - p2.idade;
        Comparator<Pessoa> porIdade = Comparator.comparing(Pessoa::getIdade);
        Comparator<Pessoa> ordemAlfabetica = Comparator.comparing(Pessoa::getNome);

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
                case 4 -> {
                    int numero = lerInteiro("\nDigite um número: ");
                    System.out.println(dobrarNumero(dobraNumero, numero));
                }
                case 5 -> {
                    String frase = lerString("\nDigite a frase: ");
                    System.out.println(verificarCaracters(verificaQntCaracters, frase));
                }
                case 6 -> {
                    String nome = lerString("\nDigite o nome: ");
                    int idade = lerInteiro("\nDigite a idade: ");
                    Pessoa p = new Pessoa(nome, idade);
                    System.out.println(verificarIdade(extraiIdade, p));
                    System.out.println(fraseComIdade.apply(p));
                }
                case 7 -> {
                    int numero = lerInteiro("\nDigite um número: ");
                    imprimirConsole.accept(numero);
                }
                case 8 -> {
                    String frase = lerString("\nDigite a frase: ");
                    imprimirMaiusculo.accept(frase);
                }
                case 9 ->{
                    String nome = lerString("\nDigite o nome: ");
                    int idade = lerInteiro("\nDigite a idade: ");
                    Pessoa p = new Pessoa(nome, idade);
                    int aumentar;
                    do{
                        imprimirPessoa.accept(p);
                        aumentar = lerInteiro("0) Sair\n1)Aumentar idade ");
                        if(aumentar == 1)
                            aumentarIdade.accept(p);
                    } while(aumentar != 0);
                }
                case 10 -> {
                    StringBuilder s = new StringBuilder();
                    list.sort(porIdade);
                    for(Pessoa p : list){
                        s.append("\n" + p.toString());
                    }
                    System.out.println(s);
                }
                case 11 ->{
                    StringBuilder s = new StringBuilder();
                    list.sort(ordemAlfabetica);
                    for(Pessoa p : list){
                        s.append("\n" + p.toString());
                    }
                    System.out.println(s);
                }
                case 12 -> {
                    list.stream()
                    .filter(p -> p.getIdade() >= 18)
                    .sorted(Comparator.comparing(Pessoa::getNome))
                    .map(Pessoa::getNome)
                    .forEach(System.out::println);
                }
                case 13 ->{
                    long qnt = list.stream()
                    .filter(p -> p.getIdade() > 0 && p.getIdade() < 18)
                    .count();
                    System.out.println(qnt + " menor(es) de idade.");
                }
                case 14 -> {
                    list.stream()
                    .mapToInt(p -> p.getIdade())
                    .distinct()
                    .sorted()
                    .forEach(System.out::println);
                    ;
                }
                case 15 -> {
                    list.stream()
                    .max(Comparator.comparing(Pessoa::getIdade))
                    .ifPresent(System.out::println);
                    ;
                }
            }

        } while(escolha != 0);

    }

}
