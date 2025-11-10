package models;

public enum EEstrelas {
    UMA(1),
    DUAS(2),
    TRES(3),
    QUATRO(4),
    CINCO(5);

    private int qntEstrelas;
    private static int MAX_ESTRELAS = 5;

    private EEstrelas(int qntEstrelas){
        this.qntEstrelas = qntEstrelas;
    }

    public int getEstrelas(){
        return qntEstrelas;
    }

    public static int getMaxEstrelas(){
        return MAX_ESTRELAS;
    }

}
