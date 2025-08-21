import java.util.LinkedList;

public class teste {
    public static void main(String[] args) {
        String[][] arrayUm = {{"ola", "tudo"}, {"bem","tchau"}};
        
        for(int i =0; i < arrayUm.length; i++){
            for(int j = 0; j < arrayUm[i].length; i++){
            System.out.println(arrayUm[i][j]);
            }
        }

        LinkedList<String> lista = new LinkedList<>();
        
    }
}


/*
 * ["mg para sp", 120k]
["sp para ma", 110k]

rotas[trajeto][km]

-> registrar as rotas
-> associar rotas a um veiculo

lerRota -> guardarRota no Array -> printar veiculos disponiveis -> dar a opção da rota 

- quantas rotas quer registrar para o veiculo XX

- digite a rota
- digite a quilometragem

[[X, rota, km, rota, km], ]

array de lista

veiculo -> placa, consumoMedioAutomovel

rota -> quilometragem, trajeto, 

Array[]

List<list<>

[[]]
 */