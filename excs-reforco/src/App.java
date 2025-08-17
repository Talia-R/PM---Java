import java.util.Arrays;
import java.util.Scanner;


public class App {
    public static void main(String[] args) throws Exception {
    //   - 5 automoveis ----------- ok
    //     - id = placa ----------- ok
    //     -consumo médio = km/l ----------- ok
    //     1l combustivel = 5.89 ----------- ok
    
    
    //     gestor pode registrar várias rotas ----------- ok
    
    //     registrarRotasIndividuais(String placa)

    //     registrarQualAutomovelPercorreuMaisKm

    //     registrarQualAutomovelConsumiuMais

    //     registrarValorTotalCombustivelGasto

    //     registrarQuemGastaMaisDaMedia

        Scanner entrada = new Scanner(System.in);

        String[] automoveis = {"QJP4H27", "BRA2G91", "XLM9C54", "KZT1B08" , "RDF7J63"};
        double valorCombustivel = 5.89;

        System.out.print("Quantas rodas você deseja registrar?: ");
        String rotasASeremRegistradas = entrada.next();
        System.out.println();

        String[][] todasRotas = new String [automoveis.length][Integer.valueOf(rotasASeremRegistradas) + 1];

        for(int i = 0; i < automoveis.length; i++){
            todasRotas[i][0] = automoveis[i];
        }

        registrarRotasIndividuais(todasRotas, entrada);

        // for (int i = 0; i < todasRotas.length; i++) {
        //     System.out.println(Arrays.toString(todasRotas[i]));
        // }



        entrada.close();
    }

    /**
     * Calcula o consumo médio de um automóvel dividindo o km percorrido pela quantidade de litros gastas
     * @param km quilômetros percorridos
     * @param litro litros de combustível gasto
     * @return consumo médio do automóvel
     */
    public static double consumoMedioAutomovel(int km, double litro){
        return km/litro;
    }

    public static void registrarRotasIndividuais(String[][] rotasSalvas, Scanner entrada){

        System.out.println("-".repeat(5) + "Automóveis Disponíveis" + "-".repeat(5) );
        for(int i = 0; i < rotasSalvas.length; i++){
            System.out.println("Automóvel " + (i + 1)+ ": " + rotasSalvas[i][0]);
        }
        System.out.print("Para qual dos automovéis deseja inserir a rota: ");
        int automovelEscolhido = Integer.valueOf(entrada.next());

        // for(int i = 0; i < rotasSalvas.length; i++){
        //     if(automovelEscolhido == rotasSalvas[i][0].)
        // }
    }
}
