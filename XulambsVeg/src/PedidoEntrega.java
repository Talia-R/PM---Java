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

    private double calcularValorFrete(){
        double precoTaxa = VALOR_FRETE[0];
        if(distancia > DISTANCIAS[0] && distancia <= DISTANCIAS[1]){
            precoTaxa = VALOR_FRETE[1];
        } else if(distancia > DISTANCIAS[1]){
            precoTaxa = VALOR_FRETE[2];
        }
        return precoTaxa;
    }

    protected boolean menorMaxPizza(){
        return todasAsPizzas.size() < MAX_QNT_PIZZAS;
    }

    @Override
    protected boolean podeAdicionar(){
        return super.podeAdicionar() && menorMaxPizza();
    }

    @Override
    public double calcularPrecoFinal(){
        return super.calcularPrecoFinal() + calcularValorFrete();
    }

    @Override
    public String toString(){
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
        return super.toString() + " (frete: " + moeda.format(calcularValorFrete()) + ")";

    }

    // public double calcularPrecoFinal(){
    // }

}
