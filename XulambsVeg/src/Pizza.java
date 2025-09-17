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

    public boolean atingiuMaxAdicionais(){
        return qntAdicionaisAtual > MAX_INGREDIENTES_ADICIONAIS;
    }

    public void colocarAdicionais(int qntAdicionais){
        if(atingiuMaxAdicionais()){
            throw new IllegalArgumentException("Atingiu o máximo de ingredientes");
        }
        qntAdicionaisAtual += qntAdicionais;
    }

    
}
