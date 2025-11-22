package models;

import java.util.LinkedList;
import java.util.List;

public class Cliente {
    private static int ultimoID;
    private int id;
    private String nome;
    private List<Pedido> pedidos;

    public Cliente(String nome){
        id = ++ultimoID;

        if(nome.isEmpty()){
            throw new IllegalArgumentException("Nome não deve ser vazio");
        }
        this.nome = nome.trim();

        pedidos = new LinkedList<>();
    }

    public int registrarPedido(Pedido novoPedido){
        if(novoPedido == null){
            throw new IllegalArgumentException("Pedido não deve ser nulo.");
        }
        pedidos.add(novoPedido);
        return pedidos.size();
    }

    public double totalGasto(){
        double total = 0;
        if(pedidos.size() == 0)
            throw new IllegalArgumentException("Lista vazia");
        for(Pedido p : pedidos){
            total += p.calcularPrecoFinal();
        }
        return total;
    }

    @Override
    public String toString(){
        return String.format("%s | (#%d)", nome, id);
    }

    public String relatorioPedidos(){
        StringBuilder s = new StringBuilder("\n" + toString());
        for (Pedido p : pedidos) {
            s.append("\n" + p.toString());
        }
        return s.toString();
    }

}
