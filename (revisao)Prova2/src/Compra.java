public class Compra {
    private static double FRETE_FIXO = 15d;
    private static int QNT_DESCONTO = 10;
    
    private int qntItens;
    private Vinho[] itens = new Vinho[qntItens];

    public Compra(){}

    /**
     * Soma o valor de todos os itens desconsiderando o mais barato.
     * @return soma de valores do itens na lista com o desconto do mais barato.
     */
    private double valorVinhos(){
        double vinhoMaisBarato = 0;
        double precoVinhos = 0;
        for(Vinho v : itens){
            precoVinhos += v.getPreco();
        }

        if(qntItens >= QNT_DESCONTO){
            vinhoMaisBarato = encontrarVinhoMaisBarato().getPreco();
            precoVinhos -= vinhoMaisBarato;
        }

        /* outra forma: mais trabalhosa para o sistema? Fica comparando a cada iteração (verificar)
            Vinho vinho = encontrarVinhoMaisBarato();
            for(Vinho v : itens){
            if(qntItens >= QNT_DESCONTO && v.equals(vinho)){
                continue;
            }
            precoVinhos += v.getPreco();
            }
        */ 

        return precoVinhos - vinhoMaisBarato;
    }

    /**
     * Encontra o vinho mais barato na lista de compras. Caso haja vinhos com o mesmo valor o primeiro vinho mais barato
     * será o escolhido.
     * @return o vinho com o menor preço na lista de compras;
     */
    private Vinho encontrarVinhoMaisBarato(){
        Vinho vinho = itens[0];
        double valorMenorVinho = itens[0].getPreco();

        for(int i = 1; i < itens.length; i++){
            if(valorMenorVinho > itens[i].getPreco()){
                valorMenorVinho = itens[i].getPreco();
            }
        }
        return vinho;
    }
}
