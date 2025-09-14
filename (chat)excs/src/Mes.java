/*
 * 4. Exercícios específicos para datas (sem usar LocalDate)

Crie uma classe Mes:

Atributo: número do mês (1–12).

Retorne o nome do mês em português.

Método que informa quantos dias tem o mês (desconsidere anos bissextos por enquanto).

Crie uma função que recebe dia, mês e ano e valida se é uma data válida.

Exemplo: 30/02/2024 → inválido, 29/02/2024 → válido, 29/02/2023 → inválido.
 */

public class Mes {
    private int mes;
    private static final int[] QNT_DIA_MES = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31,30, 31};
    private static final String[] NOME_MES = {"", "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
    "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

    public Mes(int mes){
        if(mes <= 0 || mes > 12){
            throw new IllegalArgumentException("Mês inválido");
        }
        this.mes = mes;
    }

    /**
     * Retorna a quantidade de dias do mês armazenado neste objeto. Considerando meses de 28 a 31 dias e sem considerar 
     * ano bissexto.
     * @return o número de dia do mês armazenado
     */
    public int calcularQntosDiasNoMes(){
        /*
        int qntDias;

        switch(mes){
            case 1,3,5,7,8,10,12 -> qntDias = 31;
            case 4,6,9,11 -> qntDias = 30;
            default -> qntDias = 28;
        }
        return qntDias;
         */

         // Refarotando:
         return QNT_DIA_MES[mes];
    }

    /**
     * Retorna o nome do mês armazenado neste objeto.
     * @return nome do mês em português correspondente a este objeto.
     */
    public String retornarNomeMes(){
        return NOME_MES[mes];
    }

    /**
     * Valida se a data passada é válida.
     * Desconsidera anos bissextos
     * @param data string data a ser avaliada
     * @return true se a data for válida
     */
    public static boolean validarData(String data){

        String[] arrayData = data.split("/");

        int diaData = Integer.parseInt(arrayData[0]);
        int mesData = Integer.parseInt(arrayData[1]);
        int anoData = Integer.parseInt(arrayData[2]);

        return (mesData >= 1 && mesData <=12) && (diaData > 0 && diaData <= QNT_DIA_MES[mesData]) && anoData > 1900;
    }


}
