package models;

public class XulambsSenior implements IFidelidade {
    private static double PCT_DESC = 0.15;
    public static int MIN_PEDIDOS = 20;
    public static double MIN_MEDIA = 44;
    private static int COMPRAS_POR_MES= 5;
    private static int VALOR_CUPOM = 10;
    private int qntCompras;

    @Override
    public double descontoPedido(Pedido pedido) {
        double desconto = pedido.calcularPrecoFinal()*PCT_DESC;
        if(qntCompras+1 % COMPRAS_POR_MES == 0) 
            desconto += VALOR_CUPOM;
        qntCompras++;
        pedido.aplicarDesconto(desconto);
        return desconto;
    }

    @Override
    public String toString(){
        return "Xulambs Senior";
    }
    
}
