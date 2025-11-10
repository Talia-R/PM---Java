package models;

public interface IComida {
    public double precoFinal();
    public void editarQntIngredPizza(int escolha, int novaQntIngredientes);
    public double calcularPrecoAdicional();
}
