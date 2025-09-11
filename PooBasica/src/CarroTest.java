import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CarroTest {
    private Carro c1;

    @BeforeEach
    void setup(){
        c1 = new Carro("ABC1234", 70);
    }

    @Test
    public void acelerarCorretamente(){
        c1.acelerar(10);
        assertEquals(80, c1.exibirVelocAtual());
    }

    @Test
    public void acelerarMaisQueLimiteCorretamente(){
        c1.acelerar(130);
        assertEquals(120, c1.exibirVelocAtual());
    }

    @Test
    public void frearCorretamente(){
        c1.frear(10);
        assertEquals(60, c1.exibirVelocAtual());
    }

    @Test
    public void frearParadoCorretamente(){
        c1.frear(80);
        assertEquals(0, c1.exibirVelocAtual());
    }

}
