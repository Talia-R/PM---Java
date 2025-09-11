public class CPF {
    private String cpf;

    public CPF(String cpf){
        if(!validarCPF(cpf))
            throw new IllegalArgumentException("CPF inválido");
        this.cpf = cpf;
    }

    public boolean validarCPF(String cpf){
        return cpf.matches("[0-9]{11}");
    }

    public String formatarCPF(){
        String[] cpfArray = cpf.split("");
        StringBuilder cpfFormatado = new StringBuilder();

        for(int i = 0; i < cpfArray.length; i ++){
            cpfFormatado.append(cpfArray[i]);

        if(i == 2 || i == 5){
            cpfFormatado.append(".");
        }

        if(i == 8){
            cpfFormatado.append("-");
        }
        }
        return cpfFormatado.toString();
    }
}
