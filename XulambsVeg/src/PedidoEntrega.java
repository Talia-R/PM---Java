import java.text.NumberFormat;

public class PedidoEntrega extends Pedido {
    private static int MAX_QNT_PIZZAS = 8;

    protected static int[] DISTANCIAS = {4,8};
    protected static double[] VALOR_FRETE = {0,5,8};

    public PedidoEntrega(double distancia){
        super();
        this.distancia = distancia;

    }
    @Override
    public String cabecalhoPedido(){
        return super.cabecalhoPedido() + " | " + distancia;
    }

    /**
     * Calcula o valor do frete. De acordo com os valores passados pela taxa.
     * @return double contendo o valor do frete.
     */
    private double calcularValorFrete(){
        double precoTaxa = VALOR_FRETE[0];
        if(distancia > DISTANCIAS[0] && distancia <= DISTANCIAS[1]){
            precoTaxa = VALOR_FRETE[1];
        } else if(distancia > DISTANCIAS[1]){
            precoTaxa = VALOR_FRETE[2];
        }
        return precoTaxa;
    }

    /**
     * Verificar se a lista de pizzas atingiu o valor máximo.
     * @return retorna false caso não tenha atingido.
     */
    protected boolean menorMaxPizza(){
        return todasAsPizzas.size() < MAX_QNT_PIZZAS;
    }

    @Override
    protected boolean podeAdicionar(){
        return super.podeAdicionar() && menorMaxPizza();
    }

    @Override
    public double calcularPrecoFinal(){
        return super.valorItens() + calcularValorFrete();
    }

    @Override
    public String toString(){
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
        return super.notaBasePedido() + " (frete: " + moeda.format(calcularValorFrete()) + ")";

    }

}
