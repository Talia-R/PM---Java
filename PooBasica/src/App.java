public class App {
    public static void main(String[] args) throws Exception {
        ContaBancaria c1 = new ContaBancaria("12345678901", new int[]{1,2,3,4,5}, 100d, 50d);
        // c1.darLimiteEspecial(250d);
        // System.out.println(c1.sacar(30d));
        // System.out.println(c1.saldoTotal());

        System.out.println(c1.sacar(150d));
        // System.out.println(c1.depositar(25d));
    }
}
