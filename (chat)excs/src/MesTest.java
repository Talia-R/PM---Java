import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MesTest {
    private Mes m1;
    
    @BeforeEach
    void setup(){
        m1 = new Mes(4);
    }

    @Test
    public void calcularQntosDiasNoMesCorretamente(){
        assertEquals(30, m1.calcularQntosDiasNoMes());
    }

    @Test
    public void retornarNomeMesCorretamente(){
        assertEquals("Abril", m1.retornarNomeMes());
    }
    
    @Test
    public void validarDataCorretamente(){
        assertTrue(Mes.validarData("12/09/2025"));
    }

    @Test
    public void validarDataIncorreta(){
        assertFalse(Mes.validarData("31/09/2025"));
    }
}
