import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.LinkedList;

public class App {
    // static LinkedList<Pizza> pizzas = new LinkedList<>();
    static LinkedList<Pedido> pedidos = new LinkedList<>();
    static NumberFormat moeda = NumberFormat.getCurrencyInstance();

    /**
     * Retorna uma string formada pela repetição do divisor informado.
     * @param divisor o texto que será repetido
     * @param repeticoes número de vezes que o divisor deve ser repetido
     * @return uma nova string composta pela repetição do divisor
     * @throws IllegalArgumentException se repeticoes for negativo
     */
    public static String detalheDivisor(String divisor, int repeticoes){
        return divisor.repeat(repeticoes);
    }

    /**
     * Retorna uma linha divisória composta por 20 traços.
     * Útil para separar visualmente informações em saídas
     * @return  uma string contendo 20 caracteres de traço ("-")
     */
    public static String detalheDivisorTraco(){
        return "-".repeat(20);
    }

    /**
     * Retorna uma mensagem de boas-vindas padrão do sistema.
     * "Bem-vindo(a) ao XulambsVeg!".
     * @return uma string com a mensagem de boas-vindas
     */
    public static String cabecalho(){
        return " --- Bem-vindo(a) ao XulambsVeg! ---";
    }

    /**
     * Exibe o cardápio enumerado
     * @return cardápio com itens enumerados.
     */
    public static String cardapio(){
        StringBuilder s = new StringBuilder();
        s.append("Cardápio: ");
        s.append("\n0) Sair");
        s.append("\n1) Pizza: " + moeda.format(Pizza.getPrecoPadrao()));
        s.append("\n");
        s.append(detalheDivisorTraco());
        
        return s.toString();
    }

    //#region Pizza
        /**
         * Cria um objeto pizza e o adiciona na lista de Pizzas.
         * Exibe o relatorio (nota de compra).
         */
        public static Pizza comprarPizza(){
            Pizza novaPizza = new Pizza();
            montarPizza(novaPizza);
            // pizzas.add(novaPizza);
            System.out.println(notaDeCompra(novaPizza));
            System.out.println(detalheDivisorTraco());
            return novaPizza;
        }

        /**
         * Monta um pizza de acordo com os adicionais passados.
         * @param pizza pizza a ser alterada de acordo com os adicionais passados
         */
        public static void montarPizza(Pizza pizza){
            System.out.print("Vamos montar sua pizza: ");
            int qntAdicionais = InputUtils.lerInt("\nQuantos adicionais gostaria de incluir (máx: " + 
                                                    Pizza.getMaxIngredientesAdicionais() +
                                                    ")?: ");

            pizza.editarPizza(1, qntAdicionais);
        }

        /**
         * Exibe a nota de compra (relatorio) de uma pizza
         * @param pizza a ter a nota exibida
         * @return nota de compra da pizza
         */
        public static String notaDeCompra(Pizza pizza){
            StringBuilder s = new StringBuilder();
            System.out.println(pizza.relatorio());
            return s.toString();
        }
    //#endregion

    //#region Pedido
        public Pedido abrirPedido(){
            return new Pedido();
        }

        public String relatorioPedido(int idPedido){
            for(Pedido pedido : pedidos){
                if(pedido.getIdPedido() == idPedido)
                    return pedido.relatorio();    
            }
            return "Pedido não encontrado";
        }

        public Pedido localizarPedido(int idPedido){
            for(Pedido pedido : pedidos){
                if(pedido.getIdPedido() == idPedido)
                    return pedido;
            }
            return pedidoProcurado;
        }

        public void fecharPedido(Pedido pedido){
            pedidos.add(pedido);
            pedido.fecharPedido();
        }
    //#endregion

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
                if(escolha == 0)
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


    /* pro futuro editarPedido
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
