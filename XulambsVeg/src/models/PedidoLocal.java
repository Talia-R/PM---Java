package models;
import java.text.NumberFormat;

public class PedidoLocal extends Pedido {
    private double TAXA_SERVICO = 0.1;


    private double valorServico(){
        return TAXA_SERVICO * super.valorItens();
    }

    @Override
    public double calcularPrecoFinal(){
        return (super.valorItens() + valorServico()) - desconto;
    }

    @Override
    public String toString(){
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
        return super.toString() + " (taxa de serviço: " + moeda.format(valorServico()) + ")";
    }
}
