import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AtividadeComplementarTest {
private AtividadeComplementar a1;

    @BeforeEach
    void setUp() {
        a1 = new AtividadeComplementar(new int[] {160, 0, 60});
    }

    @Test
    public void calcularCreditosCorretamente(){
        assertEquals(5, a1.somarCreditosTotais());
    }

    @Test
    public void verificarTemCreditoSuficienteCorretamente(){
        assertFalse(a1.alunoTemCreditoSuficiente());
    }
}
