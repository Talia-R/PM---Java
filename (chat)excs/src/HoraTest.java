import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HoraTest {
    private Hora h1;

    @BeforeEach
    void setup(){
        h1 = new Hora(5, 25, 50);
    }
    
    @Test
    public void imprimirHoraCorretamente(){
        String resultado = "05:25:50";
        assertEquals(resultado, h1.formatarHora());
    }

}
