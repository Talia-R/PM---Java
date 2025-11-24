package models;

public class XulambsPleno implements IFidelidade {
    private static double PCT_DESC = 0.1;
    public static int MIN_PEDIDOS = 10;
    public static double MIN_MEDIA = 29;

    @Override
    public double descontoPedido(Pedido pedido) {
        double desconto = pedido.calcularPrecoFinal()*PCT_DESC;
        pedido.aplicarDesconto(desconto);
        return desconto;
    }

    @Override
    public String toString(){
        return "Xulambs Pleno";
    }
    
}
