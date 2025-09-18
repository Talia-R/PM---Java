import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.LinkedList;

public class App {
    static LinkedList<Pizza> pizzas = new LinkedList<>();
    static NumberFormat moeda = NumberFormat.getCurrencyInstance();

    public static String detalheDivisor(String divisor, int repeticoes){
        return divisor.repeat(repeticoes);
    }

    public static String detalheDivisorTraco(){
        return "-".repeat(20);
    }

    public static String cabecalho(){
        return " --- Bem-vindo(a) ao XulambsVeg! ---";
    }

    public static String cardapio(){
        StringBuilder s = new StringBuilder();
        s.append("Cardápio: ");
        s.append("\n0) Sair");
        s.append("\n1) Pizza: " + moeda.format(Pizza.getPrecoPadrao()));
        s.append("\n");
        s.append(detalheDivisorTraco());
        
        return s.toString();
    }

    /**
     * Cria um objeto pizza e o adiciona na lista de Pizzas.
     * Exibe o relatorio (nota de compra).
     */
    public static void comprarPizza(){
        Pizza novaPizza = new Pizza();
        montarPizza(novaPizza);
        pizzas.add(novaPizza);
        System.out.println(notaDeCompra());
        System.out.println(detalheDivisorTraco());
    }

    public static void montarPizza(Pizza pizza){
        System.out.print("Vamos montar sua pizza: ");
        int qntAdicionais = InputUtils.lerInt("\nQuantos adicionais gostaria de incluir (máx: " + 
                                                Pizza.getMaxIngredientesAdicionais() +
                                                ")?: ");

        pizza.editarPizza(1, qntAdicionais);
    }

    public static String notaDeCompra(){
        StringBuilder s = new StringBuilder();

        for(int i = 0; i < pizzas.size(); i++){
            s.append(String.format("%d) %s", (i+1), pizzas.get(i).gerarNotaDescritiva()));

            if(i >= 1){
                int precoFinal = 0;
                for(Pizza pizza: pizzas){
                    precoFinal += pizza.getPrecoFinal();
                }
                s.append("\nTotal Pedido: " + moeda.format(precoFinal));
            }

            // quebra de linha apos pizzas:
            if(pizzas.size() >= 1 && i == pizzas.size() - 1){
                break;
            }
            s.append("\n");
        }

        return s.toString();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(cabecalho());

        int escolha = -1;
        do{
        try{
       
            System.out.println(cardapio());
            escolha = InputUtils.lerInt("Opção:  ");
            switch(escolha){
                case 1 -> comprarPizza();
            //    case 3 -> editarPedido();
                case 0 -> {
                    System.out.print("Até a próxima! =^.^=");
                    continue;
                }
            }
            escolha = InputUtils.lerInt("Gostaria de pedir algo mais? 0) Não | 1) Sim ");
            System.out.print("Até a próxima! =^.^=");
            
        } catch (NullPointerException npe){
            System.out.print("Entrada vazia: " + npe.getMessage());
        } catch (InputMismatchException ime){
            System.out.println("Valor de entrada inválida: " + ime.getMessage());
        } catch(NumberFormatException nfe){
            System.out.println("Digite um número válido: " + nfe.getMessage());
        }
    } while (escolha != 0);

        InputUtils.fechar();
    }


    /*
     * pro futuro
     * public static void editarPedido(){
     *  int escolha = InputUtils.lerInt("1) Concluir Pedido | 2) Editar Pizza");

        while (escolha == 2) {
        escolha = InputUtils.lerInt("1) Adicionar Ingredientes | 2) Remover Ingredientes");
        int novaQntIngredientes = InputUtils.lerInt("\nQuantos ingredientes?: ");
        novaPizza.editarPizza(escolha, novaQntIngredientes);
        }
    }
     */

}
