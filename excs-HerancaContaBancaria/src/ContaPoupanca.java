public class ContaPoupanca extends Conta{
    private double[] taxaRendimento = {0.15, 0.85};

    public ContaPoupanca(int cpf, int digitoIdentificador, double depositoParaAbertura){
        super(cpf,digitoIdentificador,depositoParaAbertura);
    }

    public double estimarRendimento(){
        return saldoAtual * gerarRendimento();
    }

    public double atualizarSaldoRendimento(){
        return saldoAtual + estimarRendimento();
    }

    // double valor = min + (Math.random() * (max - min));
    private double gerarRendimento(){
        return taxaRendimento[0] + (Math.random() *( 0.85 - 0.15));
    }
    @Override
    public double sacar(double valor){}

    @Override
    public double depositar(double valor){}
}
