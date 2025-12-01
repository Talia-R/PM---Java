package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

public class BaseDados<T> {
    private Map<Integer, T> dados;

    public BaseDados(int size){
        if(size <= 0)
            throw new IllegalArgumentException("Objeto vazio.");
        dados = new HashMap<>(size);
    }

    public int put(T novo){
        dados.put(novo.hashCode(), novo);
        return dados.size();
    }

    public int size(){
        return dados.size();
    }

    public T get(int key){
        return dados.get(key);
    }

    public Collection<T> values(){
        return dados.values();
    }

    public String simpleReport(){
        StringBuilder s = new StringBuilder();
        for(T dado : dados.values()){
            s.append("\n" + dado.toString());
        }
        return s.toString();
    }

    public String sortedReport(Comparator<T> comparator){
        List<T> lista = new ArrayList<>(dados.values());
        Collections.sort(lista, comparator);

        StringBuilder s = new StringBuilder();
        for(T dado : lista){
            s.append("\n" + dado);
        }
        
        return s.toString();
    }

    public String filterReport(Predicate<T> condicao){
        StringBuilder s = new StringBuilder();
        List<T> list = new LinkedList<>(dados.values());

        list.stream()
        .filter(condicao)
        .sorted()
        .forEach(dado -> s.append("\n" + dado));

        // ou sem ordem decrescente
        // for(T dado : dados.values()){
        //     if(condicao.test(dado)){
        //         s.append("\n" + dado);
        //     }
        // }
        return s.toString();
    }

    public double total(Function<T, Double> funcao){
        double soma = 0;
        for(T dado : dados.values()){
            soma += funcao.apply(dado);
        }
        return soma;
    }

}
