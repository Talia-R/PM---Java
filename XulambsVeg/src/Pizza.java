public class Pizza {
    private static final int MAX_INGREDIENTES_ADICIONAIS = 8;
    private static final int CUSTO_ADICIONAIS = 5;
    private static int preco = 29;
    private int qntAdicionaisAtual = 0;

    public Pizza(){};

    public Pizza(int qntAdicionais){
        qntAdicionaisAtual = qntAdicionais;
    }

    public static int getPrecoPadrao(){
        return preco;
    }

    /**
     * Verifica se a pizza atingiu o máximo de ingredientes adicionais.
     * @return true caso tenho atingindo o maior de ingredientes adicionais
     */
    public boolean atingiuMaxAdicionais(){
        return qntAdicionaisAtual > MAX_INGREDIENTES_ADICIONAIS;
    }

    /**
     * Adiciona ingredientes adicionais a pizza caso ela não tenha ultrapassado o valor máximo de ingredientes adicionais. 
     * @param qntAdicionais inteiro com a quantidade de adicionais a serem adicionadas
     */
    public void colocarAdicionais(int qntAdicionais){
        if(atingiuMaxAdicionais()){
            throw new IllegalArgumentException("Atingiu o máximo de ingredientes");
        }
        qntAdicionaisAtual += qntAdicionais;
    }

    //a pizza vai saber oq vendeu a partir do relatorio
    // comeca 
    
}
