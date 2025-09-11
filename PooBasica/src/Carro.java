public class Carro {
    private static final int MAX_VELOC = 120;
    private String placa;
    private int velocidade_atual;

    public Carro(String placa, int velocidade){
        if(!validarPlaca(placa)){
            throw new IllegalArgumentException("Placa inválida");
        }
        if(velocidade < 0 || velocidade > MAX_VELOC){
            throw new IllegalArgumentException("Velocidade inválida");
        }
        this.placa = placa;
        this.velocidade_atual = velocidade;
    }

    /**
     * Válida uma placa de acordo com o padrão brasileiro de 7 caracters no formato: ABC1234 ou LLLNLNN
     * @param placa placa do veiculo
     * @return true caso a placa atenda os requisitos
     */
    public boolean validarPlaca(String placa){
        if(placa == null){
            return false;
        }
        String placaFormatada = placa.trim().toUpperCase();

        // ABC1234
        String placasAntigas = "[A-Z]{3}\\d{4}";

        // LLLNLNN
        String placasMercoSul = "[A-Z]{3}\\d[A-Z]\\d{2}";

        return placaFormatada.matches(placasAntigas) || placaFormatada.matches(placasMercoSul);
    }

    /**
     * Acelera o carro, caso a velocidade máxima seja maior que o limite, o  carro acelerá apenas até o limite
     * @param velocidade int para incremento da velocidade
     * @return valor da velocidade após aceleramento
     */
    public void acelerar(int velocidade){
        velocidade_atual += velocidade;

        if(velocidade_atual> MAX_VELOC){
            velocidade_atual = MAX_VELOC;
        }
    }

    /**
     * Freoa o carro, caso a velocidade mínima seja menos que zero a velocidade atual é atualizada para zero
     * @param velocidade int para decremento da velocidade
     * @return valor da velocidade após desaceleração
     */
    public void frear(int velocidade){
        velocidade_atual -= velocidade;

        if(velocidade_atual <= 0){
            velocidade_atual = 0;
        }
    }

    /**
     * Exibe a velocidade atual do veiculo
     * @return velocidade atual
     */
    public int exibirVelocAtual(){
        return velocidade_atual;
    }

}
