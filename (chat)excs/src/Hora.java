public class Hora {
    private int hora;
    private int minuto;
    private int segundo;

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
     * Formata uma string de horas no formato HH:MM:SS
     * @return
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
