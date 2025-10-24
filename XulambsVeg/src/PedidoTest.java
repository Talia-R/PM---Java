import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.LinkedList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PedidoTest {
    private LinkedList<Pedido> todosOsPedidos = new LinkedList<>();
    private Pedido p1 = new PedidoLocal();
    private Pedido p2 = new PedidoLocal();
    private Pedido p3 = new PedidoLocal();

    @BeforeEach
    void setup(){
        todosOsPedidos.add(p1);
        todosOsPedidos.add(p2);
        todosOsPedidos.add(p3);
    }

    @Test
    public void localizarPedidoCorretamente(){

        int idPedidoProcurado = p2.getIdPedido();
        assertTrue(p2.equals(App.localizarPedido(todosOsPedidos, idPedidoProcurado)));
    }
}
