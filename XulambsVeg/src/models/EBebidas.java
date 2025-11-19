package models;

import java.text.NumberFormat;

public enum EBebidas implements IComida{
    AGUA_SEM_GAS("Agua sem gás", 5.0),
    AGUA_COM_GAS("Agua com gás", 10.0),
    CHA_COM_GAS("Agua com gás", 15.0),
    SUCO("Suco", 8.0),
    CERVEJA("Cerveja", 15.0),
    TACA_DE_VINHO("Taça de vinho", 25.0);

    private String descricao;
    private double preco;

    private EBebidas(String descricao, double preco){
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