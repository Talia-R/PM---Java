import java.text.NumberFormat;

public class ContaBancaria{
    private static final int MIN_VALOR_OPERACAO = 1;
    private static final double JUROS = 0.03;
    private static final int MAX_TAM_CPF = 11;
    private static final int MAX_TAM_NUMERO_CONTA = 5;

    private String cpf;
    private String numeroConta;
    private double limiteEspecialFornecido;

    private double saldoAtual;
    private double limiteEspecialAtual;

    public ContaBancaria(String cpf, String numeroConta, double depositoParaAbertura, double limiteFornecido){
        if(!validarCPF(cpf))
            throw new IllegalArgumentException("CPF inválido");
        if(!validarNumeroConta(numeroConta))
            throw new IllegalArgumentException("Número da conta inválido");
        if(depositoParaAbertura < MIN_VALOR_OPERACAO)
            throw new IllegalArgumentException("Valor mínimo para abertura de conta deve ser superior a R$1.00");
        
        this.cpf = cpf;
        this.numeroConta = numeroConta;
        this.limiteEspecialFornecido = limiteEspecialAtual =  limiteFornecido;
        saldoAtual = depositoParaAbertura;
    }

    //#region setters
        public void setLimiteEspecialAtual(double valor){
            this.limiteEspecialAtual = valor;
        }

        public void setSaldoAtual(double valor){
            this.saldoAtual = valor;
        }

    //#endregion

        //#region getters
        public double getSaldoAtual(){
            return saldoAtual;
        }


    //#endregion

    //#region void
        /**
         * Altera o valor do limite atual se o novo limite não for igual ao anterior e nem negativo
         * @param novoLimite double não negativo
         */
        public void alterarLimiteFornecido(double novoLimite){
            limiteEspecialFornecido = novoLimite >= 0 && novoLimite != limiteEspecialFornecido ? novoLimite : limiteEspecialFornecido;
        }
    //#endregion

    //#region boolean
        /**
         * Formata e valida se o CPF tem 11 dígitos
         * @return true caso cpf seja válido e false caso não
         */
        public boolean validarCPF(String cpf){
            String cpfFormatado = cpf.trim();
            return cpfFormatado.length() == MAX_TAM_CPF && cpfFormatado.matches("\\d+");
        }
        
        /**
         * Formata e valida se o número da conta tem  dígitos
         * @return true caso conta seja valida e false caso não
         */
        public boolean validarNumeroConta(String numeroConta){
            String numeroContaFormatado = numeroConta.trim();
            return numeroContaFormatado.length() == MAX_TAM_NUMERO_CONTA && numeroContaFormatado.matches("\\d+");
        }

        /**
         * Verifica se o valor inserido é maior ou igual ao valor minimo para operação
         * @param valor double inserido pelo usuário
         * @return true se o valor for maior ou igual o mínimo
         */
        public boolean verificarValorMinOperacao(double valor){
            return valor >= MIN_VALOR_OPERACAO;
        } 

        /**
         * Verifica se o usuário utilizou o limite especial
         * @return true se o limite foi usado
         */
        public boolean verificarUsoLimiteEspecial(){
            return limiteEspecialAtual != limiteEspecialFornecido;
        }

        /**
         * Verifica se o valor total disponivel na conta é maior que o valor inserido
         * @param valor double inserido
         * @return true se o valor total disponivel é maior do que o valor inserido
         */
        public boolean temSaldoTotalDisponivel(double valor){
            return calcularSaldoTotal() >= valor;
        }
    //#endregion

    //#region double
        /**
         * Calcula o saldo total atual do usuário incluindo o valor na conta mais o limite especial
         * @return double com o saldo atual
         */
        public double calcularSaldoTotal(){
            return saldoAtual + limiteEspecialAtual;
        }

        /**
        * Calcula o valor do juros sobre o limite que foi utilizado. O cálculo é feito em base decimal
        * @return valor do juros em base decimal. 
        */ 
        public double calcularJuros(){
            double qntUsada = limiteEspecialFornecido - limiteEspecialAtual ;
            return qntUsada * JUROS;
        }

        /**
         * Calcula o saldo devedor atual incluindo o juros.
         * @return double com o total do saldo devedor mais o juros
         */
        public double calcularDividaLimiteEspecial(){
            return (limiteEspecialFornecido - limiteEspecialAtual) + calcularJuros();
        }

        /** Sacar um valor: verifica se o valor fornecido é válido para operação e maior que o saldo atual que o cliente tem na conta o saque
         * é feito sem alterar o valor do limite especial. Se o valor for maior, será feito o uso do saldo da conta juntamente
         * com o valor do limite especial. Caso o valor seja maior que o saldo suficiente da conta (saldo + limite especial)
         * retorna -1;
         * @param valor double valor de saque
         * @return o valor total da conta casa a operação ocorra e -1 caso falhe
         */
        public double sacar(double valor){
            if(temSaldoTotalDisponivel(valor)){
                // sacar valor do saldo atual
                if(saldoAtual >= valor){
                    saldoAtual -= valor;
                    return calcularSaldoTotal();
                }
                // sacar do limite
                double faltanteParaSaque = valor - saldoAtual;
                saldoAtual = 0;
                limiteEspecialAtual -= faltanteParaSaque;
                return calcularSaldoTotal();
            }
            return -1;
        }

        /**
         * Depositar um valor: verifica se o valor fornecido é válido para operação, se houve utilização do Limite Especial
         * calcula o valor de toda divida incluindo o juros. O valor inserido pode ser suficiente para pagar parte da divida
         * ou seu valor completo. Se o valor for suficiente ou maior que a divida, o que sobrar (superavitDeposito) vai para
         * o saldo do cliente.
         * Caso não haja uso do limite especial, deposita o valor direto no saldo do usuário.
         * @param valor double inserido para deposito
         * @return o saldo total que tem na conta do cliente
         */
        public double depositar(double valor){
            if(verificarValorMinOperacao(valor)){
                // depositar enquanto devendo
                if(verificarUsoLimiteEspecial()){
                    double divida = calcularDividaLimiteEspecial();
                    // pagar parte doq deve
                    if(valor < divida){
                        // divida -= valor; acho q n precisa pq só decrementa variavel local
                        limiteEspecialAtual += valor;
                        return calcularSaldoTotal();
                    }

                    // pagar tudo oq deve
                    double superavitDeposito = valor - divida;
                    limiteEspecialAtual = limiteEspecialFornecido;
                    saldoAtual += superavitDeposito;
                    return calcularSaldoTotal();
                }
                // depositar sem dever
                saldoAtual += valor;
                return calcularSaldoTotal();
            }
            return -1;
        }
    //#endregion

    //#region String
    public String extratoBancario(){
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
        return String.format("--- Extrato --- \n" +
                            "Conta: %s\n" +
                            "Saldo Disponivel: %s\n" +
                            "Limite Especial Disponivel: %s\n" +
                            "Usou %s do valor total do limite especial de: %s",
                            numeroConta,
                            moeda.format(saldoAtual), 
                            moeda.format(limiteEspecialAtual),
                            moeda.format(calcularDividaLimiteEspecial()),
                            moeda.format(limiteEspecialFornecido)
                            );
    }
    //#endregion
 }