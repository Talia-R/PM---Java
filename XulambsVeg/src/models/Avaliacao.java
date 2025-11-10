package models;

public class Avaliacao {
    private String comentario;
    private EEstrelas estrela;


    public Avaliacao(EEstrelas estrela, String comentario){
        if(comentario.isBlank())
            throw new IllegalArgumentException("Comentário não pode ser vazia");
        if(comentario.length() <= 3)
            throw new IllegalArgumentException("Comentário deve ter mais de 5 caracters");
            
        this.estrela = estrela;
        this.comentario = formatarDescricao(comentario);
    }

    /**
     * Formata a comentário retirando espaços em brancos e deixando os caracters em letra minúscula.
     * @param comentario descricao inserida.
     * @return descricao formatada.
     */
    private String formatarDescricao(String comentario){
        return comentario.trim().toLowerCase();
    }

    /**
     * Edita uma comentário existente.
     * @param novoComentario nova comentário.
     */
    public void editarDescricao(String novoComentario){
        comentario = novoComentario;
    }

    /**
     * Retorna uma string com a avaliação do cliente.
     * Formato:
     * Quantidade de estrelas dadas: ★
     * Quantidade de estrelas total: ☆
     * Comentário feita pelo cliente.
     */
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        int estrelasDadas = estrela.getEstrelas();
        // int max_Estrelas = EEstrelas.getMaxEstrelas();
        s.append("\n");
        s.append(" Estrelas: " + estrelasDadas);
        // if(estrelasDadas != max_Estrelas){

        //     s.append("\u2606".repeat(max_Estrelas - estrelasDadas));
        // }
        s.append("\nCometário: " + comentario);
        return s.toString();
    }
}
