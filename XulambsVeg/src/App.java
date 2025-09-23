import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.LinkedList;

public class App {
    // static LinkedList<Pizza> pizzas = new LinkedList<>();
    // static LinkedList<Pedido> todosOsPedidos = new LinkedList<>();
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
            // System.out.println(detalheDivisorTraco());
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

            pizza.editarQntIngredPizza(1, qntAdicionais);
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

        /**
         * Prepara uma pizza para edição dentro de um pedido específico.
         * Exibe o cabeçalho do pedido e o relatório de todas as pizzas,
         * solicita ao usuário qual pizza deseja editar e retorna essa pizza.
         * @param pedidoEscolhido O pedido que contém a pizza a ser editada.
         * @return A pizza selecionada pelo usuário para edição.
         */
        private static Pizza prepararPizzaParaEdicao(Pedido pedidoEscolhido){
            System.out.println(pedidoEscolhido.cabecalhoPedido());
            System.out.print(pedidoEscolhido.relatorioTodasPizzas());
            
            int posicaoPizza = InputUtils.lerInt("Qual pizza quer editar?: ");
            
            Pizza pizzaParaEdicao = pedidoEscolhido.encontrarPizza(posicaoPizza);
            System.out.println(pizzaParaEdicao.relatorio());
            return pizzaParaEdicao;
        }   

        /**
         * Altera a quantidade de ingredientes de uma pizza específica.
         * Solicita ao usuário a nova quantidade de ingredientes via console e atualiza a pizza.
         *
         * @param pizzaParaEdicao A pizza que terá a quantidade de ingredientes alterada.
         * @param escolha Indica a ação do usuário: 
         *               1 para incluir ingredientes, 
         *               2 para remover ingredientes.
         */
        private static void alterarQntIngred(Pizza pizzaParaEdicao, int escolha){
            int novaQntIngredientes = InputUtils.lerInt("Quantos ingredientes quer incluir/remover?: ");
            pizzaParaEdicao.editarQntIngredPizza(escolha, novaQntIngredientes);
        }

        /**
         * Altera a borda de uma pizza específica.
         * Solicita ao usuário o número da nova borda via console e atualiza a pizza.
         *
         * @param pizzaParaEdicao A pizza que terá sua borda alterada.
         */
        private static void alterarBorda(Pizza pizzaParaEdicao){
            int novaBorda = InputUtils.lerInt("Por qual borda gostaria de trocar?: ");
            pizzaParaEdicao.adicionarBorda(novaBorda);
        }
    //#endregion

    //#region Pedido
        /**
         * Abre um novo pedido. Assim que o pedido é aberto, um pizza é adicionada a ele
         * @return
         */
        public static Pedido abrirPedido(LinkedList<Pedido> todosOsPedidos){
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
        public static Pedido localizarPedido(LinkedList<Pedido> todosOsPedidos, int idPedido){
            if(todosOsPedidos.size() > 0){
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
        public static Pedido alterarPedido(LinkedList<Pedido> todosOsPedidos, int idPedidoAtual){
            Pedido pedido = localizarPedido(todosOsPedidos, idPedidoAtual);
            System.out.println(pedido.relatorio());

            int opcaoEdicao = InputUtils.lerInt("\n 1) Adicionar Pizzas | 2) Remover Pizzas | 3) Editar pizzas: ");

            switch(opcaoEdicao){
                case 1 -> pedido.adicionar(comprarPizza());
                case 2 -> {
                    int item = InputUtils.lerInt("\nQual item irá excluir?: ");
                    System.out.println(pedido.relatorio());
                    pedido.excluir(item);
                }
                case 3 -> {
                    Pizza pizzaParaEdicao = prepararPizzaParaEdicao(pedido);
                    int acao = InputUtils.lerInt("1) Incluir Ingredientes | 2) Remover Ingredientes | 3) Trocar Borda : ");

                    switch(acao) {
                        case 1,2 -> alterarQntIngred(pizzaParaEdicao, acao);
                        case 3 -> {                    
                            System.out.println(cardapioBordas());
                            alterarBorda(pizzaParaEdicao);
                        }
                    }
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
        public static String relatorioPedido(LinkedList<Pedido> todosOsPedidos, int idPedido){
            if(todosOsPedidos.size() > 0){
                Pedido pedido = localizarPedido(todosOsPedidos, idPedido);
                return pedido.relatorio();
            }
            return "Pedido não encontrado";
        }

        /**
         * Retorna o relatório de todos os pedidos salvos na lista 'todos os pedidos'.
         * @return um relatório de todos os pedidos salvos na lista.
         */
        public static String relatorioTodosOsPedidos(LinkedList<Pedido> todosOsPedidos){
            StringBuilder s = new StringBuilder();
            if(todosOsPedidos.size() > 0){
                for(Pedido pedido : todosOsPedidos){
                    s.append(pedido.relatorio() + "\n");
                    s.append("\n");
                }
            } else {
                s.append("Não há pedidos registrados\n");
            }
            return s.toString();
        }

        /**
         * Retorna uma lista com todos os pedidos que estão marcados com status de aberto.
         * @return Lista com todos os pedidos que estão em aberto.
         */
        public LinkedList<Pedido> mostrarApenasPedidosAbertos(LinkedList<Pedido> todosOsPedidos){
            LinkedList<Pedido> apenasPedidosAbertos = new LinkedList<>();
            for(Pedido pedido : todosOsPedidos){
                if(pedido.getStatus()){
                    apenasPedidosAbertos.add(pedido);
                }
            }
            return apenasPedidosAbertos;
        }

    //#endregion

    public static void main(String[] args) throws Exception {
        LinkedList<Pedido> todosOsPedidos = new LinkedList<>();

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
                        abrirPedido(todosOsPedidos);
                    }
                    case 2 -> {
                    System.out.println("\n --- Alterando um pedido ---");
                    if(todosOsPedidos.size() > 0){
                    System.out.println(relatorioTodosOsPedidos(todosOsPedidos));
                    idPedidoAtual = InputUtils.lerInt("Digite o ID do pedido: ");
                    alterarPedido(todosOsPedidos, idPedidoAtual);// alterar pedido (adicionar ou remover itens)
                    continue;
                    }
                    System.out.println("Não há pedidos registrados");
                    }
                    case 3 -> {
                    System.out.println("\n --- Exibindo relatório de um pedido ---");
                    if(todosOsPedidos.size() > 0){
                        idPedidoAtual = InputUtils.lerInt("Digite o ID do pedido: ");
                        System.out.println(relatorioPedido(todosOsPedidos, idPedidoAtual));
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
                    case 5 -> System.out.print(relatorioTodosOsPedidos(todosOsPedidos));
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
