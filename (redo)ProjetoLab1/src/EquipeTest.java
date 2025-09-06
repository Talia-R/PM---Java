import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EquipeTest {
    private Equipe equipe1;
    private Equipe equipe2;

    @BeforeEach
    void setup(){
        equipe1 = new Equipe("Time LPM");
        equipe2 = new Equipe("Time Xulambs");
        equipe1.registrarPartida("5x2"); //ganhou
        equipe1.registrarPartida("1x0"); // ganhou
        equipe1.registrarPartida("0x2"); // perdeu

        equipe2.registrarPartida("2x5"); // perdeu
        equipe2.registrarPartida("0x1"); // perdeu
        equipe2.registrarPartida("2x0"); // ganhou

    }
    
    @Test
    public void criaEquipeComNome(){
        Equipe equipe = new Equipe("Time LPM");
        assertTrue(equipe.resumo().contains("Time LPM"));
    }

    @Test
    public void criaEquipeAnonima(){
        Equipe equipeAnom1 = new Equipe();
        Equipe equipeAnom2 = new Equipe();
        assertTrue(equipeAnom2.resumo().contains("Equipe 2"));
    }

    @Test
    public void calculaSaldoDeGolsCorretamente(){
        Equipe equipe = new Equipe();
        equipe.registrarPartida("5x2");
        equipe.registrarPartida("1x0");
        equipe.registrarPartida("0x2");
        assertEquals(2, equipe.saldoDeGols());
    }

    @Test 
    public void superaEquipeCorretamente(){
        boolean resultado = equipe1.superaEquipe(equipe2);
        assertTrue(resultado);
    }

    @Test
    public void ignorarPlacarIncorreto(){
        Equipe equipe = new Equipe();
        equipe.registrarPartida("-2x3");
        assertEquals(0, equipe.saldoDeGols());
    }

    @Test
    public void calcularPontosGanhosCorretamente(){
        assertEquals(6, equipe1.pontosGanhos());
        assertEquals(3, equipe2.pontosGanhos());
    }

}
