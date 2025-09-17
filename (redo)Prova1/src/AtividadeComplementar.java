/*
 * para passar o aluno tem que ter 10 creditos
 * existem 3 tipos de atvComp: profissional, estagio, extensionista
 * 1 ponto é gerado a cada: 30h, 45h, 60h
 * cada atvComple pode fornecer no max 4 creditos
 * cada aluno só pode fazer até 2 tipos de atividade complementar
 * classe aluno: nome, matricula, codigoCurso
 * os creditos tem que ser numeros inteiros
 */

import java.util.Arrays;

public class AtividadeComplementar {
    private static String[] TIPO = {"Profissional","Estágio","Extensionista"};
    private static int[] cargaPorTipo = {30, 45, 60};
    private static int MAX_ATV_COMPLEMENTAR = 2;
    private static int MAX_CREDITOS_POR_ATIVIDADE = 4;
    private static int MIN_CREDITOS_APROVACAO = 10;
    private String descricao;


    private Aluno aluno;
    // private int[] cargaHorariaAluno = aluno.cargaHoraria;
    private int[] cargaHorariaAluno = new int[MAX_ATV_COMPLEMENTAR + 1];

    public AtividadeComplementar(int[] valores){
        cargaHorariaAluno = valores;
    }

    /**
     * Calcula quantas atividades complementares tem registrado.
     * @return um inteiro com o número de atividades registradas.
     */
    private int calcularQntosTiposAtvCompFez(){
        int atvComp = 0;
        for(int i = 0; i < cargaHorariaAluno.length; i++){
            if(cargaHorariaAluno[i] > 0)   
                atvComp++;
        }
        return atvComp;
    }


    /**
     * Valida se o aluno fez atividade complementar.
     * Confere se pelo menos UMA atividade foi feita e se o número máximo de atividade por aluno foi respeitada.
     * @return true caso o aluno tenha feito pelo menos uma atividade e menos do que o máximo permitido.
     */
    private boolean fezAtvComplementar(){
        return calcularQntosTiposAtvCompFez() > 0 && calcularQntosTiposAtvCompFez() <= MAX_ATV_COMPLEMENTAR;
    }

    /**
     * Calcula a quantidade de créditos gerados por uma atividade complementar.
     * @return array com quantidade de creditos gerados por um atv complementar.
     */
    private int[] calcularCreditos(){
        if(fezAtvComplementar()){
            int[] creditos = new int [MAX_ATV_COMPLEMENTAR];
            int j = 0;
            for(int i = 0; i < cargaHorariaAluno.length; i++){
                if(cargaHorariaAluno[i] > 0){
                    creditos[j] = Math.round(cargaHorariaAluno[i] / cargaPorTipo[i]);
                    if(creditos[j] > MAX_CREDITOS_POR_ATIVIDADE)
                        creditos[j] = MAX_CREDITOS_POR_ATIVIDADE;
                    j++;
                }
            }   
            return creditos;
        }
        return null;
    }

    public int somarCreditosTotais(){
        int creditos = 0;
        int[] creditosAcumulados = calcularCreditos();
        for (int i = 0; i < creditosAcumulados.length; i++) {
            creditos += creditosAcumulados[i];
        }
        return creditos;
    }
    /**
     * Calcula a carga horária por atividade feita.
     * @return array de inteiros com as horas de cada atividade feita.
     */
    private int[] calcularCargaHorariaPorAtv(){
        int[] cargaHorariaPorAtv = new int[MAX_ATV_COMPLEMENTAR];
        int j = 0;
        for(int i = 0; i < cargaHorariaAluno.length; i++){
            if(cargaHorariaAluno[i] > 0){
                cargaHorariaPorAtv[j] = cargaHorariaAluno[i];
                j++;
            }
        }
        return cargaHorariaPorAtv;
    }

    /**
     * Calcula se o aluno tem créditos suficientes para se formar
     * @return true caso o aluno tenha creditos suficientes
     */
    public boolean alunoTemCreditoSuficiente(){
        return somarCreditosTotais() >= MIN_CREDITOS_APROVACAO;
    }

    /**
     * Verifica qual o tipo de atividade complementar o aluno cursou.
     * 0 - Profissional | 1 - Estágio | 3 - Extensionista
     * @return uma string contendo os dois tipos de atividade que o aluno cursou
     */
    private String[] verificarQualAtvCompCursada(){
        String[] cursou = new String[MAX_ATV_COMPLEMENTAR];
        int j = 0;
        for (int i = 0; i < cargaHorariaAluno.length; i++) {
            if(cargaHorariaAluno[i] > 0){
                cursou[j] = TIPO[i];
                j++;
            }
        }
        return cursou;
    }

    /**
     * Calcula a carga horária de todas as atividades cursadas
     * @return inteiro com a soma da carga horária
     */
    private int somarCargaHorariaCursada(){
        int cargaTotal = 0;
        for (int horario : cargaHorariaAluno) {
            cargaTotal += horario;
        }
        return cargaTotal;
    }


    public String relatorio(){
        StringBuilder s = new StringBuilder();
        s.append(" --- Atividades Complementares --- \n");

        // s.append(String.format("Cursou: %s", String.join(", " ,verificarQualAtvCompCursada())));
        // s.append(String.format("\nCarga horária por atividade: " ));
        // int[] cargaPorAtv = calcularCargaHorariaPorAtv();
        // for (int i = 0; i < MAX_ATV_COMPLEMENTAR; i++) {
        //     s.append(cargaPorAtv[i]);
        //     if(i < (MAX_ATV_COMPLEMENTAR - 1))
        //         s.append(", ");
                
        // }
        // refatorando:

        s.append("(Curso: Carga Horária)\n");
        String[] atvCursada = verificarQualAtvCompCursada();
        int[] cargaPorAtv = calcularCargaHorariaPorAtv();

        for (int i = 0; i < MAX_ATV_COMPLEMENTAR; i++) {
            s.append(atvCursada[i]);
            s.append(": " + cargaPorAtv[i] + "h");
            if(i < (MAX_ATV_COMPLEMENTAR - 1))
                s.append(", ");
                
        }
        s.append(String.format("\nCarga horária total: %d", somarCargaHorariaCursada()));
        s.append(String.format("\nCréditos Totais: %d", somarCreditosTotais()));


        return s.toString();
    }
}
