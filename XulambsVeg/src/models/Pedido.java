package models;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

public abstract class Pedido {
    private static int ultimoPedido;
    protected LinkedList<IComida> comidas = new LinkedList<>();
    private LocalDate data;

    protected double distancia;

    private int idPedido;
    private boolean aberto;

    private Avaliacao avaliacao;

    protected double desconto;

    public Pedido(){
        idPedido = ++ultimoPedido;
        data = LocalDate.now();
        aberto = true;
    }

    public int getIdPedido(){
        return idPedido;
    }

    public boolean getStatus(){
        return aberto;
    }

    /**
     * Verifica se o pedido está aberto (aceitando adições)
     * @return se estiver aberto retorna true
     */
    protected boolean podeAdicionar(){
        return aberto;
    }

    /**
     * Adiciona uma comida no pedido.
     * @param comida comida a ser adicionada no pedido.
     * @return inteiro com a quantidade de comida já adicionadas após inclusão.
     */
    public int adicionar(IComida comida){
        if(podeAdicionar()){   
            comidas.add(comida);
        }
        return comidas.size();
    }

    /**
     * Exclui uma comida no pedido se ela não for a única comida existente
     * @param comida comida a ser excluida do pedido.
     * @return inteiro com a quantidade de comida existentes após exclusão
     */
    public int excluir(int posicaoComida){
        if(comidas.size() > 0){ 
            IComida comida = comidas.get(posicaoComida - 1);
            comidas.remove(comida);
        }
        return comidas.size();
    }

    /**
     * Fecha um pedido. 
     * Verifica se o pedido tem pelo menos uma comida adicionada. Se sim o pedido é fechado, se não nada acontece.
     */
    public void fecharPedido(){
        if(comidas.size() > 0)
            aberto = false;
    }

    /**
     * Faz um relatório de todas as comidas existentes na lista.
     * @return String com relaório de todas as comidas.
     */
    public String relatorioTodasComidas(){
        StringBuilder s = new StringBuilder();
        int qntComidas = 1;
        for(IComida comida : comidas){
            s.append((qntComidas++) + ") " + comida.toString() + "\n");
        }
        return s.toString();
    }

    /**
     * Encontra uma comida na lista de todas a comidas existentes dentro de um pedido.
     * @param indexComida index da comida a ser encontrada na lista;
     * @return comida procurada.
     */
    public IComida encontrarComida(int indexComida){
        return comidas.get(indexComida - 1);
    }

    /**
     * Calcula o valor final do pedido. 
     * Soma todas as comidas inclusas dentro do pedido e retorna seu valor final
     * @return double contendo o valor de todas comidas que foram inclusa no pedido.
     */
    public double valorItens(){
        double precoFinal = 0d;
        for(IComida comida : comidas){
            precoFinal += comida.precoFinal();
        }
        return precoFinal;
    }

    protected abstract double calcularPrecoFinal();

    /**
     * Cabeçalho de um pedido com ID e data
     * @return cabeçalho de um pedido no formato: Pedido: X (dd/mm/yyyy)
     */
    public String cabecalhoPedido(){
        return String.format("Pedido: %02d (%s)", idPedido, data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    /**
     * Define o status de um pedido (aberto ou fechado).
     * @return aberto caso true, fechado caso false
     */
    public String definirStatus(){
        return aberto ? "Aberto" : "Fechado";
    }

    private String setarModalidadeEntrega(){
        return (distancia == 0) ? "Local" : "Delivery";
    }

    public Avaliacao avaliarPedido(int indexQntEstrelas, String descricao){
        EEstrelas estrelas = EEstrelas.values()[indexQntEstrelas - 1];
        this.avaliacao = new Avaliacao(estrelas, descricao);
        return avaliacao;
    }

    public Avaliacao getAvaliacao(){
        return avaliacao;
    }

    public double aplicarDesconto(double valor){
        this.desconto = valor;
        return desconto;
    }

    /**
     * Mostra um relatorio do pedido.
     * Formato:
     * idPedido - data.
     * x) Comida descrição.
     * Ao final imprime o valor total do pedido.
     * @return
     */
    @Override
    public String toString(){
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
        StringBuilder s = new StringBuilder();
        int qntItens = 1;
        String status = definirStatus();

        s.append(String.format("\n#Pedido: %02d - (%s) | Status: %s | %s", idPedido, data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), status, setarModalidadeEntrega()));

        for(IComida comida : comidas){
            s.append(String.format("\n%d) %s",qntItens, comida.toString()));
            qntItens++;
        }
        s.append("\nTotal pedido: " + moeda.format(calcularPrecoFinal()));
        return s.toString();
    }

    @Override
    public boolean equals(Object obj){
        Pedido outro = (Pedido)obj;

        return idPedido == outro.idPedido;
    }
}
