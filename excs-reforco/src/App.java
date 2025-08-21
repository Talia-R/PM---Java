import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App{
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);

        /* List<String[]> listaRotasAssociadas = new ArrayList<>();
        List<List<String>> lista = new ArrayList<>();

        String[] veiculos = {"KZT4M89", "BRA2C15", "QJP9L42", "XLM7R61", "RDF3B08"};

        for (int i = 0; i < veiculos.length; i++) {
            lista.add(veiculos[i]);
            lista.addFirst(veiculos[i]);
        }



        System.out.println("Para qual automovel deseja inserir a rota: ");
        imprimirArrayString(veiculos);
        String veiculoEscolhido = entrada.nextLine();

        String rota;
        String quilometragem;

        for (int i = 0; i < rotasASeremRegistradas; i++) {
            System.out.print("Digite a rota: ");
            rota = entrada.nextLine();

            System.out.print("Digite a quilometragem: ");
            quilometragem = entrada.nextLine();

        }
             */

        System.out.println("Quantas rotas deseja registrar?");  
        int rotasASeremRegistradas = Integer.parseInt(entrada.nextLine());
        List<String[]> rotas = new ArrayList<>();


        String trajeto;
        String quilometragem;

        for(int i = 0; i < rotasASeremRegistradas; i++){
            for(int j = 0; j < 2; j++){
                if(j == 0){
                System.out.println("Digite o trajeto");
                trajeto = entrada.nextLine();
                rotas.get(i)[j] = trajeto; 
                continue;
                }

                System.out.println("Digite a quilometragem");
                quilometragem = entrada.nextLine();
                rotas.get(i)[j] = quilometragem; 

            }
        }
        imprimirListaString(rotas);

        entrada.close();
    }

    public static void imprimirArrayString(String[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    public static void imprimirListaString(List<String[]> listaDeArrayString){
        System.out.println(Arrays.asList(listaDeArrayString));
    }
}