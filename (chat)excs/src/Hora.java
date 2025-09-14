/*
 * 🔹 1. Fundamentos de Classe e Encapsulamento

    Modele uma classe Hora que armazena hora, minuto e segundo.

    Deve validar valores (0–23, 0–59, 0–59).

    Imprima no formato HH:MM:SS.

    Crie métodos para:

    Somar segundos.

    Comparar se uma hora é anterior a outra.

    Calcular diferença em segundos entre duas horas.

    👉 Esse é tipo um "mini Data", só que focado em tempo.
 */
public class Hora {
    private int hora;
    private int minuto;
    private int segundo;
    private static final int HORA_EM_SEGUNDOS = 3600;
    private static final int MINUTOS_EM_SEGUNDOS = 60;

    public Hora(int hora, int minuto, int segundo){
        if(!validarHora(hora)){
            throw new IllegalArgumentException("Horas inválidas");
        }
        if(!validarMinutoSegundo(minuto)){
            throw new IllegalArgumentException("Minutos inválidos");
        }
        if(!validarMinutoSegundo(segundo)){
            throw new IllegalArgumentException("Segundos inválidos");
        }
        this.hora = hora;
        this.minuto = minuto;
        this.segundo = segundo;
    }

    /**
     * Valida uma hora no formato 0 ~ 23
     * @param hora hora a ser validada
     * @return true caso a hora esteja dentro do intervalo 0 ~ 23
     */
    public boolean validarHora(int hora){
        return hora >= 0 && hora <= 23; 
    }

    /**
     * Valida minuto no formato 0 ~ 59
     * @param tempo tempo a ser validado
     * @return true caso o tempo esteja dentro do intervalo 0 ~ 59
     */
    public boolean validarMinutoSegundo(int tempo){
        return tempo >= 0 && tempo <= 59; 
    }

    /**
     * Coloca os valores de hora, minuto , segundo em uma array
     * @return array de inteiros contendo horas,minutos e segundos
     */
    public int[] colocarHorasEmArray(){
        return new int[] {hora, minuto, segundo};
    }

    /**
     * Soma segundos a uma hora existente. Transforma hora e minuto em segundo, somando com os já existentes. Soma esse 
     * total de segundos aos segundos a serem somados. Salva os novos valores em um array de nova hora e depois define 
     * os valores da classe com o novo valor de horas.
     * @param segundosASomar int contendo os novos segundos a serem somados as horas atuais
     */
    public void somarSegundos(int segundosASomar){
        int totalHorasAtuaisEmSegundos = transformarHoraEmSegundos();

        totalHorasAtuaisEmSegundos += segundosASomar;

        int[] novaHora = new int[3];
        // novaHora[0] = totalHorasAtuaisEmSegundos / HORA_EM_SEGUNDOS;
        // novaHora[1] = (totalHorasAtuaisEmSegundos % HORA_EM_SEGUNDOS) / MINUTOS_EM_SEGUNDOS;
        // novaHora[3] = (totalHorasAtuaisEmSegundos % MINUTOS_EM_SEGUNDOS);

        this.hora = novaHora[0] = totalHorasAtuaisEmSegundos / HORA_EM_SEGUNDOS;
        this.minuto = novaHora[1] = (totalHorasAtuaisEmSegundos % HORA_EM_SEGUNDOS) / MINUTOS_EM_SEGUNDOS;
        this.segundo = novaHora[2] = (totalHorasAtuaisEmSegundos % MINUTOS_EM_SEGUNDOS);

    }

    public int transformarHoraEmSegundos(){
        return hora * (HORA_EM_SEGUNDOS) + (minuto * MINUTOS_EM_SEGUNDOS) + segundo;
    }

    /**
     * Compara a hora desse objeto com outra hora. Se a hora for igual compara o minuto, caso seja igual compara os minutos.
     * Se as horas foram iguais retorna exceção.
     * @param outraHora hora a ser comparada
     * @return  maior objeto do tipo Hora
     */
    public Hora compararHora(Hora outraHora){
        if(this.equals(outraHora)){
            throw new IllegalArgumentException("As horas são iguais");
        }

        Hora maiorHora = this;

        if(hora < outraHora.hora)
            maiorHora = outraHora;

        if(hora == outraHora.hora){
            if(minuto < outraHora.minuto){
                maiorHora = outraHora;
            } else if(minuto == outraHora.minuto && segundo < outraHora.segundo){
                maiorHora = outraHora;
            }
        }

        /* refatorando:
            Hora maior;

            if (hora != outraHora.hora) {
                maior = hora > outraHora.hora ? this : outraHora;
            } else if (minuto != outraHora.minuto) {
                maior = minuto > outraHora.minuto ? this : outraHora;
            } else {
                maior = segundo > outraHora.segundo ? this : outraHora;
            }

            return maior;
        }
         */

        // hora maior q outra: maior hora, se horas ==, maior minuto, se minutos --, maior segundo
        return maiorHora;
    }

    public int calcularDiferencaEmSegundos(Hora outraHora){
        int totalSegundosOutraHora = outraHora.transformarHoraEmSegundos();

        return Math.abs(transformarHoraEmSegundos() - totalSegundosOutraHora);
    }

    /**
     * Formata uma string de horas no formato HH:MM:SS
     * @return hora formata no formato HH:MM:SS
     */
    public String formatarHora(){
        StringBuilder horaFormatada = new StringBuilder();
        int arrayHora[] = colocarHorasEmArray();

        for(int i = 0; i < arrayHora.length; i++){
            horaFormatada.append(String.format("%02d",arrayHora[i]));

            if(i == 0 || i == 1){
                horaFormatada.append(":");
            }
        }
        return horaFormatada.toString();
    }

}
