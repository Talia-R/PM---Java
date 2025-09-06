import java.util.LinkedList;
import java.util.List;

public class Equipe {

	private static int ultimaEquipe = 1;
	private String nome;
	private int partidasDisputadas;
	private int vitorias;
	private int empates;
	private int derrotas;
	private int golsMarcados;
	private int golsSofridos;
	private List<String> todasPartidas = new LinkedList<>();

	/**
	 * Método inicializador para os construtores
	 * @param nome Nome da equipe a ser criada.
	 */
	private void init(String nome){
		if(nome.length() == 0){
			nome = "Equipe "+ultimaEquipe;
			ultimaEquipe++;	
		}
		this.nome = nome;
	}

	/**
	 * Construtor: cria uma equipe anônima. Seu nome será "Equipe XX", sendo XX um número
	 * definido internamente, a partir da quantidade de equipes anônimas já criadas.
	 */
	public Equipe(){
		init("");
	}
	
	/**
	 * Construtor. Deve receber o nome da equipe (não vazio). Caso receba um nome vazio, irá criar uma equipe
	 * "anônima" com nome "Equipe XX", sendo XX um valor numérico definido internamente a partir da última 
	 * equipe anônima criada. Os demais atributos serão todos iniciados com 0. 
	 * @param nome
	 */
	public Equipe(String nome) {
		init(nome);
		// partidasDisputadas = vitorias = empates = derrotas = golsMarcados = golsSofridos = 0;
	}

	/**
	 * Deve receber e registrar um placar no formato DxD, sendo D valores numéricos não negativos. 
	 * O placar não deve conter espaços entre o valor numérico e o 'x'. Em casos de valores inválidos,
	 * ignora a operação
	 * @param placar Placar da partida, no formato DxD. 
	 * @return A quantidade de pontos ganhos da equipe após registrar a partida.
	 */
	public int registrarPartida(String placar) {
		if(placar.length() != 0 && !(placar.contains("-"))){
			String[] placarArrayString = placar.trim().split("x");

			int[] placarArrayInt = new int[2];

			for(int i = 0; i < placarArrayInt.length; i++){
				if(placarArrayString[i].matches("\\d+")){
					placarArrayInt[i] = Integer.parseInt(placarArrayString[i]);
				} else{
				// throw new IllegalArgumentException("Placar com valores inválidos: " + placar);
					return 0;
				}
			}

			todasPartidas.add(placar);

			golsMarcados += placarArrayInt[0];
			int golsMarcadosPartidaAtual = placarArrayInt[0];

			golsSofridos += placarArrayInt[1];
			int golsSofridosPartidaAtual = placarArrayInt[1];

			if(golsMarcadosPartidaAtual > golsSofridosPartidaAtual){
				vitorias++;
			}
			else if(golsMarcadosPartidaAtual == golsSofridosPartidaAtual){
				empates++;
			} else if(golsMarcadosPartidaAtual < golsSofridosPartidaAtual){
				derrotas++;
			}

			partidasDisputadas++;

			return pontosGanhos();
		}
		return 0;
	}


	/**
	 * Calcula e retorna o total de pontos ganhos por aquela equipe.
	 * @return Pontos ganhos da equipe (valor inteiro não negativo)
	 */
	public int pontosGanhos() {
		return (vitorias * 3) + empates;
	}

	/**
	 * Retorna o saldo de gols da equipe, ou seja, a diferença entre os gols marcados e os gols
	 * sofridos. Pode ser um valor negativo, se a equipe tiver sofrido mais gols do que marcado.
	 * @return Inteiro com o saldo de gols da equipe.
	 */
	public int saldoDeGols() {
		return golsMarcados - golsSofridos;
	}

	/**
	 * Verifica se esta equipe supera outra na tabela do campeonato, retornando TRUE/FALSE conforme
	 * o resultado da verificação.
	 * @param outra A outra equipe a ser comparada com esta.
	 * @return TRUE se esta equipe supera a outra na tabela; false caso contrário.
	 */
	public boolean superaEquipe(Equipe outra){
		return pontosGanhos() > outra.pontosGanhos() 
				&& vitorias > outra.vitorias 
				&& golsMarcados > outra.golsMarcados;
	}

	/**
	 * Deve retornar um resumo da situação da equipe conforme solicitado no enunciado.
	 * @return Uma string com a informação resumida da equipe.
	 */
	public String resumo() {
		return String.format("%s %02d %02d %02d %02d %02d %02d %02d %02d", nome,pontosGanhos(),
		partidasDisputadas, vitorias,
		empates, derrotas,
		golsMarcados, golsSofridos, saldoDeGols());
	}

	// para fazer sem metodo é só usar %02d
	// private String formatarNumeroDoisDigitos(int numero){
	// 	String s = Integer.toString(numero);
	// 	if(s.length() < 2){
	// 		return s = "0" + s;
	// 	}
	// 	return s;
	// }
}
