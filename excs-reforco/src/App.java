import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        ArrayList<Prato> cardapio = new ArrayList<>();

        try {
            BufferedReader leitor = new BufferedReader(new FileReader("src\\cardapio.txt"));
            int qntPratosCardapio = Integer.parseInt(leitor.readLine());
            for(int i = 0; i < qntPratosCardapio; i++){
                String nome = leitor.readLine();
                String avaliacoesString = leitor.readLine();

                ArrayList<Integer> avaliacoes = new ArrayList<>();
                for (String posicao: avaliacoesString.split(" ")) {
                    avaliacoes.add(Integer.parseInt(posicao));
                }

                cardapio.add(new Prato(nome, avaliacoes));
            }

            leitor.close();
        } catch (IOException ioe) {
            System.out.println("Errro ao ler arquivo" + ioe.getMessage());
        }
        
        for (Prato p : cardapio) {
            System.out.println(p);
        }
    }


}
