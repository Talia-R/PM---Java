import java.util.ArrayList;

public class Prato {
    String nome;
    ArrayList<Integer> avaliacoes = new ArrayList<>();

    public Prato(String nome, ArrayList<Integer> avaliacoes){
        this.nome = nome;
        this.avaliacoes = avaliacoes;
    }
}
