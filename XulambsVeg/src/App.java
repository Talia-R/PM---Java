import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.LinkedList;

public class App {
    // static LinkedList<Pizza> pizzas = new LinkedList<>();
    static LinkedList<Pedido> todosOsPedidos = new LinkedList<>();
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
    
    static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Exibe o menu de opções possiveis no sistema
     * @return menu com opções numeradas
     */
    public static String menu(){
        StringBuilder s = new StringBuilder();
        s.append(detalheDivisorTraco());
        s.append("\n0) Sair");
        s.append("\n1) Abrir pedido");
        s.append("\n2) Alterar pedido");
        s.append("\n3) Relatório pedido");
        s.append("\n4) Encerrar pedido");
        s.append("\n5) Relatório de todos os pedidos");
        s.append("\n");
        s.append(detalheDivisorTraco());
        
        return s.toString();
    }

    /**
     * Exibe o cardápio enumerado
     * @return cardápio com itens enumerados.
     */
    public static String cardapio(){
        StringBuilder s = new StringBuilder();
        s.append(detalheDivisorTraco());
        s.append("\n");
        s.append("Cardápio: ");
        s.append("\nPizza Padrão (borda comum e sem adicionais): " + moeda.format(Pizza.getPrecoPadrao()));
        s.append("\nAdicionais: " + moeda.format(Pizza.getPrecoAdicionais()));
        s.append("\n" + cardapioBordas());
        s.append("\n");
        s.append(detalheDivisorTraco());
        
        return s.toString();
    }

