import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CPFTest {

    @Test
    public void imprimirCpfCorretamente(){
        CPF c1 = new CPF("12345678901");
        String resultado = "123.456.789-01";
        assertEquals(resultado, c1.formatarCPF());
    }
}
