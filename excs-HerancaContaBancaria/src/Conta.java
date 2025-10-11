public abstract class Conta {
    protected static final int MIN_VALOR_OPERACAO = 1;

    private int cpf;
    private int digitoIdentificador;
    protected double saldoAtual;


    public Conta(int cpf, int digitoIdentificador, double depositoParaAbertura){
        this.cpf = cpf;
        this.digitoIdentificador = digitoIdentificador;
        saldoAtual = depositoParaAbertura;
    }

    public abstract double sacar(double valor);
    public abstract double depositar(double valor);

    /**
    * Verifica se o valor inserido é maior ou igual ao valor minimo para operação
    * @param valor double inserido pelo usuário
    * @return true se o valor for maior ou igual o mínimo
    */
    public final boolean verificarValorMinOperacao(double valor){
        return valor >= MIN_VALOR_OPERACAO;
    } 
}