    /**
     * Exibe um cardápio com os valores das bordas.
     * @return string com a descrição e preço das bordas.
     */
    public static String cardapioBordas(){
        StringBuilder s = new StringBuilder();
        EBordas[] todasAsBordas = EBordas.values();
        int qntBordas = 1;
        s.append("\n --- Bordas ---\n");
        for(EBordas borda : todasAsBordas){
            s.append(String.format("%d) %s \n", qntBordas++, borda.getDescricaoBorda()));
        }

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
            System.out.print("\nVamos montar sua pizza: ");
            int qntAdicionais = InputUtils.lerInt("\nQuantos adicionais gostaria de incluir (máx: " + 
                                                    Pizza.getMaxIngredientesAdicionais() +
                                                    ")?: ");

            pizza.editarPizza(1, qntAdicionais);
            int indexBorda = InputUtils.lerInt("Escolha a borda: ");
            pizza.adicionarBorda(indexBorda);
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
        /**
         * Abre um novo pedido. Assim que o pedido é aberto, um pizza é adicionada a ele
         * @return
         */
        public static Pedido abrirPedido(){
            Pedido novoPedido = new Pedido();
            System.out.print(novoPedido.cabecalhoPedido());
            novoPedido.adicionar(comprarPizza());
            todosOsPedidos.add(novoPedido);
            return novoPedido;
        }

        /**
         * Localiza um pedido na lista de todos os pedidos.
         * @param idPedido id do pedido a ser localizado.
         * @return se a lista com todos os pedidos não for vazia, retorna o pedido procurado caso esteja vazia retorna null.
         */
        public static Pedido localizarPedido(int idPedido){
            if(todosOsPedidos.size() != 0){
                for(Pedido pedido : todosOsPedidos){
                    if(pedido.getIdPedido() == idPedido)
                        return pedido;
                }
            }
            return null;
        }

        /**
         * Altera um pedido.
         * Pode adicionar, remover ou editar as pizzas dentro de um pedido.
         * @param idPedidoAtual id do pedido a ser alterado.
         * @return pedido após ter sido alterado.
         */
        public static Pedido alterarPedido(int idPedidoAtual){
            Pedido pedido = localizarPedido(idPedidoAtual);
            System.out.println(pedido.relatorio());
            int escolha = InputUtils.lerInt("\n 1) Adicionar itens | 2) Remover Itens | 3) Editar pizzas: ");
            if(escolha == 1){
                pedido.adicionar(comprarPizza());
            } else if(escolha == 2){
                int item = InputUtils.lerInt("\nQual item irá excluir?: ");
                System.out.println(pedido.relatorio());
                pedido.excluir(item);
            } else if(escolha == 3){
                System.out.println(pedido.cabecalhoPedido());
                System.out.print(pedido.relatorioTodasPizzas());
                escolha = InputUtils.lerInt("Qual pizza quer editar?: ");
                Pizza pizzaParaEdicao = pedido.encontrarPizza(escolha);
                System.out.println(pizzaParaEdicao.relatorio());
                escolha = InputUtils.lerInt("1) Incluir Ingredientes | 2) Remover Ingredientes | 3) Trocar Borda : ");
                if(escolha == 3){
                    System.out.println(cardapioBordas());
                    int novaBorda = InputUtils.lerInt("Por qual borda gostaria de trocar?: ");
                    pizzaParaEdicao.adicionarBorda(novaBorda);
                } else if(escolha == 1 || escolha == 2){
                    int novaQntIngredientes = InputUtils.lerInt("Quantos ingredientes quer incluir/remover?: ");
                    pizzaParaEdicao.editarPizza(escolha, novaQntIngredientes);
                }
            }
            System.out.println("\n" + pedido.relatorio());
            return pedido;
        } 

        /**
         * Exibe o relatório de um pedido específico.
         * @param idPedido id do pedido a ter o relatório exibido
         * @return caso haja elementos em todos os pedidos retorna o relatório do pedido requerido, caso não retorna "Pedido não encontrado".
         */
        public static String relatorioPedido(int idPedido){
            if(todosOsPedidos.size() > 0){
                Pedido pedido = localizarPedido(idPedido);
                return pedido.relatorio();
            }
            return "Pedido não encontrado";
        }

        /**
         * Retorna o relatório de todos os pedidos salvos na lista 'todos os pedidos'.
         * @return um relatório de todos os pedidos salvos na lista.
         */
        public static String relatorioTodosOsPedidos(){
            StringBuilder s = new StringBuilder();
            if(todosOsPedidos.size() > 0){
                for(Pedido pedido : todosOsPedidos){
                    s.append(pedido.relatorio() + "\n");
                    s.append("\n");
                }
            } else {
                s.append("Lista vazia\n");
            }
            return s.toString();
        }

    //#endregion

    public static void main(String[] args) throws Exception {
        System.out.println(cabecalho());
        Pedido pedidoAtual = null;
        int idPedidoAtual = 0;
        int escolha = -1;
        limparTela();
        do{
            try{
                System.out.println(menu());
                escolha = InputUtils.lerInt("Opção:  ");
                switch(escolha){
                    case 0 -> {
                        System.out.print("\nAté a próxima! =^.^=");
                        continue;
                    }
                    case 1 -> {
                        System.out.println("\n --- Criando um novo pedido ---");
                        System.out.println(cardapio());
                        abrirPedido();
                    }
                    case 2 -> {
                    System.out.println("\n --- Alterando um pedido ---");
                    if(todosOsPedidos.size() > 0){
                    System.out.println(relatorioTodosOsPedidos());
                    idPedidoAtual = InputUtils.lerInt("Digite o ID do pedido: ");
                    alterarPedido(idPedidoAtual);// alterar pedido (adicionar ou remover itens)
                    continue;
                    }
                    System.out.println("Não há pedidos registrados");
                    }
                    case 3 -> {
                    System.out.println("\n --- Exibindo relatório de um pedido ---");
                    if(todosOsPedidos.size() > 0){
                        idPedidoAtual = InputUtils.lerInt("Digite o ID do pedido: ");
                        System.out.println(relatorioPedido(idPedidoAtual));
                        continue;
                    }
                    System.out.println("Não há pedidos registrados");
                    }
                    case 4 ->{
                    if(todosOsPedidos.size() > 0){
                    System.out.println("\n --- Finalizando pedido ---");
                    idPedidoAtual = InputUtils.lerInt("Digite o ID do pedido: ");
                    for(Pedido pedido : todosOsPedidos){
                        if(idPedidoAtual == pedido.getIdPedido()){
                            pedidoAtual = pedido;
                        }
                    }   
                    pedidoAtual.fecharPedido();
                    System.out.println(String.format("Pedido %d fechado.", idPedidoAtual));
                    continue;
                } 
                System.out.println("Não há pedidos registrados");    
                }
                    case 5 -> System.out.print(relatorioTodosOsPedidos());
                }
                
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

}
