import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PeriodoTest {
    private Periodo p1;
    private Periodo p2;
    private Periodo p3;
    private Periodo p4;

    @BeforeEach
    void setup(){
        p1 = new Periodo("05/01/2025", "08/01/2025");
        p2 = new Periodo("08/01/2025", "04/01/2027");

        p3 = new Periodo("08/01/2025", "04/01/2024");
        p4 = new Periodo("08/01/2025", "09/01/2026");
    }

    @Test
    public void transformarDataEmDiasCorridosCorretamente(){
        int[] data = {05,01,2025};
        int[] data2 = {05,02,2025};
        int[] data3 = {31,12,2025};
        assertEquals(5, Periodo.transformarDataEmDiasCorridos(data));
        assertEquals(36, Periodo.transformarDataEmDiasCorridos(data2));
        assertEquals(365, Periodo.transformarDataEmDiasCorridos(data3));
    }

    @Test
    public void VerificarDataInicioAntesDataFim(){
    assertTrue(p1.dataInicioAntesDataFim());
    assertTrue(p2.dataInicioAntesDataFim());
    assertFalse(p3.dataInicioAntesDataFim());
    }

    @Test
    public void calcularDuracaoPeriodo(){
        // testar se tá funcionando para o mesmo ano
        assertEquals(366, p4.calcularDuracaoPeriodo());
    }
}
