import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public class Pedido {
    private static int ultimoPedido;
    private LinkedList<Pizza> pizzas = new LinkedList<>();
    private LocalDate data;

    private int idPedido;
    private boolean aberto;

    public Pedido(){
        idPedido = ++ultimoPedido;
        data = LocalDate.now();
        aberto = true;
        ultimoPedido++;
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
     * @return inteiro com a quantidade de pizza já adicionadas
     */
    public int adicionar(Pizza pizza){
        if(podeAdicionar()){   
            pizzas.add(pizza);
        }
        return pizzas.size();
    };

    /**
     * Fecha um pedido. 
     * Verifica se o pedido tem pelo menos uma pizza adicionada. Se sim o pedido é fechado, se não nada acontece.
     */
    public void fecharPedido(){
        if(pizzas.size() > 0)
            aberto = false;
    }

    /**
     * Calcula o valor final do pedido. 
     * Soma todas as pizzas inclusas dentro do pedido e retorna seu valor final
     * @return double contendo o valor de todas pizzas que foram inclusa no pedido.
     */
    public double calcularPrecoFinal(){
        double precoFinal = 0d;
        for(Pizza pizza : pizzas){
            precoFinal += pizza.getPrecoFinal();
        }
        return precoFinal;
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

        s.append(String.format("%02d - %s", idPedido, data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));

        for(Pizza pizza : pizzas){
            s.append(String.format("%d) %s",qntItens, pizza.relatorio()));
            // quebra de linha
            s.append("\n");
            qntItens++;
        }
        s.append("\nTotal pedido: " + moeda.format(calcularPrecoFinal()));
        return s.toString();
    }
}
