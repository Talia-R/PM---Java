/*import java.text.NumberFormat;

public class ContaBancaria {
    int MIN_Transicao = 1;
    int caracters_cpf = 11;
    int caracters_numeroConta = 5;
    double jurosLimiteEspecial = 0.03;

    String cpfInserido = new String();
    String[] cpfFormatado = new String[caracters_cpf];
    
    int[] numeroConta = new int[caracters_numeroConta];

    double limiteEspecialFornecido; // que o banco liberou para o cliente
    double limiteEspecialDisponivel;

    double saldoDisponivel; // achoq pd ser um metodo, vai ser atualizado de acordo com oq o cliente retira/poem
    double saldoTotal;

    public ContaBancaria(String cpfInserido, int[] numeroConta, double valorParaAbrirConta, double limiteEspecialFornecido){
        cpfFormatado = cpfInserido.split("");
        if(cpfFormatado.length != caracters_cpf){
            throw new IllegalArgumentException(String.format("Número de cpf inválido: deve ser equivalente a %i digitos", caracters_cpf));
        }
        this.cpfFormatado = cpfInserido.split("");
        if(numeroConta.length != caracters_numeroConta){
            throw new IllegalArgumentException(String.format("Número de conta inválido: deve ser equivalente a %i digitos", caracters_numeroConta));
        }
        this.numeroConta = numeroConta;
        if(valorParaAbrirConta < MIN_Transicao){
            throw new IllegalArgumentException(String.format("Deve depositar, no mínimo, R$%.2f para abrir uma conta", MIN_Transicao));
        }
        this.saldoDisponivel = depositar(valorParaAbrirConta);
        this.limiteEspecialFornecido = this.limiteEspecialDisponivel = limiteEspecialFornecido;

        this.saldoTotal = this.saldoDisponivel + this.limiteEspecialFornecido;
    }

    public Boolean verificarMinValorInserido(double valor){
        return valor > MIN_Transicao;
    }

    public Boolean temSaldoTotalDisponivel(double valor){
        return saldoTotal() >= valor;
    }

    public double saldoTotal(){
        return saldoDisponivel + limiteEspecialDisponivel;
    }

    // public double darLimiteEspecial(double valor){
    //     return this.limiteEspecialFornecido += valor;
    // }
    // 752 saldoTotal
    // 502 saldoDisponivel
    // 250 limite
    // 700 valorSaque
    public double sacar(double valor){
        if(verificarMinValorInserido(valor)){
            if(temSaldoTotalDisponivel(valor)){
                if(valor > saldoDisponivel){
                    double valorFaltante = valor - saldoDisponivel;
                    // System.out.println(String.format("Desejar utilizar %.2f do seu limite especial?", valorFaltante));
                    System.out.println(String.format("Utilizou %.2f do seu limite especial", valorFaltante));
                    saldoDisponivel = 0;
                    limiteEspecialDisponivel -= valorFaltante;
                    return Math.abs(saldoTotal() - valor);
                }
                saldoDisponivel -= valor;
                return saldoTotal() - valor;
            }
        System.out.println("Saldo insuficiente");
        return saldoTotal();
        }
        return saldoDisponivel;
        // chamar metodo de verificao de valor inserido, tem que ser > MIN_Transicao
            //verifica chamar temSaldoTotalDisponivel
                // sim,   
                    // se esse valor > saldoDisponivel?
                        // sim , pgnta pro usuario: quer usar tanto do seu limiteEspecial?
                            // sim,
                                // decrementa limiteEspecialDisponivel
                                // atualiza saldoTotalDisponivel
                            // nao, termina a operação
                        // não,
                            // decrementa do saldoDisponivel
                            // atualiza saldoTotalDisponivel
                // nao, saldo insuficiente
    }

    public double depositar(double valor){
        if(verificarMinValorInserido(valor)){
            if(saldoDevedorLimiteEspecial() != 0){            
                double divida = calcularJurosLimiteEspecial() + Math.abs(saldoDevedorLimiteEspecial());
                if(valor <= divida){
                    divida -= valor;
                    limiteEspecialDisponivel += valor;
                    saldoDisponivel += valor;  
                    return saldoTotal += valor;
                }
                divida -= valor * (-1);
                limiteEspecialDisponivel = limiteEspecialFornecido;
                saldoDisponivel += divida;
                divida = 0;
                return saldoTotal += divida;
                }
            return saldoTotal += valor;
        }
        return saldoTotal;
        
        // chamar metodo de verificao de valor inserido, tem que ser > MIN_Transicao
            // saldoDevedorLimiteEspecial() != 0
                // double divida = calcularJurosLimiteEspecial + saldoDevedorLimiteEspecial
                // valor > divida
                    // divida -= valor * (-1)
                    // saldoLimiteEspecialDisponivel = saldoLimiteEspecialFornecido
                    // saldoDisponivel+= divida;
                    // saldoTotal += divida;
                    // double divida = 0;
                // valor <= divida
                    // divida -= valor;
                    // limiteEspecialDisponivel += divida
                    // saldoDisponivel += divida
                    // saldoTotal += divida
            // nao, deposito vai para toda conta
    }

    // temLimiteEspecialDisponivel?

    public double saldoDevedorLimiteEspecial(){
        return limiteEspecialDisponivel < limiteEspecialFornecido ? (limiteEspecialDisponivel - limiteEspecialFornecido) : 0;
    }

    public double calcularJurosLimiteEspecial(){
        return Math.abs(saldoDevedorLimiteEspecial()*jurosLimiteEspecial);
    }

    public double verificarLimiteEspecial(){
        return limiteEspecialFornecido - limiteEspecialDisponivel;
    }

    // metodo: darLimiteAoCliente

}
 */


 public class ContaBancaria{
    private static final int MIN_VALOR_OPERACAO = 1;
    private static final int MAX_TAM_CPF = 11;
    private static final int MAX_TAM_NUMERO_CONTA = 5;

    private String cpf;
    private String numeroConta;

    private double saldoAtual;
    private double limiteEspecialAtual;


    private double limiteEspecialFornecido;

    // public void init(String cpf, String numeroConta, double depositoParaAbertura){
    //     if(validarCPF() && validarNumeroConta() && depositoParaAbertura >= MIN_VALOR_OPERACAO){
    //         this.cpf = cpf;
    //         this.numeroConta = numeroConta;
    //         saldoAtual = depositoParaAbertura;
    //     }
    
    // }

    public ContaBancaria(String cpf, String numeroConta, double depositoParaAbertura, double limiteFornecido){
        if((!validarCPF()) && (!validarNumeroConta()) && depositoParaAbertura < MIN_VALOR_OPERACAO){
            throw new IllegalArgumentException();
        }
        this.cpf = cpf;
        this.numeroConta = numeroConta;
        this.limiteEspecialFornecido = limiteEspecialAtual =  limiteFornecido;
        saldoAtual = depositoParaAbertura;
    }

    /**
     * Formata e valida se o CPF tem 11 dígitos
     * @return true caso cpf seja válido e false caso não
     */
    public boolean validarCPF(){
        String cpfFormatado = cpf.trim();
        return cpfFormatado.length() == MAX_TAM_CPF && cpfFormatado.matches("\\d+");
    }
    
    /**
     * Formata e valida se o número da conta tem  dígitos
     * @return true caso conta seja valida e false caso não
     */
    public boolean validarNumeroConta(){
        String numeroContaFormatado = numeroConta.trim();
        return numeroContaFormatado.length() == MAX_TAM_NUMERO_CONTA && numeroContaFormatado.matches("\\d+");
    }

    /**
     * Altera o valor do limite atual se o novo limite não for igual ao anterior e nem negativo
     * @param novoLimite double não negativo
     */
    public void alterarLimiteFornecido(double novoLimite){
        limiteEspecialFornecido = novoLimite >= 0 && novoLimite != limiteEspecialFornecido ? novoLimite : limiteEspecialFornecido;
    }

    /**
     * Calcula o saldo total atual do usuário incluindo o valor na conta mais o limite especial
     * @return double com o saldo atual
     */
    public double calcularSaldoTotal(){
        return saldoAtual + limiteEspecialAtual;
    }

    public double sacar(){
        return calcularSaldoTotal();
    }

    public double depositar(){
        return calcularSaldoTotal();
    }

    


 }