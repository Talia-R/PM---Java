import java.text.NumberFormat;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner entrada = new Scanner(System.in);
        int escolha;

        System.out.println(cardapio());

        escolha = Integer.parseInt(entrada.nextLine());
        switch(escolha){
            case 1:
        }

        entrada.close();
    }

    public static String cardapio(){
        StringBuilder s = new StringBuilder();
        NumberFormat moeda = NumberFormat.getCurrencyInstance();
        s.append(" --- Bem-vindo(a) ao XulambsVeg! ---");
        s.append("\nCardápio: ");
        s.append("\nPizza: " + moeda.format(Pizza.getPrecoPadrao()));
        
        return s.toString();
    }

    public static String relatorio(){
        StringBuilder s = new StringBuilder();

        
        return s.toString();
    }
}
