package models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            s.append("\n" + dado);
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
}
