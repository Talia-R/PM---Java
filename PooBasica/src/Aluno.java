import java.util.LinkedList;
import java.util.List;

public class Aluno {
    private static final int MAX_PONTOS_ATV = 10;
    private static final int MIN_APROVACAO = 6;
    private static final int MIN_REAVALIACAO = 4;

    List<Integer> todasAtvsAvaliativas = new LinkedList<>();

    private String matricula;
    private String nome;

    public Aluno(String matricula){
        if(!validarMatricula(matricula)){
            throw new IllegalArgumentException("Matricula incorreta");
        }

        this.matricula = matricula;
    }

    public Aluno(String matricula, String nome){
        if(!validarMatricula(matricula)){
            throw new IllegalArgumentException("Matricula incorreta");
        }

        if(validarNome(nome) == ""){
            throw new IllegalArgumentException("Nome inválido");
        }
        
        this.matricula = matricula;
        this.nome = nome;

    }

    public boolean validarMatricula(String matricula){
        if(matricula == null || matricula.isEmpty()){
            return false;
        }

        String matriculaFormatada = matricula.trim();

        return matriculaFormatada.matches("\\d+");
    }

    public String validarNome(String nome){
        if(nome == null || nome.trim().isEmpty() || nome.length() < 5){
            return "";
        }

        return nome.trim().toUpperCase();
    }

    public int somarNotas(List<Integer> todasAtvsAvaliativas){
        int notaTotal = 0;

        for(int i = 0; i < todasAtvsAvaliativas.size(); i++){
            notaTotal += todasAtvsAvaliativas.get(i);
        }
        return notaTotal;
    }

    // public double calcularMediaAluno(){
        
    // }

    public String verificarSituacao(){
        int pontuacaoFinal = (somarNotas(todasAtvsAvaliativas));

        if(pontuacaoFinal >= MIN_APROVACAO)
            return "Aprovado";
        else if(pontuacaoFinal > MIN_REAVALIACAO && pontuacaoFinal < MIN_APROVACAO)
            return "Em reavaliação";


        return "Reprovado";
    } 
}
