import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.NumberFormat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DinheiroTest {
    private Dinheiro c1;
    private Dinheiro c2;

    @BeforeEach
    void setup(){
    c1 = new Dinheiro(125.5);
    c2 = new Dinheiro(125);
    }

    @Test
    public void converterParaCentavosCorretamente(){        
        assertEquals(12550, c1.converterParaCentavos(125.50), 0.01);
    }

    @Test
    public void converterParaReaisCorretamente(){        
        assertEquals(125.5, c1.converterParaReais());
    }

    @Test
    public void somarValorCorretamente(){
        int resultado = 130;
        c1.somar(4.5);
        assertEquals(resultado, c1.converterParaReais());
    }

    @Test
    public void somarCentavosCorretamente(){
        double resultado = 125.80;
        c1.somar(0.3);
        assertEquals(resultado, c1.converterParaReais());
    }

    @Test
    public void compararValorCorretamente(){
        Dinheiro resultado = c1;
        assertEquals(resultado, c1.compararValores(c2));
    }

    @Test
    public void toStringCorreto(){
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
        String resultado = moeda.format(125.50);
        assertEquals(resultado, c1.toString());
    }

}
