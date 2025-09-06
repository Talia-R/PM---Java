import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class ContaBancariaTest {
    // @Test
    // public void darLimiteEspecialCerto(){
    //     ContaBancaria c1 = new ContaBancaria("12345678901", new int[]{1,2,3,4,5}, 500.00, 2.00);
    //     // c1.darLimiteEspecial(250.00);
    //     assertEquals(250d, c1.limiteEspecialFornecido, 0.01);
    //     // c1.darLimiteEspecial(50.00);
    //     assertEquals(300d, c1.limiteEspecialFornecido, 0.01);
    // }

    @Test 
    public void sacarCerto(){
        // ContaBancaria c1 = new ContaBancaria("12345678901", new int[]{1,2,3,4,5}, 500d, 2d,250d);
        // assertEquals(52d, c1.sacar(700d), 0.01);
    }

    @Test
    public void depositarCerto(){
        ContaBancaria c2 = new ContaBancaria("12345678901", new int[]{1,2,3,4,5}, 100, 50);
        assertEquals(200d, c2.depositar(50d), 0.01);
    }

    @Test
    public void depositarQuitandoTodaDivida(){
        ContaBancaria c2 = new ContaBancaria("12345678901", new int[]{1,2,3,4,5}, 100, 50);
        c2.sacar(150d);
        assertEquals(150d, c2.depositar(150d));
    }

    @Test void depositarQuitandoParteDaDivida(){
        ContaBancaria c2 = new ContaBancaria("12345678901", new int[]{1,2,3,4,5}, 100, 50);
        c2.sacar(150d);
        assertEquals(23.5, c2.depositar(25d));
    }
}
