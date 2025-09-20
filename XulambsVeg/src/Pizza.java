import java.text.NumberFormat;

public class Pizza {
    private static final int MAX_INGREDIENTES_ADICIONAIS = 8;
    private static final double CUSTO_ADICIONAIS = 5d;
    private static double precoBase = 29d;
    private int qntAdicionaisAtual = 0;

    public Pizza(){};

    public Pizza(int qntAdicionais){
        qntAdicionaisAtual = qntAdicionais;
    }

    public static double getPrecoPadrao(){
        return precoBase;
    }

    public static int getMaxIngredientesAdicionais(){
        return MAX_INGREDIENTES_ADICIONAIS;
    }
    
    /**
     * Verifica se a pizza atingiu o máximo de ingredientes adicionais.
     * @return true caso tenho atingindo o maior de ingredientes adicionais
     */
    private boolean atingiuMaxAdicionais(){
        return qntAdicionaisAtual > MAX_INGREDIENTES_ADICIONAIS;
    }

    /**
     * Adiciona ingredientes adicionais a pizza caso ela não tenha ultrapassado o valor máximo de ingredientes adicionais. 
     * @param qntAdicionais inteiro com a quantidade de adicionais a serem adicionadas
     */
    private void incluirAdicionais(int qntAdicionais){
        qntAdicionaisAtual += qntAdicionais;
        if(atingiuMaxAdicionais()){
            qntAdicionaisAtual = MAX_INGREDIENTES_ADICIONAIS;
           // throw new IllegalArgumentException("Atingiu o máximo de ingredientes");
        }
    }

    /**
     * Remove ingredientes adicionais a pizza caso ela não tenha ultrapassado o valor máximo de ingredientes adicionais. 
     * @param qntAdicionais inteiro com a quantidade de adicionais a serem removidas
     */
    private void removerAdicionais(int qntAdicionais){
        qntAdicionaisAtual -= qntAdicionais;
        if(qntAdicionaisAtual < 0){
            qntAdicionaisAtual = 0;
           // throw new IllegalArgumentException("Atingiu o máximo de ingredientes");
        }
    }

    /**
     * Edita uma pizza podendo incluir ou remover ingredientes
     * @param escolha int que decide qual operação quer fazer (adicionar ou remover) ingredientes
     * @param novaQntIngredientes int com a quantidade de ingredientes que quer editar
     */
    public void editarPizza(int escolha, int novaQntIngredientes){
        if(escolha == 1){
            incluirAdicionais(novaQntIngredientes);
        } else if(escolha == 2){
            removerAdicionais(novaQntIngredientes);
        }
    }

    /**
     * Calcula o valor dos adicionais da pizza.
     * @return double com a soma dos valores adicionais
     */
    private double calcularPrecoAdicional(){
        return qntAdicionaisAtual * CUSTO_ADICIONAIS;
    }

    /**
     * Calcula o valor final da pizza. Considera o valor padrão e os valores dos adicionais
     * @return double com a soma do valor padrão e adicionais
     */
    private double precoFinal(){
        return precoBase + calcularPrecoAdicional();
    }

    public double getPrecoFinal(){
        return precoFinal();
    }

    /**
     * Exibe uma nota descritiva da pizza com descrição dos ingredientes (caso tenha) e seu preço.
     * @return string com a descrição e preço da pizza
     */
    public String relatorio(){
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
        StringBuilder s = new StringBuilder();
        s.append("Pizza padrão no valor de: " + moeda.format(getPrecoPadrao()));
        if(qntAdicionaisAtual > 0){
            s.append(String.format(
                " com %d adicionais no valor de %s", 
                qntAdicionaisAtual, 
                moeda.format(calcularPrecoAdicional())
                ));
            s.append(" | Total: " + moeda.format(precoFinal()));
        }
            return s.toString();
    }

    //a pizza vai saber oq vendeu a partir do relatorio
    // comeca 
    
}
