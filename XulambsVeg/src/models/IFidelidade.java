package models;

import java.util.List;

public interface IFidelidade {

    private static double calcularValorMedio(List<Pedido> pedidos, int itens){
            double valorTodosPedidos = 0;
            for (Pedido pedido : pedidos) {
                valorTodosPedidos += pedido.calcularPrecoFinal();
            }
        return (Double) valorTodosPedidos/itens;
    }
    
    public static IFidelidade definirCategoria(List<Pedido> pedidos){
        IFidelidade categoria = new XulambsJunior();
        int qntPedidos = pedidos.size();
        double valorMedio = calcularValorMedio(pedidos, qntPedidos);

        if(qntPedidos >= XulambsPleno.MIN_PEDIDOS && valorMedio >= XulambsPleno.MIN_MEDIA){
            categoria = new XulambsPleno();
        } else if(qntPedidos >= XulambsSenior.MIN_PEDIDOS && valorMedio >= XulambsSenior.MIN_MEDIA){
            categoria = new XulambsSenior();
        }

        return categoria;
    }
    
    public abstract double descontoPedido(Pedido pedido);
}
