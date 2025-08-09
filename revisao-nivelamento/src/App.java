import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner entrada = new Scanner(System.in);

        System.out.println("Escolha o exercicio: \n" +
                            "1) Menu de Interações Númericas\n" +
                            "2) Desenhar Retângulo\n" +
                            "3) Avaliação do Professor\n" +
                            "4) Criptografar String\n" +
                            "5) Validar Data\n" +
                            //6) Ideia: colocar assim: vc deseja inserir data ou ler de um arquivo existente?
                            "");
        System.out.print("Escolha: ");

        int opcaoEscolhida = entrada.nextInt();


        switch(opcaoEscolhida){
            case 1:
            System.out.println("-".repeat(10) + "Menu de Interações Númericas" + "-".repeat(10));
            menuInteracaoNumerica();
            break;
            case 2:
            System.out.println("-".repeat(10) + "Desenhar Retângulo" + "-".repeat(10));
            try{
            System.out.println("Digite a largura");
            int largura = entrada.nextInt();

            System.out.println("Digite a altura");
            int altura = entrada.nextInt();

            System.out.println("Digite o deslocamento");
            int deslocamento = entrada.nextInt();

            int linhasCompletas = 2;
            preencherLinhasCompletas(largura, deslocamento);
            preencherLinhasVazias(altura, largura, linhasCompletas, deslocamento);
            preencherLinhasCompletas(largura, deslocamento);
            } catch (InputMismatchException ime){
                System.out.println("Todos os valores devem ser numerais");
            }
            break;
            case 3:
            try{
            System.out.println("-".repeat(10) + "Avaliação Professor" + "-".repeat(10));
            ArrayList<Double> todasNotasExercicios = new ArrayList<>();
            ArrayList<Double> todasNotasProvas = new ArrayList<>();

            System.out.println("Insira as notas do aluno: ");

            for(int i = 0; i < 4; i++){
                System.out.print("Nota exercício " + (i + 1) + ": ");
                double notaAtual = entrada.nextDouble();
                todasNotasExercicios.add(notaAtual);
            }
            
            todasNotasExercicios.add(somarNotas(todasNotasExercicios));

            for(int i = 0; i < 2; i++){
                System.out.print("Nota prova " + (i + 1) + ": ");
                double notaAtual = entrada.nextDouble();
                todasNotasProvas.add(notaAtual);
            }

            todasNotasProvas.add(somarNotas(todasNotasProvas));

            System.out.print("Nota trabalho: ");
            double notaTrabalho = entrada.nextDouble();

            System.out.print(calcularNotaFinal(todasNotasExercicios.getLast(),todasNotasProvas.getLast(), notaTrabalho));
            } catch (InputMismatchException ime){
                System.out.println("Todos os valores devem ser numerais");
            }
            break;
            case 4:
            // criptografarString();
            break;
            case 5:
            try{
            String dataValidada = "";
            do {
            System.out.println("Insira a data: ");
            String data = entrada.next();
            List<Integer> dataFormatada = dividirData(data);
            dataValidada = validarData(dataFormatada);
            System.out.println(dataValidada);
            } while(!dataValidada.equals("Data correta"));
            } catch (NumberFormatException nfe){
                System.out.println("Todos os valores devem ser numerais");
            }
            break;
        }
        entrada.close();
    }

    //#region Funções para o 'Menu de Interações Númericas'
    /**
     * Menu para as questões do exercicio 1
     */
    public static void menuInteracaoNumerica(){
        ArrayList<Double> todosNumeros = new ArrayList<>();

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
                            "5) Imprimir quantidades de números pares/impares\n" +
                            "6) Mostrar valores armazenados\n" +
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
                case 5:
                    System.out.println(parOuImpar(todosNumeros));
                    break;
                case 6:
                    if(todosNumeros.isEmpty()){
                        System.out.println("Não há números armazenados");
                        break;
                    }
                    System.out.println(todosNumeros);
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

    /**
     * Reitera sobre cada item de uma lista e compara se o resto desse número em uma divisão por 2 é zero. Se for zero, 
     * incrementa o valor par e pula para o próximo número da lista. Se não, incrementa o valor impar.
     * @param lista uma ArrayList contendo valores que podem ter sido salvos previamente
     * @return retorna uma string informando quantos números pares e impares existem na lista
     */
    public static String parOuImpar(ArrayList<Double> lista){
        int par = 0;
        int impar = 0;
        for (double numero : lista){
            if(numero % 2 == 0){
                par++;
                continue;
            }
            impar++;
        }
        return "Valores par: " + par + " Valores impar: " + impar;
    }
    
    //#endregion

    //#region Menu para os demais exercícios

    //#region Desenhar Retângulo
    /**
     * Imprime na tela um deslocamento e imprime o restante da mesma linha com: X
     * @param largura inteiro que define o tamanho horizontal do retângulo
     * @param deslocamento inteiro para definir o espaçamento entre o inicio do retângulo e o lado esquerdo da tela
     */
    public static void preencherLinhasCompletas(int largura, int deslocamento){
        preencherDeslocamento(deslocamento);
        System.out.println("X".repeat(largura));
    }

    /**
     * Faz um loop excluindo as duas linhas completas, imprime o deslocamento escolhido pelo usuário, imprime um X,
     * imprime o restante da linha com espaços vazios e finaliza imprimindo um X novamente.
     * @param altura inteiro que define o tamanho (vertical) do retângulo
     * @param largura inteiro que define o tamanho (horizontal) do retângulo
     * @param linhasCompletas linhas do topo e do final do retângulos definido pela função: preencherLinhasCompletas
     * @param deslocamento inteiro para definir o espaçamento entre o inicio do retângulo e o lado esquerdo da tela
     */
    public static void preencherLinhasVazias(int altura, int largura, int linhasCompletas, int deslocamento){
        for(int i = 0; i < altura - linhasCompletas; i++){
            preencherDeslocamento(deslocamento);
            System.out.print('X');
            System.out.print(" ".repeat(largura - linhasCompletas));
            System.out.println('X');
        }
    }

    /**
     * Imprime espaços vazios
     * @param deslocamento inteiro para definir o espaçamento entre o inicio do retângulo e o lado esquerdo da tela
     */
    public static void preencherDeslocamento(int deslocamento){
        System.out.print(" ".repeat(deslocamento));
    }
    //#endregion

    //#region Avaliação Professor
    /**
     * Recebe uma ArrayList com notas e faz uma reiteração somando o valor de cada nota e adiciona a variável pontuação
     * @param listaNotas ArrayList com notas
     * @return a soma de todas as notas da lista
     */
    public static double somarNotas(ArrayList<Double> listaNotas){
        double pontuacao = 0;

        for (double nota : listaNotas){
            pontuacao += nota;
        }
        return pontuacao;
    }

    /**
     * Calcula a média de cada nota pela fórmula: (pontuação/qnt de avaliações) * peso
     * Soma todas as médias mais a nota do trabalho e formata o resultado final em uma string
     * @param notaExercicios soma das notas de todos os exercícios
     * @param notaProvas soma das notas de todas as provas
     * @param notaTrabalho nota total do trabalho
     * @return nota formatada em um string com 2 casas decimais
     */
    public static String calcularNotaFinal(double notaExercicios, double notaProvas, double notaTrabalho){
        double notaFinal = ((notaExercicios/4.0) * 0.2) + ((notaProvas/2.0) * 0.6) + notaTrabalho;
        String notaFormatada = String.format("Nota final: %.2f", notaFinal);
        return notaFormatada;
    }
    
    //#endregion

    //#region Criptografar String

    public static void criptografarString(){

        ArrayList<String> listaFora= new ArrayList<>();
        String frase = "Teste Roxo Azul";

        // System.out.println(frase.split(""));




        // String frase = "mensagem secreta";
        // ArrayList<String> fraseArrayList = new ArrayList<>();

        // int limiteCaracters = 5;

        // fraseArrayList.add(frase.substring(0,5));
        // // System.out.println(fraseArrayList);

        // fraseArrayList.add(frase.substring(5,10));
        // // System.out.println(fraseArrayList);

        // fraseArrayList.add(frase.substring(10,15));
        // // System.out.println(fraseArrayList);

        // fraseArrayList.add(frase.substring(15));
        // System.out.println(fraseArrayList);

        // // System.out.print(fraseArrayList.getFirst().substring(0,1));
        // // System.out.print(fraseArrayList.get(1).substring(0,1));

        // for (String bloco : fraseArrayList){
        //     System.out.println(Arrays.asList(bloco.split(",")));
          
        // }


    }
    //#endregion

    //#region Validar Data

    /**
     * Recebe como argumento uma string que é dividida em data,mês, ano e convertida para inteiros. Retorna a lista de 
     * inteiros.
     * @param data string no formato DD/MM/AAAA
     * @return lista de datas convertidas em inteiros
     */
    public static List<Integer> dividirData(String data){
        List<String> dataString = Arrays.asList(data.split("/"));
        List<Integer> dataInt = new ArrayList<>();

        for(String digito : dataString){
            dataInt.add(Integer.parseInt(digito));
        }
        return dataInt;
    }

    /**
     * Recebe como argumento uma lista de inteiros (data), onde cada posição está na ordem: data/mês/ano e verifica se
     * os argumentos são validos
     * @param data lista de inteiros com data/mes/ano
     * @return string informando se a data está valida
     */
    public static String validarData(List<Integer> data){
        
        if(data.get(0) < 1 || data.get(0) > 31){
            return ("Data incorreta: " + data.get(0));

        } else if(data.get(1) < 1 || data.get(1) > 12) {
            return ("Mês incorreto: " + data.get(1));
        } else if(data.get(2) < 1000){
            return ("Ano incorreto: " + data.get(2));
        } 
        
        return "Data correta";
    }

    //#endregion
}


