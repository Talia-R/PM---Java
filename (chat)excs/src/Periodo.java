/*
 * 5. Mini-projeto preparatório

Modele uma classe Periodo:

Possui duas datas (início e fim).

Valide se a data de início vem antes da data de fim.

Método que retorna a duração em dias do período.

👉 Esse é praticamente o "esqueleto" de calcular diferença entre duas datas.
 */
public class Periodo {
    private static final int[] QNT_DIA_MES = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31,30, 31};
    private String dataInicio;
    private String dataFim;

    public Periodo(String dataInicio, String dataFim){
        if(!validarData(dataInicio))
            throw new IllegalArgumentException("Data inválida");
        if(!validarData(dataFim))
            throw new IllegalArgumentException("Data inválida");

        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    /**
     * Valida se a data passada é válida.
     * Desconsidera anos bissextos
     * @param data string data a ser avaliada
     * @return true se a data for válida
     */
    private boolean validarData(String data){
        int[] arrayData = construirArrayData(data);
        int dia = arrayData[0];
        int mes = arrayData[1];
        int ano = arrayData[2];

        return ((mes >= 1 && mes <=12) && (dia >= 1 && dia <= QNT_DIA_MES[mes]) && (ano >=1900));
    }

    /**
     * Retorna um array de inteiros a partir de uma string passada por parametro.
     * Formato em data: [dia, mes, ano]
     * @param data string a ser modificada
     * @return array de inteiros
     */
    private int[] construirArrayData(String data){
        String partes[] = data.split("/");
        int[] novoArray = new int[partes.length];

        for(int i = 0; i < partes.length; i++){
            novoArray[i] = Integer.parseInt(partes[i]);
        }
        
        return novoArray;
    }

    /**
     * Calcula o total de dias do inicio do ano 01/01 até uma data passada.
     * Calcula de 01/01 até 31/12
     * @param data data a ser calculada
     * @return int com quantidade de dias de 01/01 até a data passada.
     */
    protected static int transformarDataEmDiasCorridos(int[] data){
        int dia = data[0];
        int mes = data[1];

        int diasTotais = 0;

        for(int i = 0; i <= mes; i++){
            // qntdiasAtuais - totalArray
            if(i == mes){
                diasTotais += dia;
                break;
            }
            diasTotais += QNT_DIA_MES[i];
        }
        return diasTotais;
    }
    
    /**
     * Verifica se a data de inicio é anterior a data final.
     * @return true caso a data de inicio seja anterior a data final.
     */
    public boolean dataInicioAntesDataFim(){

        int[] arrayDataInicio = construirArrayData(dataInicio);
        int[] arrayDataFim = construirArrayData(dataFim);

        if(arrayDataInicio[2] != arrayDataFim[2]){
            return arrayDataInicio[2] < arrayDataFim[2];
        }

        int qntDiasDataInicio = transformarDataEmDiasCorridos(arrayDataInicio);
        int qntDiasDataFim = transformarDataEmDiasCorridos(arrayDataFim);

        return qntDiasDataInicio < qntDiasDataFim;
    }

    /**
     * Calcula o periodo, em dias, entre a data de inicio e a data final armazenadas.
     * Caso as datas a serem comparadas não ocorram no mesmo ano o cálculo considerará:
     * - dias restantes do ano inicio, 
     * - os anos intermediários (anos completos entre data inicio e data fim),
     * - dias do ano até a data fim.
     * @return int com a quantidade total de dias entre inicio e fim.
     */
    public int calcularDuracaoPeriodo(){
        int duracao = 0;

        int[] arrayDataInicio = construirArrayData(dataInicio);
        int[] arrayDataFim = construirArrayData(dataFim);

        int qntDiasDataInicio = transformarDataEmDiasCorridos(arrayDataInicio);
        int qntDiasDataFim = transformarDataEmDiasCorridos(arrayDataFim);

        if(arrayDataInicio[2] == arrayDataFim[2]){
            duracao = qntDiasDataFim - qntDiasDataInicio;
        }

        int qntDiasAnoCompleto = 365;
        int diferencaAno = (arrayDataFim[2] - arrayDataInicio[2]) - 1; // -1 porque já calculei os dias corridos do primeiro ano e os dias corridos do ultimo ano
        int qntDiasDiferencaAno = diferencaAno * qntDiasAnoCompleto;

        duracao = (qntDiasAnoCompleto - qntDiasDataInicio) + qntDiasDataFim + qntDiasDiferencaAno;

        return duracao;
    }

}
