import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class Pedido {
    private static int ultimoPedido;
    private LinkedList<Pizza> todasAsPizzas = new LinkedList<>();
    private LocalDate data;

    private int idPedido;
    private boolean aberto;

    public Pedido(){
        idPedido = ++ultimoPedido;
        data = LocalDate.now();
        aberto = true;
    }

    public int getIdPedido(){
        return idPedido;
    }
    /**
     * Verifica se o pedido está aberto (aceitando adições)
     * @return se estiver aberto retorna true
     */
    private boolean podeAdicionar(){
        return aberto;
    }

    /**
     * Adiciona uma pizza no pedido.
     * @param pizza pizza a ser adicionada no pedido.
     * @return inteiro com a quantidade de pizza já adicionadas após inclusão.
     */
    public int adicionar(Pizza pizza){
        if(podeAdicionar()){   
            todasAsPizzas.add(pizza);
        }
        return todasAsPizzas.size();
    }

    /**
     * Exclui uma pizza no pedido se ela não for a única pizza existente
     * @param pizza pizza a ser excluida do pedido.
     * @return inteiro com a quantidade de pizza existentes após exclusão
     */
    public int excluir(int posicaoPizza){
        if(todasAsPizzas.size() > 0){ 
            Pizza pizza = todasAsPizzas.get(posicaoPizza - 1);
            todasAsPizzas.remove(pizza);
        }
        return todasAsPizzas.size();
    }

    /**
     * Fecha um pedido. 
     * Verifica se o pedido tem pelo menos uma pizza adicionada. Se sim o pedido é fechado, se não nada acontece.
     */
    public void fecharPedido(){
        if(todasAsPizzas.size() > 0)
            aberto = false;
    }

    /**
     * Faz um relatório de todas as pizzas existentes na lista.
     * @return String com relaório de todas as pizzas.
     */
    public String relatorioTodasPizzas(){
        StringBuilder s = new StringBuilder();
        int qntPizzas = 1;
        for(Pizza pizza : todasAsPizzas){
            s.append((qntPizzas++) + pizza.relatorio() + "\n");
        }
        return s.toString();
    }

    /**
     * Encontra uma pizza na lista de todas a pizzas existentes dentro de um pedido.
     * @param indexPizza index da pizza a ser encontrada na lista;
     * @return pizza procurada.
     */
    public Pizza encontrarPizza(int indexPizza){
        return todasAsPizzas.get(indexPizza - 1);
    }

    /**
     * Calcula o valor final do pedido. 
     * Soma todas as pizzas inclusas dentro do pedido e retorna seu valor final
     * @return double contendo o valor de todas pizzas que foram inclusa no pedido.
     */
    public double calcularPrecoFinal(){
        double precoFinal = 0d;
        for(Pizza pizza : todasAsPizzas){
            precoFinal += pizza.getPrecoFinal();
        }
        return precoFinal;
    }

    /**
     * Cabeçalho de um pedido com ID e data
     * @return cabeçalho de um pedido no formato: Pedido: X (dd/mm/yyyy)
     */
    public String cabecalhoPedido(){
        return String.format("Pedido: %02d (%s)", idPedido, data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    /**
     * Define o status de um pedido (aberto ou fechado).
     * @return aberto caso true, fechado caso false
     */
    public String definirStatus(){
        return aberto ? "Aberto" : "Fechado";
    }

    /**
     * Mostra um relatorio do pedido.
     * Formato:
     * idPedido - data.
     * x) Pizza descrição.
     * Ao final imprime o valor total do pedido.
     * @return
     */
    public String relatorio(){
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
        StringBuilder s = new StringBuilder();
        int qntItens = 1;
        String status = definirStatus();

        s.append(String.format("#Pedido: %02d - (%s) | Status: %s", idPedido, data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), status));

        for(Pizza pizza : todasAsPizzas){
            s.append(String.format("\n%d) %s",qntItens, pizza.relatorio()));
            // quebra de linha
            //s.append("\n");
            qntItens++;
        }
        s.append("\nTotal pedido: " + moeda.format(calcularPrecoFinal()));
        return s.toString();
    }
}
