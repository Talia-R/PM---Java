import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

public class ProdutoTest {
    @Test
    public void calculaValorDeVendaCorretamente(){
        Produto prod = new Produto("Chá com gás", 100, 0.5);
        assertEquals(150d, prod.valorVenda(), 0.01);
    }
}
