/*
 * 🔹 3. Operações com valores

    Modele uma classe Dinheiro:

    Armazena um valor em reais (double).

    Métodos para somar, subtrair e comparar valores (maior, menor, igual).

    Converta para centavos internamente (int) para evitar problemas com double.
 */

import java.text.NumberFormat;

public class Dinheiro {
    double valor;
    int centavos;

    public Dinheiro(double valor){
        if(valor < 0){
            throw new IllegalArgumentException("Valor inválido");
        }
        this.valor = valor;
        this.centavos = converterParaCentavos(valor);
    }

    /**
     * Converte o valor em reais para centavos.  O cálculo é feito multiplicando o valor em reais por 100
     * e arredondando com Math.round
     * @return um valor inteiro convertido em centavos
     */
    public int converterParaCentavos(double valor){
        return (int) Math.round(valor * 100);
    }

    /**
     * Soma um valor double a centavos. 
     * Converte o valor passado para centavos e depois soma ao valor armazenado
     * @param valor double para ser feita a soma
     * @return a soma dos dois valores em centavos
     */
    public int somar(double valor){
        int valorEmCentavos = converterParaCentavos(valor);
        return centavos += valorEmCentavos;
    }

    /**
     * Subtrai um valor double de centavos. 
     * Converte o valor passado para centavos e depois subtrai ao valor armazenado
     * @param valor double para ser feita a subtração
     * @return a subtração dos dois valores em centavos
     */
    public int subtrair(double valor){
        int valorEmCentavos = converterParaCentavos(valor);
        return centavos -= valorEmCentavos;
    }

    /**
     * Compara dois valores. 
     * Faz a comparação de centavos de dois valores retornando o maior
     * @param outroDinheiro Outro Dinheiro a ser comparado
     * @return o maior tipo Dinheiro entre esse ou o valor a ser comparado
     */
    public Dinheiro compararValores(Dinheiro outroDinheiro){
        return centavos > outroDinheiro.centavos ? this : outroDinheiro;
    }

    /**
     * Converte o valor de centavos para reais.
     * Usa o cálculo centavos / 100 para transformar o valor atual de centavos em reais
     * @return valor em reais
     */
    public double converterParaReais(){
        return (centavos / 100d);
    }

    public double getReais(){
        return converterParaReais();
    }

    /**
     * Retorna uma string em reais.
     *  Formato: R$ 2.50
     */
    @Override
    public String toString(){
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
        return String.format("%s", moeda.format(converterParaReais()));
    }

}
