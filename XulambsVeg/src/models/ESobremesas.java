package models;

import java.text.NumberFormat;

public enum ESobremesas implements IComida{
    BRIGADEIRO("Brigadeiro", 5),
    PUDIM("Pudim", 10),
    DOCE_DE_LEITE("Doce de leite", 7.5);

    private String descricao;
    private double preco;

    private ESobremesas(String descricao, double preco){
        this.descricao = descricao;
        this.preco = preco;
    }

    public String getDescricao(){
        return descricao;
    }

    @Override
    public double precoFinal() {
        return preco;
    }

    @Override
    public String toString(){
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
        return descricao + " : " + moeda.format(precoFinal());
    }
    
}
