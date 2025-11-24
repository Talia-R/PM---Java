package models;

public class XulambsJunior implements IFidelidade {
    private static double PCT_DESC = 0;

    @Override
    public double descontoPedido(Pedido pedido) {
        double desconto = pedido.calcularPrecoFinal()*PCT_DESC;
        pedido.aplicarDesconto(desconto);
        return desconto;
    }

    @Override
    public String toString(){
        return "Xulambs Junior";
    }
    
}
