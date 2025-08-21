public class Produto {
    String descricao;
    double precoCusto;
    double margemLucro;


    /**
     * Construtor do produto. Recebe nome, preço de custo e margem de lucro. A margem de lucro deve estar entre 10 e 50%
     * e é uma valor fracionário (de 0.10 a 0.50)
     * @param desc String com a descrição (sem validação)
     * @param precoCusto Valor de compra do produto. Deve ser no mínimo 0.
     * @param margemLucro Margem para cálculo da venda. É um valor entra 10% e 50%, fracionário (0.10 a 0.50)
    */
    public Produto(String desc, double precoCusto, double margemLucro){
        descricao = desc;
        if(precoCusto < 0) 
            precoCusto = 0;
        if(margemLucro < 0.10  || margemLucro > 0.5)
            margemLucro = 0.10;
        this.precoCusto = precoCusto;
        this.margemLucro = margemLucro;
    }

    /**
     * Calcula o preço de venda do produto
     * @return Double com o preço de venda. Será um valor não negativo
     */
    public double valorVenda(){
        return precoCusto * (1 + margemLucro); // precoCusto = precoCusto + margemLucro , só colocou em evidência
    }

    // String.Format() vai retornar uma string. Só formata, não imprime
    public String toString(){
        return String.format("%s, com preço de venda R$ %.2f", 
                            descricao, valorVenda());
    }
}
