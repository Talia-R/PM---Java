
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;

import models.Cliente;
import models.EBebidas;
import models.EBordas;
import models.ESobremesas;
import models.IComida;
import models.InputUtils;
import models.Pedido;
import models.PedidoEntrega;
import models.PedidoLocal;
import models.Pizza;

public class App {
    static NumberFormat moeda = NumberFormat.getCurrencyInstance();
    static HashMap<Integer, Pedido> todosOsPedidos = new HashMap<>();
    static HashMap<Integer, Cliente> clientes = new HashMap<>();
    
    //#region Utilitarios

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
        
    //#endregion

    //#region Menu/Cardapios
        /**
         * Exibe o menu de opções possiveis no sistema
         * @return menu com opções numeradas
         */
        public static String menu(){
            StringBuilder s = new StringBuilder();
            s.append(detalheDivisorTraco());
            s.append("\n0) Sair");
            s.append("\n1) Atualizar fidelidade");
            s.append("\n2) Abrir pedido");
            s.append("\n3) Alterar pedido");
            s.append("\n4) Encerrar pedido");
            s.append("\n5) Relatórios");
            s.append("\n");
            s.append(detalheDivisorTraco());
            
            return s.toString();
        }

        /**
         * Exibe o cardápio enumerado
         * @return cardápio com itens enumerados.
         */
        public static String menuPizzas(){
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

        public static String menuEntrega(){
            StringBuilder s = new StringBuilder();
            s.append("\n1) Local | 2) Delivery : ");
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

        public static String menuBebidas(){
            EBebidas[] list = EBebidas.values();

            StringBuilder s = new StringBuilder();
            int qnt = 0;
            for(EBebidas b : list){
                s.append(String.format("\n %d) %s", ++qnt, b));
            }
            return s.toString();
        }

        public static String menuSobremesas(){
            ESobremesas[] list = ESobremesas.values();

            StringBuilder s = new StringBuilder();
            int qnt = 0;
            for(ESobremesas b : list){
                s.append(String.format("\n %d) %s", ++qnt, b));
            }
            return s.toString();
        }
    
        public static String menuRelatorios(){
            StringBuilder s = new StringBuilder();
            s.append(detalheDivisorTraco());
            s.append("\n");
            s.append("Relatórios em ordem:");
            s.append("\n");
            s.append("\n---P E D I D O S---");
            s.append("\n1) Encontrar Pedido por ID");
            s.append("\n2) Relatório de todos os pedidos");

            s.append("\n");
            s.append("\n---C L I E N T E S---");
            s.append("\n3) Encontrar Cliente por ID"); // refatorar esse e o de cima, é a mesma coisa para lista diferente
            s.append("\n4) Relatório de todos os clientes (com pedidos)");
            s.append("\n5) Relatório de todos os clientes (só nomes)");
            s.append("\n) Alfabética");
            s.append("\n) Gasto (crescente)");

            s.append("\n");
            s.append("\n---F I D E L I D A D E---");
            s.append("\n) Fidelidade(crescente)");
            s.append("\n");

            s.append("\n");
            s.append(detalheDivisorTraco());
            return s.toString();
        }
        //#endregion

    //#region Pizza
        /**
         * Cria um objeto pizza e o adiciona na lista de Pizzas.
         * Exibe o relatorio (nota de compra).
         */
        public static Pizza adicionarPizza(){
            System.out.println(menuPizzas());
            Pizza novaPizza = new Pizza();
            montarPizza(novaPizza);
            System.out.println(notaDeCompra(novaPizza));
            return novaPizza;
        }


        public static EBebidas adicionarBebida(){
            EBebidas[] list = EBebidas.values();
            System.out.print(menuBebidas());
            int bebida = InputUtils.lerInt("\nAdicionar: ");
            return list[bebida - 1];
        }

        public static ESobremesas adicionarSobremesa(){
            ESobremesas[] list = ESobremesas.values();
            System.out.print(menuSobremesas());
            int sobremesa = InputUtils.lerInt("\nAdicionar: ");
            return list[sobremesa - 1];
        }

        public static IComida comprarComida(){
            IComida novaComida = null;
            int indexComida = InputUtils.lerInt("\n1) Pizzas | 2) Bebidas | 3) Sobremesa ");
            switch(indexComida){
                case 1 -> novaComida = adicionarPizza();
                case 2 -> novaComida = adicionarBebida();
                case 3 -> novaComida = adicionarSobremesa();
            }

            return novaComida;
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

            pizza.editarQnt(1, qntAdicionais);
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
            System.out.println(pizza.toString());
            return s.toString();
        }

        /**
         * Prepara uma pizza para edição dentro de um pedido específico.
         * Exibe o cabeçalho do pedido e o relatório de todas as pizzas,
         * solicita ao usuário qual pizza deseja editar e retorna essa pizza.
         * @param pedidoEscolhido O pedido que contém a pizza a ser editada.
         * @return A pizza selecionada pelo usuário para edição.
         */
        private static IComida prepararComidaParaEdicao(Pedido pedidoEscolhido){
            System.out.println(pedidoEscolhido.cabecalhoPedido());
            System.out.print(pedidoEscolhido.toString());
            
            int posicaoPizza = InputUtils.lerInt("Qual pizza quer editar?: ");
            
            IComida pizzaParaEdicao = pedidoEscolhido.encontrarComida(posicaoPizza);
            System.out.println(pizzaParaEdicao.toString());
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
            pizzaParaEdicao.editarQnt(escolha, novaQntIngredientes);
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
        public static Pedido abrirPedido(int modalidadePedido, double distancia){
            Pedido novoPedido = switch(modalidadePedido) {
                case 1 -> novoPedido = new PedidoLocal();
                case 2 -> novoPedido = new PedidoEntrega(distancia);
                default -> throw new IllegalArgumentException("Modalidade inválida");
            };

            System.out.print(novoPedido.cabecalhoPedido());
            novoPedido.adicionar(comprarComida());
            todosOsPedidos.put(novoPedido.hashCode(), novoPedido);
            return novoPedido;
        }

        /**
         * Localiza um pedido na lista de todos os pedidos.
         * @param idPedido id do pedido a ser localizado.
         * @return se a lista com todos os pedidos não for vazia, retorna o pedido procurado caso esteja vazia retorna null.
         */
        public static Pedido localizarPedido(int idPedido){
            if(todosOsPedidos.size() > 0){
                for(Pedido pedido : todosOsPedidos.values()){
                    if(pedido.getIdPedido() == idPedido)
                        return pedido;
                }
            }
            return null;
        }

        /**
         * Retorna uma lista com todos os pedidos que estão marcados com status de aberto.
         * @return Lista com todos os pedidos que estão em aberto.
         */
        private static HashMap<Integer,Pedido> criarListaPedidosAbertos(){
            HashMap<Integer,Pedido> todosPedidosAbertos = new HashMap<>();

            for(Pedido pedido : todosOsPedidos.values()){
                if(pedido.getStatus()){
                    todosPedidosAbertos.put(pedido.hashCode(), pedido);
                }
            }
            return todosPedidosAbertos;
        }

        /**
         * Verifica se um pedido está aberto.
         * @param todosOsPedidosAbertos lista com todos os pedidos em aberto.
         * @param idPedido id do pedido que quer encontrar
         * @return se o pedido procurado está em aberto
         */
        private static boolean verificarPedidoAberto(int idPedido){
            HashMap<Integer,Pedido> todosOsPedidosAbertos = criarListaPedidosAbertos();
            boolean pedidoEstaAberto = false;
            for(Pedido pedido : todosOsPedidosAbertos.values()){
                if(idPedido == pedido.getIdPedido()){
                    pedidoEstaAberto = true;
                }
          } 
          return pedidoEstaAberto; 
        }

        /**
         * Altera um pedido.
         * Pode adicionar, remover ou editar as pizzas dentro de um pedido.
         * @param idPedidoAtual id do pedido a ser alterado.
         * @return pedido após ter sido alterado.
         */
        public static Pedido alterarPedido(int idPedidoAtual){
            Pedido pedido = localizarPedido(idPedidoAtual);
            System.out.println(pedido.toString());

            int opcaoEdicao = InputUtils.lerInt("\n 1) Adicionar Comida | 2) Remover Pizzas | 3) Editar pizzas: ");

            switch(opcaoEdicao){
                case 1 -> pedido.adicionar(comprarComida());
                case 2 -> {
                    int item = InputUtils.lerInt("\nQual item irá excluir?: ");
                    System.out.println(pedido.toString());
                    pedido.excluir(item);
                }
                case 3 -> {
                    IComida comidaParaEdicao = prepararComidaParaEdicao(pedido);
                    int acao = InputUtils.lerInt("1) Incluir Ingredientes | 2) Remover Ingredientes | 3) Trocar Borda : ");

                    switch(acao) {
                        case 1,2 -> {
                            Pizza pizzaParaEdicao = (Pizza) comidaParaEdicao;
                            alterarQntIngred(pizzaParaEdicao, acao);
                        }
                        case 3 -> {                    
                            System.out.println(cardapioBordas());
                            Pizza pizzaParaEdicao = (Pizza) comidaParaEdicao;
                            alterarBorda(pizzaParaEdicao);
                        }
                    }
                    }
            }
            System.out.println("\n" + pedido.toString());
            return pedido;
        } 

    //#endregion

    //#region Cliente
        private static Cliente localizarCliente(int id){
            Cliente procurado = null;
            for(Cliente c : clientes.values()){
                if(c.hashCode() == id)
                    procurado = c;
            }
            return procurado;
        }

        private static String listarClientes(){
            StringBuilder s = new StringBuilder();
            for (Cliente c : clientes.values()) {
                s.append(String.format("\n%d) %s", c.hashCode(), c.toString()));
            }
            return s.toString();
        }

        private static Cliente criarCliente(String nome){  
             return new Cliente(nome);
        }
        
    //#endregion

    //#region Gerador automático
        static void gerarClientes(){
            Cliente novo = new Cliente("Anônimo");
            clientes.put(novo.hashCode(), novo);
            try{
                Path caminho = Path.of("src","Clientes.txt");
                List<String> nomes = Files.readAllLines(caminho, Charset.forName("UTF-8"));
                for(String nome : nomes){
                    novo = new Cliente(nome);
                    clientes.put(novo.hashCode(), novo);
                }
            } catch(IOException exception){
                System.out.println("Problema na leitura do arquivo. Sistema iniciado somente com cliente anônimo.");
            }
        }

        static void gerarPedidos(){
            Random aleat = new Random(42);
            int quantos = clientes.size()*16;
            Pedido pedido;
            IComida comida = null;

            for(int i = 0; i < quantos; i++){
                int tipo = aleat.nextInt(10_000) % 3;
                if(tipo <= 1){
                    pedido = new PedidoLocal();
                } else{
                    pedido = new PedidoEntrega(aleat.nextInt(10) + 1);
                }

                int qntComidas = aleat.nextInt(1000);

                if(qntComidas > 950){
                    qntComidas = 4;
                } else if(qntComidas > 750){
                    qntComidas = 3;
                } else if(qntComidas > 500){
                    qntComidas = 2;
                } else{
                    qntComidas = 1;
                }


                for (int j = 0; j < qntComidas; j++) {
                    tipo = aleat.nextInt(30_000) % 5;
                    switch(tipo){
                        case 0,3,4 -> {
                            int qntAdicionais = aleat.nextInt(6);
                            int borda = aleat.nextInt(EBordas.values().length);
                            comida = new Pizza(qntAdicionais);
                            ((Pizza)comida).adicionarBorda(borda);
                        }
                        case 1 -> {
                                int bebida = aleat.nextInt(EBebidas.values().length);
                                comida = EBebidas.values()[bebida];
                            }
                        case 2 -> {
                                int sobre = aleat.nextInt(ESobremesas.values().length);
                                comida = ESobremesas.values()[sobre];
                            }
                    }
                    
                    try{
                        pedido.adicionar(comida);
                    } catch(IllegalStateException ise){
                        System.err.println("Comida inválida | Pedido sem comida");
                    }
                    }
                    // Cliente quem = clientes.get((aleat.nextInt(clientes.size())+1));
                    Cliente quem = clientes.get((aleat.nextInt(clientes.size())));

                    if(quem == null)
                        quem = clientes.get(1);

                    pedido.fecharPedido();
                    quem.registrarPedido(pedido);
                    todosOsPedidos.put(pedido.hashCode(), pedido);
            
                }
            }
        
        static void config() {
            gerarClientes();
            gerarPedidos();
        }
    //#endregion
   
    //#region Fidelidade
    private static void atualizarFidelidade(){
        System.out.println("Atualizando Fidelidades");
        for(Cliente c : clientes.values()){
            c.atualizaCategoria();
        }
    }
   //#endregion
   
   //#region Relatórios
   
        private static String relatorioClientes(){
            StringBuilder s = new StringBuilder();
            for (Cliente cliente : clientes.values()) {
                s.append(cliente.relatorioPedidos());
                s.append("\n" + detalheDivisorTraco());
            }
            return s.toString();
        }

        /**
         * Exibe o relatório de um pedido específico.
         * @param idPedido id do pedido a ter o relatório exibido
         * @return caso haja elementos em todos os pedidos retorna o relatório do pedido requerido, caso não retorna "Pedido não encontrado".
         */
        public static String relatorioPedido(int idPedido){
            if(todosOsPedidos.size() > 0){
                Pedido pedido = localizarPedido(idPedido);
                return pedido.toString();
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
                for(Pedido pedido : todosOsPedidos.values()){
                    s.append(pedido.toString() + "\n");
                    s.append("\n");
                }
            } else {
                s.append("Não há pedidos registrados\n");
            }
            return s.toString();
        }

   //#endregion

    public static void main(String[] args) throws Exception {
        config();
        
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
                    case 1 -> atualizarFidelidade();
                    case 2 -> {
                        System.out.println("\n --- Criando um novo pedido ---");
                        int escolhaEntrega = InputUtils.lerInt(menuEntrega());
                        double distancia = 0;
                        if(escolhaEntrega == 2){
                            distancia = InputUtils.lerDouble("\nQual a distância até o local?: ");
                        }
                        int idCliente = InputUtils.lerInt("Digite o id do cliente: ");
                        Cliente cliente = localizarCliente(idCliente);
                        if(cliente == null){
                            limparTela();
                            System.out.println("\nCliente não encontrado.\n\n---Criando novo cliente---");
                            String nomeCliente = InputUtils.lerString("Digite o nome do cliente: ");
                            cliente = criarCliente(nomeCliente);
                            clientes.put(cliente.hashCode(), cliente);
                        }
                        cliente.registrarPedido(abrirPedido(escolhaEntrega, distancia));
                        
                    }
                    case 3 -> {
                    System.out.println("\n --- Alterando um pedido ---");
                    if(todosOsPedidos.size() > 0){
                        System.out.print(relatorioTodosOsPedidos());
                        idPedidoAtual = InputUtils.lerInt("Digite o ID do pedido: ");
                        if(verificarPedidoAberto(idPedidoAtual)){
                            alterarPedido(idPedidoAtual);// alterar pedido (adicionar ou remover itens)
                            continue;
                        }
                        Pedido pedido = localizarPedido(idPedidoAtual);
                        System.out.println(String.format("O pedido %02d está %s",idPedidoAtual, pedido.definirStatus().toLowerCase()));
                        continue;
                    }
                    System.out.println("Não há pedidos registrados");
                    }
                    case 4 ->{
                    if(todosOsPedidos.size() > 0){
                    System.out.println("\n --- Finalizando pedido ---");
                    idPedidoAtual = InputUtils.lerInt("Digite o ID do pedido: ");
                    for(Pedido pedido : todosOsPedidos.values()){
                        if(idPedidoAtual == pedido.getIdPedido()){
                            pedidoAtual = pedido;
                        }
                    }
                    int avaliacao = InputUtils.lerInt("\nGostaria de avaliar seu pedido?\n 1) sim | 2) Não ");
                    if(avaliacao == 1){
                        int estrelas = 0;
                        while(estrelas <= 0 || estrelas > 5)
                            estrelas = InputUtils.lerInt("Quantas estrelas? (1 a 5): ");
                        String descricao = InputUtils.lerString("Escreva o que achou do pedido: ");
                        pedidoAtual.avaliarPedido(estrelas, descricao);
                        System.out.print("\nPedido avaliado: ");
                        System.out.print(pedidoAtual.getAvaliacao());
                    }  
                    pedidoAtual.fecharPedido();
                    System.out.println(String.format("\nPedido %d fechado.", idPedidoAtual));
                    continue;
                } 
                    System.out.println("Não há pedidos registrados");    
                }
                    case 5 -> {
                        System.out.println(menuRelatorios());
                        int opcao = InputUtils.lerInt("Escolha: ");
                        switch(opcao){
                            case 1 -> {
                                System.out.println("\n --- Exibindo relatório de um pedido ---");
                                if(todosOsPedidos.size() > 0){
                                    idPedidoAtual = InputUtils.lerInt("Digite o ID do pedido: ");
                                    System.out.println(relatorioPedido(idPedidoAtual));
                                    continue;
                                }
                                System.out.println("Não há pedidos registrados");
                            }

                            case 2 -> System.out.print(relatorioTodosOsPedidos());
                            case 3 -> {
                                int id = InputUtils.lerInt("Insira o id: ");
                                Cliente c = localizarCliente(id);
                                if(c == null){
                                    System.out.println("Cliente não encontrado");
                                    continue;
                                }
                                System.out.println(c.toString());
                    }
                            case 4 -> System.out.print(relatorioClientes());
                            case 5 -> System.out.print(listarClientes());
                            
                        }
                    
                    }
            
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
