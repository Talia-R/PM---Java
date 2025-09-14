import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HoraTest {
    private Hora h1;
    private Hora h2;

    @BeforeEach
    void setup(){
        h1 = new Hora(5, 25, 50);
        h2 = new Hora(5, 25,55);
    }
    
    @Test
    public void imprimirHoraCorretamente(){
        String resultado = "05:25:50";
        assertEquals(resultado, h1.formatarHora());
    }

    @Test
    public void somarSegundosCorretamente(){
        h1.somarSegundos(10);
        String resultado = "05:26:00";
        assertEquals(resultado, h1.formatarHora());
    }

    @Test
    public void compararHoraCorretamente(){
        Hora resultado = h2;
        assertEquals(resultado, h1.compararHora(h2));
    }
    
    @Test
    public void calcularDiferencaEmSegundosCorretamente(){
        int resultado = 0;
        assertEquals(resultado, h1.calcularDiferencaEmSegundos(h2));
    }

}
