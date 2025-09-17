public class Aluno {
    private String nome;
    private int matricula;
    private int codigoCurso;
    int[] cargaHoraria;

    public Aluno(int matricula, int[] cargaHorariaAcumulada){
        this.matricula = matricula;
        cargaHoraria = cargaHorariaAcumulada;
    }
}
