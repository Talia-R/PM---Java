package models;

public class Avaliacao {
    private String descricao;
    private EEstrelas estrela;


    public Avaliacao(EEstrelas estrela, String descricao){
        if(descricao.isBlank())
            throw new IllegalArgumentException("Descrição não pode ser vazia");
        if(descricao.length() <= 3)
            throw new IllegalArgumentException("Descrição deve ter mais de 5 caracters");
            
        this.estrela = estrela;
        this.descricao = formatarDescricao(descricao);
    }

    /**
     * Formata a descrição retirando espaços em brancos e deixando os caracters em letra minúscula.
     * @param descricao descricao inserida.
     * @return descricao formatada.
     */
    private String formatarDescricao(String descricao){
        return descricao.trim().toLowerCase();
    }

    /**
     * Edita uma descrição existente.
     * @param novaDescricao nova descrição.
     */
    public void editarDescricao(String novaDescricao){
        descricao = novaDescricao;
    }

    /**
     * Retorna uma string com a avaliação do cliente.
     * Formato:
     * Quantidade de estrelas dadas: ★
     * Quantidade de estrelas total: ☆
     * Descrição feita pelo cliente.
     */
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        int estrelasDadas = estrela.getEstrelas();
        int max_Estrelas = EEstrelas.getMaxEstrelas();
        s.append("\n");
        s.append("\u2605".repeat(estrelasDadas));
        if(estrelasDadas != max_Estrelas){
            s.append("\u2606".repeat(max_Estrelas - estrelasDadas));
        }
        s.append("\n" + descricao);
        return s.toString();
    }
}
