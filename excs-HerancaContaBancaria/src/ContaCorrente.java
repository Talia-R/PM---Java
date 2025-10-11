public class ContaCorrente extends Conta {
    private static final double JUROS = 0.03;
    private double limiteEspecialFornecido;
    private double limiteEspecialAtual;

    public ContaCorrente(int cpf, int digitoIdentificador, double depositoParaAbertura, double limiteFornecido){
        super(cpf,digitoIdentificador,depositoParaAbertura);
        this.limiteEspecialFornecido = limiteEspecialAtual =  limiteFornecido;
    }

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
    //#endregion

    //#region Override
        /** Sacar um valor: verifica se o valor fornecido é válido para operação e maior que o saldo atual que o cliente tem na conta o saque
         * é feito sem alterar o valor do limite especial. Se o valor for maior, será feito o uso do saldo da conta juntamente
         * com o valor do limite especial. Caso o valor seja maior que o saldo suficiente da conta (saldo + limite especial)
         * retorna -1;
         * @param valor double valor de saque
         * @return o valor total da conta casa a operação ocorra e -1 caso falhe
         */
        @Override
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
        @Override
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
    
}
