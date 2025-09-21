import java.text.NumberFormat;

public enum EBordas {
    SEM_BORDA("Borda Tradicional", 0),
    REQUEIJAO("Borda de Requeijão", 7d),
    CHEDDAR("Borda de Cheddar", 10d),
    CHOCOLATE("Borda de Chocolate", 8d);

    private String descricao;
    private double preco;

    private EBordas(String descricao, double preco){
        this.descricao = descricao;
        this.preco = preco;
    }

    public String getDescricaoBorda(){
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
        return String.format("%s: %s", descricao, moeda.format(preco));
    }

    public double getPrecoBorda(){
        return preco;
    }
}
