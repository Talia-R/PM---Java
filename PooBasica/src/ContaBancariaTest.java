import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContaBancariaTest{
    private ContaBancaria c1;

    @BeforeEach
    void setup(){
        c1 = new ContaBancaria("12345678912", "12345", 1, 50);
    }

    // @Test
    // public void criarContaCorretamente(){
    //     c1 = new ContaBancaria("12345678912", "12345", 1, 50);
    // }

    @Test
    public void verificarUsoLimiteEspecialCorretamente(){
        c1.setLimiteEspecialAtual(25);
        assertTrue(c1.verificarUsoLimiteEspecial());
    }

    @Test
    public void verificarUsoLimiteEspecialIncorretamente(){
        assertFalse(c1.verificarUsoLimiteEspecial());
    }

    @Test 
    public void calcularDividaLimiteEspecialCorretamente(){
        c1.setLimiteEspecialAtual(25);
        assertEquals(25.75, c1.calcularDividaLimiteEspecial(), 0.01);
    }

    @Test
    public void depositarSemTerDividaCorretamente(){
        c1.depositar(50d);
        assertEquals(101d, c1.calcularSaldoTotal(), 0.01);
    }

    @Test
    public void depositarPagandoParteDaDividaCorretamente(){
        c1.setSaldoAtual(0);
        c1.setLimiteEspecialAtual(0);

        c1.depositar(50d);
        assertEquals(50d, c1.calcularSaldoTotal(), 0.01);
    }

    @Test
    public void depositarPagandoTodaDividaCorretamente(){
        c1.setSaldoAtual(0);
        c1.setLimiteEspecialAtual(0);

        c1.depositar(51.5d);
        assertEquals(50d, c1.calcularSaldoTotal(), 0.01);
    }

    @Test
    public void sacarCorretamente(){
        c1.setSaldoAtual(50d);
        c1.sacar(25d);
        assertEquals(25d,c1.getSaldoAtual(), 0.01);
    }

    @Test
    public void sacarUsandoLimiteCorretamente(){
        c1.setSaldoAtual(50d);
        c1.sacar(80d);
        assertEquals(20d,c1.calcularSaldoTotal(), 0.01);
    }

    @Test
    public void sacarIncorretamente(){
        c1.setSaldoAtual(50d);
        c1.sacar(120d);
        assertNotEquals(0d, c1.calcularSaldoTotal(), 0.01);
    }

}