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
        int rotasASeremRegistradas = lerQuantidadeRotas(entrada);
        String[][] todasRotas = criarArrayRotas(automoveis, rotasASeremRegistradas);

        menu(entrada, todasRotas, rotasASeremRegistradas);

        // registrarRotasIndividuais(todasRotas, entrada);

        entrada.close();
    }

    public static void menu(Scanner entrada, String[][] todasRotas, int qntRotas){
        int opcaoEscolhida;
        
        do{
            System.out.println(formatarTituloExercicio("Menu"));
            System.out.print("0) Visualizar Rotas\n"+
                             "1) Registrar Rota Individual\n");
            opcaoEscolhida = Integer.parseInt(entrada.nextLine());

        switch (opcaoEscolhida){
            case 0:
            // System.out.println(todasRotas.length);
            visualizarArray(todasRotas);
            break;
            case 1:
            formatarTituloExercicio("Registrar Rota Individual");
            System.out.print("Para qual dos automoveis disponiveis deseja registrar a rota: ");
            for (int i = 0; i < todasRotas.length; i++) {
                System.out.print(todasRotas[i][0] + " / ");
            }
            // int automovelEscolhido = Integer.parseInt(entrada.nextLine());
            String automovelEscolhido = entrada.nextLine();
            String[] rota = new String[qntRotas];
            String[] quilometragem = new String[qntRotas];

            for(int i = 0; i< qntRotas; i++){
                System.out.println("Digite a rota " + (i + 1) + ": ");
                rota[i] = entrada.nextLine();
                System.out.print("Digite a quilometragem percorrida na rota '" + rota[i] + "': ");
                quilometragem[i] = entrada.nextLine();
            }
            registrarRotasIndividuais(todasRotas, automovelEscolhido, rota, quilometragem);
            break;
        }
        } while (opcaoEscolhida != -2);
    }

    public static String formatarTituloExercicio(String exercicio){
        return "-".repeat(5) + exercicio + "-".repeat(5);
    }

    public static void visualizarArray(String[][] todasRotas){
        int verificarRotaVazia = 0;
        for (int i = 0; i < todasRotas.length; i++) {
            for(int j = 0; j < todasRotas[i].length; j++){
                if(todasRotas[i][j] == null){
                    verificarRotaVazia++;
                }
            }

            // if(verificarRotaVazia > 0){
            // System.out.println(todasRotas[i][0] + ": rota não preenchida");
            // continue;
            // }

            System.out.println(Arrays.toString(todasRotas[i]));
            verificarRotaVazia = 0;
        }
    }

    /**
     * Registra a quantidade de rotas que o usuário quer salvar 
     * @param entrada scanner para ler entrada do usuário
     * @param qntAutomoveis quantidade de automoveis disponiveis no sistema
     * @return transforma a quantidade de rotas salvas em um inteiro e retorna
     */
    public static int lerQuantidadeRotas(Scanner entrada){
        System.out.print("Quantas rotas você deseja registrar?: ");
        String qntRotas = entrada.nextLine();
        System.out.println();
        return Integer.valueOf(qntRotas);
    }

    /**
     * Cria um array bidimensional[placa][rotas a serem salvas] adicionando a eles os automoveis já existentes no sistema
     * @param automoveisNoSistema automoveis salvos no sistema
     * @param qntRotasParaRegistro valor inteiro com a quantidade de rotas a serem salvas
     * @return array já com as placas dos automoveis já disponiveis no sistema
     */
    public static String[][] criarArrayRotas(String[] automoveisNoSistema, int qntRotasParaRegistro){
        int qntQuilometragemRegistradas = qntRotasParaRegistro;
        String arrayRotas[][] = new String [automoveisNoSistema.length][((qntRotasParaRegistro + qntQuilometragemRegistradas)) + 1];

        for (int i = 0; i < automoveisNoSistema.length; i++) {
            arrayRotas[i][0] = automoveisNoSistema[i];
        }

        return arrayRotas;
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


    /**
     * Regista as rotas e quilometragem
     * verifica se o automovel passado por parametro é igual ao automovel na primeira posiçao do array
     * percorre o subarray começando do segundo elemento (pois o primeiro é o automovel)
     * a partir daqui a array vai ser preenchida por outras duas arrays (rota e quilometragem)
     * se a posição da array principal (com exceção do primeiro elemento) for par é preenchida com uma rota
     * se for impar é preenchida com uma quilometragem
     * @param rotasSalvas todas a rotas salvas
     * @param automovel id do automovel escolhido para preenchimento de rota
     * @param rota array contendo a rota
     * @param quilometragem array contendo as quilometragens
     */
    // par = rota/ impar = quilometragem
    public static void registrarRotasIndividuais(String[][] rotasSalvas, String automovel, String[] rota, String[] quilometragem){
        int percorrerRota = 0;
        int percorrerQuilometragem = 0;
        int percorrer = 0;

        for(int i = 0; i < rotasSalvas.length; i++){
            if(automovel.equals(rotasSalvas[i][0])){
                    for(int j = 1; j < rotasSalvas[i].length; j++){
                        if(percorrer % 2 != 0){
                            rotasSalvas[i][j] = quilometragem[percorrerQuilometragem];
                            percorrerQuilometragem++;
                            percorrer++;
                            continue;
                        }
                        rotasSalvas[i][j] = rota[percorrerRota];
                        percorrerRota++;
                        percorrer++;
                }
            }
        }

    }
}
